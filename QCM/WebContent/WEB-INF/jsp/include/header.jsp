<?xml version="1.0" encoding="UTF-8"?>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
	"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr">
	<head>
		<title>QCM</title>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="shortcut icon" href="<spring:url value="/static/img/favicon.ico" />" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="<spring:url value="/static/css/qcm.css" />" />
		
		<script type="text/javascript" src="<spring:url value="/static/js/jquery.js" />"></script>
		<script type="text/javascript" src="<spring:url value="/static/js/jquery.tagcloud.js" />"></script>
		<script type="text/javascript" src="<spring:url value="/static/js/qcm.js" />"></script>
	</head>

	<body>
		<c:set var="connectedUser" value="${sessionScope.user}" />
		<div id="menu">
			<h1><a href="<spring:url value="/" />">QCM</a></h1>
			<ul id="navigation">
				<li><a href="#">
					<img src="<spring:url value="/static/img/table.png" />" alt="" />
					Questionnaires
				</a></li>
				<li class="separator"></li>
				<li><a href="<spring:url value="/userlist" />">
					<img src="<spring:url value="/static/img/user.png" />" alt="" />
					Utilisateurs
				</a></li>
				<li class="separator"></li>
				<li><a href="<spring:url value="/tag" />">
					<img src="<spring:url value="/static/img/tag_blue.png" />" alt="" />
					Tags
				</a></li>
				<c:if test="${connectedUser.admin}">
					<li class="separator"></li>
					<li><a href="<spring:url value="/questionnaire/create" />">
						<img src="<spring:url value="/static/img/table_add.png" />" alt="" />
						Nouveau questionnaire
				</a></li>
				</c:if>
			</ul>
			<ul id="user">
				
				<c:choose>
					<c:when test="${not empty connectedUser}">
						<li>
							<a href="<spring:url value="/logout" />">
								<img src="<spring:url value="/static/img/key.png" />" alt="" />
<<<<<<< .mine
								DÃ©connexion
=======
								Déconnexion
>>>>>>> .r23
							</a>
						</li>
						<li class="separator"></li>
						<li>
							<a href="<spring:url value="/user/${connectedUser.id}" />">
								<img src="<spring:url value="/static/img/user.png" />" alt="" />
								<c:out value="${connectedUser}" />
								(<c:out value="${connectedUser.resultsSize}" />)
							</a>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<a href="<spring:url value="/signup" />">
								<img src="<spring:url value="/static/img/pencil.png" />" alt="" />
								Inscription
							</a>
						</li>
						<li class="separator"></li>
						<li>
							<a href="<spring:url value="/login" />">
								<img src="<spring:url value="/static/img/key.png" />" alt="" />
								Connexion
							</a>
						</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<div id="container">
