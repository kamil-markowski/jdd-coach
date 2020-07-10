<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link href="../../coach.css" rel="stylesheet">
    <title>Coaches</title>
</head>

<h3 style="color: red">Logotype and colours used for training</h3>

<#include "navbar.ftlh">

<body class="">

<h1 class="header-margins">COACH DATABASE ACCESS</h1>
<form id="form1" name="form1" method="post" action="/add-coach" enctype="multipart/form-data">
<div class="container" id="editiondiv">

    <h3 class="header3-margins">Welcome</h3>
    <div class="row justify-content-md-center main-div">

        <div class="col-7 infoShareColor text-centre white-text">

            <h1 class="text-centre">Welcome in Coach editor</h1>
            <h5>This application allowing to add, edit and delete coaches,</h5>
            <h5>including monitoring of the events in this app.</h5>
            <h5>Select action by pressing one of the buttons below.</h5>
            <br>
            <h5>In the navbar You have option to see list and statistics.</h5>
        </div>
    </div>
</div>

<div class="row justify-content-md-center main-div" id="buttons">
    <div class="row">
        <button type="button" class="btn btn-info editing-btns" id="addthat">Add</button>
        <button type="submit" class="btn btn-info editing-btns" id="edit" disabled>Edit</button>
        <button type="submit" class="btn btn-info" id="del" disabled>Delete</button>
        <button type="submit" class="btn btn-info conf-btns" id="confirm" disabled>Confirm</button>
        <button type="submit" class="btn btn-warning conf-btns" id="test">Test Load Db</button>

    </div>
</div>
</form>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
<script src="../../coach.js"></script>
</body>

<#include "footer.ftlh">

</html>