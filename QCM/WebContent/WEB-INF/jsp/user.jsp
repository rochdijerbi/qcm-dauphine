<%@include file="include/taglib.jsp" %>
<jsp:include page="include/header.jsp" />
	<h1><c:out value="${user}" /></h1>
	<p><c:out value="${user.resultsSize}" /> questionnaire(s)</p>
	<c:if test="${not empty user.results}">
		<table>
			<tr>
				<td>Title</td>
				<td>Score</td>
			</tr>
			<c:forEach items="${user.results}" var="result">
				<tr>
					<td>
						<a href="result.do?id=${result.questionnaire.id}">
							<c:out value="${result.questionnaire}" />
						</a>
					</td>
					<td>
						<c:out value="${fn:length(result.correctAnswers)}" />
						out of
						<c:out value="${fn:length(result.questionnaire.questions)}" />
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
<jsp:include page="include/footer.jsp" />
