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
	


					<button type="submit" onclick="crawling()" class="btn btn-default">Avvia Crawling Default</button>


			</div>

			<div class="col-sm-9" id="responses">


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
 	    setTimeout(function(){ $('#loading').slideUp(); }, 2000);

	    
	});
	
	function crawling(){
		$('#responses').html("Crawling is running");
		setInterval(readfile() , 500);


		
		$.ajax({url : '/TwitterFollowingFinder/admin/crawling',method : 'GET',async : true,complete : function(data) {
			if(data.status == 200){ 
				$('#responses').html("Crawling is successfully finished!");
			}
			
		}
	});
	}
	
	
	function readfile()
	{
		var txtFile = new XMLHttpRequest();
		txtFile.open("GET", "<c:url value="/resources/crawling.txt" />", true);
		txtFile.onreadystatechange = function() {
		  if (txtFile.readyState === 4) {  // Makes sure the document is ready to parse.
		    if (txtFile.status === 200) {  // Makes sure it's found the file.
		      allText = txtFile.responseText; 
		      lines = txtFile.responseText.split("\n"); // Will separate each line into an array
				$('#responses').append("<br>"+lines);

		    }
		  }
		}
		txtFile.send(null);
	}
</script>
</body>
</html>