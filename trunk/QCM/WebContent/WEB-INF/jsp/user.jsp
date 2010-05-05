<jsp:include page="include/header.jsp" />
	<%@ include file="include/taglib.jsp" %>
	<ul id="advice">
		<li>
			<h3>stats</h3>
			<strong><c:out value="${user.resultsSize}" /></strong> questionnaire(s)
		</li>
	</ul>
	<div id="content">
	<div id="avatar"> <img src="<spring:url value="/static/img/default_profile.jpg" />" /></div>
		<h2><c:out value="${user}" /></h2>
		<c:if test="${not empty user.results}">
			<table>
				<tr>
					<td>Titre</td>
					<td>Score</td>
				</tr>
				<c:forEach items="${user.results}" var="result">
					<tr>
						<td>
							<a href="<spring:url value="/result/${result.questionnaire.id}" />">
								<c:out value="${result.questionnaire}" />
							</a>
						</td>
						<td>
							<c:out value="${fn:length(result.correctAnswers)}" />
							sur
							<c:out value="${fn:length(result.questionnaire.questions)}" />
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>
<jsp:include page="include/footer.jsp" />
