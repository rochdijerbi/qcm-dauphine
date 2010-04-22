<%@include file="include/taglib.jsp" %>
<jsp:include page="include/header.jsp" />
	<h1><c:out value="Login" /></h1>
	<form:form modelAttribute="user">
		<form:label path="login">Login</form:label>
		<form:input path="login" />
		<form:errors path="login" />
		<br />
		
		<form:label path="password">Password</form:label>
		<form:password path="password" />
		<form:errors path="password" />
		<br />
		
		<input type="submit" value="Login" />
	</form:form>
<jsp:include page="include/footer.jsp" />
