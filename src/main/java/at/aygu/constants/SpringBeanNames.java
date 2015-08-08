package at.aygu.constants;

/**
 * Contains bean names.
 */
public final class SpringBeanNames {
	
	// Utility beans
	public static final String SecurityUtil = "securityUtil";
	
	// Validator beans
	public static final String BeanValidationCommon = "beanValidationCommon";
    public static final String UserValidator = "userValidator";
    
    // Security beans
    public static final String SampleAuthenticationFailureHandler = "sampleAuthenticationFailureHandler";
    
    // Service beans
    public static final String PersonServiceImpl = "personServiceImpl";
       
    
    // Disable default constructor
    private SpringBeanNames() {}
}
