<?php
	require('fpdf/fpdf.php');
	/*MYSQL CONNECTION*/
	$server = "localhost";
	$user = "root";
	$pass = "usuario";
	$dbName = "restabar";	
	$con = new mysqli($server,$user,$pass,$dbName);		//connection
	$results=$con->query("SELECT * FROM pedidosPagados ORDER BY ncodmesa ASC");
	if ($con->connect_error) {
		die("Error ".$con->connect_error);		
	}
	/**/
	$pdf = new FPDF();	
	
	$pdf->AddPage();
	$pdf->SetFont('Arial','B',16);
	$pdf->Cell(0,0,'Caja Restabar',0,1,'C');		//header	
	$pdf->Image('images/icono.png',180,8,22); 			
	$pdf->Cell(60,10,'Registro de pedidos',1,1,'R');		
	$pdf->Ln();$pdf->Ln();$pdf->Ln();
	$totalCaja = 0;	//caja total de todos los pedidos
	$pdf->SetFont('Arial','B',12);
	while($row = $results->fetch_assoc()) {	
		$ncodmesa=$row['ncodmesa'];
		$fecha=$row['fecha'];
		$importe=$row['importe'];
		$totalCaja=$totalCaja + $importe;
		$fechaPedido = $row['dfecpedido'];
		
		$pdf->Cell(40,10,'Mesa: '.$ncodmesa."           Fecha: ".$fecha."          Importe abonado: ".$importe." Euros");
		$pdf->Ln();
		
	}
	$pdf->Ln();
	$pdf->Cell(60,10,'Total en caja: '.$totalCaja." Euros ",1);
	$pdf->Output();

?>