<%@include file="include/taglib.jsp" %>
<jsp:include page="include/header.jsp" />
	<div id="content">
		<c:choose>
			<c:when test="${empty result}">
				You did not take this questionnaire yet.
			</c:when>
			<c:otherwise>
				<h2><c:out value="${result.questionnaire}" /></h2>
				<p>
					<c:out value="${fn:length(result.correctAnswers)}" />
					out of
					<c:out value="${fn:length(result.questionnaire.questions)}" />
				</p>
				<dl class="questionnaire">
					<c:forEach items="${result.questionnaire.questions}" var="question">
						<dt><c:out value="${question}" /></dt>
						<dd>
							<ul>
								<c:forEach items="${question.answers}" var="answer">
									<li>
										<c:out value="${answer}" />
										<c:if test="${answer.correct}"> (Correct)</c:if>
										<c:if test="${not answer.correct and dauphine:contains(result.answers, answer)}"> (Incorrect)</c:if>
									</li>
								</c:forEach>
							</ul>
						</dd>
					</c:forEach>
				</dl>
			</c:otherwise>
		</c:choose>
	</div>
<jsp:include page="include/footer.jsp" />
