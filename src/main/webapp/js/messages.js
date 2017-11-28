(function (jQuery) {
    jQuery("#msg-form").submit(function (e) {
        e.preventDefault();
        var sendButton = jQuery("#msg-send");
        var inputField = jQuery('#msg-input');
        sendButton.attr('disabled', 'disabled');
        var targetId = jQuery(".friend.active")[0].id;

        $.ajaxSetup({
            beforeSend: function(xhr, settings) {
                if (!csrfSafeMethod(settings.type) && !this.crossDomain) {
                    xhr.setRequestHeader('X-CSRF-TOKEN', jQuery('#_csrf').val());
                }
            }
        });
        if (inputField.val().length>0) {
            jQuery.ajax({
                url: "/message/" + targetId,
                type: "POST",
                data: inputField.val(),
                contentType:"application/json; charset=utf-8",
                dataType:"json",
                success: function() {
                    inputField.val('');
                    sendButton.removeAttr("disabled");
                    loadConversationForUser(targetId);
                },
                error: function() {
                    sendButton.removeAttr("disabled");
                    console.log("Messaging error!");
                }
            });
        }
    });


    function csrfSafeMethod(method) {
        // these HTTP methods do not require CSRF protection
        return (/^(GET|HEAD|OPTIONS|TRACE)$/.test(method));
    }



    jQuery(".friend").click(function (e) {
        e.preventDefault();
        jQuery(".friend").removeClass('active');
        jQuery(this).addClass('active');
        window.clearInterval(loadConversationForUser);
        loadConversationForUser(this.id);
    });

    jQuery(".friend").first().click();

    function loadConversationForUser(id) {
        var container = jQuery(".conversation");
        var content = '';
        var previousContent = container.html();
        jQuery.get("/message/" + id, function (data) {
           if (data && data.data.length>0) {
               data.data.forEach(function (elem) {
                   //if targetId is the partner, then the user was the one who sent, the message
                   //not ugly-hack
                   if (elem.to.id != id) {
                       content += '<div class="col-sm-8 text-right pull-right"><div class="msg-1">' + elem.text + '</div></div>';
                   } else {
                       content += '<div class="col-sm-8 text-left"><div class="msg-2">' + elem.text + '</div></div>';
                   }
               });
           } else {
               content = '<h2>Come on, start talking!</h2>';
           }
           if (content !== previousContent) {
               container.html(content);
           }
        });
    }




})(window.jQuery);