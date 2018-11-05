<!DOCTYPE html>
<html>
  <head>
      
    <!-- GoogleAPI jQuery CDN -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <!-- Font Awesome Icons -->
      
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>


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
           <label for="typoooooo">Election Type</label>
           <select class="form-control" id="typeOfElection" name="typeofelection">
             <option value="Congressional">Congressional</option>
             <option value="Presidential">Presidential</option>
             <option value="Legislative">Legislative</option>
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
          
            <div id="equality-expanded" style="display:none;">
            <p><input type="range" name="equality" min="0" max="1" value="0" step="0.01" class="slider">
          <span  class="slider_label"></span></p>

          <div class="form-check">
            <label class="form-check-label">
              <input type="checkbox" class="form-check-input">
              Consistency
            </label>
          </div>

        <div id="consistency-expanded" style="display:none;">
        <p><input type="range" name="consistency" min="0" max="1" value="0" step="0.01" class="slider">
      <span  class="slider_label"></span></p>

          <div class="form-check">
            <label class="form-check-label">
              <input type="checkbox" class="form-check-input">
              Partisan Symmetry
            </label>
          </div>
        
          <div id="symmetry-expanded" style="display:none;">
            <p><input type="range" name="symmetry" min="0" max="1" value="0" step="0.01" class="slider">
          <span  class="slider_label"></span></p>

          <div class="form-check">
            <label class="form-check-label">
              <input type="checkbox" class="form-check-input">
              Political Fairness
            </label>
          </div>
            
            <div id="fairness-expanded" style="display:none;">
            <p><input type="range" name="fairness" min="0" max="1" value="0" step="0.01" class="slider">
          <span  class="slider_label"></span></p>

          <div class="form-check">
            <label class="form-check-label">
              <input type="checkbox" class="form-check-input">
              Alignment
            </label>
          </div>
            
              <div id="alignment-expanded" style="display:none;">
            <p><input type="range" name="alignment" min="0" max="1" value="0" step="0.01" class="slider">
          <span  class="slider_label"></span></p>

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

    <script src="Resourses/Scripts/map.js"> </script>
    
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDCrjByEUtH5awpWcjFJqLBB7W8npgKKGo&callback=initMap"
    async defer></script>

    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDCrjByEUtH5awpWcjFJqLBB7W8npgKKGo&libraries=places&callback=initAutocomplete"
         async defer></script>
  </body>
</html>
