package at.aygu.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import at.aygu.domain.User;

/**
 * Auxiliary methods to handle security issues.
 * @author guersel
 *
 */
@Component(at.aygu.constants.SpringBeanNames.SecurityUtil)
public class SecurityUtil {
	
	/**
	 * @return the username of the logged in user
	 */
	public String getCurrentUsername() {
		User user = getCurrentUser();
		
		if (user == null) {
			return null;
		}
		
		return user.getUsername();
	}
	
	/**
	 * @return the {@link User} object containing the logged in user
	 */
	public User getCurrentUser() {	
		if (!isSecurityContext()) {
			return null;
		}
		
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	/**
	 * @return true if a security context exists otherwise false
	 */
	public boolean isSecurityContext() {
		return SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null;
	}
}
