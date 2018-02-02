<?php	//select ncodprod from productos where cdesc like '%Coca-Cola%';
	error_reporting(E_ALL);
	ini_set('display_errors', '1');		
	$servername = "localhost";
	$username = "root";
	$password = "usuario";
	$dbname = "restabar";
	//argumentos
	$ncodped=$_REQUEST["codPed"];		
	// Create connection	
	$conn = new mysqli($servername, $username, $password, $dbname);	
	// Check connection
	if ($conn->connect_error) {
		echo "ERROR ". $conn->connect_error;
		die("Connection failed: " . $conn->connect_error);
	}
	$sql = "DELETE FROM linpedido where ncodped=$ncodped";	
	if ($conn->query($sql) === TRUE) {
		echo "Lineas borradas ";
	} else {
		echo "Error: " . $sql . "<br>" . $conn->error;
	}	

	$conn->close();
	
?>