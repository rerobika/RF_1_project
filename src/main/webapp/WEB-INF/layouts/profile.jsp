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
                    <h1 class="page-header">${profilePerson.user.name}</h1>
                    <div class="row">
                        <div class="col-md-4">
                            <img src="${profilePerson.profilePicID.location}" class="img-thumbnail" alt="Profile picture">
                        </div>
                        <div class="col-md-8">
                            <ul>
                                <li><strong>Name:</strong> ${profilePerson.user.name}</li>
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