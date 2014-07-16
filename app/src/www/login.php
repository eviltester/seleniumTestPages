<?php
require_once 'default_globals.php';
redirectIfAlreadyLoggedIn();
?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
		"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<title>Login To PhpTestApp</title>
	</head>
	<body>
    <div>To login, make sure the password includes 'pass' - secure huh!</div>
    <br/>
    <form name="HTMLFormElements"
          action= "login_form_processor.php"
          method="post"
          id="HTMLFormElements">

            Username: 
            <input type="text" name="username" size="15" /></br>

            Password:
            <input type="password" name="password" size="15" /></br>
            <input type="submit" name="loginbutton" value="Login" /></br>

    </form>

        <p id="para1" class="main">A paragraph of text</p>
		<p id="para2" class="sub">Another paragraph of text</p>

	</body>
</html>