<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url var="loginUrl" value="/login" />

<script>
    function validateLog() {
        var name = document.forms["formLog"]["username"].value;
        var pass = document.forms["formLog"]["password"].value;
        if(name==""){
            swal("Error!","Username is empty.","error");
            return false;
        }else if(pass==""){
            swal("Error!","Password is empty.","error");
            return false;
        }
        return true;
    }
</script>

<div class="row">

    <div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">

        <c:if test="${param.error != null}">
            <div class="login-error">Incorrect username or password.</div>
        </c:if>

        <div class="panel panel-default">

            <div class="panel-heading">
                <div class="panel-title">User Log In</div>
            </div>


            <div class="panel-body">
                <form name="formLog" onsubmit="return validateLog()" method="post" action="${loginUrl}" class="form-horizontal">

                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}" />

                    <div class="form-group">
                        <div class="col-lg-8">
                            <input type="text" name="username" placeholder="Email"
                                   class="form-control" />
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-lg-8">
                            <input type="password" name="password" placeholder="Password"
                                   class="form-control" />
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-lg-8">
                            <button type="submit" class="btn btn-primary">Sign
                                In</button>
                        </div>
                    </div>

                </form>
            </div>
        </div>

    </div>



</div>