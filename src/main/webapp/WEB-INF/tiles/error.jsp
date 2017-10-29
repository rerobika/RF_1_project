<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:out value="${error.getMessage()}" default="Error message not defined!" />

<button onclick="window.history.back()"> Back </button>