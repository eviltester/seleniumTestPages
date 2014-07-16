<?php

class Item {

    public $id;
    public $title;
    public $description;
    public $url;
    public $items;

    function __construct($aGivenId, $aGivenTitle="") {
        $this->id = $aGivenId;
        $this->title = $aGivenTitle;
        $this->description = "";
        $this->url = "";
        $this->items = array();
    }

    function addChild($anItem){
        array_push($this->items, $anItem);
    }
} 