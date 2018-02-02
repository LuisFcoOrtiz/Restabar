<?php	
	error_reporting(E_ALL);
	ini_set('display_errors', '1');		
	$servername = "localhost";
	$username = "root";
	$password = "usuario";
	$dbname = "restabar";
	//argumentos
	$ncodped=$_REQUEST["cod"];
	$ncodmesa = $_REQUEST["mes"];
	$ncodcam = $_REQUEST["cam"];				
	// Create connection	
	$conn = new mysqli($servername, $username, $password, $dbname);
	// Check connection
	if ($conn->connect_error) {
		echo "ERROR ". $conn->connect_error;
	    die("Connection failed: " . $conn->connect_error);
	}
	$sql = "INSERT INTO pedidos VALUES($ncodped,$ncodmesa,$ncodcam,'n',NOW())";	
	if ($conn->query($sql) === TRUE) {
	    echo "Pedido insertado correctamente: ";
	} else {
	    echo "Error: " . $sql . "<br>" . $conn->error;
	}
	$conn->query("UPDATE mesas SET cocupada='s' where ncodmesa=$ncodmesa");

	$conn->close();
	
?>