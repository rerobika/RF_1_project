<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:if test="${passwordUpdateFail}">
    <script>
        swal("Error!", "Old and new password don't match!", "error");
    </script>
</c:if>

<div class="container">
    <h1>Edit Profile</h1>
    <hr>
    <div class="row">
        <!-- left column -->
        <form:form method="post" class="form-horizontal" role="form" enctype="multipart/form-data">
            <div class="col-md-3">
                <div class="text-center" >
                    <img src="${profilePerson.profilePicID.location}" id="edit_profile_pic" class="avatar" alt="profile picture">
                    <h6>Upload a different photo...</h6>

                    <input type="file" accept="image/png, image/jpeg, image/gif" id="imgInpEdit" name="file" class="form-control"/>
                </div>
            </div>

        <!-- edit form column -->
        <div class="col-md-9 personal-info">
            <h3>Personal info</h3>
                <div class="form-group">
                    <label class="col-lg-3 control-label">Name:</label>
                    <div class="col-lg-8">
                        <input class="form-control" name="name" type="text" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">Job:</label>
                    <div class="col-lg-8">
                        <input class="form-control" type="text" name="job" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">School:</label>
                    <div class="col-lg-8">
                        <input class="form-control" type="text" name="school"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">Hobby:</label>
                    <div class="col-lg-8">
                        <input class="form-control" type="text" name="hobby"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">Location:</label>
                    <div class="col-lg-8">
                        <input class="form-control" type="text" name="location"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">Date of birth:</label>
                    <div class="col-md-8">
                        <div class="form-group">
                            <input class="form-control" id="date" name="date" placeholder="YYYY-MM-DD" type="text" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">Old Password:</label>
                    <div class="col-md-8">
                        <input class="form-control" type="password" placeholder="Old password" name="plainPasswordOld" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">New Password:</label>
                    <div class="col-md-8">
                        <input class="form-control" type="password" placeholder="New password" name="plainPassword" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label">Confirm password:</label>
                    <div class="col-md-8">
                        <input class="form-control" type="password" placeholder="New password confirm" name="plainPasswordConfirm" />
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