<html>
<head>
<title>Selenium Simplified Calculator</title>
</head>

<body>
<img src="cover_small.gif" style="float:right"/>
<?php
	/* based on http://www.codingfriends.com/index.php/2009/07/27/method-add-two-numbers-3/ */
       function calculate($a = 0, $f = "plus",  $b = 0)
       {
			if($f=="plus"){
              return ($a + $b);
			}
			if($f=="times"){
              return ($a * $b);
			}
			if($f=="minus"){
              return ($a - $b);
			}			
			if($f=="divide"){
				if($b==0){
					return 0;
				}else{
					return ($a / $b);
				}
			}			
			
       }
	   
       $number1 = $_POST['number1'];
	   $function = $_POST['function'];
       $number2 = $_POST['number2'];
?>
<h1>The "Selenium Simplified" Calculator</h1>
<form action="calculate.php" method="post">
                     <input type="text" id="number1" name="number1" value="<?php echo $number1;?>" />
					 <select id="function" name="function">
						<option value="plus" <?php if($function=="plus"){echo 'selected="selected"';}?>>plus</option>
						<option value="times" <?php if($function=="times"){echo 'selected="selected"';}?>>times</option>
						<option value="minus" <?php if($function=="minus"){echo 'selected="selected"';}?>>minus</option>
						<option value="divide" <?php if($function=="divide"){echo 'selected="selected"';}?>>divide</option>
					</select>
                     <input type="text" id="number2" name="number2" value="<?php echo $number2;?>" />
                     <input type="submit" id="calculate" value="Calculate"/>
              </form>
              Answer : <span id="answer"><?php echo calculate($number1,$function,$number2); ?></span>
</body>
</html>
<!-- php and bing powered -->