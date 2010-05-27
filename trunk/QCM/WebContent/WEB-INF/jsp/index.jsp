<jsp:include page="include/header.jsp" />
	<%@ include file="include/taglib.jsp" %>
	<ul id="advice">
		<li>
			<h3>Statistiques</h3>
			<strong>${nbQuestionnaires}</strong> questionnaire(s)<br/>
			<strong>${nbUsers}</strong> utilisateur(s)<br/>
			<strong>${nbTakenQCM}</strong> QCM passés
		</li>
		
		<li>
			<h3>Qu'est-ce qu'un QCM ?</h3>
			<p>Un questionnaire à choix multiples (ou QCM) est un questionnaire dans lequel sont proposées plusieurs réponses pour chaque question.</p>
			<p>Une seule de ces réponses est correcte.</p>
		</li>
	</ul>
	
	<div id="content">
		<h2><c:out value="Derniers QCM ajoutés" /></h2>
			<c:forEach items="${listLastQCM}" var="questionnaire">
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
						<span class="list-count">
							${questionnaire.resultsSize}
						</span>
						hits
					</div>		
					
					<h4>
						<a href="<spring:url value="/questionnaire/${questionnaire.id}" />"> 
							${questionnaire.title}
						</a>
					</h4>
					${questionnaire.description}
					
					<div class="tags">
						<c:forEach items="${questionnaire.tags}" var="tag">
							<a href="<spring:url value="/tag/${tag}/0" />"><span class="tag">${tag}</span></a>
						</c:forEach>
					</div>
				</div>
			</c:forEach>
		
		<br />
		
		<h2><c:out value="QCM les plus joués !" /></h2>
			<c:forEach items="${listPopularQCM}" var="questionnaire">
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
						<span class="list-count">
							${questionnaire.resultsSize}
						</span>
						hits
					</div>	
					
					<h4>
						<a href="<spring:url value="/questionnaire/${questionnaire.id}" />"> 
							${questionnaire.title}
						</a>
					</h4>
					${questionnaire.description}
					
					<div class="tags">
						<c:forEach items="${questionnaire.tags}" var="tag">
							<a href="<spring:url value="/tag/${tag}/0" />"><span class="tag">${tag}</span></a>
						</c:forEach>
					</div>	
				</div>
			</c:forEach>
	</div>
	
<jsp:include page="include/footer.jsp" />
