<%@include file="../include/taglib.jsp" %>
<jsp:include page="../include/header.jsp" />
	<div id="content">
			<c:choose>
			<c:when test="${empty result.questionnaire}">
				<p>
					You can't take this questionnaire right now.
				</p>
			</c:when>
			<c:otherwise>
				<h2><c:out value="${result.questionnaire}" /></h2>
				
				<form:form modelAttribute="result">
					<form:errors path="*" />
					
					<ul>
						<c:forEach items="${result.questionnaire.questions}" var="question" varStatus="statusQ">
							<li class="questions">
								<h3>${statusQ.index+1}. ${question}</h3>
								
								<div class="answers">
									<ul>
										<c:forEach items="${question.answers}" var="answer" varStatus="statusA">
											<li>
												<form:radiobutton path="answers[${statusQ.index}].id" value="${answer.id}" label="${answer.label}" />
											</li>
										</c:forEach>
									</ul>
								</div>
							</li>
						</c:forEach>
					</ul>
					
					<input type="submit" value="Envoyer les réponses" />
				</form:form>
			</c:otherwise>
		</c:choose>
	</div>
<jsp:include page="../include/footer.jsp" />
