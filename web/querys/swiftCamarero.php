<?php
	error_reporting(E_ALL);
	ini_set('set_errors','1');
	//$sqlQuery = $_REQUEST['sql'];
	//variables
	$server = "localhost";
	$user = "root";
	$pass = "usuario";
	$dbName = "restabar";
	//connection
	$con = new mysqli($server,$user,$pass,$dbName);
	if ($con->connect_error) {
		die("Error ".$con->connect_error);
	}
	//
	$results=$con->query("SELECT * FROM camareros");
	while($row = $results->fetch_assoc()) {
		foreach($row as $key => $value) {
			$array[$key] = $value;
		}
		$dataArray[] = $array;
	}
	echo json_encode($dataArray);

?>
