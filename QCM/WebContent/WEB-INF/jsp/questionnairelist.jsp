<%@include file="include/taglib.jsp"%>
<jsp:include page="include/header.jsp" />
<ul id="advice">
	<li>
		<h3>Statistiques</h3>
		${fn:length(listQuestionnaire)} questionnaire(s)
	</li>
</ul>

<div id="content">
	<h2><c:out value="Liste des questionnaires" /></h2>
	<c:choose>
		<c:when test="${not empty listQuestionnaire}">
			<c:forEach items="${listQuestionnaire}" var="questionnaire">
				<li class="questions">
					<a href="<spring:url value="/questionnaire/${questionnaire.id}" />"> 
						<c:out value="${questionnaire.title}" /> 
					</a>
				</li>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<h3>Il n'y a pas encore de QCM !</h3>	
		</c:otherwise>
	</c:choose>
</div>
<jsp:include page="include/footer.jsp" />
