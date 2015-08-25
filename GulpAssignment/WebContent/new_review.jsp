<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div class="container">
  <h2>Add a new review</h2>
  <form class="form-horizontal" role="form" method="post" action="AddReview">
    
    <input type="hidden" name="user_id" value="${user_id}">
     <input type="hidden" name="restaurant_name" value="${restaurant_name}">
     <div class="form-group">
      <label class="control-label col-sm-2">Rating: </label>
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
    
<label for="description">Comments: </label><br>
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