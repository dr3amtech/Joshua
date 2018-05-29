package com.spring.mvc.DAO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.spring.mvc.config.Connection;
import com.spring.mvc.pojo.Appointment;
import com.spring.mvc.pojo.SearchCriteria;

public class AppointmentDAOImpl implements AppointmentDAO {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	private static final String Appointment = null;
	private static SessionFactory sessionFactory = null;
	private static Session session;

	public AppointmentDAOImpl() {
		getConnection();
	}

	private static void getConnection() {
		try {
			session.getSessionFactory().isOpen();

		} catch (NullPointerException ne) {
			Connection.getConnection();
		}
	}

	@Override
	public boolean timeOpenAndAfterCurrentTime(String time) {

		LocalDateTime utc = LocalDateTime.now();
		LocalDateTime localDateTime = LocalDateTime.parse(time);
		boolean valDate = true;
		
		if (utc.getMonth().getValue() <= localDateTime.getMonth().getValue()) {

				try {
					sessionFactory = Connection.getSessionFactory();

					session = sessionFactory.openSession();

					session.getTransaction().begin();

					String hql = "From Appointment";
					@SuppressWarnings("unchecked")
					List<Appointment> appointment = (List<Appointment>) session.createQuery(hql).getResultList();

					for (int i = 0; i < appointment.size(); i++) {
						LocalDateTime ldt = LocalDateTime.parse(appointment.get(i).getAppointmentDate());
						// if db date is greater than current date and time
						if (utc.compareTo(localDateTime) < 0) {
							// At this level if the time is greater or equal to the times in the database
							if (localDateTime.compareTo(ldt) == 1) {
								valDate = true;
							} else {
								valDate = false;
							}
						} else {
							valDate = false;
						}
					}

				} catch (HibernateException hb) {
					hb.printStackTrace();
				}

			} else {
				valDate = false;
			}
			

		// check for current date first then compare
		// gather information from database in order to compare to current date
		// if (time.isAfter())) {
		// return false;
		// }else {}
		// true being this time is ava
		return valDate;
	}

	@Override
	public List<Appointment> findAllRelatedAppointments(String search) {
		sessionFactory = Connection.getSessionFactory();

		session = sessionFactory.openSession();

		String hql = "From Appointment where description  like :description";
		@SuppressWarnings("unchecked")
		List<Appointment> appointment = (List<Appointment>) session.createQuery(hql).setParameter("description", search).getResultList();
		
		return appointment;
	
	}

	@Override
	public void addAppointmentTOSchedule(Appointment apt) {
		try {
			sessionFactory = Connection.getSessionFactory();

			session = sessionFactory.openSession();

			session.getTransaction().begin();

			session.save(apt);

		} catch (HibernateException hb) {
			hb.printStackTrace();
		} finally {
			session.getTransaction().commit();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Appointment> findAllAppointments() {

		sessionFactory = Connection.getSessionFactory();

		session = sessionFactory.openSession();

		String hql = "From Appointment";
		List<Appointment> appointment = (List<Appointment>) session.createQuery(hql).getResultList();

		return appointment;
	}

}
