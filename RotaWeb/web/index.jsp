<%-- 
    Document   : entry
    Created on : 15/06/2015, 20:31:29
    Author     : Daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rota Web</title>
        <script type="text/javascript"
                src="http://maps.googleapis.com/maps/api/js?key=AIzaSyANMurqlY48fJinMIU76-m6hB8M8yfG4sg&sensor=true">
        </script>
        <script src="js/jquery.js"></script>
        <script src="js/entry.js"></script> 
        <link rel="stylesheet" href="css/style.css" type="text/css">  
    </head>
    <body>
        <div id="map_canvas"></div>
        <div id="tabela" style="
             position: absolute;
             z-index: 999;
             top:5%;
             left:7%;
             ">
            <center>
                <form id="formulario" action="Controler">
                    <table id="table">
                        <thead>
                            <tr>
                                <td></td>
                                <td><label>Coordenadas</label></td>
                            </tr>
                        </thead>
                        <tr>
                            <td><label>Inicial: </label></td>
                            <td><input type="text" value="x,y" name="latlon1"id="latlon1"/></td>
                        </tr>
                        <tr>
                            <td><label>Final: </label></td>
                            <td><input type="text" value="x,y" name="latlon2"id="latlon2"/></td>
                        </tr>
                        <tr>
                            <td colspan="2" align='center'>
                            <select id="selecao" onchange="escolha()">
                                <option> 
                                </option>
                                <option>
                                    Busca em Largura
                                </option>
                                <option>
                                    Busca A* com heuristica de rota
                                </option>
                                <option>
                                    Busca A* com heuristica de radar
                                </option>
                                <option>
                                    Busca Gulosa com heuristica de rota
                                </option>
                                <option>
                                    Busca Gulosa com heuristica de radar
                                </option>
                            </select>
                        </td>
                        </tr>
                    </table>
                </form>
            </center>
        </div>
    </body>
</html>