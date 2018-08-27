<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<section>
    <h2>
        <c:if test="${action == 'create'}"><spring:message code="meal.createtitle"/></c:if>
        <c:if test="${action == 'edit'}"><spring:message code="meal.edittitle"/></c:if>
    </h2>
    <hr>
    <form:form modelAttribute="meal" method="post" action="${pageContext.request.contextPath}/meals/create">
        <form:input path="id" type="hidden" value="${meal.id}"/>
        <dl>
            <dt>DateTime:</dt>
            <dd><form:input path="dateTime" type="datetime-local" value="${meal.dateTime}" required="true"/></dd>
        </dl>
        <dl>
            <dt>Description:</dt>
            <dd><form:input path="description" type="text" value="${meal.description}"  required="true"/></dd>
        </dl>
        <dl>
            <dt>Calories:</dt>
            <dd><form:input path="calories" type="number" value="${meal.calories}" required="true"/></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form:form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
