

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
    if (inProgress) {
        if (paused) {
            document.getElementById("updatemsg").innerHTML = "Algorithm is resumed.";
            document.getElementById("pauseicon").innerHTML = "<i class='far fa-pause-circle'>";
            updateMapManager();
        } else {
            document.getElementById("updatemsg").innerHTML = "Algorithm is paused.";
            document.getElementById("pauseicon").innerHTML = "<i class='far fa-play-circle'>";
            clearInterval(interval);
        }
        paused = !paused;
    }
}

function stop() {
    inProgress = false;
    paused = false;
    clearInterval(interval);
    document.getElementById("updatemsg").innerHTML = "Algorithm was cancelled.";
}

function save() {
    if (paused | !inProgress) {
        var fname = prompt("Enter a name for this map", "myMap");
        if (fname != null) { // user did not cancel prompt
            function request() {
                $.ajax({
                    url: 'save',
                    type: 'POST',
                    dataType: 'text',
                    data: {name: fname},
                    success: function (response) {
                        console.log("Map was saved.");
                    },
                    error: function (error) {
                        console.log("Map failed saving.");
                    }
                });
            }
            
            request();
            


        }

    }
}

function load() {
    if (!inProgress) {
        var fname = 25;
        if (fname != null) { // user did not cancel prompt
            function request() {
                $.ajax({
                    url: 'load',
                    type: 'GET',
                    dataType: 'json',
                    data:{name: 'ass'},
                    success: function (response) {
                        displayMoves(response);
                    },
                    error: function (error) {
                        console.log("Map failed loading.");
                    }
                });
            }
            
            request();
            

        }

    }
}

//function deleteFiles(files) {
//	// TODO
//	files.forEach(filename => {
//		if (fileName == "myMap") {
//			document.getElementById("1").innerHTML = "";
//		} else {
//			document.getElementById("2").innerHTML = "";
//
//		}
//
//	});
//}

function selectState(event) {
	var state = event.feature;
	usaLayer.revertStyle(state);
	var stateName = state.getProperty('name');
	if (state != selectedState && stateName == "Oregon"
		| stateName == "Ohio" | stateName == "Massachusetts") {
				usaLayer.overrideStyle(selectedState, {fillColor: 'silver', fillOpacity: 0.4, strokeWeight: 1});
				var latLng = new google.maps.LatLng(state.lat, state.lon);
				map.setCenter(latLng);
				map.setZoom(6);
				setSelected(state);
				document.getElementById('state').value = stateName;
	}
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
 }
 else if (selectedState.getProperty('name') == "Ohio") {
	  currentLayer = ohLayer;
	  maLayer.setMap(null);
	  orLayer.setMap(null);
	  ohLayer.setMap(map);
 }
 else if (selectedState.getProperty('name') == "Massachusetts") {
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
  '<p><b>Democrat:</b> 47% <span class="democrat box"></span></p>'+
  '<p><b>Republican:</b> 33% <span class="republican box"></span></p>'+
  '<hr></hr>'+
  '<p><b>Black:</b> 14% <span class="black box"></span></p>'+
  '<p><b>White:</b> 80% <span class="white box"></span></p>'+
  '<p><b>Other:</b> 6% <span class="other box"></span></p>'+
  '</div>'+
  '</div>';

    var infowindow = new google.maps.InfoWindow({
        content: contentString,
        maxWidth: 200
    });


//

}

