<%@include file="include/taglib.jsp"%>
<jsp:include page="include/header.jsp" />

<ul id="advice">
	<li>
		<h3>Statistiques</h3>
		<strong>${nbQuestionnaires}</strong> questionnaire(s)
	</li>
</ul>

<div id="content">
	<h2><c:out value="Liste des questionnaires" /></h2>
	<c:choose>
		<c:when test="${not empty listQuestionnaire}">
			<c:forEach items="${listQuestionnaire}" var="questionnaire">
				<div class="list-box">
					<h3>
						<a href="<spring:url value="/questionnaire/${questionnaire.id}" />"> 
							<c:out value="${questionnaire.title}" />
						</a>
					</h3>
					<c:out value="${questionnaire.description}" />	
					<div class="list-stat answered">
						<div class="list-count">
							<c:out value="${questionnaire.resultsSize}" /> 
						</div>
						<div>Hits</div>
					</div>	
				</div>
			</c:forEach>
			<br/>
			<c:if test="${page > 0}">
				<a href="<spring:url value="/questionnaire/questionnairelist/${page - 1}" />" id="pagination"> 
					Précédent
				</a>
			</c:if>
			<c:if test="${page < (nbQuestionnaires / nbResults) - 1}">
				<a href="<spring:url value="/questionnaire/questionnairelist/${page + 1}" />" id="pagination"> 
					Suivant
				</a>
			</c:if>
		</c:when>
		<c:otherwise>
			<h3>Il n'y a pas encore de QCM !</h3>	
		</c:otherwise>
	</c:choose>
</div>

<jsp:include page="include/footer.jsp" />
