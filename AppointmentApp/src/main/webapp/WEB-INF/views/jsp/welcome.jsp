<%@page session="false"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Appointment Application</title>

<c:url var="home" value="/" scope="request" />

<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link
	href="C:\Users\joshu\EclipseProjects\eclipse-workspacefirstusage\AppointmentApp\src\main\webapp\resources\core\css\bootstrap.min.css"
	rel="stylesheet" />

<spring:url value="/resources/core/js/jquery.1.10.2.min.js"
	var="jqueryJs"/>
<script src="${jqueryJs}"></script>
</head>

<nav class="navbar navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Appointment Application </a>
		</div>
	</div>
</nav>

<div class="starter-template">
	<h1>Search Form</h1>
	<br>

	<div id="feedback"></div>
	<div class="form-group">
		<form class="form-horizontal" id="Add-form" style="width: 1093px;">
			<div class="col-sm-offset-2 col-sm-10" id="buttonSelection">
				<button type="submit" id="bth-NewAppointment"
					class="btn btn-primary btn-lg" style="width: 134px">NEW</button>


			</div>
		</form>
		<br>
		<form class="form-horizontal" id="search-form">

			<div class="form-group form-group-lg">
				<label class="col-sm-2 control-label"></label>
				<div class="col-sm-10" style="width: 327px;">
					<input type="text" class="form-control" id="userInput">
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10" style="width: 74px;">
					<button type="submit" id="bth-search"
						class="btn btn-primary btn-lg">Search</button>
				</div>
			</div>
		</form>
		<!-- Table of Adding Customer -->
		<table id="appointmentTables" class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>DATE</th>
					<th>Time</th>
					<th>Description</th>
					
			</thead>
			<tbody>
			<tr id=trid> 
							<td id=td1></td>
							<td id=td2></td>
							<td id=td3></td></tr>
				</tr>
			</tbody>
			
		</table>
	</div>

</div>

<div class="container">
	<footer> </footer>
</div>

<script>
	jQuery(document)
			.ready(
					function($) {
					//document.getElementById("appointmentTables").empty();
					//document.getElementById("appointmentTables").hide();
						$("#bth-NewAppointment")
								.click(
										function(event) {
											event.preventDefault();
											addAppointments();
											$(
													document
															.getElementById("bth-NewAppointment"))
													.hide();
											$(
													document
															.getElementById("buttonSelection"))
													.append(
															'<button   type="submit" id="ADD" class="btn btn-primary btn-lg" style="width: 90px">ADD</button>');
											$(
													document
															.getElementById("buttonSelection"))
													.append(
															'<button onclick="cancleOption()" id="CANCELBUTTON" class="btn btn-primary btn-lg" style="width: 90px" >CANCEL</button>');
											$(
													document
															.getElementById("Add-form"))
													.append(
															'<label  id="date">Date:</label> <input type="datetime-local" class="form-control" id="DateInput"/>');
											$(
													document
															.getElementById("Add-form"))
													.append(
															'<label id="decsription">Description:</label> <input type="text" class="form-control" id="DescInput"/>');
										});
						$("#search-form").submit(function(event) {
							// Disble the search button
							enableSearchButton(false);
							// Prevent the form from submitting via the browser.
							event.preventDefault();
							getAppointments();
							//searchViaAjax();
						});
						
						$("#Add-form").submit(function(event) {
			// Disble the search button
			enableSearchButton(false);
			// Prevent the form from submitting via the browser.
			event.preventDefault();
			addApointment()
			//searchViaAjax();
		});
		
					});
	function cancleOption() {
		console.log("Adding Button");
	
		$(document.getElementById("CANCELBUTTON")).remove();
		$(document.getElementById("ADD")).remove();
		$(document.getElementById("DateInput")).remove();
		$(document.getElementById("DescInput")).remove();
		$(document.getElementById("date")).remove();
		$(document.getElementById("time")).remove();
		$(document.getElementById("decsription")).remove();
		$(document.getElementById("bth-NewAppointment")).show();
		$(document.getElementById("search-form")).show();
		
	}
	function addAppointments() {
		console.log("Adding Appointments Buttons: ");
		$(document.getElementById("search-form")).hide();
	}
	function addApointment() {
	$(document.getElementById("appointmentTables tr")).remove();
		$(document.getElementById("appointmentTables")).show();
		enableSearchButton(false);
		var appointment = {}
		appointment["description"] = $(document.getElementById("DescInput")).val();
		appointment["appointmentDate"] = $(document.getElementById("DateInput")).val();
		appointment["time"] = $(document.getElementById("DateInput")).val();
		
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${home}search/api/addAppointment",
			data : JSON.stringify(appointment),
			datType : "json",
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS");
				displayIt(data);
			},
			error : function(e) {
				console.log(e);
				display(e);
			},
			done : function(e) {
				console.log("Done");
				enableSearcgButton(true);
			}
		});
	}
	function getAppointments() {
	  
		
		var search = {}
		search["description"] =  $(document.getElementById("userInput")).val();
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${home}search/api/getAllAppointmentSearchResult",
			data : JSON.stringify(search),
			dataType : "json",
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				displayResults(data)
				display(data);
				
				
			},
			error : function(e) {
				console.log("ERROR: ", e);
				display(e);
			},
			done : function(e) {
				console.log("Done");
				enableSearcgButton(true);
				
			}
		});
	}
	function searchViaAjax() {
		var search = {}
		search["username"] = $("#username").val();
		search["email"] = $("#email").val();
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${home}search/api/getSearchResult",
			data : JSON.stringify(search),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				display(data);
			},
			error : function(e) {
				console.log("ERROR: ", e);
				display(e);
			},
			done : function(e) {
				console.log("DONE");
				enableSearchButton(true);
			}
		});
	}
	function enableSearchButton(flag) {
		$("#btn-search").prop("disabled", flag);
	}
	function display(data) {
		var json = "<h4>Ajax Response</h4><pre>"
				+ JSON.stringify(data, null, 4) + "</pre>";
		$('#feedback').html(json);
	}
	function displayResults(data) {
		var x=0;
		 $("#appointmentTables tr").remove(); 
	for(ar in data.appointmentResults){
	
	
	//$('#appointmentTables > tr > td').remove();
		 
		 var table = document.getElementById("appointmentTables");

// Create an empty <tr> element and add it to the 1st position of the table:
var row = table.insertRow(x);

// Insert new cells (<td> elements) at the 1st and 2nd position of the "new" <tr> element:
var cell1 = row.insertCell(0);
var cell2 = row.insertCell(1);
var cell3 = row.insertCell(2);

// Add some text to the new cells:
cell1.innerHTML = data.appointmentResults[ar].timeS;
cell2.innerHTML = data.appointmentResults[ar].appointmentDateS;
cell3.innerHTML = data.appointmentResults[ar].description;
		 x++;
	    
		/*  appointmentTables = '<tr id=trid>' +
							'<td id=td1>' + data.appointmentResults[ar].timeS + '</td>' +
							'<td id=td2>' + data.appointmentResults[ar].appointmentDateS + '</td>'+
							'<td id=td3>' + data.appointmentResults[ar].description+ '</td></tr>';
						  $('#appointmentTables').append(appointmentTables);
						  */
						 }
						 
						
	}
	 
    	function displayIt(data) {
    	if (data.appointmentResults==null){
     alert(data.msg);
}
     }
</script>


</html>
