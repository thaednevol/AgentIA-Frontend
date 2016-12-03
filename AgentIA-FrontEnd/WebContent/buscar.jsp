<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	
	<link rel="stylesheet" type="text/css" href="style.css">
	
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
<!--     <script src="websocket.js"></script> -->

	<script src="comet.js"></script>

</head>
<body>
	<div id="sfbg">
		<div class="logoencontrado" id="nuevaBusqueda"></div>
	</div>

	<div id="logo"></div>
	
	<div id="formWrapper">
		<form action="" >
		<input type="text" name="busqueda" id="busqueda"  maxlength="50">
		<div id="botones">
		<input type="button" class="botones" value="Geografía" onclick=formSubmit(0)>
		<input type="button" class="botones" value="Educación" onclick=formSubmit(1)>
		<input type="button" class="botones" value="Gobierno" onclick=formSubmit(2)>
		
		</div> 
	</form>
	</div>
	
	<div id="content"></div>

</body>
</html>