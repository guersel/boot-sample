package at.aygu.service;

import at.aygu.domain.User;

/**
 * Person related services.
 * @author guersel
 *
 */
public interface PersonService {
	
	/**
	 * Save the given user.
	 * @param user {@link User} to save
	 */
	public void savePerson(final User user);
	
	/**
	 * Check if the given user with the username or email address still exists.
	 * @param user {@link User} to check
	 * @return true if the user exists otherwise false
	 */
	public boolean existsUser(final User user);
}
