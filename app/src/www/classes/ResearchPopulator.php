<?php
class ResearchPopulator
{
    private function populateItemFromJSON($anItem, $someJson)
    {

        if (array_key_exists('title', $someJson)) {
            $anItem->title = $someJson['title'];
        }
        if (array_key_exists('description', $someJson)) {
            $anItem->description = $someJson['description'];
        }
        if (array_key_exists('url', $someJson)) {
            $anItem->url = $someJson['url'];
        }
    }

    private function addChildrenItemsAsTree($research, $parent, $arrayOfItems)
    {

        $anItem = $research->getNewItem($arrayOfItems['title']);
        $this->populateItemFromJSON($anItem, $arrayOfItems);
        array_push($parent->items, $anItem);

        foreach ($arrayOfItems['items'] as $itemInArray) {
            $this->addChildrenItemsAsTree($research, $anItem, $itemInArray);
        }
    }

    function populateResearchFromHashArray($research, $researchresults)
    {
        // create list of notes
        if (array_key_exists('notes', $researchresults)) {
            foreach ($researchresults['notes'] as $noteArray) {
                $text = $noteArray['text'];
                $aNote = $research->addNewNote($text);
            }
        }

        // create tree of items
        if (array_key_exists('items', $researchresults)) {
            // the first one is the root
            $anItem = $research->getNewItem($researchresults['items'][0]['title']);
            $this->populateItemFromJSON($anItem, $researchresults['items'][0]);
            $research->addAsRootItem($anItem);

            // now run through each item in the item's 'items'
            foreach ($researchresults['items'][0]['items'] as $itemInArray) {
                $this->addChildrenItemsAsTree($research, $anItem, $itemInArray);
            }
        }

        // create list of resources
        if (array_key_exists('resources', $researchresults)) {
            foreach ($researchresults['resources'] as $resourceArray) {
                $title = $resourceArray['title'];
                $url = $resourceArray['url'];
                $aResource = $research->addNewResource($title, $url);
            }
        }

    }
}
?>