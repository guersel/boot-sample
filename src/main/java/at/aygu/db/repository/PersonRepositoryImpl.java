package at.aygu.db.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import at.aygu.db.entity.PersonTable;
import at.aygu.db.entity.QPersonTable;

import com.mysema.query.jpa.impl.JPAQuery;

/**
 * Access to table {@link PersonTable}.
 * @author guersel
 *
 */
@Repository
public class PersonRepositoryImpl implements PersonRepository {

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Save the given person.
	 * @param personTable person data
	 */
	@Override
	public final void save(final PersonTable userTable) {
		em.persist(userTable);
	}
	
	/**
	 * Find a person by username or email.
	 * @param username the username of the person
	 * @param email the email of the person
	 * @return person data if found otherwise null
	 */
	@Override
	public final PersonTable findByUsernameOrEmail(final String username, final String email) {
		QPersonTable personTable = QPersonTable.personTable;
		JPAQuery query = new JPAQuery(em);
		return query.from(personTable)
				.where(personTable.username.equalsIgnoreCase(username)
				.or(personTable.email.equalsIgnoreCase(email)))
				.uniqueResult(personTable);
	}

	/**
	 * Find a person by username or email. The user must also be enabled and confirmed.
	 * @param username the username of the person
	 * @param email the email address of the person
	 * @return person data if found otherwise null
	 */
	@Override
	public final PersonTable findActive(final String username, final String email) {
		QPersonTable personTable = QPersonTable.personTable;
		JPAQuery query = new JPAQuery(em);
		return query.from(personTable)
				.where(personTable.username.equalsIgnoreCase(username)
				.or(personTable.email.equalsIgnoreCase(email))
				.and(personTable.enabled.isTrue())
				.and(personTable.confirmed.isTrue()))
				.uniqueResult(personTable);						
	}

	

}
