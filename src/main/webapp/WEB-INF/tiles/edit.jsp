<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="container">
    <h1>Edit Profile</h1>
    <hr>
    <div class="row">
        <!-- left column -->
        <div class="col-md-3">
            <div class="text-center">
                <img src="//placehold.it/100" class="avatar img-circle" alt="avatar">
                <h6>Upload a different photo...</h6>

                <input type="file" class="form-control">
            </div>
        </div>

        <!-- edit form column -->
        <div class="col-md-9 personal-info">
            <h3>Personal info</h3>
            <form:form method="post" modelAttribute="person" class="form-horizontal" role="form">
                <div class="form-group">
                    <label class="col-lg-3 control-label">Name:</label>
                    <div class="col-lg-8">
                        <form:input class="form-control" path="user.name" type="text" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">Job:</label>
                    <div class="col-lg-8">
                        <form:input class="form-control" type="text" path="job" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">School:</label>
                    <div class="col-lg-8">
                        <form:input class="form-control" type="text" path="school"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">Hobby:</label>
                    <div class="col-lg-8">
                        <form:input class="form-control" type="text" path="hobby"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">Location:</label>
                    <div class="col-lg-8">
                        <form:input class="form-control" type="text" path="location"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">Date of birth:</label>
                    <div class="col-md-8">
                        <div class="form-group">
                            <form:input class="form-control" id="date" name="date" placeholder="YYYY-MM-DD" type="text" path="birth"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">Password:</label>
                    <div class="col-md-8">
                        <form:input class="form-control" type="password" placeholder="New password" path="user.plainPassword" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">Confirm password:</label>
                    <div class="col-md-8">
                        <form:input class="form-control" type="password" placeholder="New password confirm" path="user.repeatPassword" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label"></label>
                    <div class="col-md-8">
                        <input type="submit" class="btn btn-primary" value="Save Changes">
                        <span></span>
                        <input type="reset" class="btn btn-default" value="Cancel">
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>