<%@include file="include/taglib.jsp"%>
<jsp:include page="include/header.jsp" />
<ul id="advice">
		<li>
			<h3>stats</h3>
			${fn:length(user)} Utilisateurs
			</li>
	</ul>
<div id="content">
<h2><c:out value="Liste des utilisateurs" /></h2>
<c:if test="${not empty user}">
	<table class="tablesorter" cellspacing="1">
		<thead>
			<tr>
				<th width=200>Login</th>
				<th width=300>Nombre de QCM répondus</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${user}" var="user">
				<tr>
					<td><a href="<spring:url value="/user/${user.id}" />"> <c:out
						value="${user}" /> </a></td>
					<td><c:out value="${user.resultsSize}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if></div>
<jsp:include page="include/footer.jsp" />
