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
		<link rel="stylesheet" type="text/css" href="css/fbuchung.css">
		<script type="text/javascript" src="vendor/jquery/jquery.js"></script>
		<script type="text/javascript" src="vendor/jquery/jquery.autocomplete.min.js"></script>
		<script type="text/javascript" src="vendor/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="vendor/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
		<script type="text/javascript" src="js/moment.js"></script>
		<script type="text/javascript" src="js/flughafen-autocomplete.js"></script>
		<script type="text/javascript" src="js/flugmanagement-datetimepicker.js"></script>
	</head>

	<body>
		<%
		String buchungid = (String)request.getAttribute("buchungid");
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
			<h1>Buchung ist erfolgreich!</h1>
			<br>
			<p class="lead">Ihre Buchungcode ist <%= buchungid %>.</p>
			<br/><br/>
			<p><a href="/skywings">Zur ersten Seite zurück</a></p>
		</div>
	</body>
</html>