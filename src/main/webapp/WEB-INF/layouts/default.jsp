<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
           prefix="sec"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <title><tiles:insertAttribute name="title" /></title>

    <c:set var="contextRoot" value="${pageContext.request.contextPath}" />

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Bootswatch flatly theme -->
    <link rel="stylesheet" href="https://bootswatch.com/flatly/bootstrap.min.css">

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${contextRoot}/js/ekko-lightbox.js"></script>
    <script src="${contextRoot}/js/image-upload.js"></script>

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    <link href="${contextRoot}/css/style.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${contextRoot}/css/ekko-lightbox.css" rel="stylesheet">
    <link href="${contextRoot}/css/font-awesome.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<!-- Static navbar -->
<nav class="navbar navbar-default navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed"
                    data-toggle="collapse" data-target="#navbar" aria-expanded="false"
                    aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span>
            </button>

            <sec:authorize access="!isAuthenticated()">
                <a class="navbar-brand" href="/">Yankee</a>
            </sec:authorize>

            <sec:authorize access="isAuthenticated()">
                <a class="navbar-brand" href="/home">Yankee</a>
            </sec:authorize>

        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">

            </ul>
            <ul class="nav navbar-nav navbar-right">

                <sec:authorize access="!isAuthenticated()">
                    <li><a href="${contextRoot}/login">Login</a></li>
                    <li><a href="${contextRoot}/register">Register</a></li>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <li><a href="${contextRoot}/home">Home</a></li>
                    <li><a href="${contextRoot}/profile">Profile</a></li>
                    <li><a href="javascript:$('#logoutForm').submit();">Logout</a></li>
                </sec:authorize>

                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li class="dropdown"><a href="#" class="dropdown-toggle"
                                            data-toggle="dropdown" role="button" aria-haspopup="true"
                                            aria-expanded="false">Status <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="${contextRoot}/addstatus">Add Status</a></li>
                            <li><a href="${contextRoot}/viewstatus">View Status
                                Updates</a></li>
                        </ul></li>
                </sec:authorize>

            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>
</nav>

<c:url var="logoutLink" value="/logout" />
<form id="logoutForm" method="post" action="${logoutLink}">
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}" />
</form>

<div class="container">
    <tiles:insertAttribute name="content" />
</div>

</body>
</html>