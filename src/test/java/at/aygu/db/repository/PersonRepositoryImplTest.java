package at.aygu.db.repository;

import java.util.Arrays;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.dbunit.DataSourceDatabaseTester;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import at.aygu.db.entity.PersonTable;
import at.aygu.db.entity.RoleTable;
import at.aygu.domain.Authority;
import at.aygu.test.config.TestConfiguration;
import at.aygu.test.helper.DBUnitHelper;

/**
 * Test the class {@link PersonRepository}.
 * @author guersel
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestConfiguration
public class PersonRepositoryImplTest {
	
	@Resource
	private PersonRepository personRepo;
	
	@Resource
	private RoleRepository roleRepo;
	
	@Resource
	private DataSourceDatabaseTester dbTester;
	
	/**
	 * Find person by username and email.
	 */
	@Test
	public void findUserByUsernameAndEmail() throws Exception {
		try {
			DBUnitHelper.initDBWithFixtures(dbTester, "fixtures/db/PersonRepositoryImplTest_findUser.xml");
			
			// Find the user by username
			PersonTable personByUsername = personRepo.findByUsernameOrEmail("test", "test");
			Assert.assertNotNull(personByUsername);
			Assert.assertNotNull(personByUsername.getRoles());
			Assert.assertEquals(1, personByUsername.getRoles().size());
			
			// Find the user by email
			PersonTable personByEmail = personRepo.findByUsernameOrEmail("test@gmx.at", "test@gmx.at");
			Assert.assertNotNull(personByEmail);
			Assert.assertNotNull(personByEmail.getRoles());
			Assert.assertEquals(1, personByEmail.getRoles().size());
			
		} finally {
			DBUnitHelper.resetDB(dbTester);
		}
	}
	
	/**
	 * Find enabled and confirmed person by username or email.
	 */
	@Test
	public void findActive() throws Exception {
		try {
			DBUnitHelper.initDBWithFixtures(dbTester, "fixtures/db/PersonRepositoryImplTest_findActive.xml");
			
			PersonTable person = personRepo.findActive("test", "test@gmx.at");
			Assert.assertNotNull(person);			
		} finally {
			DBUnitHelper.resetDB(dbTester);
		}
	}
	
	/**
	 * Try to find disabled person by username or email.
	 */
	@Test
	public void tryToFindInactive() throws Exception {
		try {
			DBUnitHelper.initDBWithFixtures(dbTester, "fixtures/db/PersonRepositoryImplTest_tryToFindInactive.xml");
			
			PersonTable person = personRepo.findActive("test", "test@gmx.at");
			Assert.assertNull(person);			
		} finally {
			DBUnitHelper.resetDB(dbTester);
		}
	}
	
	/**
	 * Save a person with role and find it by username or email.
	 */
	@Test
	@Transactional
	public void saveAndFindUser() {
		PersonTable userTable = new PersonTable();
		userTable.setEmail("test");
		userTable.setEnabled(true);
		userTable.setUsername("test");
		userTable.setEncryptedPassword("encrypted password");
		
		RoleTable role = roleRepo.findByName(Authority.USER.getAuthority());
		userTable.setRoles(Arrays.asList(role));
		
		personRepo.save(userTable);
		
		PersonTable savedUser = personRepo.findByUsernameOrEmail(userTable.getUsername(), userTable.getEmail());
		
		Assert.assertNotNull(savedUser);
		Assert.assertNotNull(savedUser.getId());
		Assert.assertNotNull(savedUser.getRoles());
		Assert.assertEquals(1, savedUser.getRoles().size());
	}
	
}
