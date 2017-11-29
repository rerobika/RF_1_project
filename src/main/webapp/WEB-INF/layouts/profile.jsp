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
                    <form:form method="post">
                        <h1 class="page-header">${profilePerson.user.name}
                            <c:if test="${profilePerson.user.id == currentPerson.user.id}">
                                <button type="button" class="btn btn-primary button_pull_right" onclick="location.href='/profile/${profilePerson.user.id }/edit';">Edit profile</button>
                            </c:if>
                            <c:if test="${profilePerson.user.id != currentPerson.user.id && relation_status == -1}">
                                <button type="submit" class="btn btn-primary button_pull_right" name="mark">Mark as friend</button>
                                <div style="clear:both;"></div>
                            </c:if>
                            <c:if test="${profilePerson.user.id != currentPerson.user.id && relation.from == currentPerson && relation.to == profilePerson && relation_status == 0}">
                                <button type="button" class="btn btn-primary button_pull_right" disabled>Friend request sent</button>
                                <div style="clear:both;"></div>
                            </c:if>
                            <c:if test="${profilePerson.user.id != currentPerson.user.id && relation.from == profilePerson && relation.to == currentPerson && relation_status == 0}">
                                <button type="submit" class="btn btn-primary button_pull_right" name="confirm">Confirm friend request</button>
                                <div style="clear:both;"></div>
                            </c:if>
                            <c:if test="${profilePerson.user.id != currentPerson.user.id && relation_status == 1}">
                                <button type="button" class="btn btn-primary button_pull_right" disabled>Already friends</button>
                                <div style="clear:both;"></div>
                            </c:if>

                        </h1>
                    </form:form>
                    <div class="row">
                        <div class="col-md-4">
                            <img src="${profilePerson.profilePicID.location}" class="img-thumbnail" alt="Profile picture">
                        </div>
                        <div class="col-md-8">
                            <ul>
                                <li><strong>Email:</strong> ${profilePerson.user.email}</li>
                                <li><strong>Location:</strong> ${profilePerson.location.name}</li>
                                <li><strong>Job:</strong> ${profilePerson.job.name}</li>
                                <li><strong>School:</strong> ${profilePerson.school.name}</li>
                                <li><strong>Hobby:</strong> ${profilePerson.hobby.name}</li>
                                <li><strong>DOB:</strong> ${fn:substring(profilePerson.birth, 0, 10)}</li>
                                <li><strong>Invited by:</strong> <a href="/profile/${profilePerson.refID.id}"> ${profilePerson.refID.name} </a></li>
                                <li><strong>Reference code :</strong> ${invite_token.token} </a></li>
                            </ul>
                        </div>
                    </div><br><br>
                    <tiles:insertAttribute name="posting" />
                </div>
            </div>
            <div class="col-md-4">
                <div class="panel panel-default friends">
                    <div class="panel-heading">
                        <h3 class="panel-title">Friends</h3>
                    </div>
                    <div class="panel-body">
                        <ul>
                            <c:if test="${not empty friends}">
                                <c:forEach items="${friends}" var="friend" varStatus="status">
                                    <li><a href="${friend.id}" class="thumbnail"><img src="${friend.profilePicID.location}" alt="friend_profile_picture"></a></li>
                                </c:forEach>
                            </c:if>
                        </ul>
                        <div class="clearfix"></div>
                        <a class="btn btn-primary" href="/friends/${profilePerson.user.id}">View All Friends</a>
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
    </div>
</section>
