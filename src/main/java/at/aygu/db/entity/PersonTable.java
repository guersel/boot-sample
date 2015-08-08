package at.aygu.db.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Table person.
 * @author guersel
 *
 */
@Entity
@Table(name = "person")
@SequenceGenerator(name="person_id_seq", sequenceName="person_id_seq", allocationSize = 50)
public class PersonTable implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** Row id. **/
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_id_seq")
	private Long id;
	
	/** Username. **/
	private String username;
	
	/** Email address. **/
	private String email; 
	
	/** Encrypted password. **/
	@Column(name="encrypted_password")
	private String encryptedPassword;
	
	/** Specifies if this person is enabled in the system. **/
	private boolean enabled;
	
	/** Confirm registration. **/
	private boolean confirmed;
	
	/** Roles of the person. **/
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "person_role",
				joinColumns = {@JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false, table = "person")},
				inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false, table = "role")})
	private List<RoleTable> roles;
	
	
	public List<RoleTable> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleTable> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	
}
