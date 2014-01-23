<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="trip.edit.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.jtravelagency.web.TripsActionBean" var="actionBean"/>
        <script>
            $(document).ready(function() {
                $("li.nav-trip").addClass("active");
            });
        </script>
        <s:form beanclass="cz.muni.fi.pa165.jtravelagency.web.TripsActionBean" class="new-form">
            <s:hidden name="trip.id"/>
            <fieldset><legend><f:message key="trip.edit.edit"/></legend>
                <%@include file="form.jsp"%>
                <div class="new-form-input">
                    <s:submit name="save" class="btn btn-default"><f:message key="trip.edit.save"/></s:submit>
                    <s:submit name="cancel" class="btn btn-default"><f:message key="trip.edit.cancel"/></s:submit>
                    </div>
                </fieldset>
        </s:form>

    </s:layout-component>
</s:layout-render>