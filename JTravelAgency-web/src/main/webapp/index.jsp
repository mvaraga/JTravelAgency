<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:layout-render name="/layout.jsp" titlekey="index.title">
    <s:layout-component name="body">

        <div class="jumbotron">
            <h1>Welcome!</h1>
            <p>For reservation please click on "Reservations", then select a customer, trip and excursion
                related to the trip.</p>
            </br>
            <h2>Contact:</h2>
            <a href="https://github.com/mvaraga/JTravelAgency">JTravelAgency</a>
        </div>
    </s:layout-component>
</s:layout-render>