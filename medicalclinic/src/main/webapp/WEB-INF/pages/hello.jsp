<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
  <script src="../../assets/js/ie-emulation-modes-warning.js"></script>
  		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>
</head>
<body>

<sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_NURSE')">		
		<c:url value="/logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
		</form>
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
        <li><a href="#"><span class="glyphicon glyphicon-tint"></span> Specialist Clinic</a></li>
        <li><a href="#"><span class="glyphicon glyphicon-eye-open"></span> Specialists</a></li> 
        <li><a href="#"><span class="glyphicon glyphicon-check"></span> Registration</a></li>
        <sec:authorize access="hasRole('ROLE_USER')">
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
			<a href="#"><span class="glyphicon glyphicon-log-in"></span> Sign Up </a>
		</c:if>
		</li>	
		
		<li>
        <c:if test="${pageContext.request.userPrincipal.name != null}">
			<a><span class="glyphicon glyphicon-user"></span>  Hello:  ${pageContext.request.userPrincipal.name} </a> 
		</c:if>
		</li>

        <li>
        <c:if test="${pageContext.request.userPrincipal.name != null}">
			<a href="javascript:formSubmit()"><span class="glyphicon glyphicon-log-out"></span> Log Out</a>
		</c:if>
		</li>	
		
		<li>
		<c:if test="${pageContext.request.userPrincipal.name == name}">
			<a href="login"><span class="glyphicon glyphicon-log-in"></span> Sign In</a>
		</c:if>
		</li>	
	 </ul>
    </div>
  </div>
</nav>
<div class="container theme-showcase" role = "main">      
	
</div>
</body>
</html>
