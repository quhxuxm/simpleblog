$(document).ready(function () {
    $.ajaxSetup({
        "global": true
    });

    //Setup the login redirect on login required.
    $(document).ajaxComplete(function (event, xhr, ajaxOptions) {
        var serverResponse = null;
        try {
            serverResponse = JSON.parse(xhr.responseText);
        } catch (e) {
            return;
        }
        if (serverResponse["loginRequired"]) {
            window.location.href = serverResponse.location;
            return;
        }
    });
});
