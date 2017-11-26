<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<section>
    <c:if test="${club.owner.id == profilePerson.id}">
            <div class="panel-body">
            <form:form method="post" modelAttribute="postInfo" class="post-form" enctype="multipart/form-data">
                <div class="form-group">
                    <form:input type="text" path="text" placeholder="Write on the wall" class="form-control" />
                    <form:hidden path="from"  value = "${club.user.id}" class="post-control"/>
                    <form:hidden path="to"  value = "${club.user.id}" class="post-control"/>
                    <form:hidden path="likeNumber"  value = "0" class="post-control"/>
                </div>
                <div class="row">

                        <button type="submit" class="btn btn-default col-lg-2"  name="sendclubpost" >Submit</button>

                            <span class="col-lg-2" style="float:right;">
                                <span class="btn btn-default btn-file">
                                    Browse… <input type="file" accept="image/png, image/jpeg, image/gif" id="imgInp" name="file" />
                                </span>
                            </span>
                            <input type="text" class="form-control col-lg-8" readonly style="float:right; width: 65%!important; display:inline;">

                        <img id='img-upload'/>

                </div>
            </form:form>
        </div>
    </c:if>
        <c:if test="${profilePersonIsMember == true}">
            <c:if test="${not empty posts}">
                <c:forEach items="${posts}" var="posted" varStatus="status">
                    <div class="panel panel-default post">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-sm-10">
                                    <div class="bubble">
                                        <div class="pointer">
                                            <div class="pull-right">${posted.date}</div>
                                            <hr>
                                            <c:forTokens items="${posted.text}" delims=" " var="mySplit">
                                                <c:choose>
                                                    <c:when test="${fn:startsWith(mySplit, '&')}">
                                                        <img src="${fn:substring(mySplit, 1, fn:length(mySplit))}" class="img-thumbnail" alt="post_pic">
                                                    </c:when>
                                                    <c:when test="${fn:startsWith(mySplit, 'http')}">
                                                        <img src="${mySplit}" class="img-thumbnail" alt="post_pic">
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:out value="${mySplit} "/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forTokens>
                                        </div>
                                        <div class="pointer-border"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </c:if>
</section>