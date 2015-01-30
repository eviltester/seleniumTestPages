<?php
require_once 'default_globals.php';
redirectIfNotLoggedLoggedIn();

// show a text area with the research text and allow it to be submitted - same as upload
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>
        HTML Form Elements
    </title>

    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/json2/20121008/json2.js"></script>
    <script src="./js/amplify/1.1.2/amplify.min.js" type="text/javascript"></script>



    <?php
    // if I was called with a post then write the researchjson to the local storage here
    $researchjson = "";
    if (array_key_exists('researchjson', $_POST)) {
        if (is_empty($_POST["researchjson"])) {
        } else {
            $researchjson = utf8_encode($_POST["researchjson"]);
            ?>

            <script type="text/javascript">
                var userNameCookieValue = $.cookie('<?php echo($loggedInCookieName) ?>');
                amplify.store("research_user_" + userNameCookieValue, <?php echo($researchjson);?>);
            </script>
    <?php
        }
    }
    ?>

</head>
<body>

<?php
displayHeader();
?>

<form name="EditResearchDetails" action=
    "display_research.php"
      method="post" id="EditResearchForm">

    <span id="editprompt">Wait...loading JSON...</span>:<br />
    <textarea id="researchjsoneditor" cols="60" rows="15" name="researchjson"></textarea> <br/>
    <input type="submit" value="Preview"><br/>
</form>
<button id="saveonclick">Save</button>



<script type="text/javascript">

    // get username
    var userNameCookieValue = $.cookie('<?php echo($loggedInCookieName) ?>');

    // setup save function
    $('#saveonclick').click(function() {
        //debugger;
        $("#editprompt").text("Saving..");
        var text = $('textarea#researchjsoneditor').val();
        amplify.store("research_user_" + userNameCookieValue,JSON.parse(text));
        $("#editprompt").text("Saved..");
        $("#editprompt").text("Edit JSON Here");
    });


    // get stored research json and load into text area
    var localStoreResearch = amplify.store("research_user_" + userNameCookieValue);
    // display the json from the local storage here
    if (typeof localStoreResearch !== "undefined") {
        $("#researchjsoneditor").text(JSON.stringify(localStoreResearch));
    }
    $("#editprompt").text("Edit JSON Here");
</script>
</body>