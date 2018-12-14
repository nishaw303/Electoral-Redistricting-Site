// *** GLOBAL VARIABLES ***
var colors = ['#A2A79E', '#2B2D42', '#8D99AE', '#C5D5E8', '#476C9B',
	'#EAE8FF','#DFDCE1', '#ADACB5', '#2D3142', '#B0D7FF'];
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
	  zoom: 4 // 0 = most zoomed out
	});

    // creating a layer
    usaLayer = new google.maps.Data();
    usaLayer.loadGeoJson(properties.usaLayer);
    usaLayer.setMap(map);

    // styling map:
    usaLayer.setStyle({
    	fillColor: 'silver',
    	strokeWeight: 1,
    	fillOpacity: 0.3
    });
    
    usaLayer.forEach(function(feature) {
    	if(feature.getProperty('name') == "Oregon" |
        		feature.getProperty('name') == "Ohio" |
        		feature.getProperty('name') == "Massachusetts") {
    		console.log("yes");
            usaLayer.overrideStyle(feature, {fillOpacity: 0.8});
        }
    });


    // sets selected state
	usaLayer.addListener('click', function(event) {
		var state = event.feature;
//		usaLayer.overrideStyle(function(feature) {
//			if (feature == state) {
//				usaLayer.overrideStyle(selected, {strokeWidth: 3});
//			}
//		})
		usaLayer.overrideStyle(selected, {strokeWidth: 3});
		setSelected(state);
	
	});
    
    // styling a particular feature:
    usaLayer.addListener('mouseover', function(event) {
        selected = event.feature;
        
            if(selected.getProperty('name') != selectedState && 
            		(selected.getProperty('name') == "Oregon" |
            		selected.getProperty('name') == "Ohio" |
            		selected.getProperty('name') == "Massachusetts")) {
                usaLayer.overrideStyle(selected, {fillColor: 'lightblue', fillOpacity: 0.8});
            }
    });

    usaLayer.addListener('mouseout', function(event) {
    	selected = event.feature;
    	if (selected.getProperty('name') != selectedState) {
    		usaLayer.revertStyle(selected);
    	}
    });
    
    

    orLayer = new google.maps.Data();
    orLayer.loadGeoJson(properties.orPrecinctsLayer);
    orLayer.setStyle({
    	strokeWeight: 1	
    });

    ohLayer = new google.maps.Data();
    ohLayer.loadGeoJson(properties.ohPrecinctsLayer);
    ohLayer.setStyle({
    	strokeWeight: 1	
    });

    maLayer = new google.maps.Data();
    maLayer.loadGeoJson(properties.maPrecinctsLayer);
    maLayer.setStyle({
    	strokeWeight: 1	
    });

    google.maps.event.addListener(map, 'click', function(event) {
    	console.log(event.latLng.lat(),event.latLng.lng());
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
	if (selectedState != null) {
		if (state != selectedState) {
			//map.data.revertStyle();
		}
	}
	selectedState = state.getProperty('name'); // setting global variable
	setCurrentLayer(selectedState);
	
  // change color, border, zoom, pan, etc of selected state as necessary
}

function setCurrentLayer(selectedState) {
  if (selectedState == "Oregon") {
	  currentLayer = orLayer;
	  maLayer.setMap(null);
	  orLayer.setMap(null);
	  orLayer.setMap(map);

  } else if (selectedState == "Ohio") {
	  currentLayer = ohLayer;
	  maLayer.setMap(null);
	  orLayer.setMap(null);
	  ohLayer.setMap(map);

  } else if (selectedState == "Massachusetts") {
	  currentLayer = maLayer;
	  ohLayer.setMap(null);
	  orLayer.setMap(null);
	  maLayer.setMap(map);

  }
  
  console.log("current layer set to: " + selectedState)

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
	

	currentLayer.addListener('mouseover', function(event) {
		var precinct = event.feature;
		//var contentString = getInfo(precinct);
		//infowindow.setContent(contentString);
		  infowindow.setPosition(event.latLng);
		infowindow.open(map);
	});

	currentLayer.addListener('mouseout', function(event) {
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
	  '<p><b>Democrat:</b>' + feature.VotingData.democrat + '%<span class="democrat box"></span></p>'+
	  '<p><b>Republican:</b>' + feature.VotingData.republican + '%<span class="republican box"></span></p>'+
	  '<hr></hr>'+
	  '<p><b>Black:</b>' + feature.DemographicData.black + '%<span class="black box"></span></p>'+
	  '<p><b>White:</b>' + feature.DemographicData.white + '%<span class="white box"></span></p>'+
	  '<p><b>Other:</b>' + feature.DemographicData.other + '%<span class="other box"></span></p>'+
	  '</div>'+
	  '</div>';

	return contentString;
}

//*** ALGORITHM UPDATE FUNCTIONS ***
function displayMoves() {
	var moves = getMoves();
	if (moves != -1) {
		moves.forEach(move => {
			showMovePrecinct(move);
		});
	}
}

function getMoves(numMoves) {
  var moves = -1;
  // get 10 from backend
  function request() {
      $.ajax({
          url: 'updating',
          type: 'GET',
          dataType: 'json',
          success: function (jsonObject) {
              moves = jsonObject;
          },
          error: function (error) {
              alert("Moves still in progress");
          }
      });
   }

  // var i = 0;
  // var interval = setInterval(request, 250);
  request();
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
