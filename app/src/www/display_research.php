<?php
require_once dirname(__FILE__) . '/classes/Research.php';
require_once 'default_globals.php';
redirectIfNotLoggedLoggedIn();

$errorsPresent = array();
$researchresults = array();
$researchjson = "";

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
    if (is_empty($_POST["researchjson"])) {
        array_push($errorsPresent, "No Value for Research");
    } else {
        $researchjson = utf8_encode($_POST["researchjson"]);
        $researchresults = json_decode($researchjson, true);
    }
}
?>

<html>
<head>
    <title>Your Research <?php echo($_COOKIE[$loggedInCookieName]) ?></title>
</head>
<body>

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
}
?>

<form name="EditResearchDetails" action="edit_research.php"
      method="post" id="EditResearchForm"
      onsubmit="return confirm('Are you sure? This will overwrite any existing research you have?');">
    <textarea style="display:none;" id="researchjsoneditor" cols="60" rows="15" name="researchjson">
        <?php echo($researchjson);?>
    </textarea> <br/>
    <input type="submit" value="Use This">
</form>
<?php

    $research = new Research();

// create list of notes
if (array_key_exists('notes', $researchresults)) {
        foreach($researchresults['notes'] as $noteArray){
                $text = $noteArray['text'];
                $aNote = $research->addNewNote($text);
        }
}

// create list of resources
if (array_key_exists('resources', $researchresults)) {
    foreach($researchresults['resources'] as $resourceArray){
        $title = $resourceArray['title'];
        $url = $resourceArray['url'];
        $aResource = $research->addNewResource($title,$url);
    }
}

// now display the research

    foreach($research->notes as $aNote){
        echo("<p id='p".$aNote->id."' name='pName".$aNote->id."' class='normal'>".
            "<a id='a".$aNote->id."' name='pName".$aNote->id."' class='normal'></a>".
            $aNote->text.
            "</p>");
    }

    $aOffset = count($research->notes);

    echo("<ul>");
    foreach($research->resources as $aResource){
        $idWithOffset = $aResource->id+$aOffset;
        echo("<li id='li".$aResource->id."' name='liName".$aResource->id."' class='normal'>".
            "<a href='".$aResource->url."' id='a".$idWithOffset."' name='aName".$idWithOffset."' class='normal'>".
            $aResource->title.
            "</a>".
            "</li>");
    }
    echo("</ul>");
?>


