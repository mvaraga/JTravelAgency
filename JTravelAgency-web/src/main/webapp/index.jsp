<%-- 
    Document   : index
    Created on : 19.11.2013, 20:02:19
    Author     : xvaraga
--%>



<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>



<s:layout-render name="/layout.jsp" titlekey="index.title">

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script>
        $(document).ready(function () {
            window.setInterval(updateBoard,1000);
        });
 
        function updateBoard() {
//            $('#board').load('${pageContext.request.contextPath}/board?type=html');
            console.log("updated board");
        }
 
        function sendText() {
            //alert(${pageContext.request.contextPath});
            $.ajax({
                type: 'POST',
                contentType: 'application/json',
                dataType: 'json',
                url: "http://localhost:8081/pa165/websources/customers/",
                data: { 'firstName': "A", 'lastName' : "last_name", 'status' : "REGULAR"},
                success: function (data) {
                    alert('got ' + data);
                }
            })
            .done(function( data ) {
                alert('got ' + data);
            });
        }
    </script>
    
    <s:layout-component name="body">
 
    <div id="welcome">
      <h2>About:</h2>
      Welcome to our Travel Agency!
      <p>For reservation please click on "Reservations", then select a customer, trip and excursion
      related to the trip.
    </div>
        
    <div id="contact">
      <h2>Contact:</h2>
      <a href="https://github.com/mvaraga/JTravelAgency">JTravelAgency
    </div>

        
     <button onclick="sendText();">Publish</button>
    </s:layout-component>
</s:layout-render>