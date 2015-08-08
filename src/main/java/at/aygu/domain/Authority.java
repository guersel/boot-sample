package at.aygu.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;

/**
 * Role of user.
 * @author guersel
 *
 */
public enum Authority implements GrantedAuthority {

	USER("ROLE_USER");
	
	private String value;
	
	private Authority(final String value) {
		this.value = value;
	}
	
	public static Authority findAuthority(final String value) {
		Optional<Authority> optional = Arrays.asList(Authority.values())
											.stream()
											.filter(a -> a.getAuthority().equals(value))
											.findFirst();
		
		if (!optional.isPresent()) {
			throw new IllegalArgumentException("No authority with value " + value + " found");
		}
		
		return optional.get();
	}
	
	public static List<Authority> findAuthorities(final List<String> values) {
		return Arrays.asList(Authority.values())
			.stream()
			.filter(a -> values.contains(a.getAuthority())).collect(Collectors.toList());
	}
	
	@Override
	public String getAuthority() {
		return value;
	}
	
	public void setAuthority(final String authority) {
		this.value = authority;
	}
	

}
