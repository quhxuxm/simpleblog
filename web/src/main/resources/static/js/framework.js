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

/*    $(window).resize(function(){
        var cardContainerWrapper = $("body>main");
        var cardContainer = $(".card-container", cardContainerWrapper);
        var cardContainerColumns = $(">.card-container-column", cardContainer);
        var cardContainerWidth = parseInt(cardContainer.width());
        var allCards = $(">div.card", cardContainerColumns);
        if(cardContainerWidth < 568 ){
            if(cardContainerColumns.length > 1){

            }
            return;
        }
        if(cardContainerWidth < 960 && cardContainerWidth >= 568){
            return;
        }
        if(cardContainerWidth >= 960){
            return;
        }
    });*/

});
