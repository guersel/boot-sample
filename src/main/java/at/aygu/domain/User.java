package at.aygu.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	/** Property username. **/
	public static final String PROPERTY_USERNAME = "username";
	
	/** Property email. **/
	public static final String PROPERTY_EMAIL = "email";
	
	/** Property password. **/
	public static final String PROPERTY_PASSWORD = "password";
	
	/** Name of the property {@link #getPasswordConfirmation()} . **/
	public static final String PROPERTY_PASSWORD_CONFIRMATION = "passwordConfirmation";
	
	private long id;
		
	private String username;
	
    @Email(message = "{user.email.Format}")
	private String email;
	
	private String password;
	
	private String passwordConfirmation;
	
	private boolean enabled;
	
	private List<Authority> roles;
	
	public void addAuthority(final Authority role) {
		getAuthorities().add(role);
	}
	
	@Override
	public Collection<Authority> getAuthorities() {
		if (roles == null) {
			roles = new ArrayList<>();
		}
		return roles;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public List<Authority> getRoles() {
		return roles;
	}

	public void setRoles(List<Authority> roles) {
		this.roles = roles;
	}

}
