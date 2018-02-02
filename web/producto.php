<html>
	<?php
		$mesaEscogida=($_GET['mesaEscogida']);
	?>
	<head>
		<title>Lista de productos </title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<meta http-equiv="refresh" content="20">
	</head>
	<body>		
		<?php
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
					  <center> <h1>Lista de productos disponibles: </h1> <a href='index.php'>  <img src="images/products.png" class="img-responsive" alt="Cinque Terre"> </a> </center>
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
									  <th scope="col">Stock</th>
									  <th scope="col">Precio unidad</th>
									  <th scope="col">Imagen</th>
									</tr>
								  </thead>
									<?php
										$total=0;
										$whatRow=0;
										$results=$con->query("SELECT * FROM productos");
										while($row = $results->fetch_assoc()) {											
											echo "<tbody>
													<tr>
													  <th scope='row'>".$row['ncodprod']."</th>
													  <td>".$row['cdesc']."</td>
													  <td>".$row['nstock']."</td>
													  <td>".$row['nprecio']." €</td>
													  <td> <img src=".$row['curlfoto']." class='img-circle' alt='Cinque Terre' width=70 height=70> </td>
													</tr>									
												  </tbody>";
										}
									?>
								  
								</table>
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