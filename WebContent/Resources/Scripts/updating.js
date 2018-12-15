


   function request() {
        $.ajax({
            url: 'calculate',
            type: 'POST',
            dataType: 'json',
            success: function (jsonObject) {
                console.log(jsonObject)
               displayMoves(jsonObject);
               
//                var feature = currentLayer.getFeatureById(jsonObject.id);
//         currentLayer.overrideStyle(feature, {fillColor: 'lime'});

            },
            error: function (error) {
//                alert("error");
            }
        });
    }
//
////
//function suka() {
//
//    function request() {
//        $.ajax({
//            url: 'updating',
//            type: 'GET',
//            dataType: 'json',
//            success: function (jsonObject) {
////                console.log(jsonObject.id);
//                var feature = currentLayer.getFeatureById(jsonObject.id);
//         currentLayer.overrideStyle(feature, {fillColor: 'lime'});
//
//            },
//            error: function (error) {
//                alert(error);
//            }
//        });
//        i++;
//        if (i > 700)
//            clearInterval(interval);
//    }
//
//    var i = 0;
//    var interval = setInterval(request, 2000);
//
//};



