package mhdsys.sports.coaching.wing.dto;

import java.util.Date;

public class ServantDTO {
	
	//private long tr
	private long trainingCentreId;
	private long servantId;
	private String name;
	private String surname;
	private String address;
	private Date dob;
	private String dobstr;
	private Date joiningDate;
	private String dojstr;
	private Date createDate;
	private Date modifiedDate;
	private String formStatus;
	
	public ServantDTO(long trainingCentreId, long servantId, String name, String surname, String address, Date dob,
			Date joiningDate, Date createDate, Date modifiedDate, String formStatus) {
		super();
		this.trainingCentreId = trainingCentreId;
		this.servantId = servantId;
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.dob = dob;
		this.joiningDate = joiningDate;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
		this.formStatus = formStatus;
	}
	
	
	public ServantDTO() {
	}
	public long getTrainingCentreId() {
		return trainingCentreId;
	}
	public void setTrainingCentreId(long trainingCentreId) {
		this.trainingCentreId = trainingCentreId;
	}
	public long getServantId() {
		return servantId;
	}
	public void setServantId(long servantId) {
		this.servantId = servantId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Date getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getFormStatus() {
		return formStatus;
	}
	public void setFormStatus(String formStatus) {
		this.formStatus = formStatus;
	}


	public String getDobstr() {
		return dobstr;
	}


	public void setDobstr(String dobstr) {
		this.dobstr = dobstr;
	}


	public String getDojstr() {
		return dojstr;
	}


	public void setDojstr(String dojstr) {
		this.dojstr = dojstr;
	}


	@Override
	public String toString() {
		return "ServantDTO [trainingCentreId=" + trainingCentreId + ", servantId=" + servantId + ", name=" + name
				+ ", surname=" + surname + ", address=" + address + ", dob=" + dob + ", dobstr=" + dobstr
				+ ", joiningDate=" + joiningDate + ", dojstr=" + dojstr + ", createDate=" + createDate
				+ ", modifiedDate=" + modifiedDate + ", formStatus=" + formStatus + "]";
	}
	
}	
