function init(init) {
    
    var coords = ("" + init).split(" ");
    var mapOptions = {
        center: new google.maps.LatLng(coords[0], coords[1]),
        zoom: 16,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var image = {
        url: 'image/bolinha_verde.png',
        size: new google.maps.Size(15, 15)
    };
    var map = new google.maps.Map(document.getElementById("map_canvas"),
            mapOptions);
    for (var i = 0; i < coords.length; i = i + 2) {
        new google.maps.Marker({
            position: new google.maps.LatLng(coords[i], coords[i + 1]),
            map: map,
            title: ((i+2)/2).toString(),
            icon: image
        });
    }
}