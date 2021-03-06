<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Review</title>
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="index.html">Gulp</a>
    </div>
    <div>
      <ul class="nav navbar-nav">
         <li><a href="GetRestaurantList">Restaurant List</a></li>
        <li><a href="SignUp.html">Sign up</a></li>
        <li><a href="SignIn.html">Sign In</a></li>
          <li><a href="GetMyReview">My Profile</a></li>
           <li><a href="UpdateRestaurant.html">Update Restaurant</a></li>
        
        <li><a href="EditProfile">Edit Profile</a></li>
      </ul>
    </div>
  </div>
</nav>
<%! public String rest_name=""; %>
<div class="container">
  <h2>Update Review</h2>
      <% rest_name = request.getParameter("restaurant_name");%>
  <form class="form-horizontal" role="form" method="get" action="UpdateReview">
    <input type="hidden" name="user_id" value="${user_id}">
     <input type="hidden" name="restaurant_name" value=<%=rest_name%>>
 
     <div class="form-group">
      <label class="control-label col-sm-2">New Rating: </label>
      <div class="col-sm-10">
					<select id="type" name="rating" REQUIRED>
						<option value="0">0</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
					</select> 
      </div>
    </div>
    
<label for="description">New Comments: </label><br>
<textarea rows="4" cols="50" name="comments">
</textarea>
<br><br>
    
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <input type="submit" value="Submit" id="submit">
      </div>
    </div>
  </form>
</div>




</body>
</html>