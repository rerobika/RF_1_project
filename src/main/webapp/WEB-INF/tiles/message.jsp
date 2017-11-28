<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<section>
    <div class="container messages-container">
        <div class="row">
            <div class="col-sm-12 text-center">
                Messages
            </div>
        </div>
        <div class="row messages">
            <div class="col-sm-3">
                <c:choose>
                    <c:when test="${not empty friends}">
                        <c:forEach items="${friends}" var="friend" varStatus="status">
                            <div class="row friend" id="${friend.id}">
                                <img src="${friend.profilePicID.location}" class="img-msg" alt="Profile pic">
                                    ${friend.user.name}
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        You have no friends! :(
                    </c:otherwise>
                </c:choose>


            </div>
            <div class="col-sm-9 messages-text">
                <div class="row conversation">
                </div>

                <div class="row msg-input">
                    <form id="msg-form">
                        <div class="col-sm-10 no-padding">
                                <div>
                                    <input type="hidden" id="${_csrf.parameterName}"
                                           value="${_csrf.token}" />
                                    <input id="msg-input" autocomplete="off" name="message" class="form-control" aria-label="Message" placeholder="Your Message" type="text"/>
                                </div>
                        </div>
                        <div class="col-sm-2">
                            <button id="msg-send" type="submit">Eternify</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>

<script src="${pageContext.request.contextPath}/js/messages.js"></script>
