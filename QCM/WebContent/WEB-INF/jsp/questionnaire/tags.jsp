<%@include file="../include/taglib.jsp" %>
<c:forEach items="${questionnaire.tags}" var="tag">
	<span class="tag">
		${tag}
		<a href="#" rel="${tag}">x</a>
	</span>
</c:forEach>
