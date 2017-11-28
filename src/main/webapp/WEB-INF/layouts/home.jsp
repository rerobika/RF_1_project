<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<section>
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Wall</h3>
                    </div>
                    <tiles:insertAttribute name="posting" />
                </div>
            </div>
            <div class="col-md-4">
                <c:if test="${not empty friendsWhoseHaveBirthday}">
                <div class="panel panel-default friends">
                    <div class="panel-heading">
                        <h3 class="panel-title">Friends who have birthday</h3>
                    </div>
                    <div class="panel-body">
                        <ul>
                                <c:forEach items="${friendsWhoseHaveBirthday}" var="friend" varStatus="status">
                                    <li><a href="/profile/${friend.user.id}" class="thumbnail"><img src="${friend.profilePicID.location}" alt="friend_profile_picture"></a></li>
                                </c:forEach>
                        </ul>
                        <div class="clearfix"></div>
                    </div>
                </div>
                </c:if>
                <div class="panel panel-default friends">
                    <div class="panel-heading">
                        <h3 class="panel-title">My Friends</h3>
                    </div>
                    <div class="panel-body">
                        <ul>
                            <c:if test="${not empty friends}">
                                <c:forEach items="${friends}" var="friend" varStatus="status">
                                    <li><a href="/profile/${friend.user.id}" class="thumbnail"><img src="${friend.profilePicID.location}" alt="friend_profile_picture"></a></li>
                                </c:forEach>
                            </c:if>
                        </ul>
                        <div class="clearfix"></div>
                        <a class="btn btn-primary" href="friends/${profilePerson.user.id}">View All Friends</a>
                    </div>
                </div>
                <div class="panel panel-default groups">
                    <div class="panel-heading">
                        <h3 class="panel-title">My Clubs</h3>
                    </div>
                    <div class="panel-body">
                        <div class="group-item">
                            <c:if test="${not empty smallClubList}">
                                <c:forEach items="${smallClubList}" var="club" varStatus="status">
                                    <h4><a href="club/${club.id}" class="thumbnail">${club.user.name}</a></h4>
                                </c:forEach>
                            </c:if>
                        </div>
                        <div class="clearfix"></div>
                        <a href="/clubs" class="btn btn-primary">View All Clubs</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
