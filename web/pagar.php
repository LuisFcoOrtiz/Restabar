<?php
	$mesaEscogida=($_GET['mesaEscogida']);
	$importe=($_GET['importe']);
	echo $importe." ".$mesaEscogida;
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
	//$con->query("UPDATE pedidos SET pagado='s' where ncodmesa=".$mesaEscogida."");
	
	$sql = "DELETE FROM pedidos WHERE ncodped=$mesaEscogida";
	//$sql = "UPDATE pedidos SET pagado='s' WHERE ncodmesa=$ncodMesa;";
	if ($con->query($sql) === TRUE) {
	    echo "Mesa borrada: ";
	} else {
	    echo "Error: " . $sql . "<br>" . $conn->error;
	}
	$con->query("INSERT INTO pedidosPagados VALUES(NULL, $mesaEscogida, NOW(), $importe)");
	if ($con->query($sql) === TRUE) {
		echo "Inserccion en pedido pagado: ";
	} else {
		echo "Error: " . $sql . "<br>" . $conn->error;
	}
	$con->query("UPDATE mesas SET cocupada='n' where ncodmesa=$mesaEscogida");
	if ($con->query($sql) === TRUE) {
		echo "MEsa actualizada correctamente: ";
	} else {
		echo "Error: " . $sql . "<br>" . $conn->error;
	}

	header("Location: mesas.php");

?>