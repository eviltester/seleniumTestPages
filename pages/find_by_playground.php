<html>
<head>
	<title>Welcome to the Find By Playground</title>
<head>
<body>
<?php 

// global array of element tag names to ids
$tagNameIDs = array();

function getNextIdForTag($aTagName){
   global $tagNameIDs;
   
   if(array_key_exists($aTagName,$tagNameIDs)){
		$id = $tagNameIDs[$aTagName];
		$id = $id + 1;
		$tagNameIDs[$aTagName] = $id;
		return $id;
	}else{
		$tagNameIDs[$aTagName] = 1;
		return 1;
	}
}

Class HtmlElement{ 

  public $myTagName;
  public $myAttributes;
  public $myChildren;
  public $myBodyText;
  public $myID;

  public function __construct($tagName = 'div', $attributes = array(), $children = array(), $bodyText='')
  {
    $this->myID = getNextIdForTag($tagName);
    $this->setTagName($tagName); 
	$this->setAttributes($attributes);
	$this->setChildren($children);
	$this->setBodyText($bodyText);
  }
  
  public function cloneMe(){
	$newChildren = array();
	for($cloneLoop=0;$cloneLoop<count($this->myChildren);$cloneLoop++){	
		$value=$this->myChildren[$cloneLoop];
		array_push($newChildren,$value->cloneMe());

	}
	return new HtmlElement($this->myTagName,$this->myAttributes,$newChildren,$this->myBodyText);
  }
  
  public function addElement($anHtmlElement){
    array_push($this->myChildren,$anHtmlElement);
  }
	
  public function setTagName($aTagName)
  {
    $this->myTagName = $aTagName;
  }	
  
  public function setAttributes($someAttributes){
	if(is_null($someAttributes)){
		$this->myAttributes=array();
	}else{
		$this->myAttributes=$someAttributes;
	}	
  }
  
  public function setChildren($someHtmlElements){
	if(is_null($someHtmlElements)){
		$this->myChildren=array();
	}else{
		$this->myChildren=$someHtmlElements;
	}	
  }  
  
  public function setBodyText($aBodyText){
	$this->myBodyText=$aBodyText;
  }   
  
  public function outputAsHTML($lineprefix=''){
	print("\n".$lineprefix."<".$this->myTagName);
	
	foreach ($this->myAttributes as $key => $val) {
		print(" ".$key."='".$val."'");
	}
	
	// TODO: could just add these as default on construct rather than in output
	 if(array_key_exists("id",$this->myAttributes)){
		//print(" id='".$this->myAttributes["id"]."'");
	 }else{
		print(" id='".$this->myTagName.$this->myID."'");
	 }

	 if(array_key_exists("name",$this->myAttributes)){
		//print(" name='".$this->myAttributes["name"]."'");
	 }else{
		print(" name='".$this->myTagName."Name".$this->myID."'");
	 }
	 
	 if(array_key_exists("class",$this->myAttributes)){
		//print(" class='".$this->myAttributes["class"]."'");
	 }else{
		print(" class='normal'");
	 }	 
	 
	print(">");
	
	for($childLoop=0;$childLoop<count($this->myChildren);$childLoop++){	
		$value=$this->myChildren[$childLoop];
		$value->outputAsHTML($lineprefix.' ');
	}
	
	if(strlen($this->myBodyText)>0){
	   print($this->myBodyText);
	}
	
	print("\n".$lineprefix."</".$this->myTagName.">");
  }
}
?>

<?php
	
	$mainDiv = new HtmlElement("div",array("class"=>"specialDiv","name"=>"mydivname"));
	
	// create a list of paras
	for($divChild=0;$divChild<25;$divChild++){
		$newPara=new HtmlElement("p",NULL,NULL,"This is ".chr(ord("a")+$divChild)." paragraph text");
		$mainDiv->addElement($newPara);
		$newPara->addElement(new HtmlElement("a",array("name"=>"pName".$tagNameIDs['p'])));
	}
	
	$lastIndexedParaCount = $tagNameIDs['p'];
	
	// create a set of divs where each div has a para
	$nestedDiv = new HtmlElement("div",array("class"=>"nestedDiv","name"=>"nestedDiv"));
	$nestedPara = new HtmlElement("p",NULL,NULL,"nested para text");
	$nestedDiv->addElement($nestedPara);
	
	$mainDiv->addElement($nestedDiv);
	
	for($nestedDivCount=0; $nestedDivCount<15; $nestedDivCount++){
		$newDiv = $nestedDiv->cloneMe();
		$nestedDiv->addElement($newDiv);
		$nestedDiv = $newDiv;
	}
	
	// create a set of hrefs to the paras
	$linkDiv = new HtmlElement("div",array("class"=>"linkDiv","name"=>"linkdivname")); 
	$mainDiv->addElement($linkDiv);
	$ul=new HtmlElement("ul");
	$linkDiv->addElement($ul);
	
	// 1 less link than there are paras off by one error
	for($hrefCount=0; $hrefCount<$lastIndexedParaCount; $hrefCount++){
		$ul->addElement(new HtmlElement("li",NULL,array(
								new HtmlElement('a',array("href"=>"#pName".$hrefCount),NULL,"jump to para ".$hrefCount))));
	}
	

	$mainDiv->addElement(
		new HtmlElement("div",array("class"=>"this has multiple values","name"=>"multiValues"),
			array(new HtmlElement("pre",NULL,NULL,"within div of multiple class styles"))));
	
	$mainDiv->outputAsHTML();
	
	print "\n";
?>
</body>