<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<head>
    <title>Weather Wizard</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>

<body>
	<h1>Weather Wizard</h1>

  <table style="border: 1px solid black;">
   <tr>
       <td style="border: 1px solid black;" colspan=3>Default City Temperature(s)</td>
   </tr>
   <tr>
       <td style="border: 1px solid black;">City Name</td>
       <td style="border: 1px solid black;">Temp</td>
       <td style="border: 1px solid black;">Description</td>
   </tr>
   <c:forEach var="weather" items="${defaultWeatherList}">
       <tr>
           <td style="border: 1px solid black;">${weather.city}</td>
           <td style="border: 1px solid black;">${weather.temperature}</td>
           <td style="border: 1px solid black;">${weather.description}</td>
       </tr>
  </c:forEach>

   </table>

	<br>

  <table style="border: 1px solid black;">

   <tr>
       <td style="border: 1px solid black;" colspan=3>Search weather by city name</td>
   </tr>
   <form id="weather-form">
   <tr>
       <td style="border: 1px solid black;">City</td>
       <td style="border: 1px solid black;"><input type="text" id="city"/></td>
       <td style="border: 1px solid black;"><button type="submit" id="bth-search">Search</button></td>
   </tr>
   <tr>
       <td style="border: 1px solid black;" colspan=3><div id="feedback"></div></td>
   </tr>
   </form>

   </table>

<br>

<!-- Scripts should be loaded at the end. -->
<script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript" src="js/main.js"></script>

</body>

</html>
