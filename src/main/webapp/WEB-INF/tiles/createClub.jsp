<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>


<section>
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div class="profile">
                    <h1>
                        Create a club
                    </h1>
                    <div class="row">
                        <div class="col-md-8">
                           <form:form method="post" enctype="multipart/form-data" modelAttribute="club">
                               <div class="form-group">
                                   <label class="col-lg-3 control-label">Name:</label>
                                   <div class="col-lg-8">
                                       <form:input type="text" path="user.name" placeholder="Club name"
                                                   class="form-control" />
                                   </div>
                               </div>
                               <div class="form-group">
                                   <label class="col-lg-3 control-label">Description:</label>
                                   <div class="col-lg-8">
                                       <form:input type="text" path="description" placeholder="Description"
                                                   class="form-control" />
                                   </div>
                               </div>
                               <div class="form-group">
                                   <label class="col-md-3 control-label"></label>
                                   <div class="col-md-8">
                                       <input type="submit" class="btn btn-primary" value="Create">
                                       <span></span>
                                       <input type="reset" class="btn btn-default" value="Cancel">
                                   </div>
                               </div>
                           </form:form>
                        </div>
                    </div><br><br>
                </div>
            </div>
        </div>
    </div>
</section>

<footer>
    <div class="container">
        <p>RF I project, Social network, 2017</p>
    </div>
</footer>