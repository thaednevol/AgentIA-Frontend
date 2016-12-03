var URL = 'http://localhost:8080/AgentIA-FrontEnd/comet';

var refreshIntervalId;

var elementosEncontrados = [];

var t0, t1;

var tipoBusqueda;

function formSubmit(tipo) {
	var busqueda = document.getElementById("busqueda").value;

	showResults();

	if (tipo === 0) {
		buscar(busqueda, "geografia");
	} else if (tipo === 1) {
		buscar(busqueda, "educacion");
	} else if (tipo == 2) {
		buscar(busqueda, "gobierno");
	}
}

function showResults() {
	$("#logo").hide();
	$("#formWrapper").hide();
	$("#sfbg").show();

	$('#content').accordion();
	
	var html = '<div class="wait">';
	html += '<iframe src="//giphy.com/embed/LGw9wBFdmYSDm" width="480" height="360" frameBorder="0" class="giphy-embed" allowFullScreen></iframe>';
	html += '</div>';
	
	html += '<div class="wait"><p>Por favor, espere. Estamos haciendo lo mejor que podemos</p></div>';
	
	$("#content").append(html);
}

function buscar(busqueda, tipo) {
	
	tipoBusqueda=tipo;

	var busquedaJson = {
		action : "buscar",
		datos : {
			abuscar : busqueda,
			metodo : tipo
		}
	};

	var envio = JSON.stringify(busquedaJson)

	$.ajax({
		type : "POST",
		url : URL,
		async : true,
		data : {
			notification : envio
		},
		success : function(data) {
			// console.log("DATA DE AJAX: "+data);
			t1 = performance.now();

			console.log("Call to doSomething took " + (t1 - t0)
					+ " milliseconds.")

			refreshIntervalId = setInterval(function() {
				buscarResultados(true);
			}, 5000);
		}
	});

}

function buscarResultados(repeat) {
	$.ajax({
		url : URL,
		cache : false, // cache must be false so that messages dont repeat
		// themselves
		// dataType : 'json',
		async : true,
		success : function(data) {
			if (data) {
				var respuesta = JSON.parse(data);
				for (i = 0; i < respuesta.resultados.length; i++) {
					
					$(".wait").hide();
					
					var resultado = respuesta.resultados[i];
					var idDiv = resultado.creatorUserId;
					var id = resultado.id;
					
					
					if (elementosEncontrados.indexOf(id) > -1){
						
					}
					else {
						elementosEncontrados.push(id); 
						
						var title = resultado.title;
						
						if (title.split("\"").length==2){
							title=title.split("\"")[0];
						}	

						var html = '<div class="item" id="' + idDiv + '" datos="'
								+ resultado + '">';
						
						if (tipoBusqueda=="educacion"){
							html += '<img src="images/rdf-xml-128.png" alt="rdf xml" height="42" width="42">';
						}
						if (tipoBusqueda=="gobierno"){
							html += '<div>' + JSON.stringify(resultado)+ '</div>';
						}
						
						html += '<div class="title"><p>' + title+ '</p></div>';
						
						if (tipoBusqueda=="geografia"){
							if (resultado.organization.numFollowers === 0){}
							else {
								html += '<div class="datosRelevantes">Poblacion: ' + resultado.organization.numFollowers+ '</div>';
							}
						}
						
						if (resultado.url){
							html += '<a href="'+resultado.url+'">';
							html += '<div class="datosRelevantes" >URL: ' + resultado.url+ '</div>';
							html += '</a>';
						}
						
						
						html += '<div id="contenido_' + idDiv + '"></div>';
						html += '</div>';
						$('#content').append(html);

						$('#' + idDiv).data('datos', resultado);
						$('#' + idDiv).off().on("click",function() {
							setHtml($(this));
						});

						
					}
				}

			}

			// console.log(respuesta);
		},

		// when a request is complete a new one is started
		error : function(a, b, c) {
			console.log("clearInterval");
			console.log(a);
			console.log(b);
			console.log(c);
			clearInterval(refreshIntervalId);
		}
	});
}

$(document).ready(function() {
	t0 = performance.now();

	$("#logo").toggleClass("logoinicial");
	$("#sfbg").hide();

	$("#nuevaBusqueda").click(function() {
		nuevaBusqueda();
	});
});

function nuevaBusqueda() {
	$("#logo").show();
	$("#formWrapper").show();
	$("#sfbg").hide();
	$("#content").empty();
	clearInterval(refreshIntervalId);
}

function setHtml(item) {
	
	
	$('.item').each(function(i) { 
	    if ($(this).attr('id')===item.attr('id')){
	    	
	    }
	    else {
	    	var idx=$(this).attr('id');
	    	$("#contenido_" + idx).empty();
	    }
	    
	});
	
	
	var datos = item.data('datos');
	
	console.log(datos);
	
	var id = item.attr('id');
	var contenido = $("#contenido_" + id);
	
	if (tipoBusqueda==="geografia"){
		if (contenido.children().length == 0){
			contenido.append("<object style='width: 50%; height: 100%;' data='" + datos.notes + "'>");
			contenido.append("<object style='width: 50%; height: 100%;' data='" + datos.organization.description + "'>");
			contenido.append("<object style='width: 50%; height: 100%;' data='" + datos.organization.imageUrl + "'>");
		}
		else {
			contenido.empty();
		}
	}
	else if (tipoBusqueda==="gobierno"){
		if (contenido.children().length == 0){
			var html = "<div class='resources'>";
			
			for (var i=0; i<200; i++){
				html += '<img src="images/rdf-xml-128.png" alt="rdf xml" height="42" width="42">';
			}
			html += "</div>";
			
			contenido.append(html);
			
		}
		else {
			contenido.empty();
		}
	}
	
	
	
}