<html>
	<?php
		$mesaEscogida=($_GET['mesaEscogida']);
	?>
	<head>
		<title>Pedido de mesa</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<meta http-equiv="refresh" content="5">
	</head>
	<body>		
		<?php
			$timezone = date('Y/m/d   |   H:i a', time());
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
		?>
		<!-- Contenido centrado -->
		<div class="container" >
		
		<!-- fila principal -->
		<div class="main row">

				<!-- Columna central de contenido -->
			<div class="col-xs-12 col-sm-12 col-md-12 ">		    		

					<div class="alert alert-success" role="alert">
					  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
					  <span class="sr-only">Error:</span>
					  <center> <h1>Pedido de la mesa Nº: <?php echo $mesaEscogida?></h1> <a href='mesas.php'>  <img src="images/pedido.png" class="img-responsive" alt="Cinque Terre"> </a> </center>
					
						<!--fecha y mensajes-->
						<center>
						<div class="w-25 p-3" role="alert" style="background-color: #82F592;">
						<center><h5><?php echo $timezone;?></h5> </center>
						</div></center>
					
					</div>
					<div class="panel panel-default">
						<div class="alert alert-info" role="alert">														
							
							<center>	
								<!--INTRODUCCION DE MESAS-->
								<table class="table">
								  <thead class="thead-dark">
									<tr>
									  <th scope="col">#</th>
									  <th scope="col">Producto</th>
									  <th scope="col">Cantidad</th>
									  <th scope="col">Precio</th>
									</tr>
								  </thead>
									<?php
										$fechaPedido=0;
										$total=0;
										$whatRow=0;
										$results=$con->query("SELECT * FROM v_pedido_completo WHERE ncodmesa=".$mesaEscogida."");
										while($row = $results->fetch_assoc()) {
											$whatRow = $whatRow+1;
											$total=$total + $row['nprecio'];
											$fechaPedido = $row['dfecpedido'];
											echo "<tbody>
													<tr>
													  <th scope='row'>".$whatRow."</th>
													  <td>".$row['cdesc']."</td>
													  <td>".$row['ncantidad']."</td>
													  <td>".$row['nprecio']." €</td>
													</tr>									
												  </tbody>";
										}
									?>
								  
								</table>
								<div class="alert alert-warning" role="alert">
									<center> 
										<h6> <img src="images/pay.png" class="img-circle" alt="Cinque Terre" width=70 height=70> Fecha de pedido (<?php echo $fechaPedido?>) | Total a pagar: <?php echo $total ?> € 
											<?php
											 echo "<a href='pagar.php?mesaEscogida=$mesaEscogida&importe=$total'> <button class='btn btn-success'> Realizar pago </button> </a>";
																
											echo "<h3><a href='generarPDFpedido.php?mesaEscogida=$mesaEscogida'>  <img src='images/printer.png' target='_blank' class='img-responsive' alt='Cinque Terre'> </a></h3>";
											?>
										</h6> 
									</center>
								</div>
								<!--FIN DE INTRODUCCION DE MESAS-->
							</center>


						</div><!--Panel de datos interno-->
					</div> <!--Panel de datos externo -->

					<div class='panel-footer'>
						<div class="alert alert-success" role="alert">
							<center> <h7>Software desarrollado por Luis Ortiz y Alejandro Burgueño <img src="images/icono.png" class="img-circle" alt="Cinque Terre" width=70 height=70> </h7> </center>
						</div>
					</div> <!--Final div footer -->

				</div>	<!--Final Fila central -->					
			</div> <!-- FIN MAIN ROW -->
			
		</div><!--Final div central de contenido -->
		
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	</body>
</html>