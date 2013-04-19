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
@Table(name = "userType")
public class UserType implements ModelPersistenty
{
	private Integer userTypeId;
	private String type;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}	
}
