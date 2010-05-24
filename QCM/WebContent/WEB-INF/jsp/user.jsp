<jsp:include page="include/header.jsp" />
<%@ include file="include/taglib.jsp"%>
	<ul id="advice">
		<li>
			<h3>stats</h3>
			<strong>${user.resultsSize}</strong> questionnaire(s)
		</li>
	</ul>

	<c:set var="connectedUser" value="${sessionScope.connected_user}" />

	<div id="content">
		<img src="<spring:url value="/user/photo" />" />

		<h2>${user}</h2>
		
		<c:forEach items="${user.results}" var="result">
			<div class="list-box">
				<c:choose>
					<c:when
						test="${fn:length(result.correctAnswers)==fn:length(result.questionnaire.questions)}">
						<div class="list-stat answered">
					</c:when>
					<c:otherwise>
						<div class="list-stat unanswered">
					</c:otherwise>
				</c:choose>
					<span class="list-count">
						${fn:length(result.correctAnswers)}
						/
						${fn:length(result.questionnaire.questions)}
					</span>
					score
				</div>
				
				<h4>
					<c:choose>
						<c:when test="${user.id == connectedUser.id}">
							<a href="<spring:url value="/result/${result.questionnaire.id}" />">
								${result.questionnaire}
							</a>
						</c:when>
						<c:otherwise>
							${result.questionnaire}
						</c:otherwise>
					</c:choose>
				</h4>
				
				${result.questionnaire.description}
				
				<div class="tags">
					<c:forEach items="${result.questionnaire.tags}" var="tag">
						<span class="tag">${tag}</span>
					</c:forEach>
				</div>
			</div>
		</c:forEach>

		<c:if test="${user.id == connectedUser.id or connectedUser.admin}">
			<div class="user-editbox">
				<form:form modelAttribute="user" enctype="multipart/form-data">
					<form:label path="uploadPhoto">Avatar</form:label>
					<form:input type="file" path="uploadPhoto" />
					<form:errors path="uploadPhoto" />
					
					<br />
					<c:if test="${connectedUser.admin}">
						<form:checkbox path="admin" value="${user.admin}" />
						<form:label path="admin">Administrateur</form:label>
						<form:errors path="admin" />
						<br />
					</c:if>
			
					<input type="submit" value="Modifier" />
				</form:form>
			</div>
		</c:if>
	</div>
<jsp:include page="include/footer.jsp" />
