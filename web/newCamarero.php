<?php
	$ncodcam = $_POST['id'];
	$cnomcam = $_POST['nombre'];
	$cpuesto = $_POST['puesto'];
	$dfecIni =  $_POST['fecha'];
	$tfno = $_POST['telefono'];
	$ccorreo = $_POST['email'];
	$cpasscam = $_POST['pass'];

	error_reporting(E_ALL);
	ini_set('display_errors', '1');		
	$servername = "localhost";
	$username = "root";
	$password = "usuario";
	$dbname = "restabar";		
	// Create connection	
	$conn = new mysqli($servername, $username, $password, $dbname);
	// Check connection
	if ($conn->connect_error) {
		echo "ERROR ". $conn->connect_error;
	    die("Connection failed: " . $conn->connect_error);
	}
	$sql = "INSERT INTO camareros VALUES($ncodcam,'$cnomcam','$cpuesto','$dfecIni', $tfno,'$ccorreo','$cpasscam');";	
	if ($conn->query($sql) === TRUE) {
	    echo "Pedido insertado correctamente: ";
	} else {
	    echo "Error: " . $sql . "<br>" . $conn->error;
	}
	$conn->close();
	header("Location: camarero.php");

?>