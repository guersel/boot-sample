package at.aygu.domain;

import javax.annotation.Resource;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import at.aygu.constants.MessageKeys;
import at.aygu.constants.SpringBeanNames;
import at.aygu.util.UserExtendedValidation;


/**
 * Validates an {@link User} domain object.
 */
@Component(SpringBeanNames.UserValidator)
public class UserValidator implements Validator {

    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(UserValidator.class);

    @Resource(name = SpringBeanNames.BeanValidationCommon)
    private BeanValidationCommon beanValidation;
    
    @Resource
    private MessageSource validationMessageSource;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    /**
     * Validates the given {@link User} domain object.
     */
    @Override
    public void validate(Object obj, Errors errors) {    	
        User user = (User) obj;

        validateUsername(user, errors);
        
        validateEmail(user, errors);
        
        validatePassword(user, errors);
    }
    
    /**
     * Validate the password property of the user object.
     * @param user the user object
     * @param errors list of validation errors
     */
    private void validatePassword(User user, Errors errors) {
		if (StringUtils.isBlank(user.getPassword())) {
			errorMessageFromProperties(MessageKeys.USER_PASSWORD_NOT_BLANK, User.PROPERTY_PASSWORD, errors);
		} else if (user.getPassword().length() < 6) {
			errorMessageFromProperties(MessageKeys.USER_PASSWORD_LENGTH, User.PROPERTY_PASSWORD, errors);
		}
		
		if (StringUtils.isBlank(user.getPasswordConfirmation())) {
			errorMessageFromProperties(MessageKeys.USER_PASSWORD_CONFIRMATION_NOT_BLANK, User.PROPERTY_PASSWORD_CONFIRMATION, errors);
		}
		
		if (errors.hasFieldErrors(User.PROPERTY_PASSWORD) || errors.hasFieldErrors(User.PROPERTY_PASSWORD_CONFIRMATION)) {
			return;
		}
				
		// Check if password and password confirmation matches
        if (!user.getPassword().equals(user.getPasswordConfirmation()) && !errors.hasFieldErrors(User.PROPERTY_PASSWORD_CONFIRMATION)) {
        	errorMessageFromProperties(MessageKeys.USER_PASSWORD_CONFIRMATION_NOT_MATCH, User.PROPERTY_PASSWORD_CONFIRMATION, errors);
        }
	}

    /**
     * Validates the email property of the user object.
     * @param user the user object
     * @param errors list of validation errors
     */
	private void validateEmail(User user, Errors errors) {
		if (StringUtils.isBlank(user.getEmail())) {
			errorMessageFromProperties(MessageKeys.USER_EMAIL_NOT_BLANK, User.PROPERTY_EMAIL, errors);
		}
		
		if (errors.hasFieldErrors(User.PROPERTY_EMAIL)) {
			return;
		}
		
		beanValidation.validateProperty(user, User.PROPERTY_EMAIL, errors);
	}

	/**
	 * Validates the username property of the user object.
	 * @param user the user object
	 * @param errors list of validation errors
	 */
	private void validateUsername(final User user, final Errors errors) {
    	if (StringUtils.isBlank(user.getUsername())) {
        	errorMessageFromProperties(MessageKeys.USER_USERNAME_NOT_BLANK, User.PROPERTY_USERNAME, errors);
        }
    	
    	if (errors.hasFieldErrors(User.PROPERTY_USERNAME)) {
    		return;
    	}
    	
    	if (user.getUsername().length() < 5) {
    		errorMessageFromProperties(MessageKeys.USER_USERNAME_LENGTH, User.PROPERTY_USERNAME, errors);
    	}
    }
	
	/**
	 * Get a validation error message from the property file and put it into the given error object.
	 * @param messageKey the key of the validation error message 
	 * @param propertyName the name of the property for which the validation failed
	 * @param errors error object containing the validation errors
	 */
	private void errorMessageFromProperties(final String messageKey, final String propertyName, final Errors errors) {
		String message = validationMessageSource.getMessage(messageKey,
                new Object[]{},
                LocaleContextHolder.getLocale());

    	errors.rejectValue(propertyName, messageKey, message);
	}
}
