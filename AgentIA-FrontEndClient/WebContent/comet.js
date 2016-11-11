var URL = 'http://localhost:8080/AgentIA-FrontEnd/comet';

var  refreshIntervalId;

function formSubmit(tipo) {
	var busqueda = document.getElementById("busqueda").value;
	
	console.log("Buscar algo: "+busqueda);
	
	
	showResults();
	
	if (tipo === 0) {
		buscar(busqueda, "geografia");
	} else if (tipo === 1) {
		buscar(busqueda, "http://demo.ckan.org/");
	} else if (tipo == 2) {
		buscar(busqueda, "http://catalog.data.gov/");
	} else if (tipo == 3) {
		buscar(busqueda, "gobierno");
	}
}

function showResults() {
	$("#logo").hide();
	$("#formWrapper").hide();
	$("#sfbg").show();

	$('#content').accordion();
}

function buscar(busqueda, tipo) {

	var busquedaJson = {
		action : "buscar",
		datos : {
			abuscar : busqueda,
			metodo : tipo
		}
	};

	var envio = JSON.stringify(busquedaJson)

//	$.post(URL, {
//		notification : envio
//	}).complete(function() {
//		refreshIntervalId=setInterval(function(){
//				buscarResultados(true);
//			}, 1000);
//
//	});
	
	console.log("Envio "+envio);
	
	 $.ajax({
         type:"POST",
         url: URL,
         data: {notification:envio},
         success: function(data) {
        	 console.log("DATA DE AJAX: "+data);
        	 
        	 refreshIntervalId=setInterval(function(){
 				buscarResultados(true);
 			}, 1000);
         } 
     }); 

	
}

function buscarResultados(repeat) {
	console.log("busca resultados");
	$.ajax({
		type:"GET",
		url : URL,
		cache : false, // cache must be false so that messages dont repeat
		// themselves
		dataType : 'json',
		success : function(data) {
			console.log("PIDE DATOS "+data);

			var obj = data;

			for (i = 0; i < obj.length; i++) {
				$('#content').append(
						'<div class="accordion-title"><p>' + obj
								+ '</p></div>');
			}

			// // when a request is complete a new one is started
			// if (repeat) {
			// setTimeout(function() {
			// console.log("buscaResultados desde ajax");
			// buscarResultados(true);
			// }, 100);
			// }

		},

		// when a request is complete a new one is started
		error : function(a, b, c) {
			console.log("clearInterval");
			clearInterval(refreshIntervalId);
		}
	});
}

$(document).ready(function() {
	$("#logo").toggleClass("logoinicial");
	$("#sfbg").hide();
	
	$("#nuevaBusqueda").click(function() {
		  nuevaBusqueda();
	});
	// loadEvents();
	//	
	// setTimeout(function() {
	// console.log("buscaResultados desde documentready");
	// buscarResultados(true);
	// }, 1000);

});

function nuevaBusqueda(){
	console.log("Es llamada una nueva busqueda");
	$("#logo").show();
	$("#formWrapper").show();
	$("#sfbg").hide();
	$("#content").empty();
	
	clearInterval(refreshIntervalId);
}