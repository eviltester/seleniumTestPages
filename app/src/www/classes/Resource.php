<?php

class Resource {

    public $id;
    public $title;
    public $url;
    public $description;

    function __construct($aGivenId = 0) {
        $this->id = $aGivenId;
        $this->title = "";
        $this->url = "";
        $this->description = "";
    }

} 