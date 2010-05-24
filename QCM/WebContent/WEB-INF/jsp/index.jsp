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
		
		<div class="definition">
			<h3>Qu'est-ce qu'un QCM ?</h3>
			Un questionnaire à choix multiples (ou QCM) est un questionnaire  
			dans lequel sont proposées plusieurs réponses pour chaque question. 
			Une ou plusieurs de ces réponses sont correctes.
		</div>
		
		<div class="indexList">
			<h3>Derniers QCM ajoutés</h3>
			<c:forEach items="${listLastQCM}" var="questionnaire">
				<div class="list-box">
					<h4>
						<a href="<spring:url value="/questionnaire/${questionnaire.id}" />"> 
							<c:out value="${questionnaire.title}" />
						</a>
					</h4>
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
		
		<div class="indexList">
			<h3>QCM les plus joués !</h3>
			<c:forEach items="${listPopularQCM}" var="questionnaire">
				<div class="list-box">
					<h4>
						<a href="<spring:url value="/questionnaire/${questionnaire.id}" />"> 
							<c:out value="${questionnaire.title}" />
						</a>
					</h4>
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
