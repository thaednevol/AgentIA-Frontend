var URL = 'http://localhost:8080/AgentIA-FrontEnd/comet';

var refreshIntervalId;

var t0, t1;

function formSubmit(tipo) {
	var busqueda = document.getElementById("busqueda").value;

	console.log("Buscar algo: " + busqueda);

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

	// $.post(URL, {
	// notification : envio
	// }).complete(function() {
	// refreshIntervalId=setInterval(function(){
	// buscarResultados(true);
	// }, 1000);
	//
	// });

	console.log("Envio " + envio);

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
	console.log("busca resultados");
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
					var resultado = respuesta.resultados[i];
					var id = resultado.id;

					var html = '<div class="item" id="' + id + '" datos="'
							+ resultado + '">';
					html += '<div class="title"><p>' + resultado.title
							+ '</p></div>';
					html += '<div id="contenido_' + id + '"></div>';
					html += '</div>';
					$('#content').append(html);

					$('#' + id).data('datos', resultado);
					$('#' + id).off().on("click",function() {
						setHtml($(this));
					});
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

	// loadEvents();
	//	
	// setTimeout(function() {
	// console.log("buscaResultados desde documentready");
	// buscarResultados(true);
	// }, 1000);

});

function nuevaBusqueda() {
	console.log("Es llamada una nueva busqueda");
	$("#logo").show();
	$("#formWrapper").show();
	$("#sfbg").hide();
	$("#content").empty();

	clearInterval(refreshIntervalId);
}

function setHtml(item) {
	var datos = item.data('datos');
	var id = item.attr('id');
	var contenido = $("#contenido_" + id);
	contenido.append("<object data='" + datos.notes + "'>");
	contenido.append("<object data='" + datos.organization.description + "'>");
}