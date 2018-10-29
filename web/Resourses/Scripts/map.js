var map;
      function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
          center: {lat: 37.09024, lng: -95.712891}, // approximate center of US
          zoom: 4 // 0 = most zoomed out
        });
      }

      function expand() {
        console.log("Reached expand");
        var display = document.getElementById("content").style.display;
        console.log(display);
        if (display === "none") {
          console.log("none");
          document.getElementById("left-panel").style = "width:20%; transition-duration: 1s;";
          document.getElementById("expander").style = "left:20%;transition-duration:1s;"
          setTimeout(displayContent, 1000);
        }
        else {
          console.log("else");
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
          document.getElementById("compactness-expanded").style.display = "inline";
        }
        else {
          document.getElementById("compactness-expanded").style.display="none";
        }
      }
      // function to update slider values
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
        
          
        var oregonCtaLayer = new google.maps.KmlLayer({
          url: 'https://www.dropbox.com/s/rfkjs7onbk7mowf/Oregon.json?dl=0',
          map: map,
          preserveViewport: true
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
