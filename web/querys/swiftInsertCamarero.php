<?php	
	error_reporting(E_ALL);
	ini_set('display_errors', '1');		
	$servername = "localhost";
	$username = "root";
	$password = "usuario";
	$dbname = "restabar";
	//argumentos		
	$ncodcam = $_REQUEST["codcam"];	
	$cnomcam = $_REQUEST["cnomcam"];
	$cpuesto = $_REQUEST["cpuesto"];
	$tfno = $_REQUEST["tfno"];
	$ccorreo = $_REQUEST["ccorreo"];
	$cpasscam = $_REQUEST["cpasscam"];
	// Create connection	
	$conn = new mysqli($servername, $username, $password, $dbname);
	// Check connection
	if ($conn->connect_error) {
		echo "ERROR ". $conn->connect_error;
	    die("Connection failed: " . $conn->connect_error);
	}
	$sql = "INSERT INTO camareros VALUES($ncodcam,'$cnomcam','$cpuesto', NOW(), '$tfno','$ccorreo','$cpasscam')";	
	if ($conn->query($sql) === TRUE) {
	    echo "Camarero insertado: ";
	} else {
	    echo "Error: " . $sql . "<br>" . $conn->error;
	}
	$conn->close();
	
?>