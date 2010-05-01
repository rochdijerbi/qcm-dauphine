<jsp:include page="../include/header.jsp" />
	<%@include file="../include/taglib.jsp" %>
	<form:form modelAttribute="questionnaire">
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
				<form:label path="title">Titre</form:label>
				<form:input path="title" />
				<form:errors path="title" />
				
				<ul class="questions">
					<c:forEach items="${questionnaire.questions}" var="question" varStatus="statusQ">
						<li>
							<form:label path="questions[${statusQ.index}].label">Libellé</form:label>
							<form:input path="questions[${statusQ.index}].label" />
							<form:errors path="questions[${statusQ.index}].label" />
							
							<ul class="answers">
								<c:forEach items="${question.answers}" varStatus="statusA">
									<li>
										<form:checkbox path="questions[${statusQ.index}].answers[${statusA.index}].correct" />
										
										<form:label path="questions[${statusQ.index}].answers[${statusA.index}].label">Libellé</form:label>
										<form:input path="questions[${statusQ.index}].answers[${statusA.index}].label" />
										<form:errors path="questions[${statusQ.index}].answers[${statusA.index}].label" />
									</li>
								</c:forEach>
							</ul>
						</li>
					</c:forEach>
				</ul>
				
				<input type="submit" value="Sauvegarder" />
			
		</div>
	</form:form>
<jsp:include page="../include/footer.jsp" />
