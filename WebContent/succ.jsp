<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="st" class="org.jspider.searchApp.StudentDTO" scope="request"></jsp:useBean>


<jsp:getProperty property="name" name="st"/>
<jsp:getProperty property="course" name="st"/>
<jsp:getProperty property="perc" name="st"/>
<a href="index.jsp">Click here for home Page</a>
</body>
</html>