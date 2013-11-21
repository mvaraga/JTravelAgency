<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:layout-definition>
    <!DOCTYPE html>
    <html lang="${pageContext.request.locale}">
        <head>
            <title><f:message key="${titlekey}"/></title>
            <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
            <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
            <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
            <script src="${pageContext.request.contextPath}/jquery-ui-timepicker-addon.js"></script>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css" />
            <script>
            $(function() {
              $("#datepicker").datetimepicker();
            });
            </script>
            <s:layout-component name="header"/>
        </head>
        <body>
            <h1><f:message key="${titlekey}"/></h1>
            <div id="navigation">
                <ul>
                    <li><s:link beanclass="cz.muni.fi.pa165.jtravelagency.web.TripsActionBean"><f:message key="index.trips.link"/></s:link></li>
                    <li><s:link beanclass="cz.muni.fi.pa165.jtravelagency.web.CustomersActionBean"><f:message key="index.customers.link"/></s:link></li>
                    <li><s:link beanclass="cz.muni.fi.pa165.jtravelagency.web.ExcursionsActionBean"><f:message key="index.excursions.link"/></s:link></li>
                    <li><s:link beanclass="cz.muni.fi.pa165.jtravelagency.web.ReservationsActionBean"><f:message key="index.reservations.link"/></s:link></li>
                    </ul>
                </div>
                <div id="content">
                <s:messages/>
                <s:layout-component name="body"/>
            </div>
        </body>
    </html>
</s:layout-definition>