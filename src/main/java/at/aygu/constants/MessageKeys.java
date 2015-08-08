package at.aygu.constants;

/**
 * Contains message keys.
 */
public final class MessageKeys {

    // Defines the default message, should be provided in *.properties message files
    public static final String DEFAULT_MESSAGE = "default.message";
    
    // Login failed
    public static final String SIGNIN_LOGIN_FAILED = "signin.loginFailed";

    // Validation message keys
    public static final String USER_USERNAME_NOT_BLANK = "user.username.NotBlank";
    public static final String USER_USERNAME_LENGTH = "user.username.Length";
    public static final String USER_EMAIL_NOT_BLANK = "user.email.NotBlank";
    public static final String USER_PASSWORD_NOT_BLANK = "user.password.NotBlank";
    public static final String USER_PASSWORD_LENGTH = "user.password.Length";
    public static final String USER_PASSWORD_CONFIRMATION_NOT_BLANK = "user.passwordConfirmation.NotBlank";
    public static final String USER_PASSWORD_CONFIRMATION_NOT_MATCH = "user.password.confirmation.NotMatch";    
    public static final String USER_NOT_UNIQUE = "user.not.unique";
    public static final String USER_BADCREDENTIALS = "user.badcredentials";

    private MessageKeys(){}
}
