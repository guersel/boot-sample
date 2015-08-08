package at.aygu.service;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import at.aygu.constants.SpringBeanNames;
import at.aygu.db.entity.RoleTable;
import at.aygu.db.entity.PersonTable;
import at.aygu.db.repository.RoleRepository;
import at.aygu.db.repository.PersonRepository;
import at.aygu.domain.Authority;
import at.aygu.domain.User;

/**
 * Person related services.
 * @author guersel
 *
 */
@Service(SpringBeanNames.PersonServiceImpl)
public class PersonServiceImpl implements PersonService, UserDetailsService {

	@Resource
	private PersonRepository userRepo;
	
	@Resource
	private RoleRepository roleRepo;
	
	@Resource
    private BCryptPasswordEncoder bcryptEncoder;
	
	/**
	 * Find an user with the given username.
	 * The given <code>username</code> must match either the username or email address of the user.
	 * This method is needed for authorization purpose by the spring container.
	 * @param username the username of the user to find
	 * @return user information
	 * @throws UsernameNotFoundException if the username was not found
	 */
	@Override
	public final UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		PersonTable userTable = userRepo.findByUsernameOrEmail(username, username);
		
		if (userTable == null) {
			String errorMessage = MessageFormat.format("Requested user {0} could not be found", username);
			throw new UsernameNotFoundException(errorMessage);
		}
		
		User user = new User();
		user.setUsername(userTable.getUsername());
		user.setEmail(userTable.getEmail());
		user.setPassword(userTable.getEncryptedPassword());
		user.setEnabled(userTable.isEnabled());
		
		List<String> roleNames = userTable.getRoles().stream()
			.map(role -> role.getName())
			.collect(Collectors.toList());
		
		user.getAuthorities().addAll(Authority.findAuthorities(roleNames));	
		
		return user;
	}
	
	/**
	 * Save the given user. The user is still inactive until the user confirms the URL in the registration email.
	 * @param user {@link User} to save
	 */
	@Override
	@Transactional
	public final void savePerson(final User user) {
		PersonTable userTable = new PersonTable();
		userTable.setUsername(user.getUsername());
		userTable.setEmail(user.getEmail());
		
		String encodedPassword = bcryptEncoder.encode(user.getPassword());
		userTable.setEncryptedPassword(encodedPassword);
		userTable.setEnabled(true);
		
		// User must confirm the registration
		userTable.setConfirmed(false);
		
		RoleTable role = roleRepo.findByName(Authority.USER.getAuthority());
		userTable.setRoles(Arrays.asList(role));
		
		userRepo.save(userTable);
	}

	/**
	 * Check if the given user with the username or email address still exists.
	 * @param user {@link User} to check
	 * @return true if the user exists otherwise false
	 */
	@Override
	public final boolean existsUser(final User user) {
		PersonTable userData = userRepo.findByUsernameOrEmail(user.getUsername(), user.getEmail());
		
		return userData != null;
	}	
	
}
