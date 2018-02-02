<?php	
	error_reporting(E_ALL);
	ini_set('display_errors', '1');		
	$servername = "localhost";
	$username = "root";
	$password = "usuario";
	$dbname = "restabar";
	//argumentos		
	$ncodcam = $_REQUEST["codcam"];	
	$cmensaje = $_REQUEST["msg"];		
	// Create connection	
	$conn = new mysqli($servername, $username, $password, $dbname);
	// Check connection
	if ($conn->connect_error) {
		echo "ERROR ". $conn->connect_error;
	    die("Connection failed: " . $conn->connect_error);
	}
	$sql = "INSERT INTO mensajes(ncodmen,ncodcam,cmensaje) VALUES(NULL,$ncodcam,'$cmensaje');";	
	if ($conn->query($sql) === TRUE) {
	    echo "Pedido insertado correctamente: ";
	} else {
	    echo "Error: " . $sql . "<br>" . $conn->error;
	}
	$conn->close();
	
?>