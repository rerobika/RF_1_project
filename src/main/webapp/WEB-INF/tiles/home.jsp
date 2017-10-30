<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>

<section>
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Wall</h3>
                    </div>
                    <div class="panel-body">
                        <form:form method="post" modelAttribute="postInfo" class="post-form">
                            <form:hidden path="from"  class="post-control"/>
                            <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                            <div class="form-group">
                                <form:input type="text" path="text" placeholder="Write on the wall" class="form-control" />
                            </div>
                            <div class="pull-right">
                                <div class="btn-toolbar">
                                    <button type="submit" class="btn btn-default">Submit</button>
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
                </div>
                <c:if test="${not empty posts}">
                    <c:forEach items="${posts}" var="posted">
                        <div class="panel panel-default post">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-sm-2">
                                        <a href="profile.html" class="post-avatar thumbnail"><img src="img/user.png" alt="Profile picture"><div class="text-center">Full name</div></a> <!-- TODO: FIX img src-->
                                        <div class="likes text-center">123 likes</div> <!-- TODO: FIX like number-->
                                    </div>
                                    <div class="col-sm-10">
                                        <div class="bubble">
                                            <div class="pointer">
                                                <p>${posted.text}</p>
                                            </div>
                                            <div class="pointer-border"></div>
                                        </div>
                                        <p class="post-actions"><a href="#">Like</a>
                                        <div class="comment-form">
                                            <form class="form-inline">
                                                <div class="form-group">
                                                    <input type="text" class="form-control" placeholder="enter comment">
                                                </div>
                                                <button type="submit" class="btn btn-default">Add</button>
                                            </form>
                                        </div>
                                        <div class="clearfix"></div>
                                        <div class="comments">
                                            <div class="comment">
                                                <a href="#" class="comment-avatar pull-left"><img src="img/user.png" alt="Commenter profile pic"></a> <!-- TODO: FIX img src-->
                                                <div class="comment-text">
                                                    <p>First comment goes here.</p>
                                                </div>
                                            </div>
                                            <div class="clearfix"></div>
                                            <div class="comment">
                                                <a href="#" class="comment-avatar pull-left"><img src="img/user.png" alt="Commenter profile pic"></a> <!-- TODO: FIX img src-->
                                                <div class="comment-text">
                                                    <p>Second comment goes here</p>
                                                </div>
                                            </div>
                                            <div class="clearfix"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
            <div class="col-md-4">
                <div class="panel panel-default friends">
                    <div class="panel-heading">
                        <h3 class="panel-title">My Friends</h3>
                    </div>
                    <div class="panel-body">
                        <ul>
                            <li><a href="profile.html" class="thumbnail"><img src="img/user.png" alt="frined's profile picture"></a></li> <!-- TODO: FIX img src-->
                            <li><a href="profile.html" class="thumbnail"><img src="img/user.png" alt="frined's profile picture"></a></li> <!-- TODO: FIX img src-->
                            <li><a href="profile.html" class="thumbnail"><img src="img/user.png" alt="frined's profile picture"></a></li> <!-- TODO: FIX img src-->
                            <li><a href="profile.html" class="thumbnail"><img src="img/user.png" alt="frined's profile picture"></a></li> <!-- TODO: FIX img src-->
                            <li><a href="profile.html" class="thumbnail"><img src="img/user.png" alt="frined's profile picture"></a></li> <!-- TODO: FIX img src-->
                            <li><a href="profile.html" class="thumbnail"><img src="img/user.png" alt="frined's profile picture"></a></li> <!-- TODO: FIX img src-->
                        </ul>
                        <div class="clearfix"></div>
                        <a class="btn btn-primary" href="#">View All Friends</a>
                    </div>
                </div>
                <div class="panel panel-default groups">
                    <div class="panel-heading">
                        <h3 class="panel-title">My Groups</h3>
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