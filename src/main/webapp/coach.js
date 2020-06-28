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

    $("#test").click(function () {
        $(this).closest("form").attr("action", "/test-load-db");
    });

    $("form").on("submit", function () {
        alert($(this).attr("action"));
    });
});