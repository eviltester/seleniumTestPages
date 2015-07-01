<?php
require_once 'default_globals.php';
redirectIfNotLoggedLoggedIn();
?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <title>Miscellaneous Stuff</title>

    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>

    <script type="text/javascript">
        function setRenderer(rendererName){
            $.cookie('rendererName',rendererName);
        }
    </script>

</head>
<body>

<?php
displayHeader();
?>


<h2>Renderers</h2>
<!-- allow choice of renderer -->
<button id="find_by_playground_renderer" onclick="setRenderer('find_by_playground')">Find By Playground</button> : warning - really ugly<br/>
<button id="d3" onclick="setRenderer('d3')">D3</button> : Experimental<br/>
<button id="basic_renderer" onclick="setRenderer('basic')">Basic</button> : Simple but visible<br/>


</body>
</html>
