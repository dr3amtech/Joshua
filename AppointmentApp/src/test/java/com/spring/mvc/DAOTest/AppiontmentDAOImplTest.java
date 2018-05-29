package com.spring.mvc.DAOTest;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.spring.mvc.DAO.AppointmentDAO;
import com.spring.mvc.DAO.AppointmentDAOImpl;
import com.spring.mvc.pojo.Appointment;



public class AppiontmentDAOImplTest {
	
	
	
	//@Ignore
	@Test
	public void TestAddAppointmentTOSchedule() {
		String ld =  LocalDateTime.now().toString();
		AppointmentDAO aptimpl = new AppointmentDAOImpl();
		Appointment oneIndex1 =new Appointment(1L,"Appointment Description1",ld,ld,ld.toString(),ld.toString());
		aptimpl.addAppointmentTOSchedule(oneIndex1);
	}
	@Test 
	public void findAllAppointments() {
		AppointmentDAO apt = new AppointmentDAOImpl();
		System.out.println(apt.findAllAppointments());
	}
	
	
	@Test
	public void TestTimeOpenAndAfterCurrentTime(){
		
		
		AppointmentDAO apt = new AppointmentDAOImpl();
		boolean valid = apt.timeOpenAndAfterCurrentTime("2018-06-01T13:00");
		assertTrue(valid);
	}
	
	
	@Test
	public void findAllRelatedAppointments() {
		AppointmentDAO appt = new AppointmentDAOImpl();
		List<Appointment> appts= new ArrayList<>();
		appts= appt.findAllRelatedAppointments("Late");
		System.out.println(appts);
	}

}
