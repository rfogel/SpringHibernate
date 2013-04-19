package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
*
* @author Fogel
*/

@Entity
@Table(name = "role")
public class Role implements ModelPersistenty
{
	private Integer roleId;
	private String name;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
}
