$(document).ready(function () {
    $("article div.article-content>footer>a.action.praise").click(function () {
        articleAdditionalAction($(this), $(this).contents().last()[0],"praiseNumber");
        return false;
    });

    $("article div.article-content>footer>a.action.bookmark").click(function () {
        articleAdditionalAction($(this), $(this).contents().last()[0],"bookmarkNumber");
        return false;
    });

    $(".load-more-article-btn").click(function(){
        loadArticleSummariesCollection($(this), $(".article-summaries"));
        return false;
    });
});

