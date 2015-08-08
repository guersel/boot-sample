package at.aygu.db.repository;

import org.springframework.data.repository.CrudRepository;

import at.aygu.db.entity.RoleTable;

public interface RoleRepository extends CrudRepository<RoleTable, Long> {
	
	public RoleTable findByName(final String name);
}
