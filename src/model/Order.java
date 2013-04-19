/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Fogel
 */

@Entity
@Table(name = "orders")
public class Order implements ModelPersistenty
{
	private Integer orderId;
	private String name;
	private String conferenceName;
	private Qualification qualification;
	private Financing financing;
	private Integer numberOfArticles;
	private User author;
	private Quota quota;
	private Status status;
	private String statusString;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getOrderId() {
		return orderId;
	}
	
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getConferenceName() {
		return conferenceName;
	}
	public void setConferenceName(String conferenceName) {
		this.conferenceName = conferenceName;
	}
	
	@OneToOne
	@JoinColumn(name="qualificationId")
	public Qualification getQualification() {
		return qualification;
	}
	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}
	
	@OneToOne
	@JoinColumn(name="financingId")
	public Financing getFinancing() {
		return financing;
	}
	public void setFinancing(Financing financing) {
		this.financing = financing;
	}
	
	public Integer getNumberOfArticles() {
		return numberOfArticles;
	}
	public void setNumberOfArticles(Integer numberOfArticles) {
		this.numberOfArticles = numberOfArticles;
	}

	@ManyToOne
	@JoinColumn(name="userId")
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	
	@OneToOne
	@JoinColumn(name="quotaId")
	public Quota getQuota() {
		return quota;
	}
	public void setQuota(Quota quota) {
		this.quota = quota;
	}
	
	@OneToOne
	@JoinColumn(name="statusId")
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Transient
	public String getStatusString() {
		return statusString;
	}
	
	public void setStatusString(String statusString) {
		this.statusString = statusString;
	}
}
