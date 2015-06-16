var map;
function initialize() {
    
    var mapOptions = {
        center: new google.maps.LatLng(-22.901808, -43.126412),
        zoom: 16
    };
    map = new google.maps.Map(document.getElementById("map_canvas"),
            mapOptions);
}
function escolha(){
    if($("option:selected").text()!==""){
        $("#formulario").append("<input type='hidden' value='"+$("option:selected").text()+"' name='funcao'/>");
        $("table").append("<tr><td colspan='2'align='center'><input type='text' value='Aguarde o caminho carregar' name='msg' disabled/></td></tr>");
        $("#formulario").submit();
    }
}
google.maps.event.addDomListener(window, 'load', initialize);