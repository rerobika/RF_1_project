<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<section>
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div class="members">
                    <c:if test="${currentPerson.user.id == profilePerson.user.id}">
                        <h1 class="page-header">My friends</h1>
                    </c:if>

                    <c:if test="${currentPerson.user.id != profilePerson.user.id}">
                        <h1 class="page-header">${currentPerson.user.name}'s friends</h1>
                    </c:if><section>
                        <div class="container">
                            <div class="row">
                                <div class="col-md-8">
                                    <div class="members">
                                        <c:if test="${currentPerson.user.id == profilePerson.user.id}">
                                            <h1 class="page-header">My friends</h1>
                                        </c:if>


                                        <c:if test="${not empty friends}">
                        <c:forEach items="${friends}" var="friend" varStatus="status">
                            <div class="row member-row">
                                <div class="col-md-3">
                                    <a href="/profile/${friend.id}"><img src="${friend.profilePicID.location}" class="img-thumbnail" alt="Profile pic"></a>
                                    <div class="text-center">
                                        <a href="/profile/${friend.id}">${friend.user.name}</a>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <p><a href="${pageContext.request.contextPath}/message" class="btn btn-default btn-block"><i class="fa fa-envelope"></i> Send Message</a></p>
                                </div>
                                <div class="col-md-3">
                                    <p><a href="/profile/${friend.id}" class="btn btn-primary btn-block"><i class="fa fa-edit"></i> View Profile</a></p>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</section>
