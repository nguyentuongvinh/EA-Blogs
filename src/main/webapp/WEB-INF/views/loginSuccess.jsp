<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml11.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Refresh" content="1; url=${loginRequestPage}" />
<title>Login Success - EA Blogs</title>
</head>
<body>
	<h1 align="center">Login Success!</h1>
	<sec:authentication property="principal" var="authentication"/>
	<h2 align="center">Welcome <font color="red">${authentication.username}!</font></h2>
</body>
</html>