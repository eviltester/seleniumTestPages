<?php
require_once 'default_globals.php';
redirectIfNotLoggedLoggedIn();

require_once dirname(__FILE__) . '/classes/User.php';

//ini_set('display_errors', 1);
//ini_set('error_reporting', E_ERROR);

$errorsPresent = array();
$passwordIncluded = false;
$startrek = "";
$validStarTrek = "|original|nextgen|voyager|enterprise|deepspace|animated|";
$interests = "";
$validInterests = "|computers|games|books|";
$education = "";
$validEducation = "|school|college|university|life|";
$gender = "";
$validGender = "|male|female|unknown|";
$username = "";
$description = "";

// process forms see http://apptools.com/phptools/forms/forms5.php

// check username
if (array_key_exists('username', $_POST)) {
    if (is_empty($_POST["username"])) {
        array_push($errorsPresent, "No Value for username");
    } else {
        $username = $_POST["username"];
        if (strlen($username) > 15) {
            array_push($errorsPresent, "Username must be a maximum of 15 characters");
        }
        if (strlen($username) < 5) {
            array_push($errorsPresent, "Username must be a minimum of 5 characters");
        }
    }
}else{
    array_push($errorsPresent, "You need to supply a username");
}

if (array_key_exists('userdescription', $_POST)) {
    if (!is_empty($_POST["userdescription"])) {
        $description = $_POST["userdescription"];
    }
}
// gender is mandatory
if (array_key_exists('radioval', $_POST)) {
    if (is_empty($_POST["radioval"])) {
        array_push($errorsPresent, "No Value for Gender");
    } else {
        $gender = $_POST["radioval"];
        if (strpos($validGender, "|" . $gender . "|" ) === false) {
            array_push($errorsPresent, "You chose a non-authorised and unlisted gender " . $gender);
        }
    }
}else{
    array_push($errorsPresent, "You need to supply a Gender");
}

// interest is not mandatory but must be from list
if (array_key_exists('checkboxes', $_POST)) {
    if (is_empty($_POST["checkboxes"])) {
    } else {
        $interests = $_POST["checkboxes"];
        if (is_array($interests)) {
            foreach ($interests as $v) {
                if (strpos($validInterests, "|" . $v . "|") === false) {
                    array_push($errorsPresent, "You chose a non-authorised and unlisted interest " . $v);
                }
            }
        } else {
            if (strpos( $validInterests, "|" . $interests . "|") === false) {
                array_push($errorsPresent, "You chose a non-authorised and unlisted interest " .$interests);
            }
        }
    }
}

// education is not mandatory but must be from list
if (array_key_exists('multipleselect', $_POST)) {
    if (is_empty($_POST["multipleselect"])) {
    } else {
        $education = $_POST["multipleselect"];
        if (is_array($education)) {
            foreach ($education as $v) {
                if (strpos( $validEducation, "|" . $v . "|") === false) {
                    array_push($errorsPresent, "You chose a non-authorised and unlisted education " . $v);
                }
            }
        } else {
            if (strpos( $validEducation, "|" . $education . "|") === false) {
                array_push($errorsPresent, "You chose a non-authorised and unlisted education " . $education);
            }
        }
    }
}

// star trek selection is mandatory
if (array_key_exists('dropdown', $_POST)) {
    if (is_empty($_POST["dropdown"])) {
        array_push($errorsPresent, "No Value for Favourite Star Trek Series");
    } else {
        $startrek = $_POST["dropdown"];

        if (strpos($validStarTrek, "|" . $startrek . "|") === false) {
            array_push($errorsPresent, "You chose a non-authorised and unlisted Star Trek Series " . $startrek);
        }
    }
}else{
    array_push($errorsPresent, "You need to supply a favourite Star Trek Series");
}

?>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Edited User Form Processor</title>

    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/json2/20121008/json2.js"></script>
    <script src="./js/amplify/1.1.2/amplify.min.js" type="text/javascript"></script>

</head>
<body>

<?php

    displayHeader();

    // report any errors
    if(count($errorsPresent) > 0){
        echo("<strong class='error'>You made a mistake editing the user details:</strong>");
          $errorId=1;
          foreach($errorsPresent as $error){
              echo("<p "."id='error".$errorId."' class='error'>".$error."</p>");
              $errorId++;
          }
        echo("<a href='edit_user.php'>Edit User Details Again</p>");
        return;
    }

    // else
    // create the php object and then serialise to json object and output to the screen to load into local storage
    $aUser = new User;
    $aUser->username = $username;
    $aUser->description = $description;
    $aUser->gender = $gender;

    $aUser->education = "";
    if (is_array($education)) {
        foreach ($education as $v) {
            $aUser->education = $aUser->education.$v.",";
        }
        $aUser->education = rtrim($aUser->education, ",");
    } else {
        $aUser->education = $education;
    }

    $aUser->interests = "";
    if (is_array($interests)) {
        foreach ($interests as $v) {
            $aUser->interests = $aUser->interests.$v.",";
        }
        $aUser->interests = rtrim($aUser->interests, ",");
    } else {
        $aUser->interests = $interests;
    }

    $aUser->startrek = $startrek;


?>

<script type="text/javascript">
    var currentUserName = "<?php echo($aUser->username); ?>";
    var userDetails = <?php echo(json_encode($aUser)) ?>;
    amplify.store("user_" + currentUserName, userDetails);
</script>


<p id="usertitle"><strong>User Details Have Been Stored:</strong></p>
<pre id="userdetails">
    <?php echo(json_encode($aUser, JSON_PRETTY_PRINT)); ?>
</pre>



</body>
</html>

