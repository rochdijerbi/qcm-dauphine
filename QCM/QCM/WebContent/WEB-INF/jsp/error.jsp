<%@include file="include/taglib.jsp" %>
<jsp:include page="include/header.jsp" />
	<div id="content">
		<h2>Erreur <c:out value="${code}"/></h2>
		<p>
			<c:if test="${not empty message}">
				<c:out value="${message}" />
				<br />
			</c:if>
			Retour à la <a href="<spring:url value="/"/>">page d'accueil</a>.
		</p>
	</div>
<jsp:include page="include/footer.jsp" />
