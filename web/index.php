<html>
	<head>
		<title>Restabar control panel</title>
		<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
		
		<script src="bootstrap/js/bootstrap.min.js"></script>	
		<!---<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<?php
			$timezone = date('Y/m/d', time());			
			//$timezone = date('Y/m/d h:i:s a', time());
		?>
		
	</head>
	<body>		
		<!-- Contenido centrado -->
		<div class="main-wrapper" >
			
    		<div class="col-xs-12 col-sm-12 col-md-12 ">	<!--Fila central -->
				
				<div class="alert alert-success" role="alert">
				  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
				  <span class="sr-only">Error:</span>
				  <center> <h1>Restabar APP control panel</h1> <img src="images/controlpanel.png" class="img-responsive" alt="Cinque Terre"></center>
					<!-- Fecha -->
					<center>
					<span class="label label-info">
						<center><h3><?php echo $timezone;?></h3> </center>
					</span></center>										
				</div>				
				<div class="panel panel-default">
					<div class="alert alert-info" role="alert">

						<table class="table table-info">
						  <thead>
							<tr>
							  <th scope="col"></th>
							  <th scope="col"></th>						  
							</tr>
						  </thead>
						  <tbody>
							<tr>						  
							  <td> <center> <a href='producto.php'> <button> <img src="images/producto.png" class="img-responsive" alt="Cinque Terre"> </button> </a> </center> </td>
							  <td> <center> <a href='mensajes.php'> <button> <img src="images/message.png" class="img-responsive" alt="Cinque Terre"> </button> </a> </center> </td>
							</tr>
							<tr>						  
								<td> <center> <a href='mesas.php'> <button> <img src="images/mesa.png" class="img-responsive" alt="Cinque Terre"> </button> </a> </center> </td>
								<td> <center> <a href='camarero.php'> <button> <img src="images/camarero.png" class="img-responsive" alt="Cinque Terre"> </button> </a> </center> </td>	
							</tr>							  								   
						  </tbody>
						</table><!--End table -->
						<center><center> 
							<a href='downloadApk.php'> <img src="images/download.png" class="img-responsive" alt="Cinque Terre"> </a> 
							<a href='registroPedidos.php'> <img src="images/dinero.png" class="img-responsive" alt="Cinque Terre"> </a>
						</center></center>
						
					</div><!--Panel de datos interno  <a href='mesas.php'>-->
				</div> <!--Panel de datos externo -->

				<div class='panel-footer'>
					<div class="alert alert-success" role="alert">
						<center> <h7>Software desarrollado por Luis Ortiz y Alejandro Burgueño <img src="images/icono.png" class="img-circle" alt="Cinque Terre" width=70 height=70> </h7> </center>
					</div>
				</div> <!--Final div footer -->
				
			</div>	<!--Final Fila central -->					
			
		</div><!--Final div central de contenido -->
		
		<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	</body>
</html>