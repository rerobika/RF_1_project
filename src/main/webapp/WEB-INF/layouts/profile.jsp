<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
                                <li><strong>City:</strong> ${profilePerson.location}</li>
                                <li><strong>State:</strong> ${profilePerson.location}</li>
                                <li><strong>Gender:</strong> ${profilePerson.location}</li>
                                <li><strong>DOB:</strong> ${profilePerson.location}</li>
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
                        <h3 class="panel-title">Latest Groups</h3>
                    </div>
                    <div class="panel-body">
                        <div class="group-item">
                            <img src="img/group.png" alt="">
                            <h4><a href="#" class="">Sample Group One</a></h4>
                            <p>This is a paragraph of intro text, or whatever I want to call it.</p>
                        </div>
                        <div class="clearfix"></div>
                        <div class="group-item">
                            <img src="img/group.png" alt="">
                            <h4><a href="#" class="">Sample Group Two</a></h4>
                            <p>This is a paragraph of intro text, or whatever I want to call it.</p>
                        </div>
                        <div class="clearfix"></div>
                        <div class="group-item">
                            <img src="img/group.png" alt="">
                            <h4><a href="#" class="">Sample Group Three</a></h4>
                            <p>This is a paragraph of intro text, or whatever I want to call it.</p>
                        </div>
                        <div class="clearfix"></div>
                        <a href="#" class="btn btn-primary">View All Groups</a>
                    </div>
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