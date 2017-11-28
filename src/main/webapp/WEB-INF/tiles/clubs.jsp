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
                <div><a href="createClub" class="btn btn-primary button_pull_right">Create Club</a> </div>
                <div class="members">
                    <h2 class="page-header">Clubs</h2>

                        <c:if test="${not empty clubs}">
                            <c:forEach items="${clubs}" var="club" varStatus="status">
                                <div class="row member-row">
                                    <div class="col-md-3">
                                        <div class="text-center">
                                            <a href="club/${club.id}">${club.user.name}</a>
                                        </div>
                                    </div>
                                        <c:if test="${profilePersonIsMember[club.id] != true}">
                                            <div class="col-md-3">
                                                <form:form method="post">
                                                    <input type="hidden" name="club_id"
                                                           value="${club.id}" />
                                                    <button type="submit" class="btn btn-primary button_pull_right" name="join">Join</button>
                                                </form:form>
                                            </div>
                                        </c:if>
                                </div>
                            </c:forEach>
                        </c:if>
                </div>
            </div>
            <div class="col-md-4">
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
                        <a class="btn btn-primary" href="/friends/${profilePerson.user.id}">View All Friends</a>
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