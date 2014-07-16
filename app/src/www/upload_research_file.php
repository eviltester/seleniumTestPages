<?php
require_once 'default_globals.php';
redirectIfNotLoggedLoggedIn();

// show a form to allow a research json file to be uploaded

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


</head>
<body>

<?php
displayHeader();
?>

<form name="UploadFile" action=
    "uploaded_file.php"
      method="post" id="UploadFileForm" enctype="multipart/form-data">

        <label for="file">Filename:</label>
        <input type="file" name="file" id="file" /> <br />
        <input type="submit" name="submit" value="Upload" />
    </form>
</form>

</body>