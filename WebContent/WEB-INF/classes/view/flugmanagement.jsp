<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Skywings Portal</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap-datetimepicker.min.css">
		<link rel="stylesheet" type="text/css" href="vendor/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="css/fmanagement.css">
		<script type="text/javascript" src="vendor/jquery/jquery.js"></script>
		<script type="text/javascript" src="vendor/jquery/jquery.autocomplete.min.js"></script>
		<script type="text/javascript" src="vendor/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="vendor/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
		<script type="text/javascript" src="js/moment.js"></script>
		<script type="text/javascript" src="js/flughafen-autocomplete.js"></script>
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

		ArrayList<String> datList = (ArrayList<String>)request.getAttribute("datList");
		ArrayList<String> emailList = (ArrayList<String>)request.getAttribute("emailList");
		ArrayList<String> telList = (ArrayList<String>)request.getAttribute("teList");
		ArrayList<String> flugcodeList = (ArrayList<String>)request.getAttribute("flugcodeList");
		ArrayList<Integer> anzPassList = (ArrayList<Integer>)request.getAttribute("anzPassagierList");
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

		<%
			if(request.getRemoteUser() == null) {
				response.sendRedirect("/skywings/");
			}
		%>

		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-3 col-md-3 sidebar">
					<img src="img/airline.jpg" class="img-circle" alt="Flug Management image" width="140" height="140" style="display:block;margin:auto;border:5px solid white;">
					<h2 style="text-align:center;">Flug Management</h2>
					<h4 style="padding-top:15px;">Flug Eintragen</h4>
					<form id="add-flug" action="management/eintragen" method="POST" class="form-fmanagement">
						<ul class="nav">
							<li class="input-list"><input type="text" name="flugnr" class="form-control" placeholder="Flugnummer z.B. SW123" required></li>
							<li class="input-list"><input type="text" name="preis" class="form-control" placeholder="Preis (euro) z.B. 350.50" required></li>
							<!--
							<li class="input-list"><input type="text" name="ab_datum" class="form-control" data-format="dd/MM/yyyy hh:mm:ss" placeholder="Abflugsdatum z.B. 2016-06-06@12:30" required></li>
							-->
							<li class="input-list">
								<div class="form-group" style="margin-bottom:10px">
									<div class="input-group date form_datetime" data-date-format="yyyy-mm-dd@hh:ii" data-link-field="dtp_input1">
										<input class="form-control" type="text" placeholder="Abflugssdatum" name="ab_datum" readonly required>
										<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
										<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
									</div>
									<input type="hidden" id="dtp_input1" value="" />
								</div>
         			</li>

							<li class="input-list">
								<div class="form-group" style="margin-bottom:10px">
									<div class="input-group date form_datetime" data-date-format="yyyy-mm-dd@hh:ii" data-link-field="dtp_input1">
										<input class="form-control" type="text" placeholder="Ankunftsdatum" name="an_datum" readonly required>
										<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
										<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
									</div>
									<input type="hidden" id="dtp_input1" value="" />
								</div>
         			</li>
         			<!--
							<li class="input-list"><input type="text" name="an_datum" class="form-control form_datetime" placeholder="Ankunftsdatum z.B. 2016-06-06@14:30" required></input></li>
							-->
							<li class="input-list"><input type="text" name="ab_ort" class="form-control" placeholder="Abflugsort z.B. VIE" id="ab_ort" required></li>
							<li class="input-list"><input type="text" name="an_ort" class="form-control" placeholder="Ankunftsort z.B. HTR" id="an_ort" required></li>
							<li style="margin-top:20px;"><button class="btn btn-lg btn-success btn-block" type="submit">Trägt ein</button></li>
						</ul>
					</form>
					<script type="text/javascript">
					$('.form_datetime').datetimepicker({
							weekStart: 1,
							todayBtn:  0,
							autoclose: 1,
							todayHighlight: 1,
							startView: 2,
							startDate: '+1d',
							forceParse: 0,
							showMeridian: 0
						});
					</script>

					<hr>

					<h4>Flug Löschen</h4>
					<form name="del-flug" action="management/loeschen" method="POST" class="form-fmanagement">
						<ul class="nav nav-side-bar">
							<li><input type="hidden" name="sel-flug" id="sel-flug" value=""></li>
							<% if(flugnrList.size() > 0) { %>
							<li style="margin-top:10px;"><button class="btn btn-lg btn-danger btn-block">Löscht ausgewähltes Flug</button></li>
							<% } else { %>
							<li style="margin-top:10px;"><button class="btn btn-lg btn-danger btn-block" onlick="handleDelSubmit()" disabled>Löscht ausgewähltes Flug</button></li>
							<% } %>
						</ul>
					</form>
				</div>

				<div class="col-sm-9 col-sm-offset-3 col-md-9 col-md-offset-3 main">
					<h1 class="page-header">Alle Flüge</h1>
					<div class="row">
						<div class="col-sm-1 col-md-1">
							
						</div>

						<div class="col-sm-10 col-md-10">
							<form name="flugList-form">
								<!--
								<div class="list-group">
									<a class="list-group-item radio"><label><input type="radio" name="radio1" value="test1">Flug 1 tyest test test test</label></a>
								</div>
								<div class="list-group">
									<a class="list-group-item radio"><label><input type="radio" name="radio1" value="test1">Flug 1 tyest test test test</label></a>
								</div>
								<div class="list-group">
									<a class="list-group-item radio"><label><input type="radio" name="radio1" value="test1">Flug 1 tyest test test test</label></a>
								</div>
								<div class="list-group">
									<a class="list-group-item radio"><label><input type="radio" name="radio1" value="test1">Flug 1 tyest test test test</label></a>
								</div>
								-->
								<%				
									// check if it exists
									if(flugnrList == null | freiplatzList == null | abDatList == null | anDatList == null) {
								%>
										<div class="alert alert-danger" role="alert">
											<strong>Falscher Pfad!</strong>
											Sie haben direkt an .jsp-Datei gerufen. Bitte laden Sie noch einmal <a href="/management">hier</a>!
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

						<div class="col-sm-1 col-md-1">
							
						</div>
					</div>
					<h1 class="page-header">Alle Buchungen</h1>
					<div class="row">
						<div class="col-sm-1 col-md-1"></div>
						<div class="col-sm-10 col-md-10">
							<%
							if(datList == null | emailList == null | telList == null | flugcodeList == null | anzPassList == null) {
							%>
									<div class="alert alert-danger" role="alert">
										<strong>Falscher Pfad!</strong>
										Sie haben direkt an .jsp-Datei gerufen. Bitte laden Sie noch einmal <a href="/management">hier</a>!
									</div>
							<%
								} else if(datList.isEmpty() | emailList.isEmpty() | telList.isEmpty() | flugcodeList.isEmpty() | anzPassList.isEmpty()) {
							%>
									<div class="alert alert-warning" role="alert">
										<strong>Keine Liste!</strong>
										Es gibt im Moment kein Flug in der Liste.
									</div>
							<%
								} else {
							%>
							<table class="table">
								<thead>
									<tr>
										<th>Buchungsdatum</th>
										<th>E-mail</th>
										<th>Telefonnummer</th>
										<th>Flugcode</th>
										<th>Anzahl Passagieren</th>
									</tr>
								</thead>
								<tbody>
									<%
									for(int i=0; i < datList.size(); i++) {
									%>
									<tr>
										<td><%= datList.get(i) %></td>
										<td class="tcenter"><%= emailList.get(i) %></td>
										<td class="tcenter"><%= telList.get(i) %></td>
										<td class="tcenter"><%= flugcodeList.get(i) %></td>
										<td class="tcenter"><%= anzPassList.get(i) %></td>
									</tr>
									<%
										}
									}
									%>
								</tbody>
							</table>
						</div>
						<div class="col-sm-1 col-md-1"></div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>