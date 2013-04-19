package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "financing")
public class Financing implements ModelPersistenty
{
	private Integer financingId;
	private String name;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getFinancingId() {
		return financingId;
	}
	public void setFinancingId(Integer financingId) {
		this.financingId = financingId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
}
