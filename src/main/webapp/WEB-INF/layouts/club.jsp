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
                        <h1 class="page-header">${club.user.name}
                        </h1>
                    <div class="row">
                        <div class="col-md-8">
                            <ul>
                                <li><strong>Owner:</strong> ${club.owner.user.name}</li>
                                <li><strong>Description:</strong> ${club.description}</li>
                            </ul>
                        </div>
                        <c:if test="${profilePersonIsMember != true}">
                            <div>
                                <form:form method="post">
                                    <button type="submit" class="btn btn-primary button_pull_right" name="join">Join</button>
                                </form:form>
                            </div>
                        </c:if>
                    </div><br><br>
                    <tiles:insertAttribute name="clubPosting" />
                </div>
            </div>


            <div class="col-md-4">
                <div class="panel panel-default friends">
                    <div class="panel-heading">
                        <h3 class="panel-title">Members</h3>
                    </div>
                    <div class="panel-body">
                        <ul>
                            <c:if test="${not empty members}">
                                <c:forEach items="${members}" var="member" varStatus="status">
                                    <li><a href="/profile/${member.who.user.id}" class="thumbnail"><img src="${member.who.profilePicID.location}" alt="friend_profile_picture"></a></li>
                                </c:forEach>
                            </c:if>
                        </ul>
                        <div class="clearfix"></div>
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