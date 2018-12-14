
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="initial-scale=1.0">
        <meta charset="utf-8">

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
        <link rel="stylesheet" type="text/css" href="Resources/Styles/home-style.css">

        <title>U.S. Redistricting</title>
    </head>
    <body>
        <div id="map"></div>
        <div id="left-panel">
            <div id="content" style="display:inline;">
                <div id="tabs">
                    <button type="button" class="btn btn-default active" title="Customize Values">
                        <i class="fas fa-sliders-h"></i>
                    </button>

                    <button type="button" class="btn btn-default" title="User Settings" onclick="window.location.href = 'login.html'">
                        <i class="fas fa-user"></i>
                    </button>

                    <button type="button" class="btn btn-default" title="About/FAQ" onclick="window.location.href = 'faq.html'">
                        <i class="fas fa-question-circle"></i>
                    </button>
                    
                    <button type="button" class="btn btn-default" title="State Constitution" onclick="window.location.href = 'const.html'">
                        <i class="fas fa-file-alt"></i>
                    </button>
                </div>

                <div id="customize-panel">
                    <form class="form" id="options-form" action="calculate" method="post">
                        <h1>Redistricting Metrics</h1>
                        <div class="form-group row" id="state">
                            <label class="control-label col-sm-4" for="state">
                                <h2>State</h2>
                            </label>
                            <div class="col-sm-8">
                                <select class="form-control" id="state" name="state">
                                    <option name="Massachusetts" value="MA">Massachusets</option>
                                    <option name="Ohio" value="OH">Ohio</option>
                                    <option name="Oregon" value="OR">Oregon</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group row" id="districts">
                            <label class="control-label col-sm-4" for="districts">
                                Districts
                            </label>
                            <div class="col-sm-7">
                                <input class="form-control" type="number" id="numDistricts" name="numDistricts" min="1" max="100">
                            </div>
                        </div>

                        <div class="form-group row" id="electionYear">
                            <label class="control-label col-sm-4" for="year">
                                Election Year
                            </label>
                            <div class="col-sm-7">
                                <select class="form-control" id="year" name="year">
                                </select>
                            </div>
                        </div>

                        <div class="form-group row" id="electionYear">
                            <label class="control-label col-sm-4" for="year">
                                Seed Strategy
                            </label>
                            <div class="col-sm-7">
                                <select class="form-control" id="seedStrategy" name="seedStrategy">
                                    <option>Random</option>
                                    <option>Incumbent</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-check" id="compactness">
                            <label class="form-check-label">
                                <input type="checkbox" name="compactness" class="form-check-input" onchange="toggleCheck(this);">
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

                        <div class="form-check" id="populationEquality">
                            <label class="form-check-label">
                                <input type="checkbox" name="populationEquality" class="form-check-input" onchange="toggleCheck(this);">
                                Population Equality
                            </label>
                        </div>
                        <div id="population-equality-expanded" style="display:none;">
                            <p><input type="range" name="populationEquality" min="0" max="1" value="0" step="0.01" class="slider">
                                <span  class="slider_label"></span></p>
                        </div>

                        <div class="form-check" id="consistency">
                            <label class="form-check-label">
                                <input type="checkbox" name="consistency" class="form-check-input" onchange="toggleCheck(this);">
                                Consistency (*?)
                            </label>
                        </div>
                        <div id="consistency-expanded" style="display:none;">
                            <p><input type="range" name="consistency" min="0" max="1" value="0" step="0.01" class="slider">
                                <span  class="slider_label"></span></p>
                        </div>

                        <div class="form-check" id="partisanSymmetry">
                            <label class="form-check-label">
                                <input type="checkbox" name="partisanSymmetry" class="form-check-input" onchange="toggleCheck(this);">
                                Partisan Symmetry
                            </label>
                        </div>
                        <div id="partisan-symmetry-expanded" style="display:none;">
                            <p><input type="range" name="partisanSymmetry" min="0" max="1" value="0" step="0.01" class="slider">
                                <span  class="slider_label"></span></p>
                        </div>

                        <div class="form-check" id="politicalFairness">
                            <label class="form-check-label">
                                <input type="checkbox" name="politicalFairness" class="form-check-input" onchange="toggleCheck(this);">
                                Political Fairness
                            </label>
                        </div>
                        <div id="political-fairness-expanded" style="display:none;">
                            <p><input type="range" name="politicalFairness" min="0" max="1" value="0" step="0.01" class="slider">
                                <span  class="slider_label"></span></p>
                        </div>

                        <div class="form-check" id="alignment">
                            <label class="form-check-label">
                                <input type="checkbox" name="alignment" class="form-check-input" onchange="toggleCheck(this);">
                                Alignment
                            </label>
                        </div>
                        <div id="alignment-expanded" style="display:none;">
                            <p><input type="range" name="alignment" min="0" max="1" value="0" step="0.01" class="slider">
                                <span  class="slider_label"></span></p>
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
            <button type="button" class="btn btn-default disabled" title="Save Redistricting" onclick="save();">
                <i class="far fa-save"></i>
            </button>

            <button type="button" class="btn btn-default disabled" title="Load Redistricting" onclick="load();">
                <i class="fas fa-upload"></i>
            </button>

            <button type="button" class="btn btn-default disabled" title="View Saved List" onclick="viewSaved();">
                <i class="fas fa-list-ul"></i>
            </button>

            <button type="button" class="btn btn-default" title="Pause Algorithm" onclick="pause();">
                <i class="far fa-pause-circle"></i>
            </button>

            <button type="button" class="btn btn-default" title="Cancel Algorithm" onclick="cancel();">
                <i class="far fa-times-circle"></i>
            </button>

        </div>
        <div id="console">
            <p>Welcome, ${sessionScope.username}<p>
            <p>/Console log</p>
            <p>Server is currently active.<p>
        </div>

        <script src="Resources/Scripts/properties.js"></script>
        <script src="Resources/Scripts/map.js"></script>
        <script src="Resources/Scripts/controls.js"></script>
        <script src="Resources/Scripts/updating.js"></script>


        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDCrjByEUtH5awpWcjFJqLBB7W8npgKKGo&libraries=places&callback=initialize"
        async defer></script>
    </body>
</html>
