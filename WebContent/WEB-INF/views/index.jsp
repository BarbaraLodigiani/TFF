<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Twitter Following Finder</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link href="<c:url value="/resources/auth-buttons.css" />" rel="stylesheet">
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
	border: 1px solid #eee;
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

p.speech {
  position: absolute;
  width: 200px;
  height: 50px;
  text-align: center;
  line-height: 50px;
  background-color: #fff;
  -webkit-border-radius: 20px;
  -moz-border-radius: 20px;
  border-radius: 20px;
  -webkit-box-shadow: 2px 2px 0px #333;
  -moz-box-shadow: 2px 2px 0px #333;
  box-shadow: 2px 2px 0px #333;
  top: 100px;
  left:100px;
  color: #333;
}

p.speech:before {
  content: ' ';
  position: absolute;
  width: 0;
  height: 0;
  left: 85px;
  top: -30px;
  border: 15px solid;
  border-color: transparent  #fff #fff transparent;
}

p.tweetsbutton{
	opacity: 0;
	margin-top:6px;
	-webkit-transition: display 400ms ease-in-out;
	transition: display 400ms ease-in-out;
}
.panel.panel-card:hover .tweetsbutton {
opacity:1;
	-webkit-transition: opacity 600ms ease-in-out;
	transition: opacity 600ms ease-in-out;
}

div.twitter-tweet-rendered p.entry-title {
    font-family: Arial, sans-serif !important;
    font-size: 14px !important;
    padding: 20px 10px 10px 10px !important;
    background: #eee !important;
    max-width: 90% !important;
    position:relative;
    text-align:justify;
}

div.twitter-tweet-rendered p.entry-title a.link {
    font-family: Arial, sans-serif !important;
    font-size: 14px !important;
}

div.twitter-tweet-rendered div.twt-border {
    padding: 0 !important;
    border: none !important;
    box-shadow: none !important;
}

div.twitter-tweet-rendered p.entry-title:before{
content:'“';
display:block;
position:absolute;
top:-10px;
left:0px;
font-size:40px;
font-family:Georgia;
color:#555;
}


div.twitter-tweet-rendered p.entry-title:after{
content:'”';

display:block;
position:absolute;
bottom:-30px;
right:0px;
font-size:40px;
font-family:Georgia;
color:#555;
}

</style>


</head>
<body>
<!-- <div id="loading" style="position:absolute; top:0px; left:0px; z-index:100; height:100%; width:100%; background:#5BA8E2" align="center"> -->
<!-- <img src="https://theultralinx.com/.image/MTM3NDI5MTcxNjg0MTIzODE2/twittercelebrationgif.gif" style="margin:0 auto;"/> -->
<!-- </div> -->
	<div class="container-fluid">
		<div class="row content">
			<div class="col-sm-3 sidenav">
				<h4><img class="img-responsive" src="http://i.imgur.com/p8XFYQn.png"/></h4>
								<p class="speech" class="alert-link">
								<c:choose>
									<c:when test="${empty TFFER}">
									<a class="btn-auth btn-twitter" href="/TwitterFollowingFinder/signin">
									    Sign in with <b>Twitter</b>
									</a>
									</c:when>
									<c:otherwise>
									    Welcome <b>${TFFER}</b>!
									</c:otherwise>
								</c:choose>
					</p>
					
				<form:form action="/TwitterFollowingFinder/search" method="POST" commandName="FormRicerca">
					<div class="form-group">
					<div style="display:none">
					<form:input type="text"  class="form-control" id="index"
							path="index" />
							</div>
						<form:errors path="index" cssClass="error" />
					</div>
					<div class="form-group">
						<label for="location">Location:</label>
						<form:input type="text" class="form-control" id="location"
							path="location" placeholder="Insert one Location, such as Los Angeles or California"/>
						<form:errors path="location" cssClass="error" />
					</div>
					<div class="form-group">
						<label for="interests">Interests:</label>
						<form:input type="text" class="form-control" id="interests"
							path="interests" placeholder="Insert one or more Interests" />
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
							path="hashtags" placeholder="Insert one or more Hashtags"/>
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
							<form:option value="">Select an age</form:option>
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
							<form:option value="">Select a gender</form:option>
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
				<c:set var="followingString" value="${TFFERFollowing}"/>
			
				<div class="container-fluid bg-3 text-center">
					<div class="row">
						<div class="col-sm-12" id="risultati">
						<c:choose>
							<c:when test="${empty risultati and not empty risSize}">
							<div class="alert alert-danger" role="alert">
								  <span class="alert-link">Oh snap! Change a few things up and try submitting again.</span>
								</div>
								<c:set var="risindex" value="0"/>
							</c:when>
							<c:when test="${empty risultati and empty risSize}">
							<div class="alert alert-info" role="alert">
  <span class="alert-link">Looking for Tweeps to follow is easy now! Try it!</span>
</div>
							</c:when>
							  <c:otherwise>
							  	<ul>
									<c:forEach begin="${startPage}" end="${endPage}" var="index">

										<c:set var="nameUser" value="${risultati.getJSONObject(index).get('user')}" />
										<div class="col-sm-12 col-md-4">
											<div class="panel panel-default panel-card">
												<div class="panel-heading" data-toggle="tooltip" data-placement="bottom" title="${risultati.getJSONObject(index).get('location')}">
													<img
														src="https://maps.googleapis.com/maps/api/staticmap?center=${risultati.getJSONObject(index).get('location')}&size=340x200&key=AIzaSyD5Q0p6ZG4Mb2wHMApIb_LqS6zoBlBPdZw" />
														<c:set var="nameTweep" value="${risultati.getJSONObject(index).get('name')}"/>
													<c:if test="${not empty TFFER and not TFFER.equals(nameTweep)}">
													<c:choose>
														<c:when test="${fn:contains(followingString, risultati.getJSONObject(index).get('user'))}">
															<button id="unfollow${nameUser}" onclick="unfollow('${nameUser}')"  class="btn btn-danger btn-sm" role="button">Unfollow</button>
															<button id="follow${nameUser}" onclick="follow('${nameUser}')" class="btn btn-primary btn-sm" style="display:none" role="button">Follow</button>
														
														</c:when>
														<c:otherwise>
															<button id="follow${nameUser}" onclick="follow('${nameUser}')" class="btn btn-primary btn-sm" role="button">Follow</button>
															<button id="unfollow${nameUser}" style="display:none" onclick="unfollow('${nameUser}')"  class="btn btn-danger btn-sm" role="button">Unfollow</button>
														</c:otherwise>
													</c:choose>
													</c:if>
												</div>
												<div class="panel-figure">
													<img class="img-responsive img-circle"
														src="${risultati.getJSONObject(index).get('urlimg')}" />
												</div>
												<div class="panel-body text-center">
													<h4 class="panel-header">
														<a href="https://twitter.com/${risultati.getJSONObject(index).get('user')}" target="_blank">@${risultati.getJSONObject(index).get("user")}</a>
													</h4>
													<div class="realname">${risultati.getJSONObject(index).get("name")}</div>
													<p class="tweet">${risultati.getJSONObject(index).get('tweet')}</p>
													<p class="tweetsbutton">
													<button id="tweets${index}" onclick="tweets('${index}','${nameUser}')" class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal" role="button">Show Tweets</button>
													</p>
												</div>
												<div class="panel-thumbnails">
													<div class="row">
														<div class="col-xs-4">
															<div class="thumbnail" data-toggle="tooltip" data-placement="top" title="Estimed Age">
																${risultati.getJSONObject(index).get('age')}</div>
														</div>
														<div class="col-xs-4">
															<div class="thumbnail" data-toggle="tooltip" data-placement="top" title="Estimed Gender">
																${risultati.getJSONObject(index).get('gender')}</div>
														</div>
														<div class="col-xs-4">
														<c:set var="attitude" value="${Math.random() * 10}" />
														<c:choose>
															<c:when test="${attitude < 7}">
															<div class="thumbnail" data-toggle="tooltip" data-placement="top" title="Estimed Attitude">:)</div>
															</c:when>
															<c:otherwise>
															<div class="thumbnail" data-toggle="tooltip" data-placement="top" title="Estimed Attitude">:(</div>
															</c:otherwise>
														</c:choose>
														</div>
													</div>
												</div>
											</div>
										</div>
									</c:forEach>
								</ul>
								<div class="col-sm-12 col-md-12">
								<ul class="pagination">
								<c:forEach begin="1" end="${(risSize + 30 - 1) / 30}" var="pageIndex">
									<c:choose>
									<c:when test="${page eq pageIndex}">
									<li class="active"><a href="/TwitterFollowingFinder/search/${pageIndex}">${pageIndex}</a></li>
									</c:when>
									<c:otherwise>
									<li><a href="/TwitterFollowingFinder/search/${pageIndex}">${pageIndex}</a></li>
									</c:otherwise>
									</c:choose>
								</c:forEach>
								</ul>
								</div>
							</c:otherwise>
							</c:choose>
						</div>

					</div>
				</div>

			</div>
		</div>
	</div>
<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title"><img src="https://cdn2.iconfinder.com/data/icons/neon-line-social-circles/100/Neon_Line_Social_Circles_50Icon_10px_grid-36-32.png" /> Tweets of <span id="nameofTweets"></span></h4>
      </div>
      <div class="modal-body" align="center">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>
	<footer class="container-fluid">
		<p>Footer Text</p>
	</footer>
	<script>
	$(document).ready(function(){
	    $('[data-toggle="tooltip"]').tooltip(); 
// 	    setTimeout(function(){ $('#loading').slideUp(); }, 2000);

	    
	});
	
var imgLoad = imagesLoaded('#risultati');
imgLoad.on( 'progress', function( instance, image ) {
	  var $item = $( image.img ).parent();

	  if (!image.isLoaded){
			    $item.addClass('is-broken');
			    $( image.img ).attr('src', 'http://www.webtus.net/wp-content/uploads/2016/05/Icon-Twitter-300x300.png');
	  }
});


function follow(name){
	$('#follow'+name).prop("disabled",true);
	$.ajax({url : '/TwitterFollowingFinder/follow/'+name+'',method : 'GET',async : false,complete : function(data) {
		console.log(data.responseText);
		if(data.responseText == "true"){
			
			$('#follow'+name).hide();
			$('#follow'+name).prop("disabled",false);

			$('#unfollow'+name).show();
			alert("Successful Following! You are now following this tweep!")

		}else{
			alert("Unsuccessful Following! Something went wrong!");
			$('#follow'+name).prop("disabled",false);

		}
		
	}});
}

function unfollow(name){
	$('#unfollow'+name).prop("disabled",true);
	$.ajax({url : '/TwitterFollowingFinder/unfollow/'+name+'',method : 'GET',async : false,complete : function(data) {
		console.log(data.responseText);
		if(data.responseText == "true"){
			
			$('#unfollow'+name).hide();
			$('#unfollow'+name).prop("disabled",false);

			$('#follow'+name).show();
			alert("Successful Unfollowing! You don't follow this tweep anymore!")

		}else{
			alert("Unsuccessful Unfollowing! Something went wrong!");
			$('#unfollow'+name).prop("disabled",false);

		}
		
	}});
}


function tweets(id, name){
	$('#tweets'+id).prop("disabled",true);
	$('.modal-body').html('<img src="http://www.wallpaperama.com/post-images/forums/200903/07p-6606-loading-photo.gif" />');
	$.ajax({url : '/TwitterFollowingFinder/tweets/'+id+'',method : 'GET',async : false,complete : function(data) {
		if(data.status == 200){
			$('#tweets'+id).prop("disabled",false);
			$('#nameofTweets').text('@'+name);
			$('.modal-body').html('');
			var results = JSON.parse(data.responseText);
			console.log(results.myArrayList);
		      console.log(results.jsonarray);

			   $.each(results.myArrayList, function (index, value) {
				   if(index < 100)
				      $('.modal-body').append('<div class="twitter-tweet-rendered"><p class="entry-title"> '+value+'</p></div>');
			   		});

		}else{
			$('#tweets'+id).prop("disabled",false);
		}
		
	}});
}
</script>
</body>
</html>