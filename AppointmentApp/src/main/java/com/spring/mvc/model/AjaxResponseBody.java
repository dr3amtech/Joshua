package com.spring.mvc.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.spring.mvc.jsonview.Views;
import com.spring.mvc.pojo.Appointment;
import com.spring.mvc.pojo.User;
public class AjaxResponseBody {

	@JsonView(Views.Public.class)
	String msg;
	@JsonView(Views.Public.class)
	String code;
	@JsonView(Views.Public.class)
	List<User> result;
	@JsonView(Views.Public.class)
	List<Appointment> appointmentResults;
	@JsonView(Views.Public.class)
	Appointment appointment;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<User> getResult() {
		return result;
	}

	public void setResult(List<User> result) {
		this.result = result;
	}
	

	public List<Appointment> getAppointmentResults() {
		return appointmentResults;
	}

	public void setAppointmentResults(List<Appointment> appointmentResults) {
		this.appointmentResults = appointmentResults;
	}
	

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	@Override
	public String toString() {
		return "AjaxResponseBody [msg=" + msg + ", code=" + code + ", result=" + result + ", appointmentResults="
				+ appointmentResults + ", appointment=" + appointment + "]";
	}

	
	

}
