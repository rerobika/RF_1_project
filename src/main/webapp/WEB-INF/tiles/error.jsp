<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<p align="center">
    <c:out value="${error.getMessage()}" default="Unexpected error! :(" />
    <br />
    <button onclick="window.history.back()"> Back </button>
</p>

