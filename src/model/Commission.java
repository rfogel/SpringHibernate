/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Fogel
 */

@Entity
@Table(name = "commission")
public class Commission implements Serializable, ModelPersistenty
{
	private static final long serialVersionUID = 3183592673214134048L;
	
	private User president;
	private User firstMember;
	private User secondMember;
	
	@Id
	@OneToOne
	@JoinColumn(name="idPresident")
	public User getPresident() {
		return president;
	}
	public void setPresident(User president) {
		this.president = president;
	}
	
	@Id
	@OneToOne
	@JoinColumn(name="idFirstMember")
	public User getFirstMember() {
		return firstMember;
	}
	public void setFirstMember(User firstMember) {
		this.firstMember = firstMember;
	}
	
	@Id
	@OneToOne
	@JoinColumn(name="idSecondMember")
	public User getSecondMember() {
		return secondMember;
	}
	public void setSecondMember(User secondMember) {
		this.secondMember = secondMember;
	}

}
