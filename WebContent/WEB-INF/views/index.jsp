<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
/* Set height of the grid so .sidenav can be 100% (adjust if needed) */
.row.content {
	height: 1500px
}

/* Set gray background color and 100% height */
.sidenav {
	background-color: #f1f1f1;
	height: 100%;
}

/* Set black background color, white text and some padding */
footer {
	background-color: #555;
	color: white;
	padding: 15px;
}

/* On small screens, set height to 'auto' for sidenav and grid */
@media screen and (max-width: 767px) {
	.sidenav {
		height: auto;
		padding: 15px;
	}
	.row.content {
		height: auto;
	}
}
</style>


</head>
<body>

	<div class="container-fluid">
		<div class="row content">
			<div class="col-sm-3 sidenav">
				<h4>Barbarella</h4>

				<form:form action="ricerca" method="POST" commandName="FormRicerca">
					<div class="form-group">
						<label for="location">Locations:</label> 
						<form:input type="text"
							class="form-control" id="location" path="location" />
						<form:errors path="location" cssClass="error" />
					</div>
					<div class="form-group">
						<label for="interests">Interests:</label> 
						<form:input type="text"
							class="form-control" id="interests" path="interests" />
					</div>
 					<label class="radio-inline"> 
						<form:radiobutton  name="optradioInt" value="A" path="radioInt"/>AND
 					</label>  
 					<label class="radio-inline"> 
						<form:radiobutton  name="optradioInt" value="O" path="radioInt"/>OR
 					</label>  

 					<div class="form-group"> 
 						<label for="hashtags">Hashtags:</label>  
						<form:input type="text"
							class="form-control" id="hashtags" path="hashtags" />
 					</div>
 					<label class="radio-inline"> 
						<form:radiobutton  name="optradioHash" value="A" path="radioHash" />AND
 					</label>  
 					<label class="radio-inline"> 
						<form:radiobutton  name="optradioHash" value="O" path="radioHash" />OR
 					</label>  
					
					
					<div class="form-group">
					  <label for="age">Age:</label>
					  <form:select class="form-control" id="age" path="age">
					    <form:option value="n">-</form:option>
					    <form:option value="a1">13-17</form:option>
					    <form:option value="a2">18-25</form:option>
					    <form:option value="a3">26-35</form:option>
					    <form:option value="a4">36-50</form:option>
					    <form:option value="a5">51-65</form:option>
					    <form:option value="a6">65-100</form:option>
					  </form:select>
					</div>
					
					
					
					<div class="form-group">
					  <label for="gender">Gender:</label>
					  <form:select class="form-control" id="gender" path="gender">
					    <form:option value="n">-</form:option>
					    <form:option value="M">Male</form:option>
					    <form:option value="F">Female</form:option>
					  </form:select>
					</div>
						
					<button type="submit" class="btn btn-default">Submit</button>

				</form:form>

			</div>

			<div class="col-sm-9">
				<h4>
					<small>RECENT POSTS</small>
				</h4>

			</div>
		</div>
	</div>

	<footer class="container-fluid">
		<p>Footer Text</p>
	</footer>
</body>
</html>