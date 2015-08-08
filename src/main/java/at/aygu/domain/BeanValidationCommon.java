package at.aygu.domain;

import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import at.aygu.constants.MessageKeys;
import at.aygu.constants.SpringBeanNames;

/**
 * Use this class to process bean validation.
 * @author guersel
 *
 */
@Component(SpringBeanNames.BeanValidationCommon)
public class BeanValidationCommon {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanValidationCommon.class);

    // Contains validation error messages
    @Resource
    private MessageSource validationMessageSource;

    private Validator validator;

    public BeanValidationCommon() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    /**
     * Makes bean validation. If any errors occur during validation put proper information into {@code errors} object.
     * @param obj bean to validate
     * @param errors contains error information
     */
    public final void validate(final Object obj, final Errors errors) {
        validate(obj, errors, Default.class);
    }
    
    /**
     * Just validate the given property of the object.
     * @param obj the object to validate
     * @param propertyName the property of the object to validate
     * @param errors list of validation errors
     */
    public final void validateProperty(final Object obj, final String propertyName, final Errors errors) {
    	Set<ConstraintViolation<Object>> constraints = validator.validateProperty(obj, propertyName);
    	
    	mapContraintToError(constraints, errors);
    }

    /**
     * Makes bean validation. If any errors occur during validation put proper information into {@code errors} object.
     * @param obj bean to validate
     * @param errors contains error information
     * @param group validation group
     */
    public final void validate(final Object obj, final Errors errors, final Class<?> group) {
    	Set<ConstraintViolation<Object>> constraints = validator.validate(obj, group);

        mapContraintToError(constraints, errors);    
    }
    
    /**
     * Maps the given list of constraints to proper error messages.
     * @param constraints list of constraint after bean validation
     * @param errors error information
     */
    private void mapContraintToError(final Set<ConstraintViolation<Object>> constraints, final Errors errors) {
    	for (ConstraintViolation<Object> constraint : constraints) {

            String messageKey = MessageKeys.DEFAULT_MESSAGE;
            if (StringUtils.isNotBlank(constraint.getMessageTemplate())) {
                // Bean validation message key contains '{' and '}'
                messageKey = constraint.getMessageTemplate().replaceAll("[\\{\\}]", "");
            }

            // Use locale specific validation message if exists otherwise fallback to default
            String message = validationMessageSource.getMessage(messageKey,
                    new Object[]{},
                    "Validation error occured.",
                    LocaleContextHolder.getLocale());

            errors.rejectValue(constraint.getPropertyPath().toString(), messageKey, message);
            LOGGER.warn("Bean validation (JSR 303) error '{}', validation message '{}'",
                                            constraint.getPropertyPath().toString(),
                                            message);
        }
    }
    
    public MessageSource getValidationMessageSource() {
        return validationMessageSource;
    }

}
