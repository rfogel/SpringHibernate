/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

/**
 *
 * @author Fogel
 */

@Entity
@Table(name = "user")
public class User implements ModelPersistenty
{
	private Integer userId;
	private Login login;
	private String name;
	private String email;
	private String projectName;
	private Date projectExpireDate;
	private Role role;	
	private UserType userType;
	private List<Order> orders;

	@OneToMany(targetEntity=Order.class, mappedBy="author", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@OneToOne(cascade=CascadeType.ALL)
	@Cascade( {org.hibernate.annotations.CascadeType.DELETE} )
	@JoinColumn(name="loginId")
	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	
	@OneToOne
	@JoinColumn(name="roleId")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@OneToOne
	@JoinColumn(name="userTypeId")
	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getProjectExpireDate() {
		return projectExpireDate;
	}

	public void setProjectExpireDate(Date projectExpireDate) {
		this.projectExpireDate = projectExpireDate;
	}
}
