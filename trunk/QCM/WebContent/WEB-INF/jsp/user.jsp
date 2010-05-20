<jsp:include page="include/header.jsp" />
<%@ include file="include/taglib.jsp"%>
<script>
	var data = [ [ 0, 3 ], [ 4, 8 ], [ 8, 5 ], [ 9, 13 ] ];
</script>
<ul id="advice">
	<li>
	<h3>stats</h3>
	<strong><c:out value="${user.resultsSize}" /></strong>
	questionnaire(s)</li>
	<div id="chartdiv" style="width: 200px; height: 200px;"></div>
</ul>
<c:set var="connectedUser" value="${sessionScope.connected_user}" />
<div id="content">
<div id="avatar"><c:choose>
	<c:when test="${not empty user.photo}">
		<img height=100 src="<spring:url value="/user/photo" />" /></div>
</c:when> <c:otherwise>
	<img height=100
		src="<spring:url value="/static/img/default_profile.jpg" />" /></div>
</c:otherwise>
</c:choose>
<c:if test="${user.id == connectedUser.id or connectedUser.admin}">
	<div class="user-editbox"><form:form modelAttribute="user">
		<form:label path="photo">Avatar</form:label>
		<form:input type="file" path="photo" />
		<form:errors path="photo" />
		<br />
		<c:if test="${connectedUser.admin}">
			<form:checkbox path="admin" value="${user.admin}" />
			<form:label path="admin">Administrateur</form:label>
			<form:errors path="admin" />
			<br />
		</c:if>

		<input type="submit" value="Modifier" />
	</form:form></div>
</c:if>
<h2><c:out value="${user}" /></h2>
<c:if test="${not empty user.results}">
	<h3>Résultats</h3>
	<c:forEach items="${user.results}" var="result">
		<div class="list-box"><a
			href="<spring:url value="/result/${result.questionnaire.id}" />">
		<c:out value="${result.questionnaire}" /> </a> <c:choose>
			<c:when
				test="${fn:length(result.correctAnswers)==fn:length(result.questionnaire.questions)}">
				<div class="list-stat answered">
			</c:when>
			<c:otherwise>
				<div class="list-stat unanswered">
			</c:otherwise>
		</c:choose>
		<div class="list-count"><c:out
			value="${fn:length(result.correctAnswers)}" />/<c:out
			value="${fn:length(result.questionnaire.questions)}" /></div>
		<div>score</div>
		</div>
</div>
</c:forEach>
</c:if>
</div>
<jsp:include page="include/footer.jsp" />
