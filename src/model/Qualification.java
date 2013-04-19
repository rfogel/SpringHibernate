package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "qualification")
public class Qualification implements ModelPersistenty
{
	private Integer qualificationId;
	private String type;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getQualificationId() {
		return qualificationId;
	}
	public void setQualificationId(Integer qualificationId) {
		this.qualificationId = qualificationId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
