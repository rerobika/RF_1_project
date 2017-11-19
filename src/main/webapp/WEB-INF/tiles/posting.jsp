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
                <div class="row">

                        <button type="submit" class="btn btn-default col-lg-2"  name="sendmypost" >Submit</button>

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

        <c:if test="${not empty posts}">
            <c:forEach items="${posts}" var="posted">
                <div class="panel panel-default post">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-sm-2">
                                <a href="profile/${posted.id}}" class="post-avatar thumbnail"><img src="${profilePerson.profilePicID.location}" alt="Profile picture"><div class="text-center">${posted.from.name}</div></a> <!-- TODO: FIX img src-->
                                <div class="likes text-center">${posted.likeNumber}</div> <!-- TODO: FIX like number-->
                            </div>
                            <div class="col-sm-10">
                                <div class="bubble">
                                    <div class="pointer">
                                        <c:if test="${currentPerson.user.id == profilePerson.user.id}">
                                            <p><a href="${currentPerson.user.id}"> ${currentPerson.user.name}</a></p>
                                        </c:if>
                                        <c:if test="${currentPerson.user.id != profilePerson.user.id}">
                                            <p><a href="${currentPerson.user.id}"> ${currentPerson.user.name}</a> -> <a href="${profilePerson.user.id}"> ${profilePerson.user.name}</a></p>
                                        </c:if>
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
                                                    <a href="#" class="comment-avatar pull-left"><img src="${profilePerson.profilePicID.location}" alt="Commenter profile pic"></a> <!-- TODO: FIX img src-->
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
