<footer>

    <div class="container click">
        <i style="color: white; text-align: center">
            <#assign aDateTime = .now>
            <#assign aDate = aDateTime?date>
            Copyrights &copy; All reserved
            ${aDate?string.yyyy}
        </i>
    </div>

</footer>