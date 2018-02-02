<?php
	$mesaEscogida=($_GET['mesaEscogida']);	
	require('fpdf/fpdf.php');
	/*MYSQL CONNECTION*/
	$server = "localhost";
	$user = "root";
	$pass = "usuario";
	$dbName = "restabar";	
	$con = new mysqli($server,$user,$pass,$dbName);		//connection
	$results=$con->query("SELECT * FROM v_pedido_completo WHERE ncodmesa=".$mesaEscogida."");
	if ($con->connect_error) {
		die("Error ".$con->connect_error);		
	}
	/**/
	/**/
	$pdf = new FPDF();	
	
	$pdf->AddPage();
	$pdf->SetFont('Arial','B',16);
	$pdf->Cell(0,0,'Ticket pedido',0,1,'C');		//header	
	$pdf->Image('images/icono.png',180,8,22); 			
	$pdf->Cell(60,10,'Cuenta de la mesa: '.$mesaEscogida,1,1,'R');		
	$pdf->Ln();$pdf->Ln();$pdf->Ln();
	$totalCaja = 0;	//caja total de todos los pedidos
	$pdf->SetFont('Arial','B',12);
	while($row = $results->fetch_assoc()) {			
		$fecha=$row['dfecpedido'];
		$importe=$row['nprecio'];
		$totalCaja=$totalCaja + $importe;		
		$cdesc = $row['cdesc'];
			
		$pdf->Cell(40,10,'- '.$cdesc."         ".$importe." Euros");
		$pdf->Ln();
		
	}		
	$pdf->Ln();
	$pdf->Cell(120,10,'Fecha: '.$fecha.' | Importe a pagar: '.$totalCaja." Euros ",1);
	$pdf->Output();

?>