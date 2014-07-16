<?php
require_once dirname(__FILE__) . '/../../www/classes/Research.php';

class ResearchTest extends PHPUnit_Framework_TestCase
{
    public function testCreateResearch()
    {
        $research = new Research;

        $this->assertEquals( 0, count($research->notes));
        $this->assertEquals(0, count($research->resources));
        $this->assertEquals(null, $research->itemTree);
    }

    public function testAddNoteToResearch()
    {
        $research = new Research;

        $this->assertEquals(0, count($research->notes));

        $aNote = $research->addNewNote("This is my first note");

        $this->assertEquals(1, $aNote->id);
        $this->assertEquals("This is my first note", $aNote->text);

        $this->assertEquals(1, count($research->notes));

        $aSecondNote = $research->addNewNote("This is my second note");

        $this->assertEquals(2, $aSecondNote->id);
        $this->assertEquals("This is my second note", $aSecondNote->text);

        $this->assertEquals(2, count($research->notes));
    }


    public function testAddResourceToResearch()
    {
        $research = new Research;

        $this->assertEquals(0, count($research->resources));

        $aResource = $research->addNewResource("This is my first resource","http://eviltester.com");

        $this->assertEquals(1, $aResource->id);
        $this->assertEquals("This is my first resource", $aResource->title);
        $this->assertEquals("http://eviltester.com", $aResource->url);

        $this->assertEquals(1, count($research->resources));

        $aSecondResource = $research->addNewResource("This is my second resource", "http://seleniumsimplified.com");

        $this->assertEquals(2, $aSecondResource->id);
        $this->assertEquals("This is my second resource", $aSecondResource->title);
        $this->assertEquals("http://seleniumsimplified.com", $aSecondResource->url);
        $this->assertEquals("", $aSecondResource->description);

        $this->assertEquals(2, count($research->resources));
    }

    public function testAddInitialRootItem(){

        $research = new Research;

        $this->assertEquals(null, $research->itemTree);

        $aRootItem = $research->getNewItem("A Root Item");
        $this->assertEquals(1, $aRootItem->id);
        $this->assertEquals("A Root Item", $aRootItem->title);
        $this->assertEquals("", $aRootItem->description);
        $this->assertEquals("", $aRootItem->url);
        $this->assertEquals(0, count($aRootItem->items));

        $research->addAsRootItem($aRootItem);

        $this->assertEquals(1, $research->itemTree->id);
    }

    public function testAddNewRootItem(){

        $research = new Research;

        $this->assertEquals(null, $research->itemTree);

        $aRootItem = $research->getNewItem("A Root Item");
        $this->assertEquals(1, $aRootItem->id);
        $research->addAsRootItem($aRootItem);

        $this->assertEquals(1, $research->itemTree->id);

        $aNewRootItem = $research->getNewItem("A New Root Item");
        $this->assertEquals(2, $aNewRootItem->id);
        $research->addAsRootItem($aNewRootItem);

        $this->assertEquals(2, $research->itemTree->id);
        $this->assertEquals(1, count($research->itemTree->items));
    }
}
?>