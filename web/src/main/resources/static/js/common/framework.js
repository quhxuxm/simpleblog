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

    $("article div.article-content>footer>a.action.praise").click(function () {
        articleAdditionalAction($(this), $(this).contents().last()[0],"praiseNumber");
        return false;
    });

    $("article div.article-content>footer>a.action.bookmark").click(function () {
        articleAdditionalAction($(this), $(this).contents().last()[0],"bookmarkNumber");
        return false;
    });

    $(".load-more-article-btn").click(function(){
        loadArticleSummariesCollection($(this), $(".summaries"));
        return false;
    });

    $('.card-container').masonry({
        // set itemSelector so .grid-sizer is not used in layout
        itemSelector: '.card-container > div.card',
        // use element for option
        columnWidth: '.card-container > div.card',
        percentPosition: true,
        gutter:'.card-container > div.card',
        transitionDuration:0,
        resize:true
    })

});
