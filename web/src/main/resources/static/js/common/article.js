function articleAdditionalAction(actionBtn, actionResultElement, resultFieldName) {
    $.ajax({
        url: actionBtn.attr("href"),
        method: "GET",
        dataType: "json",
        success: function (serverReturnData) {
            if(!serverReturnData.success){
                return;
            }
            actionResultElement.textContent = serverReturnData[resultFieldName];
        }
    });
}

function loadArticleSummariesCollection(triggerBtn, summariesCollectionContainer) {
    var nextPageHidden = $("input[type='hidden'][name='nextPage']", summariesCollectionContainer);
    var nextPage = nextPageHidden.attr("value");
    $.ajax({
        url: triggerBtn.attr("href") + "?page=" + nextPage,
        method: "GET",
        success: function (serverReturnData) {
            nextPageHidden.remove();
            var lastElement = summariesCollectionContainer.children().last();
            if (lastElement.is(triggerBtn)) {
                //Last one is the trigger button
                triggerBtn.before(serverReturnData);
                return;
            }
            //Default
            summariesCollectionContainer.append(serverReturnData);
        }
    });
}
