<%@include file="../include/taglib.jsp" %>
<c:set var="connectedUser" value="${sessionScope.connected_user}" />
<jsp:include page="../include/header.jsp" />
	<c:if test="${connectedUser.admin}">
		<ul id="advice">
			<li>
				<h3>meilleurs scores</h3>
			</li>
		</ul>
	</c:if>
	<div id="content">
		<c:choose>
			<c:when test="${empty result.questionnaire}">
				<p>
					Vous ne pouvez pas répondre à ce questionnaire.
				</p>
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
				
				<c:if test="${connectedUser.admin}">
					<a href="<spring:url value="/questionnaire/${result.questionnaire.id}/edit" />" class="pagination">
						Modifier le questionnaire
					</a>
				</c:if>
				
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
												<c:choose>
													<c:when test="${connectedUser.admin}">
														${answer.label}
													</c:when>
													<c:otherwise>
														<form:radiobutton path="answers[${statusQ.index}].id" value="${answer.id}" label="${answer.label}" />
													</c:otherwise>
												</c:choose>
												
											</li>
										</c:forEach>
									</ul>
								</div>
							</li>
						</c:forEach>
					</ul>
					
					<c:if test="${not connectedUser.admin}">
						<input type="submit" value="Envoyer les réponses" />
					</c:if>
				</form:form>
			</c:otherwise>
		</c:choose>
	</div>
<jsp:include page="../include/footer.jsp" />
