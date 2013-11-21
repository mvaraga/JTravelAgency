<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="excursion.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.jtravelagency.web.ExcursionsActionBean" var="actionBean"/>

        <p><f:message key="excursion.list.allexcursions"/></p>

        <table class="basic">
            <tr>
                <th>id</th>
                <th><f:message key="excursion.excursionDate"/></th>
                <th><f:message key="excursion.description"/></th>
                <th><f:message key="excursion.price"/></th>
                <th><f:message key="excursion.trip"/></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${actionBean.excursions}" var="excursion">
                <tr>
                    <td>${excursion.id}</td>
                    <td><c:out value="${excursion.excursionDate}"/></td>
                    <td><c:out value="${excursion.description}"/></td>
                    <td><c:out value="${excursion.price}"/></td>
                    <td><c:out value="${excursion.trip.destination}"/></td>
                    <td>
                     <s:link beanclass="cz.muni.fi.pa165.jtravelagency.web.ExcursionsActionBean" event="edit"><s:param name="excursion.id" value="${excursion.id}"/>edit</s:link>
                    </td>
                    <td>
                        <s:form beanclass="cz.muni.fi.pa165.jtravelagency.web.ExcursionsActionBean">
                            <s:hidden name="excursion.id" value="${excursion.id}"/>
                            <s:submit name="delete"><f:message key="excursion.list.delete"/></s:submit>
                        </s:form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <s:form beanclass="cz.muni.fi.pa165.jtravelagency.web.ExcursionsActionBean">
            <fieldset><legend><f:message key="excursion.list.newexcursion"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="add"><f:message key="excursion.list.create"/></s:submit>
            </fieldset>
        </s:form>
    </s:layout-component>
</s:layout-render>