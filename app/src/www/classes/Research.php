<?php

require_once 'Note.php';
require_once 'Resource.php';
require_once 'Item.php';

class Research {

    public $resources;
    public $itemTree;
    public $notes;
    private $nextItemId;


    function __construct() {
        $this->resources = array();
        $this->itemTree = null;
        $this->nextItemId = 1;
        $this->notes = array();
    }

    function addNewNote($noteText){

        $aNewNote = new Note(count($this->notes)+1);
        $aNewNote->text = $noteText;

        array_push($this->notes, $aNewNote);
        return $aNewNote;
    }

    function addNewResource($resourceTitle, $resourceUrl, $resourceDescription=""){

        $aNewResource = new Resource(count($this->resources)+1);
        $aNewResource->title = $resourceTitle;
        $aNewResource->url = $resourceUrl;
        $aNewResource->description = $resourceDescription;

        array_push($this->resources, $aNewResource);
        return $aNewResource;
    }

    function getNewItem($aGivenTitle){

        $aNewItem = new Item($this->nextItemId, $aGivenTitle);
        $this->nextItemId ++;
        return $aNewItem;

    }

    function addAsRootItem($anItem){

        if($this->itemTree!=null)
            $anItem->addChild($this->itemTree);

        $this->itemTree = $anItem;

    }
} 