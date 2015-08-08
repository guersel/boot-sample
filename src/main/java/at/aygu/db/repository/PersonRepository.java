package at.aygu.db.repository;

import at.aygu.db.entity.PersonTable;



/**
 * Access to table {@link PersonTable}.
 * @author guersel
 *
 */
public interface PersonRepository {

	/**
	 * Save the given person.
	 * @param personTable person data
	 */
	void save(final PersonTable personTable);
	
	/**
	 * Find a person by username or email.
	 * @param username the username of the person
	 * @param email the email address of the person
	 * @return person data if found otherwise null
	 */
	PersonTable findByUsernameOrEmail(final String username, final String email);
	
	/**
	 * Find a person by username or email. The user must also be enabled and confirmed.
	 * @param username the username of the person
	 * @param email the email address of the person
	 * @return person data if found otherwise null
	 */
	PersonTable findActive(final String username, final String email);
}
