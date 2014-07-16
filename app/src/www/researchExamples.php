<?php
require_once 'default_globals.php';
redirectIfNotLoggedLoggedIn();
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <title>Research Examples</title>
</head>
<body>
<br/>
<form name="ResearchExamples"
      action= "display_research.php"
      method="post"
      id="ResearchExamplesForm">

    <input name="otherresearch" size="15" value="find_by_playground"/></br>
    <input type="submit" name="showresearch" value="Show Research" /></br>

</form>


</body>
</html>