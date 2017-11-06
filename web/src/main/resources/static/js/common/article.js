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
    var nextStartHidden = $("input[type='hidden'][name='nextStart']", summariesCollectionContainer);
    var nextStart = nextStartHidden.attr("value");
    $.ajax({
        url: triggerBtn.attr("href") + "?start=" + nextStart,
        method: "GET",
        success: function (serverReturnData) {
            nextStartHidden.remove();
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
