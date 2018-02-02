<html>	
	<head>
		<title>Gestion de camareros</title>
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
		
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<!-- Optional theme -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

		<!-- Latest compiled and minified JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
			
		
		<meta http-equiv="refresh" content="15">
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
		<div class="main-wrapper" >
		
			<!-- fila principal -->
			<div class="main row">

				<!-- Columna central de contenido -->
			<div class="col-xs-12 col-sm-12 col-md-12 ">		    		

					<div class="alert alert-success" role="alert">
					  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
					  <span class="sr-only">Error:</span>
					  <center> <h1>Gestión de camareros</h1> <a href='index.php'>  <img src="images/waiter.png" class="img-responsive" alt="Cinque Terre"> </a> </center>
					
						<!--fecha y mensajes-->
						<center>
							<h3><button class="glyphicon glyphicon-calendar" alt="Cinque Terre"> <?php echo $timezone;?> </button> </h3>
						</center>
						
					</div>
					<div class="panel panel-default">
						<div class="alert alert-info" role="alert">														
							
							<center>	
								<!--INTRODUCCION DE MESAS-->
								<table class="table">
								  <thead class="thead-dark">
									<tr class="table-active">
									  <th scope="col">#</th>
									  <th scope="col">Camarero</th>
									  <th scope="col">Puesto</th>
									  <th scope="col">Fecha ingreso</th>
									  <th scope="col">Teléfono</th>
									  <th scope="col">correo</th>										
										<th scope="col"></th>
									</tr>
								  </thead>
									<?php										
										$results=$con->query("SELECT * FROM camareros");
										while($row = $results->fetch_assoc()) {	
											$cnomcam=$row['cnomcam'];
											$cpuesto=$row['cpuesto'];
											$dfecIni=$row['dfecIni'];
											$tfno=$row['tfno'];
											$ccorreo=$row['ccorreo'];											
											echo "<tbody>
													<tr>
													  <th scope='row'><button class='glyphicon glyphicon-user' alt='Cinque Terre'></button></th>													  
													  <td>".$cnomcam."</td>
													  <td>".$cpuesto."</td>
													  <td>".$dfecIni."</td>
													  <td>".$tfno."</td>
													  <td>".$ccorreo."</td>													  
													</tr>									
												  </tbody>";
										}
									?>
								  
								</table>
								<!--FIN DE INTRODUCCION DE MESAS-->
							</center>


						</div><!--Panel de datos interno-->
						
						<!--ingreso de nuevos camareros-->
						<div class="alert alert-success" role="alert">
							<center>
							<h3><button class="glyphicon glyphicon-floppy-disk" alt="Cinque Terre"> Ingreso de nuevos camareros  </button> </h3>
							</center>	
							<table class="table">
							  <thead>
								<tr>
								  <th scope="col">#ID</th>
								  <th scope="col">Nombre</th>
								  <th scope="col">Puesto</th>
								  <th scope="col">Fecha ingreso</th>
									<th scope="col">Teléfono</th>
									<th scope="col">Correo</th>
									<th scope="col">Contraseña</th>
								</tr>
							  </thead>
							  <tbody>
								  <form method="post" name="newCamarero.php.php" action="newCamarero.php">
								<tr>
								  <td> <input type="number" class="form-control" placeholder="Identificador" aria-describedby="basic-addon1" name="id" ></td>
									
								  <td> <input type="text" class="form-control" placeholder="Nombre camarero" aria-describedby="basic-addon1" name="nombre"></td>
								  <td>
									  <select class="custom-select" name="puesto">
									<option selected></option>
									<option value="Mesas">Mesas</option>
									<option value="Barra">Barra</option>
									<option value="Terraza">Terraza</option>
								  	</select>
									</td>
									<td> <input type="text" class="form-control" placeholder="dd/mm/yyyy" aria-describedby="basic-addon1" name="fecha" ></td>
									<td> <input type="number" class="form-control" placeholder="Teléfono" aria-describedby="basic-addon1" name="telefono" ></td>
									<td> <input type="email" class="form-control" name="email" placeholder="ejemplo@ejemplo.com"></td>												
									<td> <input type="password" class="form-control" placeholder="Introduce contraseña" aria-describedby="basic-addon1" name="pass"></td>
								  <td> <button name="subject" type="submit" class="glyphicon glyphicon-floppy-saved" alt="Cinque Terre"></button> </td>
								</tr>
								  </form> <!-- FIN de formulario para ofertas clientes -->
							  </tbody>
							</table>
								  								
																																					  							
						</div> <!--fin ingreso nueos camareros-->
						
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