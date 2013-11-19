<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="book.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.jtravelagency.web.ExcursionsActionBean" var="actionBean"/>

        <p><f:message key="book.list.allbooks"/></p>

        <table class="basic">
            <tr>
                <th>id</th>
                <th><f:message key="book.author"/></th>
                <th><f:message key="book.title"/></th>
                <th><f:message key="book.year"/></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${actionBean.excursions}" var="excursion">
                <tr>
                    <td>${excursion.id}</td>
                    <td><c:out value="${excursion.author}"/></td>
                    <td><c:out value="${excursion.title}"/></td>
                    <td><c:out value="${excursion.year}"/></td>
                    <td>
                     <s:link beanclass="cz.muni.fi.pa165.jtravelagency.web.ExcursionsActionBean" event="edit"><s:param name="book.id" value="${excursion.id}"/>edit</s:link>
                    </td>
                    <td>
                        <s:form beanclass="cz.muni.fi.pa165.jtravelagency.web.ExcursionsActionBean">
                            <s:hidden name="book.id" value="${excursion.id}"/>
                            <s:submit name="delete"><f:message key="book.list.delete"/></s:submit>
                        </s:form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <s:form beanclass="cz.muni.fi.pa165.jtravelagency.web.ExcursionsActionBean">
            <fieldset><legend><f:message key="book.list.newbook"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="add">Vytvořit novou knihu</s:submit>
            </fieldset>
        </s:form>
    </s:layout-component>
</s:layout-render>