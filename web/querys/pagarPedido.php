<?php	
	error_reporting(E_ALL);
	ini_set('display_errors', '1');		
	$servername = "localhost";
	$username = "root";
	$password = "usuario";
	$dbname = "restabar";
	//argumentos
	$ncodMesa=$_REQUEST["codMesa"];
	//$importe=$_REQUEST["import"];
	// Create connection	
	$conn = new mysqli($servername, $username, $password, $dbname);
		//**
	$importePedido=0;
	$results=$conn->query("SELECT * FROM v_pedido_completo WHERE ncodmesa=".$ncodMesa."");
	while($row = $results->fetch_assoc()) {
		$importePedido = $importePedido+ $row['nprecio'];
	}//consigue el importe
		//**

	// Check connection
	if ($conn->connect_error) {
		echo "ERROR ". $conn->connect_error;
	    die("Connection failed: " . $conn->connect_error);
	}
	$sql = "DELETE FROM pedidos WHERE ncodped=$ncodMesa;";
	//$sql = "UPDATE pedidos SET pagado='s' WHERE ncodmesa=$ncodMesa;";
	if ($conn->query($sql) === TRUE) {
	    echo "Pedido pagado correctamente: ";
	} else {
	    echo "Error: " . $sql . "<br>" . $conn->error;
	}
	$conn->query("INSERT INTO pedidosPagados VALUES(NULL, $ncodMesa, NOW(), $importePedido)");
	if ($conn->query($sql) === TRUE) {
		echo "Inserccion en pedido pagado: ";
	} else {
		echo "Error: " . $sql . "<br>" . $conn->error;
	}
	$conn->query("UPDATE mesas SET cocupada='n' where ncodmesa=$ncodMesa");
	if ($conn->query($sql) === TRUE) {
		echo "MEsa actualizada correctamente: ";
	} else {
		echo "Error: " . $sql . "<br>" . $conn->error;
	}
	$conn->close();
	
?>