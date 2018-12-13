

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

 
 
    
    var orLayer = new google.maps.Data();
    orLayer.loadGeoJson(properties.oregonPrecinctsLayer);
    var orDistrictsLayer = new google.maps.Data();
    orDistrictsLayer.loadGeoJson(properties.orDistrictsLayer);
    orDistrictsLayer.setMap(map)
    
    var ohPrecinctsLayer = new google.maps.Data();
    ohPrecinctsLayer.loadGeoJson(properties.ohPrecinctsLayer);
  
    var maPrecinctsLayer = new google.maps.Data();
    maPrecinctsLayer.loadGeoJson(properties.maPrecinctsLayer);
 
          
    google.maps.event.addListener(map, 'click', function(event) {
    console.log(event.latLng.lat(),event.latLng.lng());
});
    
    return map;
    
}




function movePrecinct(layer) {} // update the color of one precinct



function chooseColors(number) {
    var colors = []
    return colors;
}

  

