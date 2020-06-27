$(document).ready(function(){
    $("#addthat").click(function(){
        document.getElementById("editiondiv").innerHTML =
            $('#editiondiv').load('./part/addcoach.ftlh')
    });

    $("#home").click(function(){
        document.getElementById("editiondiv").innerHTML =
            $('#editiondiv').load('./part/welcome.ftlh')
    });

    $("#del").click(function () {
        $("#editiondiv").find("h3").remove();
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

});