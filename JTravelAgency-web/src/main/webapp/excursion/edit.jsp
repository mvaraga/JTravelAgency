<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="excursion.edit.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.jtravelagency.web.ExcursionsActionBean" var="actionBean"/>
        <script>
            $(document).ready(function() {
                $("li.nav-excursion").addClass("active");
            });
        </script>
        <s:form beanclass="cz.muni.fi.pa165.jtravelagency.web.ExcursionsActionBean" class="new-form">
            <s:hidden name="excursion.id"/>
            <fieldset><legend><f:message key="excursion.edit.edit"/></legend>
                <%@include file="form.jsp"%>
                <div class="new-form-input">
                    <s:submit name="save" class="btn btn-default"><f:message key="form.save"/></s:submit>
                    <s:submit name="cancel" class="btn btn-default"><f:message key="form    .cancel"/></s:submit>
                    </div>
                </fieldset>
        </s:form>

    </s:layout-component>
</s:layout-render>
