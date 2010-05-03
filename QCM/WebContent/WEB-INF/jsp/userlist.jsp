<%@include file="include/taglib.jsp" %>
<jsp:include page="include/header.jsp" />
	<div id="content">
		<h2><c:out value="Liste des utilisateurs" /></h2>
		<c:if test="${not empty user}">
			<ul>
				<c:forEach items="${user}" var="user">
					<li>
						<a href="<spring:url value="/user/${user.id}" />">
								<c:out value="${user}" />
								</a>
					</li>
				</c:forEach>
			</ul>
		</c:if>
	</div>
<jsp:include page="include/footer.jsp" />
