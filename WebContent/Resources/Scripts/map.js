var map;

var current = new Date().getFullYear();
	min = current - 20;
	select = document.getElementById('year');

for (var i = current; i >= min; i--) {
	var option = document.createElement('option');
	option.value = i;
	option.innerHTML = i;
	select.appendChild(option);
}

function initMap() {
	map = new google.maps.Map(document.getElementById('map'), {
	  center: {lat: 37.09024, lng: -95.712891}, // approximate center of US
	  zoom: 4 // 0 = most zoomed out
	});
}

function expand() {
    var display = document.getElementById("content").style.display;
    if (display === "none") {
    	document.getElementById("left-panel").style = "width:20%; transition-duration: 1s;";
    	document.getElementById("expander").style = "left:20%;transition-duration:1s;"
    	setTimeout(displayContent, 1000);
    }
	else {
	  document.getElementById("left-panel").style = "width:0; transition-duration: 1s;";
	  document.getElementById("content").style = "display:none;";
	  document.getElementById("expander").style = "left:0;transition-duration: 1s;"
	}
}

function displayContent() {
	 document.getElementById("content").style = "display:inline;";
 }

function toggleCheck(checkbox) {
    if (checkbox.checked == true) {
    	if (checkbox.name === "compactness") 
		document.getElementById("compactness-expanded").style.display = "inline";
		else if (checkbox.name === "populationEquality") 
			document.getElementById("population-equality-expanded").style.display = "inline";
		else if (checkbox.name === "consistency") 
			document.getElementById("consistency-expanded").style.display = "inline";
		else if (checkbox.name === "partisanSymmetry") 
			document.getElementById("partisan-symmetry-expanded").style.display = "inline";
		else if (checkbox.name === "politicalFairness") 
			document.getElementById("political-fairness-expanded").style.display = "inline";
		else if (checkbox.name === "alignment") 
			document.getElementById("alignment-expanded").style.display = "inline";
	}
	else {
		if (checkbox.name === "compactness") 
			document.getElementById("compactness-expanded").style.display = "none";
		else if (checkbox.name === "populationEquality") 
			document.getElementById("population-equality-expanded").style.display = "none";
		else if (checkbox.name === "consistency") 
			document.getElementById("consistency-expanded").style.display = "none";
		else if (checkbox.name === "partisanSymmetry") 
			document.getElementById("partisan-symmetry-expanded").style.display = "none";
		else if (checkbox.name === "politicalFairness") 
			document.getElementById("political-fairness-expanded").style.display = "none";
		else if (checkbox.name === "alignment") 
			document.getElementById("alignment-expanded").style.display = "none";
	 }
}
  
  
$(function() {
  $('.slider').on('input change', function(){
	  $(this).next($('.slider_label')).html(this.value);
  });
  $('.slider_label').each(function(){
	  var value = $(this).prev().attr('value');
      $(this).html(value);
  });
});
  
  
// This example adds a search box to a map, using the Google Place Autocomplete
// feature. People can enter geographical searches. The search box will return a
// pick list containing a mix of places and predicted search terms.
// This example requires the Places library. Include the libraries=places
// parameter when you first load the API. For example:
// <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places">
function initAutocomplete() {
	var map = new google.maps.Map(document.getElementById('map'), {
		zoom: 4,
		center: {lat: 37, lng: -97}
	});

	var usaCtaLayer = new google.maps.KmlLayer({
	  url: 'https://developers.google.com/kml/documentation/us_states.kml',
	          map: map,
	          preserveViewport: true
	});
          
    var oregonLayer = new google.maps.Data();
    oregonLayer.loadGeoJson('https://raw.githubusercontent.com/spitlord/election/master/WebContent/Resources/Layers/Oregon.json');
 
    var ohioLayer = new google.maps.Data();
    ohioLayer.loadGeoJson('https://raw.githubusercontent.com/spitlord/election/master/WebContent/Resources/Layers/Ohio.json');
  
    var massachusettsLayer = new google.maps.Data();
    massachusettsLayer.loadGeoJson('https://raw.githubusercontent.com/spitlord/election/master/WebContent/Resources/Layers/Massachusetts.json');
 
    usaCtaLayer.addListener('click', function(event) {
    	var name = event.featureData.name;
    	if (name.includes("Massachusetts"))  {
	        massachusettsLayer.setMap(map);
	        ohioLayer.setMap(null);
	        oregonLayer.setMap(null);
    	}
    	else if (name.includes("Ohio")) {
	        massachusettsLayer.setMap(null);
	        ohioLayer.setMap(map);
	        oregonLayer.setMap(null);
    	}
    	else if (name.includes("Oregon")) {
	        massachusettsLayer.setMap(null);
	        ohioLayer.setMap(null);
	        oregonLayer.setMap(map);
    	}
   });         
    google.maps.event.addListener(map, 'click', function(event) {
    console.log(event.latLng.lat(),event.latLng.lng());
});

// Create the search box and link it to the UI element.
var input = document.getElementById('pac-input');
var searchBox = new google.maps.places.SearchBox(input);
map.controls[google.maps.ControlPosition.TOP_RIGHT].push(input);

// Bias the SearchBox results towards current map's viewport.
map.addListener('bounds_changed', function() {
  searchBox.setBounds(map.getBounds());
});

var markers = [];
// Listen for the event fired when the user selects a prediction and retrieve
// more details for that place.
searchBox.addListener('places_changed', function() {
  var places = searchBox.getPlaces();

  if (places.length == 0) {
    return;
  }

  // Clear out the old markers.
  markers.forEach(function(marker) {
    marker.setMap(null);
  });
  markers = [];

  // For each place, get the icon, name and location.
  var bounds = new google.maps.LatLngBounds();
  places.forEach(function(place) {
    if (!place.geometry) {
      console.log("Returned place contains no geometry");
      return;
    }
    var icon = {
      url: place.icon,
      size: new google.maps.Size(71, 71),
      origin: new google.maps.Point(0, 0),
      anchor: new google.maps.Point(17, 34),
      scaledSize: new google.maps.Size(25, 25)
    };

    // Create a marker for each place.
    markers.push(new google.maps.Marker({
      map: map,
      icon: icon,
      title: place.name,
      position: place.geometry.location
    }));

    if (place.geometry.viewport) {
      // Only geocodes have viewport.
          bounds.union(place.geometry.viewport);
        } else {
          bounds.extend(place.geometry.location);
        }
      });
      map.fitBounds(bounds);
    });
  }
