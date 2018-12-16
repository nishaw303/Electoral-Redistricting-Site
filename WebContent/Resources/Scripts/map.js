// *** GLOBAL VARIABLES ***
var colors = ['#D3684E', '#5386E4', '#4B5363', '#73CEE2', '#B7BDE8', '#B1C6C1', 
			  '#36413E', '#F7B538', '#DB7C26', '#780116', '#F48484', '#CDE2BA'];
var currYear;
var selectedState = null;
var currentLayer;
var map;
var usaLayer;
var orLayer;
var ohLayer;
var maLayer;

// **** MAPS API FUNCTIONS ******
function initialize() {
    map = initMap();
    initSearchBox(map);
}

function initMap() {
    // initializing a map
	map = new google.maps.Map(document.getElementById('map'), {
	  center: {lat: 37.09024, lng: -95.712891}, // approximate center of US
	  zoom: 4, // 0 = most zoomed out
	  styles: [
		  {
			    "elementType": "geometry",
			    "stylers": [
			      {
			        "color": "#f5f5f5"
			      }
			    ]
			  },
			  {
			    "elementType": "geometry.stroke",
			    "stylers": [
			      {
			        "color": "#000000"
			      },
			      {
			        "visibility": "on"
			      }
			    ]
			  },
			  {
			    "elementType": "labels.icon",
			    "stylers": [
			      {
			        "visibility": "off"
			      }
			    ]
			  },
			  {
			    "elementType": "labels.text.fill",
			    "stylers": [
			      {
			        "color": "#616161"
			      }
			    ]
			  },
			  {
			    "elementType": "labels.text.stroke",
			    "stylers": [
			      {
			        "color": "#f5f5f5"
			      }
			    ]
			  },
			  {
			    "featureType": "administrative",
			    "elementType": "labels.text.fill",
			    "stylers": [
			      {
			        "color": "#000000"
			      }
			    ]
			  },
			  {
			    "featureType": "administrative.land_parcel",
			    "elementType": "labels.text.fill",
			    "stylers": [
			      {
			        "color": "#bdbdbd"
			      }
			    ]
			  },
			  {
			    "featureType": "landscape.natural",
			    "elementType": "geometry.fill",
			    "stylers": [
			      {
			        "color": "#f3f3f5"
			      }
			    ]
			  },
			  {
			    "featureType": "landscape.natural.terrain",
			    "elementType": "geometry.fill",
			    "stylers": [
			      {
			        "color": "#c2c5c7"
			      }
			    ]
			  },
			  {
			    "featureType": "poi",
			    "elementType": "geometry",
			    "stylers": [
			      {
			        "color": "#eeeeee"
			      }
			    ]
			  },
			  {
			    "featureType": "poi",
			    "elementType": "labels.text",
			    "stylers": [
			      {
			        "visibility": "off"
			      }
			    ]
			  },
			  {
			    "featureType": "poi",
			    "elementType": "labels.text.fill",
			    "stylers": [
			      {
			        "color": "#757575"
			      }
			    ]
			  },
			  {
			    "featureType": "poi.business",
			    "stylers": [
			      {
			        "visibility": "off"
			      }
			    ]
			  },
			  {
			    "featureType": "poi.park",
			    "elementType": "geometry",
			    "stylers": [
			      {
			        "color": "#e5e5e5"
			      }
			    ]
			  },
			  {
			    "featureType": "poi.park",
			    "elementType": "labels.text.fill",
			    "stylers": [
			      {
			        "color": "#9e9e9e"
			      }
			    ]
			  },
			  {
			    "featureType": "road",
			    "stylers": [
			      {
			        "visibility": "off"
			      }
			    ]
			  },
			  {
			    "featureType": "road",
			    "elementType": "geometry",
			    "stylers": [
			      {
			        "color": "#ffffff"
			      }
			    ]
			  },
			  {
			    "featureType": "road",
			    "elementType": "labels.icon",
			    "stylers": [
			      {
			        "visibility": "off"
			      }
			    ]
			  },
			  {
			    "featureType": "road.arterial",
			    "elementType": "labels.text.fill",
			    "stylers": [
			      {
			        "color": "#757575"
			      }
			    ]
			  },
			  {
			    "featureType": "road.highway",
			    "elementType": "geometry",
			    "stylers": [
			      {
			        "color": "#dadada"
			      }
			    ]
			  },
			  {
			    "featureType": "road.highway",
			    "elementType": "labels.text.fill",
			    "stylers": [
			      {
			        "color": "#616161"
			      }
			    ]
			  },
			  {
			    "featureType": "road.local",
			    "elementType": "labels.text.fill",
			    "stylers": [
			      {
			        "color": "#9e9e9e"
			      }
			    ]
			  },
			  {
			    "featureType": "transit",
			    "stylers": [
			      {
			        "visibility": "off"
			      }
			    ]
			  },
			  {
			    "featureType": "transit.line",
			    "elementType": "geometry",
			    "stylers": [
			      {
			        "color": "#e5e5e5"
			      }
			    ]
			  },
			  {
			    "featureType": "transit.station",
			    "elementType": "geometry",
			    "stylers": [
			      {
			        "color": "#eeeeee"
			      }
			    ]
			  },
			  {
			    "featureType": "water",
			    "elementType": "geometry",
			    "stylers": [
			      {
			        "color": "#c9c9c9"
			      }
			    ]
			  },
			  {
			    "featureType": "water",
			    "elementType": "geometry.fill",
			    "stylers": [
			      {
			        "color": "#e2edf5"
			      }
			    ]
			  },
			  {
			    "featureType": "water",
			    "elementType": "labels.text.fill",
			    "stylers": [
			      {
			        "color": "#9e9e9e"
			      }
			    ]
			  }
			]
	});


    // creating a layer
    usaLayer = new google.maps.Data();
    usaLayer.loadGeoJson(properties.usaLayer, {}, function(features) {
    	usaLayer.forEach(function(feature) {
	    	if(feature.getProperty('name') == "Oregon" | feature.getProperty('name') == "Ohio" 
	    		| feature.getProperty('name') == "Massachusetts") {
	            usaLayer.overrideStyle(feature, {fillColor: 'silver', fillOpacity: 0.4, strokeWeight: 1});
	        }
    	});
    });

    //globally styling map, revertStyle will revert ovverriden styles back to this.
    usaLayer.setStyle({
    	strokeWeight: 0,
    	fillOpacity: 0
    });
    
    usaLayer.setMap(map);


    // sets selected state
	usaLayer.addListener('click', function(event) {
    	// console.log(event.latLng.lat(),event.latLng.lng());
		var state = event.feature;
		usaLayer.revertStyle(state);
		if (state != selectedState && (state.getProperty('name') == "Oregon" 
			| state.getProperty('name') == "Ohio" | state.getProperty('name') == "Massachusetts")) {
                usaLayer.overrideStyle(selectedState, {fillColor: 'silver', fillOpacity: 0.4, strokeWeight: 1});
    			setSelected(state);	
		}
	});
    
    usaLayer.addListener('mouseover', function(event) {
        selected = event.feature;
        if (selected != selectedState) {
        	usaLayer.revertStyle(selected);
        	usaLayer.overrideStyle(selected, {fillColor: 'lightblue', fillOpacity: 0.4, strokeWeight: 1});
        }
    });

    usaLayer.addListener('mouseout', function(event) {
    	selected = event.feature;
    	if (selected != selectedState) {
    		usaLayer.revertStyle(selected);
    		if(selected.getProperty('name') == "Oregon" | selected.getProperty('name') == "Ohio" 
    			| selected.getProperty('name') == "Massachusetts") {
                usaLayer.overrideStyle(selected, {fillColor: 'silver', fillOpacity: 0.4, strokeWeight: 1});
            }

    	}
    });
    
    orLayer = new google.maps.Data();
    orLayer.loadGeoJson(properties.orPrecinctsLayer);
    orLayer.setStyle({
    	fillColor: 'lightblue',
    	fillOpacity: 0.4,
    	strokeWeight: 1
    });

    ohLayer = new google.maps.Data();
    ohLayer.loadGeoJson(properties.ohPrecinctsLayer);
    ohLayer.setStyle({
    	fillColor: 'lightblue',
    	fillOpacity: 0.4,
    	strokeWeight: 1
    });

    maLayer = new google.maps.Data();
    maLayer.loadGeoJson(properties.maPrecinctsLayer);
    maLayer.setStyle({
    	fillColor: 'lightblue',
    	fillOpacity: 0.4,
    	strokeWeight: 1
    });

	return map;

}


// ** PREFERENCE MANAGEMENT **
function saveWeights() {
  //if (! user logged in) {
  //  window.alert('You must be signed in to use this feature!');
  //}
}

function loadWeights() {
  //if (! user logged in) {
  //  window.alert('You must be signed in to use this feature!');
  //}

}

function viewSaved() {
  //if (! user logged in) {
  //  window.alert('You must be signed in to use this feature!');
  //}
}

// *** MAP CONTROL FUNCTIONS **
function pause() {
  window.alert('Pausing algorithm...');
}

function play() {

}

function stop() {
  window.alert('Cancelling algorithm...');

}

function save() {
  //if (! user logged in) {
  //  window.alert('You must be signed in to use this feature!');
  //}

}

function load(filename) {
  //if (! user logged in) {
  //  window.alert('You must be signed in to use this feature!');
  //}
}

function deleteFile(filename) {

}

function setSelected(state) {
	selectedState = state; // setting global variable
	setCurrentLayer(selectedState);
}

function setCurrentLayer(selectedState) {
  if (selectedState.getProperty('name') == "Oregon") {
	  currentLayer = orLayer;
	  maLayer.setMap(null);
	  ohLayer.setMap(null);
	  orLayer.setMap(map);

  } else if (selectedState.getProperty('name') == "Ohio") {
	  currentLayer = ohLayer;
	  maLayer.setMap(null);
	  orLayer.setMap(null);
	  ohLayer.setMap(map);

  } else if (selectedState.getProperty('name') == "Massachusetts") {
	  currentLayer = maLayer;
	  ohLayer.setMap(null);
	  orLayer.setMap(null);
	  maLayer.setMap(map);

  }
  
  console.log("current layer set to: " + selectedState.getProperty('name'));

  // info popup upon clicking a precinct
  var contentString = '<div id="content">'+
  '<div id="siteNotice">'+
  '</div>'+
  '<h1 id="firstHeading" class="firstHeading">2016</h1>'+
  '<div id="bodyContent">'+
  '<p><b>Democrat:</b> 50% <span class="democrat box"></span></p>'+
  '<p><b>Republican:</b> 50% <span class="republican box"></span></p>'+
  '<hr></hr>'+
  '<p><b>Black:</b> 30% <span class="black box"></span></p>'+
  '<p><b>White:</b> 55% <span class="white box"></span></p>'+
  '<p><b>Other:</b> 5% <span class="other box"></span></p>'+
  '</div>'+
  '</div>';

	var infowindow = new google.maps.InfoWindow({
		content: contentString,
		maxWidth: 200
	});
	
	
	orLayer.addListener('mouseover', function(event) {
		var precinct = event.feature;
		  //var contentString = getInfo(precinct);
		  //infowindow.setContent(contentString);
		  infowindow.setPosition(event.latLng);
		  infowindow.open(map);
	});

	orLayer.addListener('mouseout', function(event) {
		  var precinct = event.feature;
		  infowindow.close(map);
	});

}

function getInfo(precinct) {
	var contentString = '<div id="content">'+
	  '<div id="siteNotice">'+
	  '</div>'+
	  '<h1 id="firstHeading" class="firstHeading"> ' + currYear + '</h1>'+
	  '<div id="bodyContent">'+
	  '<p><b>Democrat:</b>' + feature.VotingData1['0'] + '%<span class="democrat box"></span></p>'+
	  '<p><b>Republican:</b>' + feature.VotingData1['1'] + '%<span class="republican box"></span></p>'+
	  '<hr></hr>'+
	  '<p><b>Black:</b>' + feature.DemographicData.black + '%<span class="black box"></span></p>'+
	  '<p><b>White:</b>' + feature.DemographicData.white + '%<span class="white box"></span></p>'+
	  '<p><b>White:</b>' + feature.DemographicData.asian + '%<span class="asian box"></span></p>'+
	  '<p><b>Other:</b>' + feature.DemographicData.other + '%<span class="other box"></span></p>'+
	  '</div>'+
	  '</div>';

	return contentString;
}

//*** ALGORITHM UPDATE FUNCTIONS ***
function startAlgorithm() {
	// initialize algorithm
	function request() {
        $.ajax({
            url: 'calculate',
            type: 'POST',
            dataType: 'json',
            success: function (response) { 
               console.log(response);
               updateMapManager();
            },
            error: function (error) {
                console.log(error);
                console.log("Could not initialize algorithm.");
            }
        });
	}	
	request();
}


function updateMapManager() {
	var interval;
	
    function request() {
    	$.ajax({
   
        url: 'update',
        type: 'GET',
        dataType: 'json',
        success: function (response) {
            console.log(response);
        	if (!response.done) {
        		if (response.movesReady) {
        			displayMoves();
        		}
        	} else clearInterval(interval);   
        },
        error: function (error) {
            console.log("request failed in update map manager");
        }
    });
}
    // TODO: add pause logic to pause interval 
    interval = setInterval(request, 2000);
 }

function displayMoves() {
	var moves = getMoves();
	
	if (moves != -1) {
		moves.forEach(move => {
			showMovePrecinct(move);
		});
	}
	
}


  // get 10 from backend
  function getMoves() {
	  var moves = -1;
      $.ajax({
          url: 'updating',
          type: 'GET',
          dataType: 'json',
          success: function (jsonObject) {
              moves = jsonObject;
          },
          error: function (error) {
              alert("oooooo");
          }
      });
      if (moves = -1) alert("moves -1!")
      return moves;
   }

function showMovePrecinct(move) {
  var srcID = move.sourceDistrict;
  var destID = move.destinationDistrict;
  var precinctID = move.precinct;

  var feature = currentLayer.getFeatureById(precinctID);
  if (destID <= colors.length) {
	  var newColor = colors[destID];
  } else {
	  var newColor = colors[destID - (destID-colors.length)];
  }
  currentLayer.setStyle(feature, {fillColor: newColor});

}
