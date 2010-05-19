<%@include file="../include/taglib.jsp" %>
<c:forEach items="${questionnaire.tags}" var="tag">
	<span class="deleteTag">
		${tag}
		<a href="#" rel="${tag}">x</a>
	</span>
</c:forEach>
