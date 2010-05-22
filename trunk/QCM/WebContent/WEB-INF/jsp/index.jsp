<jsp:include page="include/header.jsp" />
	<%@ include file="include/taglib.jsp" %>
	<ul id="advice">
		<li>
			<h3>Statistiques</h3>
			<strong>${nbQuestionnaires}</strong> questionnaire(s)<br/>
			<strong>${nbUsers}</strong> utilisateur(s)<br/>
			<strong>${nbTakenQCM}</strong> QCM pris
		</li>
	</ul>
	<div id="content">
		<h2><c:out value="Bienvenue sur le QCM !" /></h2>
		<p>Inscrivez vous dès maintenant pour remplir les QCM.</p>
		
		<div class="last">
			<h2>Derniers QCM ajoutés</h2>
			<c:forEach items="${listLastQCM}" var="questionnaire">
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
		</div>
	</div>
	
	
<jsp:include page="include/footer.jsp" />
