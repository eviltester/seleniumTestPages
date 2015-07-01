<?php
require_once 'default_globals.php';
redirectIfNotLoggedLoggedIn();
?>
    <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
    <html>
    <head>
        <title>PHP Test App - Welcomes <?php echo(getLoggedInUserName()." at ".time());?></title>

        <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/json2/20121008/json2.js"></script>
        <script src="./js/amplify/1.1.2/amplify.min.js" type="text/javascript"></script>

    </head>
    <body>

<?php

displayHeader();

?>