<%@include file="taglib.jsp" %>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
	"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr">
	<head>
		<title>QCM</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	</head>

	<body>
		<c:set var="connectedUser" value="${sessionScope.user}" />
		<c:choose>
			<c:when test="${not empty connectedUser}">
				<a href="user.do?id=${connectedUser.id}"><c:out value="${connectedUser}" /></a>
				(<a href="user.do?id=${connectedUser.id}"><c:out value="${connectedUser.resultsSize}" /></a>)
				&middot;
				<c:if test="${connectedUser.admin}">
					admin
					&middot;
				</c:if>
				<a href="logout.do">logout</a>
			</c:when>
			<c:otherwise>
				<a href="login.do">login</a>
				&middot;
				<a href="signup.do">sign up</a>
			</c:otherwise>
		</c:choose>
		