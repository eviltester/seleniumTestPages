<?php

class Note {

    public $id;
    public $text;

    function __construct($aGivenId = 0) {
        $this->id = $aGivenId;
        $this->text = "";
    }
} 