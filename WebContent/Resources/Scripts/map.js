

function initialize() {
    var map = initMap();
    initSearchBox(map);
}


function initMap() {
    // initializing a map
	map = new google.maps.Map(document.getElementById('map'), {
	  center: {lat: 37.09024, lng: -95.712891}, // approximate center of US
	  zoom: 4 // 0 = most zoomed out
	});
    

    // creating a layer
    var usaLayer = new google.maps.Data();
    usaLayer.loadGeoJson(properties.usaLayer);
    usaLayer.setMap(map);     
    
    
//    styling map:
//    usaLayer.setStyle(
//        {fillColor: 'green'}   // styling object, key-value
//    );  
 
    
// styling a particular feature:
    usaLayer.addListener('mouseover', function(event) {
        selected = event.feature;
        usaLayer.setStyle(function(feature) {
            if(feature == selected) {
                usaLayer.overrideStyle(selected, {fillColor: 'green'});
            }
            else {
                usaLayer.overrideStyle(feature, {fillColor: 'red'});
            }
        });
    });
 
 
    
    var oregonLayer = new google.maps.Data();
    oregonLayer.loadGeoJson(properties.oregonPrecinctsLayer);
    var oregonDistrictsLayer = new google.maps.Data();
    oregonDistrictsLayer.loadGeoJson(properties.oregonDistrictsLayer);
    oregonDistrictsLayer.setMap(map)
    
    var ohioPrecinctsLayer = new google.maps.Data();
    ohioPrecinctsLayer.loadGeoJson(properties.ohioPrecinctsLayer);
  
    var massachusettsPrecinctsLayer = new google.maps.Data();
    massachusettsPrecinctsLayer.loadGeoJson(properties.massachusettsPrecinctsLayer);
 
          
    google.maps.event.addListener(map, 'click', function(event) {
    console.log(event.latLng.lat(),event.latLng.lng());
});
    
    return map;
    
}

  

