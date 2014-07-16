<?php
require_once 'default_globals.php';
redirectIfAlreadyLoggedIn();

//ini_set('display_errors', 1); 
//ini_set('error_reporting', E_ERROR);

$errorsPresent = array();
$passwordIncluded = false;
$password = "";
$username = "";

// process forms see http://apptools.com/phptools/forms/forms5.php

if ( !isset ( $_REQUEST['loginbutton'] ) ) {
    array_push($errorsPresent, "You did not click the login button");
}

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

// check password
if (array_key_exists('password', $_POST)) {
    if (is_empty($_POST["password"])) {
        array_push($errorsPresent, "No Value for password");
    } else {
        $password = $_POST["password"];
        if (strlen($password) > 15) {
            array_push($errorsPresent, "Password must be a maximum of 15 characters");
        }
        if (strlen($password) < 5) {
            array_push($errorsPresent, "Password must be a minimum of 5 characters");
        }

        if (strpos($password, 'pass') !== false) {
            $passwordIncluded = true;
        }
    }
}else{
    array_push($errorsPresent, "You need to supply a password");
}

// if no errors in $errorsPresent and $passwordIncluded then can login
if(count($errorsPresent)==0 && $passwordIncluded){
    // set a cookie for logged in
    setcookie($loggedInCookieName, $username, time()+3600); // stay logged in for an hour
    // redirect to home page
    header('Location:'.$defaultLoggedInPage);
}else{
?>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Login Processor</title>
</head>
<body>

<?php

      $errorId=1;
      foreach($errorsPresent as $error){
          echo("<p "."id='error".$errorId."' class='error'>".$error."</p>");
          $errorId++;
      }
    echo("<a href='login.php'>Try and Login Again</a>");
}
?>

</body>
</html>

