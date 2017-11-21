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
                    <h1 class="page-header">Friends</h1>
                    <c:if test="${not empty friends}">
                        <c:forEach items="${friends}" var="friend" varStatus="status">
                            <div class="row member-row">
                                <div class="col-md-3">
                                    <a href="profile/${friend.id}"><img src="${friend.profilePicID.location}" class="img-thumbnail" alt="Profile pic"></a>
                                    <div class="text-center">
                                        <a href="profile/${friend.id}">${friend.user.name}</a>
                                    </div>
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
                        <h3 class="panel-title">My Friends</h3>
                    </div>
                    <div class="panel-body">
                        <ul>
                            <li><a href="profile.html" class="thumbnail"><img src="img/user.png" alt=""></a></li>
                            <li><a href="profile.html" class="thumbnail"><img src="img/user.png" alt=""></a></li>
                            <li><a href="profile.html" class="thumbnail"><img src="img/user.png" alt=""></a></li>
                            <li><a href="profile.html" class="thumbnail"><img src="img/user.png" alt=""></a></li>
                            <li><a href="profile.html" class="thumbnail"><img src="img/user.png" alt=""></a></li>
                            <li><a href="profile.html" class="thumbnail"><img src="img/user.png" alt=""></a></li>
                            <li><a href="profile.html" class="thumbnail"><img src="img/user.png" alt=""></a></li>
                            <li><a href="profile.html" class="thumbnail"><img src="img/user.png" alt=""></a></li>
                            <li><a href="profile.html" class="thumbnail"><img src="img/user.png" alt=""></a></li>
                        </ul>
                        <div class="clearfix"></div>
                        <a class="btn btn-primary" href="#">View All Friends</a>
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