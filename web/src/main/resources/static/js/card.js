$(document).ready(function () {


    function layout() {
        var cardContainer = $("body>main>section.card-container");
        var containerColumns = $(">div.card-container-column", cardContainer);
        var cards = [];
        containerColumns.each(function () {
            $(">div.card", $(this)).each(function(){
                cards.push($(this));
            })
        });
        cards.sort(function(a, b){
            var aIndex = parseInt(a.attr("data-index"));
            var bIndex = parseInt(b.attr("data-index"));
            return aIndex - bIndex;
        });
        var numberOfCards = cards.length;
        var numberOfContainerColumns = containerColumns.length;
        var windowWidth = parseInt($(window).width());
        var windowHeight = parseInt($(window).height());
        if (windowWidth <= 568) {
            cardContainer.removeClass("three-columns");
            cardContainer.removeClass("two-columns");
            if (numberOfContainerColumns > 1) {
                // 2 or 3 columns all merge to 1 column
                for(var i=0;i<cards.length; i++){
                    cards[i].appendTo(containerColumns.get(0));
                }

                if (containerColumns.get(1)) {
                    $(containerColumns.get(1)).remove();
                }
                if (containerColumns.get(2)) {
                    $(containerColumns.get(2)).remove();
                }
            }
            return;
        }

        if (windowWidth > 568 && windowWidth <= 960) {

            cardContainer.removeClass("three-columns");
            cardContainer.addClass("two-columns");

            if (numberOfContainerColumns === 3) {
                //3 columns merge to 2 column
                for (var i = 0; i < numberOfCards; i++) {
                    if (i % 2 == 0) {
                        cards[i].appendTo(containerColumns.get(0));
                        continue;
                    }
                    if (i % 2 == 1) {
                        cards[i].appendTo(containerColumns.get(1));
                        continue;
                    }
                }
                $(containerColumns.get(2)).remove();
                return;
            }

            if (numberOfContainerColumns === 1) {
                //1 column expand to 2 columns
                var newColumn = $("<div></div>").addClass("card-container-column");
                for (var i = 0; i < numberOfCards; i++) {
                    if (i % 2 == 0) {
                        cards[i].appendTo(containerColumns.get(0));
                        continue;
                    }
                    if (i % 2 == 1) {
                        cards[i].appendTo(newColumn);
                        continue;
                    }
                }
                cardContainer.append(newColumn);
                return;
            }
        }

        if (windowWidth > 960) {
            cardContainer.removeClass("two-columns");
            cardContainer.addClass("three-columns");
            if (numberOfContainerColumns === 2) {
                //2 columns expand to 3 columns
                var newColumn = $("<div></div>").addClass("card-container-column");
                for (var i = 0; i < numberOfCards; i++) {
                    if (i % 3 == 0) {
                        cards[i].appendTo(containerColumns.get(0));
                        continue;
                    }
                    if (i % 3 == 1) {
                        cards[i].appendTo(containerColumns.get(1));
                        continue;
                    }
                    if (i % 3 == 2) {
                        cards[i].appendTo(newColumn);
                        continue;
                    }

                }
                cardContainer.append(newColumn);
                return;
            }

            if (numberOfContainerColumns === 1) {
                var newColumn2 = $("<div></div>").addClass("card-container-column");
                var newColumn3 = $("<div></div>").addClass("card-container-column");

                for (var i = 0; i < numberOfCards; i++) {
                    if (i % 3 == 0) {
                        cards[i].appendTo(containerColumns.get(0));
                        continue;
                    }
                    if (i % 3 == 1) {
                        cards[i].appendTo(newColumn2);
                        continue;
                    }
                    if (i % 3 == 2) {
                        cards[i].appendTo(newColumn3);
                        continue;
                    }

                }
                cardContainer.append(newColumn2);
                cardContainer.append(newColumn3);
                return;
            }

        }


    }

    $(window).resize(function () {
        layout();
    });

    layout();

    var cardInner = $(".card-container> div.card-container-column> div.card > div.card-inner");
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
