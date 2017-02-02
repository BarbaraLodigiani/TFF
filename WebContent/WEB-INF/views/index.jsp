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
.error {
	color: red;
}

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
				<h4>Twitter Following Friends</h4>

				<form:form action="ricerca" method="POST" commandName="FormRicerca">
					<div class="form-group">
						<label for="location">Locations:</label>
						<form:input type="text" class="form-control" id="location"
							path="location" />
						<form:errors path="location" cssClass="error" />
					</div>
					<div class="form-group">
						<label for="interests">Interests:</label>
						<form:input type="text" class="form-control" id="interests"
							path="interests" />
						<form:errors path="interests" cssClass="error" />
					</div>
					<div>
						<label class="radio-inline"> <form:radiobutton
								name="optradioInt" value="and" path="radioInt" />AND
						</label> <label class="radio-inline"> <form:radiobutton
								name="optradioInt" value="or" path="radioInt" checked="checked" />OR
						</label>
						<form:errors path="radioInt" cssClass="error" />
					</div>
					<div class="form-group">
						<label for="hashtags">Hashtags:</label>
						<form:input type="text" class="form-control" id="hashtags"
							path="hashtags" />
						<form:errors path="hashtags" cssClass="error" />
					</div>
					<div>
						<label class="radio-inline"> <form:radiobutton
								name="optradioHash" value="and" path="radioHash" />AND
						</label> <label class="radio-inline"> <form:radiobutton
								name="optradioHash" value="or" path="radioHash"
								checked="checked" />OR
						</label>
						<form:errors path="radioHash" cssClass="error" />
					</div>

					<div class="form-group">
						<label for="age">Age:</label>
						<form:select class="form-control" id="age" path="age">
							<form:option value="">-</form:option>
							<form:option value="a1">13-17</form:option>
							<form:option value="a2">18-25</form:option>
							<form:option value="a3">26-35</form:option>
							<form:option value="a4">36-50</form:option>
							<form:option value="a5">51-65</form:option>
							<form:option value="a6">65-100</form:option>
						</form:select>
						<form:errors path="age" cssClass="error" />
					</div>



					<div class="form-group">
						<label for="gender">Gender:</label>
						<form:select class="form-control" id="gender" path="gender">
							<form:option value="">-</form:option>
							<form:option value="M">Male</form:option>
							<form:option value="F">Female</form:option>
						</form:select>
						<form:errors path="gender" cssClass="error" />
					</div>

					<button type="submit" class="btn btn-default">Submit</button>

				</form:form>

			</div>

			<div class="col-sm-9">
				<h4>
					<small>RECENT POSTS</small>
				</h4>

				<div class="container-fluid bg-3 text-center">
					<h3>Where To Find Me?</h3>
					<div class="row">
						<div class="col-sm-4" id="risultati">
							<c:if test="${not empty risultati}">

								<ul>
									<c:forEach var="listValue" items="${risultati}">
										<li>
											<ul>
												<c:forEach var="listValue2" items="${listValue}">
													<li>
														<ul>
															<c:forEach var="listValue3" items="${listValue2}">
																<li>${listValue3}</li>
															</c:forEach>
														</ul>
													</li>
												</c:forEach>
											</ul>
										</li>
									</c:forEach>
								</ul>

							</c:if>
							${query.result} <label for=loc>Location: </label> <img
								src="birds1.jpg" alt="Image">
						</div>
						<div class="col-sm-4">
							<p>Lorem ipsum..</p>
							<img src="birds2.jpg" alt="Image">
						</div>
						<div class="col-sm-4">
							<p>Lorem ipsum..</p>
							<img src="birds3.jpg" alt="Image">
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

	<footer class="container-fluid">
		<p>Footer Text</p>
	</footer>
</body>
</html>