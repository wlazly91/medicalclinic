<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
  <script src="../../assets/js/ie-emulation-modes-warning.js"></script>
</head>

<body>

<sec:authorize access="hasRole('ROLE_USER')">
		<!-- For login user -->
		<c:url value="/logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<script>
			function formSubmitLogout() {
				document.getElementById("logoutForm").submit();
			}
		</script>
		<c:if test="${pageContext.request.userPrincipal.name}">
			<li>
				<a	href="login"> Logout </a>
			</li>	
		</c:if>
</sec:authorize>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span> 
      </button>
      <a class="navbar-brand"> Family Clinic</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li><a href="#"><span class="glyphicon glyphicon-home"></span> Home</a></li>
        <li><a href="clinic"><span class="glyphicon glyphicon-tint"></span> Specialist Clinic</a></li>
        <li><a href="visitHours"><span class="glyphicon glyphicon-eye-open"></span> Specialists</a></li> 
        <li><a href="register"><span class="glyphicon glyphicon-check"></span> Registration</a></li>
        
        <sec:authorize access="hasRole('ROLE_ADMIN')">
        <li class="dropdown">
			<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="true"><span class="glyphicon glyphicon-th"></span> Patient Panel</a>
			<ul class="dropdown-menu" role="menu">
				  <li class="dropdown-header">My Panel</li>
                  <li><a href="#">My Visits</a></li>
                  <li><a href="#">My Health Check</a></li>
                  <li><a href="#">Medical History </a></li>
                  <li><a href="#">Change Password</a></li>
           </ul>
        </li>
        </sec:authorize>
        
        <sec:authorize access="hasRole('ROLE_ADMIN')">
        <li class="dropdown">
			<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="true"><span class="glyphicon glyphicon-cog"> </span>  Admin Options </a>
			<ul class="dropdown-menu" role="menu">
				  <li class="dropdown-header">Visits</li>
                  <li><a href="#">Add Visits</a></li>
                  <li><a href="#">Delete Visits</a></li>
                  <li><a href="#">Modify Visits</a></li>		
                  <li class="divider"></li>
                  <li class="dropdown-header">Users</li>
                  <li><a href="userList">Users</a></li>
                  <li><a href="#">Add User</a></li>
                  <li><a href="#">Delete User</a></li>
                  <li><a href="#">Modify User</a></li>
                  <li><a href="#">Change Password</a></li>
                  <li><a href="#">Permissions User</a></li>
                </ul>
        </li>
        </sec:authorize>
      </ul>
      <ul class="nav navbar-nav navbar-right">	
      
        <li>
        <c:if test="${pageContext.request.userPrincipal.name == name}">
			<a id="myBtn" href="#"><span class="glyphicon glyphicon-log-in"></span> Sign Up </a>
		</c:if>
		</li>	
		
		<li>
        <c:if test="${pageContext.request.userPrincipal.name != null}">
			<a><span class="glyphicon glyphicon-user"></span>  Hello:  ${pageContext.request.userPrincipal.name} </a> 
		</c:if>
		</li>

        <li>
        <c:if test="${pageContext.request.userPrincipal.name != null}">
			<a href="javascript:formSubmitLogout()"><span class="glyphicon glyphicon-log-out"></span> Log Out</a>
		</c:if>
		</li>	
		
		<li>
		<c:if test="${pageContext.request.userPrincipal.name == name}">
			<a href="javascript:showModalWindow()"><span class="glyphicon glyphicon-log-in"></span> Sign In</a>
		</c:if>
		</li>	
	 </ul>
    </div>
  </div>
</nav>


<div class="container">
	<!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Sing In</h4>
        </div>
        <div class="modal-body">
          		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

		<form name='loginForm'
			action="<c:url value='/login' />" method='POST'>
					<div class="form-group">	
					<label><span class="glyphicon glyphicon-user"></span> Username</label>
					<input type='text' name='login' class="form-control" placeholder="Enter username"/>
					</div>
					<div class="form-group">
					<label><span class="glyphicon glyphicon-eye-open"></span> Password</label>
					<input type='password' name='password' class="form-control" placeholder="Enter password"/>
					</div>
				
				<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
			   
        	<div class="modal-footer">
        		<button type="submit" class="btn btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Login</button>
        </div>
        </form>
      </div>
    </div>
  </div>
  
</div>

<script>
function showModalWindow() {
$(document).ready(function(){
        $("#myModal").modal();
    });
}
</script>
</div>
</body>
</html>