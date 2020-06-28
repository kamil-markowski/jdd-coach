$(document).ready(function () {
    $("#addthat").click(function () {
        let btnconf;
        document.getElementById("editiondiv").innerHTML =
            $('#editiondiv').load('./part/addcoach.ftlh');

        btnconf = $('#confirm');
        $(btnconf).prop('disabled', true);
        if ($(btnconf).prop('disabled')) $(btnconf).prop('disabled', false);
        else $(btnconf).prop('disabled', true);
    });


    $("#del").click(function () {
        // $("#editiondiv").find("h3").remove();
        $(this).closest("form").attr("method", "delete"),
            $(this).closest("form").attr("action", "/delete-coach");
    });

    $("#edit").click(function () {
        $(this).closest("form").attr("method", "get"),
            $(this).closest("form").attr("action", "/edit-coach");
    });

    $("#test").click(function () {
        $(this).closest("form").attr("action", "/test-load-db");
    });

    // $("#confirm").click(function () {
    //     $(this).closest("form").attr("action", "/add-coach");
    // });

    $("form").on("submit", function () {
        alert($(this).attr("action"));
    });


// var btnconf = $('#confirm');
// $(btnconf).prop('disabled', true);
//
// $('.click').click(function() {
//     if ($(btnconf).prop('disabled')) $(btnconf).prop('disabled', false);
//     else $(btnconf).prop('disabled', true);
// })
//
// $(button).click(function() {
//     alert('Enabled!');
// });


// $("#home").click(function () {
//     document.getElementById("editiondiv").innerHTML =
//         $('#editiondiv').load('./part/welcome.ftlh'),
//         document.getElementById("buttons").innerHTML =
//             $('#buttons').load('./part/buttons.ftlh');
// });

});
//
// function callJqueryAjax(){
//     var name = $('#firstName').val();
//     $.ajax({
//         url     : 'edit-coach',
//         method     : 'PUT',
//         data     : {name : name},
//         success    : function(resultText){
//             $('#result').html(resultText);
//         },
//         error : function(jqXHR, exception){
//             console.log('Error occured!!');
//         }
//     });
// }
// $(document).ready(function() {
//     $('#confirmEdit').click(function(event) {
//         var name = $('#firstName').val();
//         $.put('/edit-coach?coach=1', {
//             userName : name
//         }, function(responseText) {
//             $('#ajaxGetUserServletResponse').text(responseText);
//         });
//     });
// });