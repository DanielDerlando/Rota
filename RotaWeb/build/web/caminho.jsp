<%-- 
    Document   : index
    Created on : 25/04/2015, 08:58:47
    Author     : Daniel
--%>
<%@page import="ExerciseAI.ExerciseAI"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rota</title>
        <link rel="stylesheet" href="css/style.css" type="text/css">        
        <script type="text/javascript"
                src="http://maps.googleapis.com/maps/api/js?key=AIzaSyANMurqlY48fJinMIU76-m6hB8M8yfG4sg&sensor=true">
        </script>
        <script src="js/caminho.js"></script>
    </head>
    <body onload="init(<%='\''+request.getAttribute("caminho").toString()+'\''%>)">
        <div id="map_canvas"></div>
        <div style="
             position: absolute;
             z-index: 999;
             top:5%;
             left:7%;
            ">
            <input type="button" value="Voltar" onclick="history.go(-1)"/>
        </div>
    </body>
</html>
