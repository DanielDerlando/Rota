<%-- 
    Document   : index
    Created on : 25/04/2015, 08:58:47
    Author     : Daniel
--%>
<%@page import="ExerciseAI.ExerciseAI"%>
<%String[] args = {"-22.952615"," -43.089559","-22.952388","-43.090959"};

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rota</title>
        <link rel="stylesheet" href="style.css" type="text/css">        
        <script type="text/javascript"
                src="http://maps.googleapis.com/maps/api/js?key=AIzaSyANMurqlY48fJinMIU76-m6hB8M8yfG4sg&sensor=true">
        </script>
        <script src="mapa.js"></script>
    </head>
    <body onload="init(<%=('\''+ExerciseAI.executaAStarRadar(args)+'\'')%>)">
        <div id="map_canvas" style="width:100%; height:100%"></div>
    </body>
</html>
