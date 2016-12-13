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
		String selFlug = (String)request.getAttribute("selFlug");
		Integer n_passagier = (Integer)request.getAttribute("n_passagier");
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
			<p class="lead" style="margin-top:15px;">Geben Sie hier Ihre Information ein.</p>
		</div>

		<div class="container">
			<form id="passenger-list" method="POST" action="">
				<!-- -->
				<div class="row passagier-form-layout">
					<div class="col-md-2"></div>
					<div class="col-md-8 passagier-form form-group">
						<h4 style="font-weight:bold; padding-top: 5px;">Kontakt (pro Gruppe):</h4>
						<div class="row" style="margin-top:15px; margin-bottom:20px;">
							<div class="col-md-6">
								<label for="tel">Tel:</label>
								<input type="text" id="tel" name="tel" class="form-control" require />
							</div>
							<div class="col-md-6">
								<label for="email">E-mail:</label>
								<input type="text" id="email" name="email" class="form-control" require />
							</div>
						</div>
					</div>
					<div class="col-md-2"></div>
				</div>
				<%
				for(int i=0; i < n_passagier; i++) {
					String form_header = "Passagier" + " " + (i+1);
					String vorname = "vorname" + i;
					String nachname = "nachname" + i;
					String strasse = "strasse" + i;
					String ort = "ort" + i;
					String plz = "plz" + i;
					String g_datum = "g_datum" + i;
					String passnr = "passnr" + i;
					String passAbDat = "passAbDat" + i;
					String nation = "nation" + i;
				%>
				<div class="row passagier-form-layout">
					<div class="col-md-2"></div>
					<div class="col-md-8 passagier-form form-group">
						<h4 style="font-weight:bold; padding-top: 5px;"><%= form_header %></h4>
						<div class="row" style="margin-top:15px;">
							<div class="col-md-6">
								<label for=<%= vorname %> >Vorname:</label>
								<input type="text" id=<%= vorname %> name=<%= vorname %> class="form-control" require />
							</div>
							<div class="col-md-6">
								<label for=<%= nachname %> >Nachname:</label>
								<input type="text" id=<%= nachname %> name=<%= nachname %> class="form-control" require />
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<label for=<%= g_datum %> >Geburtsdatum:</label>
								<div class="form-group" style="margin-bottom:5px">
									<div class="input-group date form_date" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1">
										<input class="form-control" type="text" placeholder="Geburtsdatum" name=<%= g_datum %> id=<%= g_datum %> readonly required>
										<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
										<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
									</div>
									<input type="hidden" id="dtp_input1" value="" />
								</div>
								<label for=<%= passnr %> >Passnummer:</label>
								<input type="text" id=<%= passnr %> name=<%= passnr %> class="form-control" require />
							</div>
							<div class="col-md-6">
								<label for=<%= nation %> >Nationalität:</label>
								<input type="text" id=<%= nation %> name=<%= nation %> class="form-control" style="margin-bottom:5px;" require />
								<label for=<%= passAbDat %> >Passablaufsdatum:</label>
								<div class="form-group">
									<div class="input-group date form_date_pass" data-date-format="yyyy-mm-dd" data-link-field="dtp_input1">
										<input class="form-control" type="text" placeholder="Passablaufsdatum" name=<%= passAbDat %> id=<%= passAbDat %> readonly required>
										<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
										<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
									</div>
									<input type="hidden" id="dtp_input1" value="" />
								</div>
							</div>
						</div>
						<br>
						<div class="row" style="margin-bottom:20px;">
							<div class="col-md-6">
								<label for=<%= strasse %> >Strasse:</label>
								<input type="text" id=<%= strasse %> name=<%= strasse %> class="form-control" require />
								<label for=<%= plz %>>PLZ:</label>
								<input type="text" id=<%= plz %> name=<%= plz %> class="form-control" require />
							</div>
							<div class="col-md-6">
								<label for=<%= ort %> >Ort:</label>
								<input type="text" id=<%= ort %> name=<%= ort %> class="form-control" require />
							</div>
						</div>
					</div>
					<div class="col-md-2"></div>
				</div>
				<!-- -->
				<%
				}
				%>
				<div class="row btn-bottom-row">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<div class="col-md-3"></div>
						<div class="col-md-6">
							<input type="hidden" name='selectFlug' value=<%= selFlug %> >
							<input type="hidden" name='numPassagier' value=<%= n_passagier %>>
							<button class="btn btn-lg btn-success btn-block" type="submit">Weiter</button>
						</div>
						<div class="col-md-3"></div>
					</div>
					<div class="col-md-2"></div>
				</div>
			</form>
			<script type="text/javascript">
					$('.form_date').datetimepicker({
							weekStart: 1,
							todayBtn:  0,
							autoclose: 1,
							todayHighlight: 1,
							startView: 4,
							minView: 2,
							forceParse: 0,
						});

					$('.form_date_pass').datetimepicker({
							weekStart: 1,
							todayBtn:  0,
							autoclose: 1,
							todayHighlight: 1,
							startView: 4,
							startDate: '+1d',
							minView: 2,
							forceParse: 0,
						});
					</script>
		</div>
	</body>
</html>

<!--
	private String vorname;
	private String nachname;
	private String strasse;
	private String ort;
	private String postleitzahl;
	private Date geburtsdatum;
	private String passnummer;
	private Date passAblaufsdatum;
	private String nationalitaet;
 -->