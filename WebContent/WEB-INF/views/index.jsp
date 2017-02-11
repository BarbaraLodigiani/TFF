<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Twitter Following Finder</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script
	src="https://npmcdn.com/imagesloaded@4.1/imagesloaded.pkgd.min.js"></script>

<style>
.error {
	color: red;
}
/* Set height of the grid so .sidenav can be 100% (adjust if needed) */
html,body{
height:100%;
overflow:hidden;
position:relative;
}
 .container-fluid {

	height: 100%;
	overflow:auto;
	position:relative;
	
}

.risultati .container-fluid{
	padding-top:10px;
}
.risultati{
	height: 100%;
	overflow:auto;
margin:0px;
padding:0px;
position:relative;
padding-top:60px;}

.content {
height:100%;
overflow:auto;
position:relative;
}

/* Set gray background color and 100% height */
.sidenav {
	background-color: #010101;
	height: 100%;
	overflow:auto;
	position:relative;
	color:#fff;
	
}

/* Set black background color, white text and some padding */
footer {
	background-color: #555;
	color: white;
	padding: 15px;
}

#risultati{
height:100%;

overflow:auto;
position:relative;
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

#t-cards {
	padding-top: 80px;
	padding-bottom: 80px;
	background-color: #345;
}

/********************************/
/*          Panel cards         */
/********************************/
.panel.panel-card {
	position: relative;
	height: 260px;
	border: none;
	overflow: hidden;
}

.panel.panel-card .panel-heading {
	position: relative;
	z-index: 2;
	height: 80px;
	border-bottom-color: #fff;
	overflow: hidden;
	-webkit-transition: height 600ms ease-in-out;
	transition: height 600ms ease-in-out;
}

.panel.panel-card .panel-heading img {
	position: absolute;
	top: 50%;
	left: 50%;
	z-index: 1;
	width: 120%;
	-webkit-transform: translate3d(-50%, -50%, 0);
	transform: translate3d(-50%, -50%, 0);
}

.panel.panel-card .panel-heading button {
	position: absolute;
	top: 10px;
	right: 15px;
	z-index: 3;
}

.panel.panel-card .panel-figure {
	position: absolute;
	overflow: hidden;
	top: auto;
	left: 50%;
	z-index: 3;
	width: 70px;
	height: 70px;
	background-color: #fff;
	border-radius: 50%;
	opacity: 1;
	-webkit-box-shadow: 0 0 0 3px #fff;
	box-shadow: 0 0 0 3px #fff;
	-webkit-transform: translate3d(-50%, -50%, 0);
	transform: translate3d(-50%, -50%, 0);
	-webkit-transition: opacity 400ms ease-in-out;
	transition: opacity 400ms ease-in-out;
}

.panel.panel-card .panel-body {
	padding-top: 30px;
	padding-bottom: 20px;
	-webkit-transition: padding 400ms ease-in-out;
	transition: padding 400ms ease-in-out;
}

.panel.panel-card .panel-thumbnails {
	padding: 0 15px 10px;
	position:absolute;
	bottom:0;
	left:0;
}

.panel-thumbnails .thumbnail {
	width: 60px;
	max-width: 100%;
	margin: 0 auto;
	background-color: #fff;
}

.panel.panel-card:hover .panel-heading {
	height: 55px;
	-webkit-transition: height 400ms ease-in-out;
	transition: height 400ms ease-in-out;
}

.panel.panel-card:hover .panel-figure {
	opacity: 0;
	-webkit-transition: opacity 400ms ease-in-out;
	transition: opacity 400ms ease-in-out;
}

.panel.panel-card:hover .panel-body {
	padding-top: 20px;
	-webkit-transition: padding 400ms ease-in-out;
	transition: padding 400ms ease-in-out;
}
.panel.panel-card:hover .tweet {
	font-size: 12px;
	-webkit-transition: font-size 400ms ease-in-out;
	transition: font-size 400ms ease-in-out;
}

.panel .tweet{
  font-size:10px;
  font-style: italic;
  text-align: justify;
  line-height: 11px;
  color:grey;
  margin:2px;
display:block;
  position:relative;

}

.tweet:before{
content:'“';
display:block;
position:absolute;
top:-5px;
left:-5px;
font-size:20px;
font-family:Georgia;
color:#555;
}


.tweet:after{
content:'”';

display:block;
position:absolute;
bottom:-10px;
right:5px;
font-size:20px;
font-family:Georgia;
color:#555;
}
.numris{
position:absolute;
top:0px;
left:0px;
background:#5ACCEB;
text-align:center;
color:white;
width:100%;
z-index:10;}

.realname{
font-size:10px;
text-transform:uppercase;
margin:-10px 0 10px 0;
}

</style>


</head>
<body>

	<div class="container-fluid">
		<div class="row content">
			<div class="col-sm-3 sidenav">
				<h4><img class="img-responsive" src="http://i.imgur.com/p8XFYQn.png"/></h4>

				<form:form action="ricerca" method="POST" commandName="FormRicerca">
					<div class="form-group">
					<div style="display:none">
					<form:input type="text"  class="form-control" id="index"
							path="index" />
							</div>
						<form:errors path="index" cssClass="error" />
					</div>
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
							<form:option value="13-17">13-17</form:option>
							<form:option value="18-25">18-25</form:option>
							<form:option value="26-35">26-35</form:option>
							<form:option value="36-50">36-50</form:option>
							<form:option value="51-65">51-65</form:option>
							<form:option value="65-100">65-100</form:option>
						</form:select>
						<form:errors path="age" cssClass="error" />
					</div>



					<div class="form-group">
						<label for="gender">Gender:</label>
						<form:select class="form-control" id="gender" path="gender">
							<form:option value="">-</form:option>
							<form:option value="male">Male</form:option>
							<form:option value="female">Female</form:option>
						</form:select>
						<form:errors path="gender" cssClass="error" />
					</div>

					<button type="submit" class="btn btn-default">Submit</button>

				</form:form>

			</div>

			<div class="col-sm-9 risultati">
						<c:choose>
							<c:when test="${empty risSize}">
								<div class="numris"><h3>Search for Tweeps!</h3></div>
							</c:when>
							<c:otherwise>
								<div class="numris"><h3>Results: ${risSize}</h3></div>
							</c:otherwise>
						</c:choose>
				<c:set var="risindex" value="${risSize-1}"/>
			
				<div class="container-fluid bg-3 text-center">
					<div class="row">
						<div class="col-sm-12" id="risultati">
						<c:choose>
							<c:when test="${empty risultati and not empty risSize}">
							<div class="alert alert-danger" role="alert">
								  <a href="#" class="alert-link">Oh snap! Change a few things up and try submitting again.</a>
								</div>
								<c:set var="risindex" value="0"/>
							</c:when>
							<c:when test="${empty risultati and empty risSize}">
							<div class="alert alert-info" role="alert">
  <a href="#" class="alert-link">Looking for Tweeps to follow is easy now! Try it!</a>
</div>
							</c:when>
							  <c:otherwise>
							  	<ul>
									<c:forEach begin="0" end="${risindex}" var="index">


										<div class="col-sm-12 col-md-4">
											<div class="panel panel-default panel-card">
												<div class="panel-heading">
													<img
														src="https://maps.googleapis.com/maps/api/staticmap?center=${risultati.getJSONObject(index).get('location')}&size=340x200&key=AIzaSyD5Q0p6ZG4Mb2wHMApIb_LqS6zoBlBPdZw" />
													<button class="btn btn-primary btn-sm" role="button">Follow</button>
												</div>
												<div class="panel-figure">
													<img class="img-responsive img-circle"
														src="${risultati.getJSONObject(index).get('urlimg')}" />
												</div>
												<div class="panel-body text-center">
													<h4 class="panel-header">
														<a href="https://twitter.com/${risultati.getJSONObject(index).get("user")}">@${risultati.getJSONObject(index).get("user")}</a>
													</h4>
													<div class="realname">${risultati.getJSONObject(index).get("name")}</div>
													<p class="tweet">${risultati.getJSONObject(index).get('tweet')}</p>
												</div>
												<div class="panel-thumbnails">
													<div class="row">
														<div class="col-xs-4">
															<div class="thumbnail">
																${risultati.getJSONObject(index).get('age')}</div>
														</div>
														<div class="col-xs-4">
															<div class="thumbnail">
																${risultati.getJSONObject(index).get('gender')}</div>
														</div>
														<div class="col-xs-4">
															<div class="thumbnail">:)</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</c:forEach>
								</ul>
							</c:otherwise>
							</c:choose>
						</div>

					</div>
				</div>

			</div>
		</div>
	</div>

	<footer class="container-fluid">
		<p>Footer Text</p>
	</footer>
	<script>
var imgLoad = imagesLoaded('#risultati');
imgLoad.on( 'progress', function( instance, image ) {
	  var $item = $( image.img ).parent();

	  if (!image.isLoaded){
			    $item.addClass('is-broken');
			    $( image.img ).attr('src', 'http://www.webtus.net/wp-content/uploads/2016/05/Icon-Twitter-300x300.png');
	  }
});
</script>
</body>
</html>