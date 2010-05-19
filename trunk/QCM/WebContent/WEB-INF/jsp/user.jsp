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
<div id="content">
<div id="avatar"><img
	src="<spring:url value="/static/img/default_profile.jpg" />" /></div>
<c:if test="${user.id == 1}">
	<form:form modelAttribute="user">
		<form:checkbox path="admin" value="${user.admin}" />
		<form:label path="admin">Administrateur</form:label>
		<form:errors path="admin" />
		<br />

		<input type="submit" value="Envoyer" />
	</form:form>
</c:if>
<h2><c:out value="${user}" /></h2>
<c:if test="${not empty user.results}">
		<c:forEach items="${user.results}" var="result">
			<div class="list-box"><a
				href="<spring:url value="/result/${result.questionnaire.id}" />">
			<c:out value="${result.questionnaire}" /> </a>
			<div class="list-stat">
			<div class="list-count"><c:out
				value="${fn:length(result.correctAnswers)}" />/<c:out
				value="${fn:length(result.questionnaire.questions)}" /></div>
			<div>score</div>
			</div>
			</div>
		</c:forEach>
</c:if></div>
<jsp:include page="include/footer.jsp" />
