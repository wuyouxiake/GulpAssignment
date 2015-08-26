<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
        
      </ul>
    </div>
  </div>
</nav>
<table>
<thead>
<th>Name</th>
<th>Rating</th>
</thead>
<tbody>
${content}
</tbody>

</table>
</body>
</html>