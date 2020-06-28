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

<nav class="navbar navbar-expand-lg navbar-light navbar-length">
    <a class="navbar-brand"><img src="../../logo.svg"></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText"
            aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/" id="home">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Statistics</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/list-coach">List of Coaches</a>
            </li>

        </ul>
        <ul class="navbar-nav">
            <li class="nav-item ml-auto">
                <a class="nav-link" href="#">Login</a>
            </li>
        </ul>
        <span class="navbar-text">
    </span>
    </div>
</nav>

<body class="">

<h1 class="header-margins">COACH DATABASE ACCESS</h1>
<form method="post" action="/add-coach" enctype="multipart/form-data">

<div class="container" id="editiondiv">

    <h3 class="header3-margins">list of coaches</h3>
    <div class="row justify-content-md-center main-div">

        <div class="col-7 infoShareColor" style="width: 100%">

            <h3 style="padding-top: 7px">

                <style>
                    table {
                        font-family: arial, sans-serif;
                        border-collapse: collapse;
                        border-radius: 15px;
                        background-color: white;
                    }

                    td, th {
                        margin: 3px;
                        margin-inside: 5px;
                        text-align: center;
                        padding: 3px;
                    }

                    tr:nth-child(even) {
                        background-color: lightgray;
                    }

                    tr:nth-child(odd) {
                        background-color: white;
                    }
                </style>

                <table>

                    <tr>
                        <th>No</th>
                        <th>Last Name</th>
                        <th>First Name</th>
                        <th>E-mail</th>
                        <th>Mobile phone</th>
                    </tr>

                    <#if list??>
                        <#list list as coach>
                            <tr class="td td-point" onClick="location.href='/single-view?coach=${coach.id}';">
                                <td>${coach?counter}</td>
                                <td>${coach.lastName}</td>
                                <td>${coach.firstName}</td>
                                <td>${coach.email}</td>
                                <td>${coach.mobilePhone}</td>
                            </tr>
                        </#list>
                    </#if>

                </table>
            </h3>

        </div>
    </div>
</div>

<div class="row justify-content-md-center main-div" id="buttons">
    <div class="row">
        <button type="button" class="btn btn-info editing-btns" id="addthat" a href="/add-coach">Add</button>
        <button type="submit" class="btn btn-info editing-btns" id="edit" disabled>Edit</button>
        <button type="submit" class="btn btn-info" id="del" disabled>Delete</button>
        <button type="submit" class="btn btn-info conf-btns" id="confirm" a href="/add-coach" disabled>Confirm</button>

    </div>
</div>
</form>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!--<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"-->
<!--        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"-->
<!--        crossorigin="anonymous"></script>-->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
<script src="../../coach.js"></script>
</body>
<footer>
    <div class="container click">
        <i style="color: white; text-align: center">
            <#--            <!--            <#assign aDateTime = .now>&ndash;&gt;-->
            <#--            <!--            <#assign aDate = aDateTime?date>&ndash;&gt;-->
            Copyrights &copy; All reserved
            <#--            <!--            ${aDate?string.yyyy}.&ndash;&gt;-->
        </i>
    </div>

</footer>
</html>