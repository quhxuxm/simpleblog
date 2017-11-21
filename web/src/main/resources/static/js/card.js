$(document).ready(function () {
    var cardInner = $(".card-container> div.card > div.card-inner");
    $(">article>footer>a.action.expand-comment", cardInner).click(function () {

        var commentSection = $(">div.comment-section", $(this).parent().parent().parent());
        if (commentSection.is(".expand")) {
            commentSection.removeClass("expand");
        } else {
            commentSection.addClass("expand")
        }
        return false;
    });
});