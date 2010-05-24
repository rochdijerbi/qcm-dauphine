<jsp:include page="include/header.jsp" />
	<%@include file="include/taglib.jsp" %>
	<ul id="advice">
		<li>
			<h3>stats</h3>
			X tag(s)
			</li>
	</ul>
	<div id="content">
		<h2>Tags</h2>
		<ol id="tagsCloud">
			<c:forEach items="${tags}" var="tag">
				<li class="tag" value="${tag.questionnairesSize}"><c:out value="${tag}" /></li>
			</c:forEach>
		</ol>
	</div>
<jsp:include page="include/footer.jsp" />
