package com.spring.mvc.DAO;

import java.util.List;


import com.spring.mvc.pojo.Appointment;

public interface AppointmentDAO {
	
	
	
	public boolean timeOpenAndAfterCurrentTime(String time);
	public List<Appointment> findAllRelatedAppointments(String search);
	public List<Appointment> findAllAppointments();
	public void addAppointmentTOSchedule(Appointment app);
	
	
}
