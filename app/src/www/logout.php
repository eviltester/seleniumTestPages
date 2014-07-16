<?php
require_once 'default_globals.php';
if(isset($_COOKIE[$loggedInCookieName])) {
    unset($_COOKIE[$loggedInCookieName]);
    setcookie($loggedInCookieName, '', time() - 3600); // empty value and old timestamp
}
header('Location:' . 'login.php');
?>