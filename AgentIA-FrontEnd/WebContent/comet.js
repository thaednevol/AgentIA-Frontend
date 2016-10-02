var url='comet';

function formSubmit(tipo) {
	var busqueda = document.getElementById("busqueda").value;
    showResults();
    
	if (tipo===0){
		buscar(busqueda,"https://datahub.io/");
	}
	else if (tipo===1){
		buscar(busqueda,"http://demo.ckan.org/");
	}
	else if(tipo==2){
		buscar(busqueda,"http://catalog.data.gov/");
	}
}

function showResults(){
	$("#logo").css("display","none");
	$("#formWrapper").css("display","none");
	$("#sfbg").css("visibility","visible");
	
	$('#content').accordion();
}

function buscar(busqueda, tipo){
	
	var busquedaJson={
			action:"buscar",
			datos:{
				abuscar:busqueda,
				metodo:tipo
			}
	};
	
	var envio=JSON.stringify(busquedaJson)
	
	$.post(url, {
		notification: envio
	}).complete(function(){
		
	});
}

function buscarResultados(repeat) {
	$.ajax({
		url: url,
		cache: false, //cache must be false so that messages dont repeat themselves
		dataType: 'json', 
		success: function(data) {
			console.log(data);
			$('#content').append('<div class="accordion-title"><p>'+JSON.stringify(data)+'</p></div>');

			//when a request is complete a new one is started
			if(repeat) {
				setTimeout(function() {
					buscarResultados(true);
				}, 100);
			}
			
		},
		
		//when a request is complete a new one is started
		error: function(a, b, c) {
			if(repeat) {
				setTimeout(function() {
					buscarResultados(true);
				}, 100);
			}
		}
	});
}

$(document).ready(function() {
	$("#logo").toggleClass("logoinicial");
//	loadEvents();
//	
	setTimeout(function() {
		buscarResultados(true);
	}, 1000);
	
	
});