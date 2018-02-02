<?php	//select ncodprod from productos where cdesc like '%Coca-Cola%';
	error_reporting(E_ALL);
	ini_set('display_errors', '1');		
	$servername = "localhost";
	$username = "root";
	$password = "usuario";
	$dbname = "restabar";
	//argumentos
	$ncodped=$_REQUEST["codPed"];
	$cdesc = $_REQUEST["cdesc"];
	$ncantidad = $_REQUEST["cant"];	
	$cobservaciones = $_REQUEST["obs"];		
	
	// Create connection	
	$conn = new mysqli($servername, $username, $password, $dbname);
	//
	$results=$conn->query("SELECT ncodprod FROM productos WHERE cdesc like '%$cdesc%'");
	while($row = $results->fetch_assoc()) {
		$ncodprod = $row['ncodprod'];	
		echo $ncodprod;
	}	
	// Check connection
	if ($conn->connect_error) {
		echo "ERROR ". $conn->connect_error;
		die("Connection failed: " . $conn->connect_error);
	}
	$sql = "INSERT INTO linpedido(idlin,ncodped,ncodprod,ncantidad,cobservaciones) VALUES(NULL,$ncodped,$ncodprod,$ncantidad,'$cobservaciones');";	
	if ($conn->query($sql) === TRUE) {
		echo "Pedido insertado correctamente: ";
	} else {
		echo "Error: " . $sql . "<br>" . $conn->error;
	}	

	$conn->close();
	
?>