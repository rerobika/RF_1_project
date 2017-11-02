<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<section>
        <div class="panel-body">
            <form:form method="post" modelAttribute="postInfo" class="post-form" enctype="multipart/form-data">
                <div class="form-group">
                    <form:input type="text" path="text" placeholder="Write on the wall" class="form-control" />
                    <form:hidden path="from"  value = "${currentPerson.user.id}" class="post-control"/>
                    <form:hidden path="to"  value = "${profilePerson.user.id}" class="post-control"/>
                    <form:hidden path="likeNumber"  value = "0" class="post-control"/>
                </div>
                <div class="pull-right">
                    <div class="btn-toolbar">
                        <button type="submit" class="btn btn-default"  name="sendmypost" >Submit</button>
                        <div class="row">
                            <div class="col-xs-12 col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2" id="image-upload-container">
                                <div class="input-group image-preview">
                                    <input type="text" class="form-control image-preview-filename" disabled="disabled">
                                    <span class="input-group-btn">
                                            <button type="button" class="btn btn-default image-preview-clear" style="display:none;">
                                                <span class="glyphicon glyphicon-remove"></span> Clear
                                            </button>
                                        <div class="btn btn-default image-preview-input">
                                            <span class="glyphicon glyphicon-folder-open"></span>
                                            <span class="image-preview-input-title">Browse</span>
                                            <input type="file" accept="image/png, image/jpeg, image/gif" name="input-file-preview"/>
                                        </div>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form:form>
        </div>

        <c:if test="${not empty posts}">
            <c:forEach items="${posts}" var="posted">
                <div class="panel panel-default post">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-sm-2">
                                <a href="profile.html" class="post-avatar thumbnail"><img src="img/user.png" alt="Profile picture"><div class="text-center">${posted.from.name}</div></a> <!-- TODO: FIX img src-->
                                <div class="likes text-center">${posted.likeNumber}</div> <!-- TODO: FIX like number-->
                            </div>
                            <div class="col-sm-10">
                                <div class="bubble">
                                    <div class="pointer">
                                        <c:forTokens items="${posted.text}" delims=" " var="mySplit">
                                            <c:choose>
                                                <c:when test="${fn:startsWith(mySplit, '&')}">
                                                    <img src="${fn:substring(mySplit, 1, fn:length(mySplit))}" class="img-thumbnail" alt="post_pic">
                                                </c:when>
                                                <c:when test="${fn:startsWith(mySplit, 'http')}">
                                                    <img src="${fn:substring(mySplit, 1, fn:length(mySplit))}" class="img-thumbnail" alt="post_pic">
                                                </c:when>
                                                <c:otherwise>
                                                    <c:out value="${mySplit} "/>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forTokens>
                                    </div>
                                    <div class="pointer-border"></div>
                                </div>
                                <p class="post-actions"><a href="#">Like</a>
                                <div class="comment-form">
                                    <form:form method="post" modelAttribute="postInfo" class="form-inline" enctype="multipart/form-data">
                                        <div class="form-group">
                                            <form:hidden path="from"  value="${currentPerson.user.id}" class="post-control"/>
                                            <form:hidden path="to"  value="${posted.from.id}" class="post-control"/>
                                            <form:hidden path="parent" value="${posted.id}" class="post-control"/>
                                            <form:hidden path="likeNumber" value="0" class="post-control"/>
                                            <form:input type="text" path="text" class="form-control" placeholder="enter comment"/>
                                        </div>
                                        <button type="submit" class="btn btn-default" name="sendmypost" >Add</button>
                                    </form:form>
                                </div>
                                <div class="clearfix"></div>
                                <div class="comments">
                                    <c:if test="${not empty comments}">
                                        <c:forEach items="${comments}" var="comment">
                                            <c:if test="${comment.parent.id == posted.id}">
                                                <div class="clearfix"></div>
                                                <div class="comment">
                                                    <a href="#" class="comment-avatar pull-left"><img src="img/user.png" alt="Commenter profile pic"></a> <!-- TODO: FIX img src-->
                                                    <div class="comment-text">
                                                        <p>${comment.text}</p>
                                                    </div>
                                                </div>
                                                <div class="clearfix"></div>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:if>
</section>
