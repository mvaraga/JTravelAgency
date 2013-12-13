JTravelAgency
=============

Go to root directory and build project

mvn install

Then go to directory JTravelAgency-web and run server:

cd JTravelAgency-web

mvn tomcat7:run

Then go to directory JTravelAgency-rest-client and run rest client:

cd ../JTravelAgency-rest-client

mvn tomcat7:run

Now you can test client application:

http://localhost:8081/client
