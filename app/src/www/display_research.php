<?php
require_once dirname(__FILE__) . '/classes/Research.php';
require_once dirname(__FILE__) . '/classes/ResearchPopulator.php';

require_once dirname(__FILE__) . '/views/ResearchRenderer.php';
require_once dirname(__FILE__) . '/views/ResearchRendererBasic.php';
require_once dirname(__FILE__) . '/views/ResearchRendererD3.php';
require_once 'default_globals.php';
redirectIfNotLoggedLoggedIn();

$errorsPresent = array();
$researchresults = array();
$researchjson = "";
$userIsEditingResearch=false;

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    if (array_key_exists('otherresearch', $_POST)) {
        if (!is_empty($_POST["otherresearch"])) {
            $researchFileName = "find_by_playground.json";
            $researchName = $_POST["otherresearch"];
            if ($researchName === 'find_by_playground') {
                $researchFileName = "find_by_playground.json";
            }

            $researchFileName = dirname(__FILE__) . "/research/" . $researchFileName;
            $contents = file_get_contents($researchFileName);
            $researchjson = utf8_encode($contents);
            $researchresults = json_decode($researchjson, true);
        }
    }

    if (array_key_exists('researchjson', $_POST)) {
        $userIsEditingResearch=true;
        if (is_empty($_POST["researchjson"])) {
            array_push($errorsPresent, "No Value for Research");
        } else {
            $researchjson = utf8_encode($_POST["researchjson"]);
            $researchresults = json_decode($researchjson, true);
        }
    }
}else{
    // assume get but sadly display research only works on a post
    array_push($errorsPresent, "No Value for Research");
}

// choose renderer
$rendererName = "";
if (array_key_exists('rendererName', $_COOKIE)) {
    $rendererName = $_COOKIE['rendererName'];
}
switch ($rendererName) {
    case "find_by_playground":
        $renderer = new ResearchRenderer();
        break;
    case "d3":
        $renderer = new ResearchRendererD3();
        break;
    default:
        $renderer = new ResearchRendererBasic();
}

?>

<html>
<head>
    <title>Your Research <?php echo($_COOKIE[$loggedInCookieName]) ?></title>
    <?php
    if(method_exists($renderer, "echo_scripts") ){
        $renderer->echo_scripts();
    }
    ?>
    <?php
    if(method_exists($renderer, "echo_style") ){
        $renderer->echo_style();
    }
    ?>

</head>
<body id="body">

<?php
displayHeader();
?>

<?php
// display any errors if not called properly
if(count($errorsPresent)!=0){
    $errorId=1;
    foreach($errorsPresent as $error){
        echo("<p "."id='error".$errorId."' class='error'>".$error."</p>");
        $errorId++;
    }
    return;
}
?>

<?php
  // if user is not editing research then they are viewing a sample,
  // which they can choose to use as their research
  if(!$userIsEditingResearch){ ?>

    <form name="EditResearchDetails" action="edit_research.php"
          method="post" id="EditResearchForm"
          onsubmit="return confirm('Are you sure? This will overwrite any existing research you have?');">
        <textarea style="display:none;" id="researchjsoneditor" cols="60" rows="15" name="researchjson">
            <?php echo($researchjson);?>
        </textarea> <br/>
        <input type="submit" value="Use This">
    </form>

<?php } ?>

<?php

    $research = new Research();
    $populator = new ResearchPopulator();
    $populator->populateResearchFromHashArray($research, $researchresults);

    $renderer->echoResearch($research);

?>

</body>
</html>

