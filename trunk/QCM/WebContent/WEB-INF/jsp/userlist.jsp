<%@include file="include/taglib.jsp"%>
<jsp:include page="include/header.jsp" />
<ul id="advice">
	<li>
	<h3>stats</h3>
	${fn:length(user)} Utilisateurs</li>
</ul>
<div id="content">
<h2><c:out value="Liste des utilisateurs" /></h2>
<c:if test="${not empty user}">
	<c:forEach items="${user}" var="user">
		<div class="list-box"><a
			href="<spring:url value="/user/${user.id}" />"> <c:out
			value="${user}" /> </a>
		<c:choose>
		<c:when test="${user.resultsSize == 0}"><div class="list-stat unanswered"></c:when>
		<c:otherwise><div class="list-stat answered"></c:otherwise>
		</c:choose>
		<div class="list-count"><c:out value="${user.resultsSize}" /></div>
		<div>QCM</div>
		</div>
		</div>
	</c:forEach>
</c:if></div>
<jsp:include page="include/footer.jsp" />
