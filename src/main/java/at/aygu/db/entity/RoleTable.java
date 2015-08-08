package at.aygu.db.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Table role.
 * @author guersel
 *
 */
@Entity
@Table(name = "role")
@SequenceGenerator(sequenceName = "role_id_seq", name = "role_id_seq")
public class RoleTable implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** Row id. **/
	@Id	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_seq")
	private long id;
	
	/** Name of the role. **/
	private String name;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}
	
}
