<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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
			function formSubmit() {
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
      <a class="navbar-brand" href="#">Family Clinic</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Home</a></li>
        <li><a href="#">Page 1</a></li>
        <li><a href="#">Page 2</a></li> 
        <li><a href="#">Page 3</a></li>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
        <li class="dropdown">
			<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="true">Page 4<span class="caret"></span></a>
			<ul class="dropdown-menu" role="menu">
                  <li><a href="#">Action</a></li>
                  <li><a href="#">Another action</a></li>
                  <li><a href="#">Something else here</a></li>		
                  <li class="divider"></li>
                  <li class="dropdown-header">Nav header</li>
                  <li><a href="#">Separated link</a></li>
                  <li><a href="#">One more separated link</a></li>
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
<div class="container theme-showcase">

      <!-- Main jumbotron for a primary marketing message or call to action -->
      <div class="jumbotron">
        <h1>Theme example</h1>
        <p>
        	<sec:authorize access="hasRole('ROLE_ADMIN')">
        		<p> Jesteś Adminem </p>
        	</sec:authorize>
        	<sec:authorize access="hasRole('ROLE_USER')">
        		<p> Jesteś Userem </p>
        	</sec:authorize>
        </p>
      </div>



    </div>
</body>
</html>
