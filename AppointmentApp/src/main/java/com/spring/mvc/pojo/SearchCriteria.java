package com.spring.mvc.pojo;

public class SearchCriteria {

	String username;
	String email;
	String stringSeach;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	public String getStringSeach() {
		return stringSeach;
	}

	public void setStringSeach(String stringSeach) {
		this.stringSeach = stringSeach;
	}


	@Override
	public String toString() {
		return "SearchCriteria [username=" + username + ", email=" + email + ", stringSeach=" + stringSeach + "]";
	}

	

}
