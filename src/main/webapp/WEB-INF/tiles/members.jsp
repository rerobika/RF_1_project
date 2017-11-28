<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:if test="${successMark}">
    <script>
        swal("Succes!", "Friend request sent!", "success");
    </script>
</c:if>
<c:if test="${successConfirm}">
    <script>
        swal("Succes!", "Friend confirmed!", "success");
    </script>
</c:if>


<section>
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div class="members">
                    <h2 class="page-header">Pending friends</h2>
                    <form:form method="post">
                        <c:if test="${not empty pending_friends}">
                            <c:forEach items="${pending_friends}" var="friend" varStatus="status">
                                <div class="row member-row">
                                    <div class="col-md-3">
                                        <a href="profile/${friend.id}"><img src="${friend.profilePicID.location}" class="img-thumbnail" alt="Profile pic"></a>
                                        <div class="text-center">
                                            <a href="profile/${friend.id}">${friend.user.name}</a>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <button type="submit" class="btn btn-default btn-block pull_down" name="confirm">Confirm friend request</button>
                                        <input type="hidden" name="id" value="${friend.id}">
                                    </div>
                                    <div class="col-md-3">
                                        <p><a href="#" class="btn btn-default btn-block"><i class="fa fa-envelope"></i> Send Message</a></p>
                                    </div>
                                    <div class="col-md-3">
                                        <p><a href="profile/${friend.id}" class="btn btn-primary btn-block"><i class="fa fa-edit"></i> View Profile</a></p>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                    </form:form>
                    <h2 class="page-header">People you may know</h2>
                        <c:if test="${not empty non_friends}">
                            <c:forEach items="${non_friends}" var="friend" varStatus="status">
                                <div class="row member-row">
                                    <div class="col-md-3">
                                        <a href="profile/${friend.id}"><img src="${friend.profilePicID.location}" class="img-thumbnail" alt="Profile pic"></a>
                                        <div class="text-center">
                                            <a href="profile/${friend.id}">${friend.user.name}</a>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <form:form method="post">
                                            <button type="submit" class="btn btn-default btn-block pull_down" name="mark">Mark as friend</button>
                                            <input type="hidden" name="id" value="${friend.id}">
                                            <div style="clear:both;"></div>
                                        </form:form>
                                    </div>
                                    <div class="col-md-3">
                                        <p><a href="#" class="btn btn-default btn-block"><i class="fa fa-envelope"></i> Send Message</a></p>
                                    </div>
                                    <div class="col-md-3">
                                        <p><a href="profile/${friend.id}" class="btn btn-primary btn-block"><i class="fa fa-edit"></i> View Profile</a></p>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                </div>
            </div>
            <div class="col-md-4">
                <div class="panel panel-default friends">
                    <div class="panel-heading">
                        <h3 class="panel-title">Friends</h3>
                    </div>
                    <div class="panel-body">
                        <ul>
                            <c:if test="${not empty real_friends}">
                                <c:forEach items="${real_friends}" var="friend" varStatus="status">
                                    <li><a href="/profile/${friend.id}" class="thumbnail"><img src="${friend.profilePicID.location}" alt="friend_profile_picture"></a></li>
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
</section>

<footer>
    <div class="container">
        <p>RF I project, Social network, 2017</p>
    </div>
</footer>