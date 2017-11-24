<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url var="loginUrl" value="/login" />

<script>
    function validateReg() {
        var name = document.forms["formReg"]["user.name"].value;
        if (name == "") {
            swal("Error!","Full name cant be empty.","error");
            return false;
        }
        else if(name.length<5 && name.length>20){
            swal("Error!","Full name must be between 5 and 20 characters.","error");
            return false;
        }
        var email=document.forms["formReg"]["user.email"].value;
        var re = /\S+@\S+\.\S+/;
        if(email==""){
            swal("Error!","Email cant be empty.","error");
            return false;
        }
        if(!re.test(email)){
            swal("Error!","Invalid email format.","error");
            return false;
        }
        var pass=document.forms["formReg"]["user.plainPassword"].value;
        var rePass=document.forms["formReg"]["user.repeatPassword"].value;
        if(pass.length < 5 && pass.length > 20){
            swal("Error!","Password must be between 5 and 20 characters.","error");
            return false;
        }

        if(pass==""){
            swal("Error!","Password cant be empty.","error");
            return false;
        }
        if(pass!==rePass){
            swal("Error!","The passwords don't match.","error");
            return false;
        }
        return true;
    }
</script>

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
                <form:form name="formReg" onsubmit="return validateReg()" method="post" modelAttribute="person" class="form-horizontal">

                    <div class="form-group">
                        <div class="col-lg-8">
                            <form:input type="text" path="user.name" placeholder="Full name"
                                    class="form-control" />
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-lg-8">
                            <form:input type="text" path="user.email" placeholder="Email"
                                    class="form-control" />
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-lg-8">
                            <form:input type="password" path="user.plainPassword" placeholder="Password"
                                    class="form-control" />
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-lg-8">
                            <input type="password"
                                    placeholder="Repeat password" class="form-control" />
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-lg-8">
                            <form:input type="text" path="refID" placeholder="Reference code"
                                    class="form-control" />

                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-lg-8">
                            <button type="submit" class="btn btn-primary">Register</button>
                        </div>
                    </div>

                </form:form>
            </div>
        </div>

    </div>

</div>