<jsp:include page="include/header.jsp" />
	<%@include file="include/taglib.jsp" %>
	<ul id="advice">
		<li>
			<h3>stats</h3>
			${fn:length(tags)} tag(s)
		</li>
	</ul>
	<div id="content">
		<h2>Tags</h2>

		<ol id="tagsCloud">
			<c:forEach items="${tags}" var="tag" varStatus="statusT">
				<c:if test="${statusT.index  mod 5 == 0}">
					<li class="newline"></li>
				</c:if>
				
				<li>
					<span class="tag"><c:out value="${tag}" /></span>
					x ${tag.questionnairesSize}
				</li>
			</c:forEach>
		</ol>
	</div>
<jsp:include page="include/footer.jsp" />
