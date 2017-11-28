$(document).ready( function() {
    $(document).on('change', '.btn-file :file', function() {
        var input = $(this),
            label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
        input.trigger('fileselect', [label]);
    });

    $('.btn-file :file').on('fileselect', function(event, label) {

        var input = $(this).parents('.input-group').find(':text'),
            log = label;

        if( input.length ) {
            input.val(log);
        }

    });
    function readURL(input, bool) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            if(!bool){
                reader.onload = function (e) {
                    $('#img-upload').attr('src', e.target.result);
                    $('#img-upload').show();
                }
            }
            else{
                reader.onload = function (e) {
                    $('#edit_profile_pic').attr('src', e.target.result);
                }
            }

            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#imgInp").change(function(){
        readURL(this, false);
    });

    $("#imgInpEdit").change(function(){
        readURL(this, true);
    });


});
