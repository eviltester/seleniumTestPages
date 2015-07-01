<?php
class ResearchRendererBasic {

    private function echo_research_tree($anItem,$divId, $paraId, $incVal)
    {
        $theDivId = $divId + $incVal;
        $theParaId = $paraId + $incVal;
        $theIncVal = $incVal;

        echo("<div class='nestedDiv' name='nestedDiv' id='div" . $theDivId . "'>");
        echo("<ul>");
        echo('<li><p id="p' . $theParaId . '" name="pName' . $theParaId . '" class="normal">' . $anItem->title . '</p>');

        $theIncVal++;
        foreach ($anItem->items as $itemInArray) {
            $theIncVal = $this->echo_research_tree($itemInArray, $divId, $paraId, $theIncVal);
        }
        echo("</li>");
        echo("</ul>");
        echo("</div>");

        return $theIncVal;
    }

    function echoResearch($research){
        // now display the research
        $divCount = 1;

        echo("<h2>Notes</h2>");
        echo("<div class='specialDiv' name='mydivname' id='div".$divCount."'>");
        foreach($research->notes as $aNote){
            echo("<p id='p".$aNote->id."' name='pName".$aNote->id."' class='normal'>".
                "<a id='a".$aNote->id."' name='pName".$aNote->id."' class='normal'></a>".
                $aNote->text.
                "</p>");
        }
        $aOffset = count($research->notes);

        echo("</div>");


        echo("<h2>Items</h2>");
        $incVal = 1;
        $incVal = $this->echo_research_tree($research->itemTree,$divCount,$aOffset,$incVal);
        $aOffset = $aOffset + $incVal;
        $divCount += $incVal;

        echo("<h2>Resources</h2>");
        echo("<div class='linkDiv' name='linkdivname' id='div".$divCount."'>");
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
        echo("</div>");

        $divCount++;
    }
}
?>