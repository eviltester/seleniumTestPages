<?php
require_once 'default_globals.php';
redirectIfNotLoggedLoggedIn();
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>List Users</title>

    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/json2/20121008/json2.js"></script>
    <script src="./js/amplify/1.1.2/amplify.min.js" type="text/javascript"></script>

</head>
<body>

<?php

displayHeader();

?>

<ul id="userlist">
</ul>

<script type="text/javascript">
    //debugger;
var users = amplify.store();
    for ( key in users ) {
        if(key.indexOf("user_") == 0){
            var userdata = amplify.store(key);
            // storage key starts with user_
            $('#userlist').append(
                $('<li>').attr("id",key).append(
                    $('<span>').append(userdata.username),
                    $('<button>').append("delete").attr("name",key).click(function(){
                        var attrkey = $(this).attr('name');
                        // delete the user
                        amplify.store(attrkey,null);
                        // delete the users research
                        amplify.store("research_"+attrkey,null);
                        $("#"+attrkey).remove();
                    }))
                );
        }
    }
</script>
</body>
</html>