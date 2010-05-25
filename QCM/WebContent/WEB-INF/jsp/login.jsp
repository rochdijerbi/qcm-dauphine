<jsp:include page="include/header.jsp" />
	<%@ include file="include/taglib.jsp" %>
	<form:form modelAttribute="user">
		<div id="content">
			<h2><c:out value="Connexion" /></h2>
		
			<form:label path="login">Login</form:label>
			<form:input path="login" />
			<form:errors path="login" />
			<br />
			
			<form:label path="password">Password</form:label>
			<form:password path="password" />
			<form:errors path="password" />
			<br />
			
			<input type="submit" value="Login" />
		</div>
	</form:form>
<jsp:include page="include/footer.jsp" />
