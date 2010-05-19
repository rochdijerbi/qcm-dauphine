<jsp:include page="../include/header.jsp" />
	<%@include file="../include/taglib.jsp" %>
	<form:form modelAttribute="questionnaire" action="/QCM/questionnaire/create">
		<ul id="advice">
			<li>
				<h3>dates</h3>
				
				<form:label path="start">Début</form:label>
				<form:input path="start" />
				<form:errors path="start" />
				<br />
				
				<form:label path="end">Fin</form:label>
				<form:input path="end" />
				<form:errors path="end" />
				<br />
			</li>

			<li>
				<h3>tags</h3>
				<div id="tags">
					<jsp:include page="tags.jsp" />
				</div>
				
				<input type="text" id="newTag" class="creation edition" />
				
				<a href="#" title="Add tag" id="addTag">
					<img src="" alt="Add" />
				</a>
			</li>
		</ul>
		<div id="content">
			<c:choose>
				<c:when test="${empty questionnaire.id}">
					<h2>Création d'un nouveau questionnaire</h2>
				</c:when>
				<c:otherwise>
					<h2>Modification d'un questionnaire</h2>
				</c:otherwise>
			</c:choose>
				<form:label path="title">Titre du questionnaire</form:label>
				<form:input path="title" cssErrorClass="inputError" />
				
				<form:label path="title">Description</form:label>
				<form:textarea path="description" cssErrorClass="inputError" />
				
				<ul>
					<c:forEach items="${questionnaire.questions}" var="question" varStatus="statusQ">
						<li class="questions">
								<h3>Question ${statusQ.index+1}</h3>
								
								<form:input path="questions[${statusQ.index}].label" cssErrorClass="inputError" />
								
								<div class="answers">
									<ul>
										<c:forEach items="${question.answers}" varStatus="statusA">
											<li>
												<h4>Réponse ${statusA.index+1}</h4>
												
												<form:checkbox cssClass="correct" path="questions[${statusQ.index}].answers[${statusA.index}].correct" />
												
												<form:input path="questions[${statusQ.index}].answers[${statusA.index}].label" cssErrorClass="inputError" />
											</li>
										</c:forEach>
									</ul>
								</div>
						</li>
					</c:forEach>
				</ul>
				
			<input type="submit" value="Sauvegarder" />
			<input type="button" value="Ajouter une question" id="addQuestion" />
		</div>
	</form:form>
<jsp:include page="../include/footer.jsp" />
