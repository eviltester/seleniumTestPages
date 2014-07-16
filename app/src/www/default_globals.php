<?php

$defaultLoggedInPage = "edit_user.php";
$loggedInCookieName = "PhpTestAppLoggedIn";

function redirectIfAlreadyLoggedIn(){
    global $loggedInCookieName,$defaultLoggedInPage;
    if(isset($_COOKIE[$loggedInCookieName])) {
        header('Location:' . $defaultLoggedInPage);
        exit;
    }
}
function redirectIfNotLoggedLoggedIn(){
    global $loggedInCookieName;
    if(!isset($_COOKIE[$loggedInCookieName])) {
        header('Location:' . 'login.php');
        exit;
    }
}

function displayHeader(){
    global $loggedInCookieName;
    echo "<div class='header'>";
    echo "<p>";
    echo "Welcome ".$_COOKIE[$loggedInCookieName];
    echo " | "."<a href='edit_research.php'>Edit Research</a>";
    echo " | "."<a href='upload_research_file.php'>Upload Research</a>";
    echo " | "."<a href='edit_user.php'>Edit User Details</a>";
    echo " | "."<a href='list_users.php'>Manage Users</a>";
    echo " | "."<a href='logout.php'>Logout</a>";
    echo "</p>";
    echo "</div>";
}

// check the form details
function is_empty($var, $allow_false = false, $allow_ws = false) {

    if (!isset($var) || is_null($var)) {
        return true;
    }

    if(is_array($var)){
        if(empty($var)){
            return true;
        }else{
            return false;
        }
    }

    if(is_bool($var)){
        if($allow_false === false && $var === false){
            return true;
        }else{
            return false;
        }
    }else{
        if($allow_ws == false && trim($var) == ""){
            return true;
        }else{
            return false;
        }
    }

    return false;
}
?>