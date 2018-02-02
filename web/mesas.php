<html>
	<head>
		<title>Gestión de mesas</title>
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

		<!-- Optional theme -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

		<!-- Latest compiled and minified JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
				
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
					  <center> <h1>Gestion de mesas</h1> <a href='index.php'>  <img src="images/mesa2.png" class="img-responsive" alt="Cinque Terre"> </a> </center>
					
						<!--fecha y mensajes-->
						<center>
							<button class="glyphicon glyphicon-calendar" alt="Cinque Terre"> <?php echo $timezone;?> </button> 
						</center>
				
					</div>
					<div class="panel panel-default">
						<div class="alert alert-info" role="alert">														
							
							<center>	
								<nav aria-label="Page navigation">
								  <ul class="pagination">
									<li>
									  <a href="#" aria-label="Previous">
										<span aria-hidden="true">&laquo;</span>
									  </a>
									</li>
									  <?php
										$mesaEscogida = 0;
										$ocupada="Libre";
										$results=$con->query("SELECT * FROM mesas");
										while($row = $results->fetch_assoc()) {
											$mesaEscogida = $row['ncodmesa'];
											if ($row['cocupada']=='s') {												
												echo"<li><a href='pedidoMesa.php?mesaEscogida=$mesaEscogida'>$mesaEscogida</a></li>";
											} else {
												echo"<li><a href='#'>$mesaEscogida</a></li>";
											}
											
										}
										?>
									<li>
									  <a href="#" aria-label="Next">
										<span aria-hidden="true">&raquo;</span>
									  </a>
									</li>
								  </ul>
								</nav>
								<!--INTRODUCCION DE MESAS-->
								<table class="table table-info">									
									<?php
										$mesaEscogida = 0;
										$ocupada="Libre";
										$results=$con->query("SELECT * FROM mesas");
										while($row = $results->fetch_assoc()) {
											$mesaEscogida = $row['ncodmesa'];
											if ($row['cocupada']=='s') {
												$ocupada="Ocupada";
												echo "<thead>
													<tr>
														
													  <th scope='col'>Mesa Nº: ". $row['ncodmesa']." <button class='glyphicon glyphicon-ban-circle' alt='Cinque Terre'>".$ocupada."</button> </th>										  
													</tr>
												  </thead>";	
											echo "<tbody>
													<tr>
														<td>
															<center> <a href='pedidoMesa.php?mesaEscogida=$mesaEscogida'> <img src='images/mesa.png' class='img-responsive' alt='Cinque Terre'> </a> </center>
														</td>	
													</tr>						  
												</tbody>";
											} else {
												$ocupada="Libre";
												echo "<thead>
													<tr>
													  <th scope='col'>Mesa Nº: ". $row['ncodmesa']." <button class='glyphicon glyphicon-ok-circle' alt='Cinque Terre'>".$ocupada."</button> </th>										  
													</tr>
												  </thead>";	
											echo "<tbody>
													<tr>
														<td>
															<center> <img src='images/mesa.png' class='img-responsive' alt='Cinque Terre'> </center>
														</td>	
													</tr>						  
												</tbody>";
											}
																						
										}
									?>
								
								</table><!--End table -->
								<!--FIN DE INTRODUCCION DE MESAS-->								
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