<?php
try{
	$db=new PDO("mysql:host=localhost;dbname=collage_networking","root","");
}catch(Exception $e){
	echo $e->getMessage();
}

?>