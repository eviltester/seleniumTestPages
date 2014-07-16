<?php
/**
 * Called after uploading a file to the server
 */
require_once 'default_globals.php';
redirectIfNotLoggedLoggedIn();


$errorsPresent = array();

        //http://php.net/manual/en/features.file-upload.php
try {
    // Undefined | Multiple Files | $_FILES Corruption Attack
    // If this request falls under any of them, treat it invalid.
    if (
        !isset($_FILES['file']['error']) ||
        is_array($_FILES['file']['error'])
    ) {
        throw new RuntimeException('Invalid parameters For file upload.');
    }

    // Check $_FILES['upfile']['error'] value.
    switch ($_FILES['file']['error']) {
        case UPLOAD_ERR_OK:
            break;
        case UPLOAD_ERR_NO_FILE:
            throw new RuntimeException('No file sent.');
        case UPLOAD_ERR_INI_SIZE:
        case UPLOAD_ERR_FORM_SIZE:
            throw new RuntimeException('Exceeded filesize limit.');
        default:
            throw new RuntimeException('Unknown errors on file upload.');
    }

    // You should also check filesize here.
    if ($_FILES['file']['size'] > 1000000) {
        throw new RuntimeException('Exceeded filesize limit.');
    }

} catch (RuntimeException $e) {
    array_push($errorsPresent, $e->getMessage());
}

?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <title>Uploaded File</title>
    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/json2/20121008/json2.js"></script>
    <script src="./js/amplify/1.1.2/amplify.min.js" type="text/javascript"></script>

</head>
<body>

<?php

displayHeader();

// display any errors if not called properly
if(count($errorsPresent)!=0){
    $errorId=1;
    foreach($errorsPresent as $error){
        echo("<p "."id='error".$errorId."' class='error'>".$error."</p>");
        $errorId++;
    }
    return;
}

$uploadfilecontents = file_get_contents($_FILES['file']['tmp_name']);
$researchjson = utf8_encode($uploadfilecontents);

?>

<script type="text/javascript">
    var userNameCookieValue = $.cookie('<?php echo($loggedInCookieName) ?>');
    amplify.store("research_user_" + userNameCookieValue, <?php echo($researchjson);?>);
</script>

<p>Uploaded your research file to your local storage [<a href="edit_research.php">Edit It</a>]</p>


</body>
</html>
