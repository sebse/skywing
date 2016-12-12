<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Log Out</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="css/logout.css">
		<script src="vendor/jquery/jquery.js"></script>
		<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
	</head>
	<body>
		<%@ page session="true"%>

		<div class="jumbotron">
			<h1>Skywings Prototyp I</h1>
			<br/><br/>
			<p>User <span style="font-weight:bold;"><%=request.getRemoteUser()%></span> wird abgemeldet.

			<% session.invalidate(); %>

			<br/><br/>
			<a href="/skywings">Zur ersten Seite zurück</a></p>
		</div>
	</body>
</html>