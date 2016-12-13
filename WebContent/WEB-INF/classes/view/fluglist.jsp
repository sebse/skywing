<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Skywings Portal: FlugListe</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap-datetimepicker.min.css">
		<link rel="stylesheet" type="text/css" href="vendor/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="css/fliste.css">
		<script type="text/javascript" src="vendor/jquery/jquery.js"></script>
		<script type="text/javascript" src="vendor/jquery/jquery.autocomplete.min.js"></script>
		<script type="text/javascript" src="vendor/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="vendor/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
		<script type="text/javascript" src="js/moment.js"></script>
		<script type="text/javascript" src="js/flughafen-autocomplete.js"></script>
		<script type="text/javascript" src="js/flugmanagement-datetimepicker.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				$('input[type=radio][name=flug]').change(function() {
					var flug = document.getElementsByName('flug');
					var flug_value;
					for(var i = 0; i < flug.length; i++){
					    if(flug[i].checked){
					        flug_value = flug[i].value;
					        break;
					    }
					}
					document.getElementById('sel-flug').value = flug_value;
				});
			});
		</script>
	</head>

	<body>
		<%
		ArrayList<String> flugnrList = (ArrayList<String>)request.getAttribute("flugnrList");
		ArrayList<Double> preisList = (ArrayList<Double>)request.getAttribute("preisList");
		ArrayList<Integer> freiplatzList = (ArrayList<Integer>)request.getAttribute("freiplatzList");
		ArrayList<String> abDatList = (ArrayList<String>)request.getAttribute("abDatList");
		ArrayList<String> anDatList = (ArrayList<String>)request.getAttribute("anDatList");
		ArrayList<String> abOrtList = (ArrayList<String>)request.getAttribute("abOrtList");
		ArrayList<String> anOrtList = (ArrayList<String>)request.getAttribute("anOrtList");
		%>
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-main-collapse">
						Menu <i class="fa fa-bars"></i>
					</button>
					<a class="navbar-brand page-scroll" href="/skywings">
						<i class="fa fa-plane"></i> <span style="font-weight:bold">SKYWINGS</span> Prototyp I
					</a>
				</div>

				<%
				if(request.getRemoteUser() != null) {
				%>
					<div class="collapse navbar-collapse navbar-right navbar-main-collapse">
						<ul class="nav navbar-nav">
							<li><a class="page-scroll" href="#">User: <%= request.getRemoteUser() %></a></li>
							<li><a class="page-scroll" href="logout.jsp">Sign Out</a></li>
						</ul>
					</div>
				<%
				}
				%>
			</div>
		</nav>

		<div class="jumbotron">
			<h1>Skywings Prototyp I</h1>
			<p class="lead" style="margin-top:15px;">Alle Fluege sind hier zu buchen</p>
		</div>

		<div class="container">
			<form name="flugList-form">
				<%				
					// check if it exists
					if(flugnrList == null | freiplatzList == null | abDatList == null | anDatList == null) {
				%>
						<div class="alert alert-danger" role="alert">
							<strong>Falscher Pfad!</strong>
							Sie haben direkt an .jsp-Datei gerufen. Bitte laden Sie noch einmal <a href="/addflug/add">hier</a>!
						</div>
				<%
					} else if(flugnrList.isEmpty() | freiplatzList.isEmpty() | abDatList.isEmpty() | anDatList.isEmpty()) {
				%>
						<div class="alert alert-warning" role="alert">
							<strong>Keine Liste!</strong>
							Es gibt im Moment kein Flug in der Liste.
						</div>
				<%
					} else {
				%>

				<table class="table table-hover">
					<thead>
						<tr>
							<th></th>
							<th>Flugnummer</th>
							<th>Preis (euro)</th>
							<th>Abflugsdatum</th>
							<th>Ankunftsdatum</th>
							<th>Abflugsort</th>
							<th>Ankunftsort</th>
							<th>Anzahl Freiplätze</th>
						</tr>
					</thead>
					<tbody>
				<%
						for(int i=0; i<flugnrList.size(); i++) {
							String new_id = new String();
							new_id = "flug" + i;

							String value = new String();
							value += flugnrList.get(i) + "#" + abDatList.get(i);
				%>
							<tr>
								<td><input id='<%= new_id %>' type="radio" name="flug" value=<%= value %> /></td>
								<td><label for=<%= new_id %> style="font-weight: normal;"><%= flugnrList.get(i) %></label></td>
								<td class="tcenter"><label for=<%= new_id %> style="font-weight: normal;"><%= preisList.get(i) %></label></td>
								<td class="tcenter"><label for=<%= new_id %> style="font-weight: normal;"><%= abDatList.get(i) %></label></td>
								<td class="tcenter"><label for=<%= new_id %> style="font-weight: normal;"><%= anDatList.get(i) %></label></td>
								<td class="tcenter"><label for=<%= new_id %> style="font-weight: normal;"><%= abOrtList.get(i) %></label></td>
								<td class="tcenter"><label for=<%= new_id %> style="font-weight: normal;"><%= anOrtList.get(i) %></label></td>
								<td class="tcenter"><label for=<%= new_id %> style="font-weight: normal;"><%= freiplatzList.get(i) %></label></td>
							</tr>
				<%
						}	
					}
				%>
					</tbody>
				</table>
			</form>
		</div>

		<footer class="footer">
			<div class="container">
				<form name="book-flug" action="buchung" method="GET">
					<div class="row">
						<div class="col-md-5"></div>
						<div class="col-md-3" style="text-align:right;">
							<label for="anz_pass">Anzahl Passagieren:</label>
						</div>
						<div class="col-md-2">
							<select class="form-control" id="anz_pass" name="anz_pass">
								<option>1</option>
								<option>2</option>
								<option>3</option>
								<option>4</option>
							</select>
						</div>
						<div class="col-md-2">
							<input type="hidden" name="sel-flug" id="sel-flug" value="">
							<button class="btn btn-lg btn-success" type="submit">Buchen</button>
						</div>
					</div>
				</form>
			</div>
		</footer>
	</body>
</html>