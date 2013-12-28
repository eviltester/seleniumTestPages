<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<title>Processed Form Details</title>
</head>
<body>

<?php

//ini_set('display_errors', 1); 
//ini_set('error_reporting', E_ERROR);

function is_empty($var, $allow_false = false, $allow_ws = false) {

    if (!isset($var) || is_null($var)) {   
        return true;
    }

	if(is_array($var)){
		if(empty($var)){
			return true;
		}else{
			return false;
		}
	}

	if(is_bool($var)){
		if($allow_false === false && $var === false){
			return true;
		}else{
			return false;
		}
	}else{
		if($allow_ws == false && trim($var) == ""){
			return true;
		}else{
			return false;
		}
	}

	return false;
}

// process forms see http://apptools.com/phptools/forms/forms5.php

if ( !isset ( $_REQUEST['submitbutton'] ) ) {
      echo "<p id='_valuesubmitbutton'>You did not click the submit button</p>";
}

      echo "<p>Submitted Values</p>";
			
      foreach($_POST as $key => $val){
			
				if(is_empty($val)){
						echo "<p><strong>No Value for ".$key."</strong></p>";
						continue;
				}
						
			  echo "<div id='_".$key."'>";
				echo "<p name='_".$key."'><strong>".$key."</strong>";
				
         if (is_array($val)){
				 		$n = 0;
            echo "<ul>";
            foreach($val as $v){
							 echo "<li id='_value".$key.$n."'>";
							 $n++;
               $v = stripslashes($v);
               echo $v."</li>";
            }
						echo "</ul>";

         } else {
					 echo "<ul><li id='_value".$key."'>".stripslashes($val)."</li></ul>";
         }
				 
				echo "</div>";
	}
	
	/* now some processing for fields that don't get passed through */
	if(!isset($_POST['checkboxes'])){
		echo "<p><strong>No Value for checkboxes</strong></p>";
	}
	if(!isset($_POST['multipleselect'])){
		echo "<p><strong>No Value for multipleselect</strong></p>";
	}
	
	// blank filename does not get passed through on Chrome
	if(!isset($_POST['filename'])){
		echo "<p><strong>No Value for filename</strong></p>";
	}
	
			
if(isset($_GET["ajax"])){
  echo "<a href='basic_ajax.html' id='back_to_form'>Go back to the Ajax form</a>";
}else{ 		
  echo "<a href='basic_html_form.html' id='back_to_form'>Go back to the main form</a>";
}
?>



</body>
</html>
