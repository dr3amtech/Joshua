package com.spring.mvc.pojo;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.spring.mvc.jsonview.Views;
@Entity
@Table(name="Appointment")
public class Appointment {
	public Appointment() {}
	


	public Appointment(Long id, String description, String appointmentDate, String time,
			String appointmentDateS, String timeS) {
		super();
		this.id = id;
		this.description = description;
		this.appointmentDate = appointmentDate;
		this.time = time;
		this.appointmentDateS = appointmentDateS;
		this.timeS = timeS;
	}
	

	public Appointment(String description, String appointmentDate,String appointmentDateS, String timeS) {
		super();
		this.description = description;
		this.appointmentDateS = appointmentDateS;
		this.timeS = timeS;
	}


	@Id
	@SequenceGenerator(name="generator",sequenceName="MY_SEQ")
	@GeneratedValue(generator="generator",strategy=GenerationType.SEQUENCE)
	@Column(name="Id",updatable=false,nullable=false,insertable=true)
	private Long id;

	@Column
	@JsonView(Views.Public.class)
	private String description;
	@Column
	@JsonView(Views.Public.class)
	private String appointmentDate;
	@Column
	@JsonView(Views.Public.class)
	private String time;
	@Column
	@JsonView(Views.Public.class)
	private String appointmentDateS;
	@Column
	@JsonView(Views.Public.class)
	private String timeS;
	
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
		appointmentDateS = appointmentDate.toString();
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
		timeS = time.toString();
	}
	public String getTimes() {
		return timeS;
	}








	@Override
	public String toString() {
		return "Appointment [id=" + id + ", description=" + description + ", appointmentDate=" + appointmentDate
				+ ", time=" + time + ", appointmentDateS=" + appointmentDateS + ", timeS=" + timeS + "]";
	}

	


}
