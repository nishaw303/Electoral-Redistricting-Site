
function suka() {

    function request() {
        $.ajax({
            url: 'updating',
            type: 'GET',
            dataType: 'json',
            success: function (jsonObject) {
                console.log(jsonObject.id);
            },
            error: function (error) {
                alert(error);
            }
        });
        i++;
        if (i > 10)
            clearInterval(interval);
    }

    var i = 0;
    var interval = setInterval(request, 1000);

};



setTimeout(suka, 7000);

