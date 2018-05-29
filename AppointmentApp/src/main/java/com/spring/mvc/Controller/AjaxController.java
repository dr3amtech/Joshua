package com.spring.mvc.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.spring.mvc.DAO.AppointmentDAO;
import com.spring.mvc.DAO.AppointmentDAOImpl;
import com.spring.mvc.jsonview.Views;
import com.spring.mvc.model.AjaxResponseBody;
import com.spring.mvc.pojo.Appointment;
import com.spring.mvc.pojo.SearchCriteria;
import com.spring.mvc.pojo.User;

@RestController
public class AjaxController {

	List<User> users;
	

	// @ResponseBody, not necessary, since class is annotated with @RestController
	// @RequestBody - Convert the json data into object (SearchCriteria) mapped by field name.
	// @JsonView(Views.Public.class) - Optional, limited the json data display to client.
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/search/api/getSearchResult")
	public AjaxResponseBody getSearchResultViaAjax(@RequestBody SearchCriteria search) {

		AjaxResponseBody result = new AjaxResponseBody();

		if (isValidSearchCriteria(search)) {
			List<User> users = findByUserNameOrEmail(search.getUsername(), search.getEmail());

			if (users.size() > 0) {
				result.setCode("200");
				result.setMsg("");
				result.setResult(users);
			} else {
				result.setCode("204");
				result.setMsg("No user!");
			}

		} else {
			result.setCode("400");
			result.setMsg("Search criteria is empty!");
		}

		//AjaxResponseBody will be converted into json format and send back to client.
		return result;

	}
	@JsonView(Views.Public.class)
	@RequestMapping(value="/search/api/addAppointment")
	public AjaxResponseBody addAppointment(@RequestBody Appointment appointment) {
		AjaxResponseBody result = new AjaxResponseBody();
		if(addappointmentValidation(appointment)){
			addAppointmentTOSchedule(appointment);
			result.setCode("200");
			result.setMsg("Successfully Added this user to your appointment scheduler");
			result.setAppointment(appointment);
			
		}else{
			result.setCode("200");
			result.setMsg("was unable to assign appointment, another customer is already assigned to time spot");
			result.setAppointment(null);
		}
		
		return result;
		
	}
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/search/api/getAllAppointmentSearchResult")
	public AjaxResponseBody getSearchAppointmentResultViaAjax(@RequestBody Appointment app) {

		AjaxResponseBody result = new AjaxResponseBody();

		if (isValidForAllSearch(app.getDescription())) {
			//Get All Appointments
			List<Appointment> appointments = FinaAllAppointments();
			System.out.println(appointments);
			
				result.setCode("200");
				result.setMsg("Successfully Collected");
				result.setAppointmentResults(appointments);
			} else {
				List<Appointment> apts = findRelatedInformation(app.getDescription().replaceAll(" " , ""));
				result.setCode("200");
				result.setMsg("Successfully Collected");
				result.setAppointmentResults(apts);
				
			}
		//AjaxResponseBody will be converted into json format and send back to client.
				return result;
		} 

		

	
	
	
	
	
	private boolean isValidForAllSearch(String app) {

		boolean valid = false;

		if (app.isEmpty()) {
			valid = true;
		}

//		if ((StringUtils.isEmpty(search.getDescription())) || (StringUtils.isEmpty(search.getEmail()))) {
//			valid = false;
//		}

		return valid;
	}


	private boolean isValidSearchCriteria(SearchCriteria search) {

		boolean valid = true;

		if (search == null) {
			valid = false;
		}

		if ((StringUtils.isEmpty(search.getUsername())) && (StringUtils.isEmpty(search.getEmail()))) {
			valid = false;
		}

		return valid;
	}

	// Init some users for testing
	@PostConstruct
	private void iniDataForTesting() {
		users = new ArrayList<User>();

		User user1 = new User("mkyong", "pass123", "mkyong@yahoo.com", "012-1234567", "River Green 1402");
		User user2 = new User("yflow", "pass456", "yflow@yahoo.com", "016-7654321", "address 456");
		User user3 = new User("laplap", "pass789", "mkyong@yahoo.com", "012-111111", "address 789");
		users.add(user1);
		users.add(user2);
		users.add(user3);

	}

	// Simulate the search function
	private List<User> findByUserNameOrEmail(String username, String email) {

		List<User> result = new ArrayList<User>();

		for (User user : users) {

			if ((!StringUtils.isEmpty(username)) && (!StringUtils.isEmpty(email))) {

				if (username.equals(user.getUsername()) && email.equals(user.getEmail())) {
					result.add(user);
					continue;
				} else {
					continue;
				}

			}
			if (!StringUtils.isEmpty(username)) {
				if (username.equals(user.getUsername())) {
					result.add(user);
					continue;
				}
			}

			if (!StringUtils.isEmpty(email)) {
				if (email.equals(user.getEmail())) {
					result.add(user);
					continue;
				}
			}

		}

		return result;

	}
	
	
	
	//simulate search Results 
	public List<Appointment> FinaAllAppointments(){
		String ld =  LocalDateTime.now().toString();
		List<Appointment> appointments = new ArrayList<>();
		AppointmentDAO appimpl = new AppointmentDAOImpl();
		appointments =appimpl.findAllAppointments();
//		Appointment oneIndex1 =new Appointment(1L,"Appointment Description1",ld,ld,ld.toString(),ld.toString());
//		Appointment oneIndex2=new Appointment(2L,"Appointment Description2",ld,ld,ld.toString(),ld.toString());
//		Appointment oneIndex3=new Appointment(3L,"Appointment Description2",ld,ld,ld.toString(),ld.toString());
//		appointments.add(oneIndex1);
//		appointments.add(oneIndex2);
//		appointments.add(oneIndex3);
		
		return appointments;
	}
private boolean addappointmentValidation(Appointment appointment) {
	AppointmentDAO appimpl = new AppointmentDAOImpl();
	
		if(appimpl.timeOpenAndAfterCurrentTime(appointment.getTime())) {
			return  true;
		}else {
			
		return false;	
			
		}

	}
	

	
	public List<Appointment> findRelatedInformation(String search) {
		AppointmentDAO aptImpl =new AppointmentDAOImpl();
		List<Appointment> apts = new ArrayList<>();
		apts=aptImpl.findAllRelatedAppointments(search);
		return apts;
	
	}
	
	public void addAppointmentTOSchedule(Appointment apt) {	
		AppointmentDAO appimpl = new AppointmentDAOImpl();
		System.out.println("Added Appointment to Schedule");
		appimpl.addAppointmentTOSchedule(apt);
	}
}
