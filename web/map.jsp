<!DOCTYPE html>
<html>
  <head>
    <!-- Font Awesome Icons -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    <!-- GoogleAPI jQuery CDN -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

    <!-- our own stylesheet -->
    <link rel="stylesheet" type="text/css" href="Resourses/Styles/home-style.css">

    <title>U.S. Redistricting</title>
    <meta name="viewport" content="initial-scale=1.0">
    <meta charset="utf-8">
  </head>
  <body>
    <div id="map"></div>
    <div id="left-panel">
      <div id="content" style="display:inline;">
      <div id="tabs">
        <button type="button" class="btn btn-default active" title="Customize Values">
          <i class="fas fa-sliders-h"></i>
        </button>

        <button type="button" class="btn btn-default" title="User Settings" onclick="window.location.href='login.html'">
          <i class="fas fa-user"></i>
        </button>

        <button type="button" class="btn btn-default" title="About/FAQ" onclick="window.location.href='faq.html'">
          <i class="fas fa-question-circle"></i>
        </button>
      </div>

      <div id="customize-panel">
        <form id="options-form">
          <h2>Objective Function Factors</h2>

          <div class="form-group">
           <label for="year">Election Data Year</label>
           <select class="form-control" id="year" name="year">
             <option value="2017">2017</option>
             <option value="2016">2016</option>
             <option value="2015">2015</option>
             <option value="2014">2014</option>
             <option value="2013">2013</option>
             <option value="2012">2012</option>
             <option value="2011">2011</option>
             <option value="2010">2010</option>
           </select>
         </div>

          <div class="form-check">
            <label class="form-check-label">
              <input type="checkbox" class="form-check-input" onchange="toggleCheck(this);">
              Compactness
            </label>
          </div>

          <div id="compactness-expanded" style="display:none;">
            <p><input type="range" name="compactness" min="0" max="1" value="0" step="0.01" class="slider">
          <span  class="slider_label"></span></p>

            <p><label for="range_weight">Polsby-Popper: </label>
              <input type="range" name="polsby" min="0" max="1" value="0" step="0.01" class="sub slider">
            <span  class="slider_label"></span></p>

            <p><label for="range_weight">Schwartzberg: </label>
              <input type="range" name="schwartzberg" min="0" max="1" value="0" step="0.01" class="sub slider">
            <span  class="slider_label"></span></p>

            <p><label for="range_weight">Reock Score: </label>
              <input type="range" name="reock" min="0" max="1" value="0" step="0.01" class="sub slider">
            <span style="display:inline;"class="slider_label"></span></p>
          </div>

          <div class="form-check">
            <label class="form-check-label">
              <input type="checkbox" class="form-check-input">
              Population Equality
            </label>
          </div>

          <div class="form-check">
            <label class="form-check-label">
              <input type="checkbox" class="form-check-input">
              Consistency
            </label>
          </div>

          <div class="form-check">
            <label class="form-check-label">
              <input type="checkbox" class="form-check-input">
              Partisan Symmetry
            </label>
          </div>

          <div class="form-check">
            <label class="form-check-label">
              <input type="checkbox" class="form-check-input">
              Political Fairness
            </label>
          </div>

          <div class="form-check">
            <label class="form-check-label">
              <input type="checkbox" class="form-check-input">
              Alignment
            </label>
          </div>

          <button id="submit-btn" type="submit" onclick="window.alert('Please make sure weights add up to 1!');" class="btn btn-success">CALCULATE</button>
      </form>
  </div> <!-- end customize panel div -->

    <div id="user-settings-panel" style="display:none;"> <!-- will be displayed if user presses user button -->
      <h2>User Preferences</h2>
  </div> <!-- end user settings panel div -->
</div> <!-- end content div -->
  </div> <!-- end left-panel div -->

  <div id="expander">
    <button onclick="expand();"><i class="fas fa-arrows-alt-h"></i></button>
  </div>
    <div id="search">
      <input id="pac-input" class="controls" type="text" placeholder=" Search...">
    </div>



    <div id="user-controls">
      <button type="button" class="btn btn-default disabled" title="Save Redistricting" onclick="window.alert('You must be signed in to use this feature!');">
        <i class="far fa-save"></i>
      </button>

      <button type="button" class="btn btn-default disabled" title="Load Redistricting" onclick="window.alert('You must be signed in to use this feature!');">
        <i class="fas fa-upload"></i>
      </button>

      <button type="button" class="btn btn-default disabled" title="View Saved List" onclick="window.alert('You must be signed in to use this feature!');">
        <i class="fas fa-list-ul"></i>
      </button>

      <button type="button" class="btn btn-default" title="Pause Algorithm" onclick="window.alert('Pausing algorithm...');">
        <i class="far fa-pause-circle"></i>
      </button>

      <button type="button" class="btn btn-default" title="Cancel Algorithm" onclick="window.alert('Cancelling algorithm...');">
        <i class="far fa-times-circle"></i>
      </button>


    </div>
    <div id="console">
      <p>Welcome, ${sessionScope.username} <p>
      <p>/Console log</p>
      <p>Server is currently active.<p>
    </div>

    <script>
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
    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDCrjByEUtH5awpWcjFJqLBB7W8npgKKGo&callback=initMap"
    async defer></script>

    <script>
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

        var ctaLayer = new google.maps.KmlLayer({
          url: 'https://developers.google.com/kml/documentation/us_states.kml',
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

    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDCrjByEUtH5awpWcjFJqLBB7W8npgKKGo&libraries=places&callback=initAutocomplete"
         async defer></script>
  </body>
</html>
