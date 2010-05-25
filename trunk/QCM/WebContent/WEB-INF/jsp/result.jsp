<jsp:include page="include/header.jsp" />
	<%@ include file="include/taglib.jsp" %>
	<c:if test="${not empty result}">
		<ul id="advice">
			<li>
				<h3>score</h3>
				
				<c:out value="${result.nbCorrectAnswers}" />
					sur 
				<c:out value="${fn:length(result.questionnaire.questions)}" />
			</li>
		</ul>
	</c:if>
	<div id="content">
		<c:choose>
			<c:when test="${empty result}">
				Vous n'avez pas encore répondu à ce questionnaire.
			</c:when>
			<c:otherwise>
				<h2><c:out value="${result.questionnaire}" /></h2>
				<p>${result.questionnaire.description}</p>
				<div class="tags">
					<c:forEach items="${result.questionnaire.tags}" var="tag">
						<span class="tag">${tag}</span>
					</c:forEach>
				</div>
				<br />
				
				<ul>
					<c:forEach items="${result.questionnaire.questions}" var="question" varStatus="statusQ">
						<li class="questions">
							<h3>${statusQ.index+1}. ${question}</h3>
							
							<div class="answers">
								<ul>
									<c:forEach items="${question.answers}" var="answer" varStatus="statusA">
										<li>
											${statusA.index+1}. ${answer}
											<c:if test="${answer.correct}">
												<span class="correct">(Correct)</span>
											</c:if>
											<c:if test="${not answer.correct and dauphine:contains(result.answers, answer)}">
												<span class="incorrect">(Incorrect)</span>
											</c:if>
										</li>
									</c:forEach>
								</ul>
							</div>
						</li>
					</c:forEach>
				</ul>
			</c:otherwise>
		</c:choose>
	</div>
<jsp:include page="include/footer.jsp" />
