<%@include file="../include/taglib.jsp" %>
<jsp:include page="../include/header.jsp" />
	<c:choose>
		<c:when test="${empty result.questionnaire}">
			You can't take this questionnaire right now.
		</c:when>
		<c:otherwise>
			<h1><c:out value="${result.questionnaire}" /></h1>
			<form:form modelAttribute="result">
				<form:errors path="*" />
				<dl class="questionnaire">
					<c:forEach items="${result.questionnaire.questions}" var="question" varStatus="status">
						<dt><c:out value="${question}" /></dt>
						<dd>
							<ul>
								<c:forEach items="${question.answers}" var="answer">
									<li>
										<form:radiobutton path="answers[${status.index}].id" value="${answer.id}" label="${answer}" />
									</li>
								</c:forEach>
							</ul>
						</dd>
					</c:forEach>
				</dl>
				
				<input type="submit" value="Envoyer les réponses" />
			</form:form>
		</c:otherwise>
	</c:choose>
<jsp:include page="../include/footer.jsp" />
