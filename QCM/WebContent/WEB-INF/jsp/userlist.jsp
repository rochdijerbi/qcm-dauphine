<%@include file="include/taglib.jsp"%>
<jsp:include page="include/header.jsp" />
<ul id="advice">
	<li>
		<h3>stats</h3>
		${fn:length(user)} utilisateur(s)
	</li>
</ul>
<div id="content">
	<h2><c:out value="Liste des utilisateurs" /></h2>
	
	<c:forEach items="${user}" var="user">
		<div class="list-box">
			<c:choose>
				<c:when test="${user.resultsSize == 0}">
					<div class="list-stat unanswered">
				</c:when>
				<c:otherwise>
					<div class="list-stat answered">
				</c:otherwise>
			</c:choose>
				<span class="list-count">${user.resultsSize}</span>
				qcm
			</div>
			
			<h4>
				<a href="<spring:url value="/user/${user.id}" />">${user}</a>
			</h4>
			
			<c:choose>
				<c:when test="${user.admin}">
					<c:out value="Admistrateur" />
				</c:when>
				<c:otherwise>
					<c:out value="Utilisateur" />
				</c:otherwise>
			</c:choose>
			
			<div class="tags"></div>
		</div>
	</c:forEach>
</div>
<jsp:include page="include/footer.jsp" />
