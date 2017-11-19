<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url var="loginUrl" value="/login" />

<div class="row">

    <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">

        <div class="login-error ">
            <form:errors path="user.*" />
        </div>

        <div class="panel panel-default">

            <div class="panel-heading">
                <div class="panel-title">Create an Account</div>
            </div>


            <div class="panel-body">
                <form:form method="post" modelAttribute="person" class="login-form">

                    <div class="input-group">
                        <form:input type="text" path="user.name" placeholder="Full name"
                                    class="form-control" />
                    </div>

                    <div class="input-group">
                        <form:input type="text" path="user.email" placeholder="Email"
                                    class="form-control" />
                    </div>

                    <div class="input-group">
                        <form:input type="password" path="user.plainPassword" placeholder="Password"
                                    class="form-control" />
                    </div>

                    <div class="input-group">
                        <form:input type="password" path="user.repeatPassword"
                                    placeholder="Repeat password" class="form-control" />
                    </div>

                    <div class="input-group">
                        <form:input type="text" path="refID" placeholder="Reference code"
                                    class="form-control" />

                    </div>

                    <div class="input-group">
                        <button type="submit" class="btn-primary pull-right">Register</button>
                    </div>

                </form:form>
            </div>
        </div>

    </div>

</div>