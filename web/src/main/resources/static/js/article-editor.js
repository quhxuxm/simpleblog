$(document).ready(function () {
    var editor = $("body> main> section.article-editor");
    var editorContentBox = $("article> section.content-box", editor);
    var editorToolbar = $("ul.toolbar", editor);
    var overlay = $("div.overlay");
    var messageBoxWrapper = $("div.message-box-wrapper");

    /**
     * Start the timer to check the editor content for security
     */
    function startSecurityCheck() {
        function securityCheck(rootElement) {
            $(":not(b, blockquote, br, div, em, h1, h2, h3, h4, i, img, p, pre, q, " +
                "strike, strong, div.image-uploader, div.image-uploader-progressbar)", rootElement).each(function () {
                $(this).remove();
            });

            $("b, blockquote, br, div, em, h1, h2, h3, h4, i, img, p, pre, q, " +
                "strike, strong, div.image-uploader, div.image-uploader-progressbar", rootElement).each(function () {
                if ($(this).is("div.image-uploader")) {
                    return;
                }
                if ($(this).is("div.image-uploader-progressbar")) {
                    return;
                }
                $(this).attr("class", "");
                $(this).attr("style", "");
            });
        }

        setInterval(function () {
            securityCheck(editorContentBox)
        }, 500);
    }

    function initializeArticleEditorToolbar() {
        function execDocumentCommand(commandName, commandValue) {
            document.execCommand(commandName, false, commandValue);
            editorContentBox.focus();
            return false;
        }

        $("li.toolbar-btn-wrapper a.action.h1", editorToolbar).click(function () {
            return execDocumentCommand("formatblock", "h1");
        });
        $("li.toolbar-btn-wrapper a.action.h2", editorToolbar).click(function () {
            return execDocumentCommand("formatblock", "h2");
        });
        $("li.toolbar-btn-wrapper a.action.h3", editorToolbar).click(function () {
            return execDocumentCommand("formatblock", "h3");
        });
        $("li.toolbar-btn-wrapper a.action.h4", editorToolbar).click(function () {
            return execDocumentCommand("formatblock", "h4");
        });

        $("li.toolbar-btn-wrapper a.action.bold", editorToolbar).click(function () {
            return execDocumentCommand("bold", null);
        });

        $("li.toolbar-btn-wrapper a.action.italic", editorToolbar).click(function () {
            return execDocumentCommand("italic", null);
        });

        $("li.toolbar-btn-wrapper a.action.strikethrough", editorToolbar).click(function () {
            return execDocumentCommand("strikeThrough", null);
        });

        $("li.toolbar-btn-wrapper a.action.p", editorToolbar).click(function () {
            return execDocumentCommand("formatblock", "p");
        });

        $("li.toolbar-btn-wrapper a.action.BLOCKQUOTE", editorToolbar).click(function () {
            return execDocumentCommand("formatblock", "BLOCKQUOTE");
        });

        $("li.toolbar-btn-wrapper a.action.undo", editorToolbar).click(function () {
            return execDocumentCommand("undo", null);
        });

        $("li.toolbar-btn-wrapper a.action.image", editorToolbar).click(function () {
            var imageUploadBtn = $(this);
            var fileInput = $("+input[type='file']", imageUploadBtn);
            fileInput.click();
            return false;
        });

        $("li.toolbar-btn-wrapper a.action.image+input[type='file']", editorToolbar).change(function (e) {
            var imageUploadInput = $(this);
            var targetImageFile = e.target.files[0];

            function uploadFile(file) {
                var imageUploader = $("div.image-uploader-wrapper>div.image-uploader").clone();
                var imageUploaderProgressbar = $("div.image-uploader-wrapper>div.image-uploader>div.image-uploader-progressbar").clone();
                var range = document.getSelection().getRangeAt(0);
                range.surroundContents(imageUploader.get(0));
                //execDocumentCommand("insertHTML",imageUploaderWrapper.html());
                var currentImageUploader = $("div.image-uploader", editorContentBox);
                currentImageUploader.empty();
                currentImageUploader.append(imageUploaderProgressbar);
                var progressBar = $(">div.image-uploader-progressbar", currentImageUploader);
                var reader = new FileReader();
                reader.readAsDataURL(file);
                reader.onload = function (e) {
                    var uploadedImg = "<img src=\"" + this.result + "\"/>";
                    progressBar.css("width", 100 + "%");
                    currentImageUploader.replaceWith(uploadedImg);
                    imageUploadInput.val("");
                    editorContentBox.focus();
                };

                reader.onprogress = function (e) {
                    var percentLoaded = Math.round(e.loaded / e.total);
                    if (percentLoaded > 50) {
                        progressBar.css("width", percentLoaded + "%");
                    }
                };
            }

            uploadFile(targetImageFile);
            return false;
        });

        function sendArticle(isPublish) {
            var articleContent = editorContentBox.html();
            var articleSummary = editorContentBox.text();
            if (articleSummary.length > 100) {
                articleSummary = articleSummary.substring(0, 100);
            }
            var articleTitle = $("input.title-box", editor).val();

            var articleEditRequest = {};
            articleEditRequest["title"] = articleTitle;
            articleEditRequest["summary"] = articleSummary;
            articleEditRequest["content"] = articleContent;
            articleEditRequest["publish"] = isPublish;

            var articleEditActionsBaseUrl = editor.attr("data-url");
            var articleId = editor.attr("data-articleId");

            var action = "write";
            if (articleId != null && articleId.length > 0) {
                action = articleId + "/update";
            }

            articleEditActionsBaseUrl += action;


            $("body > div.additional-actions-wrapper > ul.additional-actions-menu ul.additional-actions-menu.anthology-menu > li").each(function () {
                var checkIcon = $(">a.additional-action-btn.anthology>span.additional-action-btn-label>span.anthology-check-status", $(this));
                if (checkIcon.is(".checked")) {
                    articleEditRequest["anthologyId"] = $(">a.additional-action-btn.anthology",$(this)).attr("data-anthology-id");
                    return;
                }
            });

            $.ajax({
                url: articleEditActionsBaseUrl,
                method: "POST",
                data: JSON.stringify(articleEditRequest),
                contentType: "application/json",
                dataType: "json",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader(editor.attr("data-csrfHeader"), editor.attr("data-csrfToken"))
                },
                success: function (serverReturnData) {

                    function showOverlayWithMessage(messageBoxClass, lightUpErrorMessages) {
                        overlay.css("display", "none");
                        messageBoxWrapper.css("display", "none");
                        $(">div.message-box", messageBoxWrapper).css("display", "none");
                        var messageBox = $(">div.message-box" + messageBoxClass, messageBoxWrapper);
                        var messageListContainer = $(">ul", messageBox);
                        $(">li.errorMessage", messageListContainer).css("display", "none");


                        overlay.css("display", "flex");
                        messageBoxWrapper.css("display", "flex");
                        messageBox.css("display", "block");

                        for (i in lightUpErrorMessages) {
                            $("li.errorMessage." + lightUpErrorMessages[i], messageListContainer).css("display", "block");
                        }
                    }

                    if (Boolean(serverReturnData.success)) {
                        var articleId = serverReturnData.articleId;
                        editor.attr("data-articleId", articleId);
                        showOverlayWithMessage(".success");
                        setTimeout(function () {
                            overlay.css("display", "none");
                            messageBoxWrapper.css("display", "none");
                        }, 1000);
                        return;
                    }

                    showOverlayWithMessage(".error", serverReturnData.errorCodes);
                }
            });
        }

        $("li.toolbar-btn-wrapper a.action.save", editorToolbar).click(function () {
            sendArticle(false);
        });

        $("li.toolbar-btn-wrapper a.action.publish", editorToolbar).click(function () {
            sendArticle(true);
        });

        editorContentBox.on("paste", function (e) {
            var data = e.originalEvent.clipboardData.getData('text/plain')
            var range = document.getSelection().getRangeAt(0);
            var newPObj = $("<p></p>");
            range.surroundContents(newPObj.get(0));
            newPObj.text(data);
            return false;
        });

    }

    function initializeContentEditor() {
        document.execCommand("DefaultParagraphSeparator", false, "p");
        initializeArticleEditorToolbar();
        //Start security check timer.
        startSecurityCheck();
        messageBoxWrapper.click(function () {
            overlay.css("display", "none");
            messageBoxWrapper.css("display", "none");
        });
        editorContentBox.focus();
    }

    function initializeAdditionalActionMenu() {
        $("body>div.additional-actions-wrapper ul.anthology-menu > li > a.anthology").click(function () {
            $("body>div.additional-actions-wrapper ul.anthology-menu > li > a.anthology>span.additional-action-btn-label>span.anthology-check-status").removeClass("checked");
            $("span.additional-action-btn-label > span.anthology-check-status", $(this)).addClass("checked");
        })
    }

    initializeContentEditor();
    initializeAdditionalActionMenu();


});
