<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
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

    <script src="https://npmcdn.com/tether@1.2.4/dist/js/tether.min.js"></script>
            <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>

    <link href="${contextRoot}/css/style.css" rel="stylesheet">

    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${contextRoot}/js/ekko-lightbox.js"></script>
    <script src="${contextRoot}/js/image-upload.js"></script>
    <script src="${contextRoot}/js/datepicker.js"></script>
    <script src="${contextRoot}/js/dropdown.js"></script>

    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

    <!-- Bootstrap Date-Picker Plugin -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>

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
            <a class="navbar-brand" href="/">Yankee</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">

                <sec:authorize access="!isAuthenticated()">
                    <li><a href="${contextRoot}/about">About</a></li>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <li><a href="${contextRoot}/home">Home</a></li>
                    <li><a href="${contextRoot}/profile">Profile</a></li>
                    <li><a href="${contextRoot}/members">Members</a></li>
                    <li><a href="${contextRoot}/clubs">Clubs</a> </li>
                </sec:authorize>

            </ul>
            <ul class="nav navbar-nav navbar-right">

                <sec:authorize access="!isAuthenticated()">
                    <li><a href="${contextRoot}/login">Login</a></li>
                    <li><a href="${contextRoot}/register">Register</a></li>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <li class="button-dropdown">
                        <a href="javascript:void(0)" class="dropdown-toggle">
                            <i class="fa fa-bell" aria-hidden="true"></i>Notifications
                        </a>
                        <ul id = "list_header" class="dropdown-menu">
                        </ul>
                     </li>
                     <li><a href="javascript:$('#logoutForm').submit();">Logout</a></li>
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

<script>
    $.get( "/notifications", function(data) {
        var container = $("#list_header");
        data.forEach(function (item) {
            var asd = item.text;
            var qwe = asd.split("|");
            container.prepend("<li><a href ='" + qwe[0] + "'> " + qwe[1] + "</a></li>")
        })
        console.log(data);
    });
</script>
</body>
</html>