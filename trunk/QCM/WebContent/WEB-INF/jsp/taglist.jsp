<jsp:include page="include/header.jsp" />
<%@include file="include/taglib.jsp"%>

<ul id="advice">
	<li>
		<h3>Statistiques</h3>
		<strong>${nbQuestionnaires}</strong> questionnaire(s)
	</li>
</ul>

<div id="content">
	<h2><c:out value="Liste des questionnaires" /></h2>
	<c:choose>
		<c:when test="${not empty listQuestionnaireByTag}">
			<c:forEach items="${listQuestionnaireByTag}" var="questionnaire">
				<div class="list-box">
				<c:choose>
						<c:when test="${questionnaire.resultsSize == 0}">
							<c:set var="classCount" value="unanswered" />
						</c:when>
						<c:otherwise>
							<c:set var="classCount" value="answered" />
						</c:otherwise>
					</c:choose>
					<div class="list-stat ${classCount}">
						<div class="list-count">
							<c:out value="${questionnaire.resultsSize}" /> 
						</div>
						<div>hits</div>
					</div>
					
					<h4>
						<a href="<spring:url value="/questionnaire/${questionnaire.id}" />"> 
							<c:out value="${questionnaire.title}" />
						</a>
					</h4>
					<c:out value="${questionnaire.description}" />	
					<div class="tags">
						<c:forEach items="${questionnaire.tags}" var="tag">
							<a href="<spring:url value="/tag/${tag}/0" />"><span class="tag">${tag}</span></a>
						</c:forEach>
					</div>	
				</div>
			</c:forEach>
			<br/>
			<c:if test="${page > 0}">
				<a href="<spring:url value="/tag/${tag}/${page - 1}" />" id="pagination"> 
					Précédent
				</a>
			</c:if>
			<c:if test="${page < (nbQuestionnaires / nbResults) - 1}">
				<a href="<spring:url value="/tag/${tag}/${page + 1}" />" id="pagination"> 
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
