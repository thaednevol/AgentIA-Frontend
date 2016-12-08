var URL = 'http://localhost:8080/AgentIA-FrontEnd/comet';

var refreshIntervalId;

var elementosEncontrados = [];

var t0, t1, tiempoTotal;

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
	html += '<iframe src="images/giphy-downsized-large.gif" width="480" height="360" frameBorder="0" class="giphy-embed" allowFullScreen></iframe>';
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
			tiempoTotal = performance.now();

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
			tiempoTotal = performance.now();
			
			console.log(tiempoTotal-t1);
			
			

			if (data) {
				var respuesta = JSON.parse(data);
				for (i = 0; i < respuesta.resultados.length; i++) {
					t1=performance.now();
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
							if (resultado.resources){
								if (resultado.resources.length>0){
									html += '<div class="adjunto"><img src="images/attachment-icon.png" alt="zip" height="12" width="12"></div>';
								}
								
							}
							//html += '<div>' + JSON.stringify(resultado)+ '</div>';
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
			
			if ((tiempoTotal-t1)>120000){
				cancelar();
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
	elementosEncontrados = [];
	clearInterval(refreshIntervalId);
}

function cancelar(){
	clearInterval(refreshIntervalId);
	console.log(elementosEncontrados);
	console.log(elementosEncontrados.length);
	if (elementosEncontrados.length >0){
		
	}
	else {
		var html = '<div class="notfound"><p>No pudimos encontrar nada, pero...</p></div>';
		html += '<div class="notfound">';
		html += '<img height="320" width="320" src="images/mom.jpg"/>';
		html += '</div>';
		
		html += '';
		
		$(".wait").hide();
		
		$("#content").append(html);
	}
	
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
			
			console.log("ENTRA!!!");
			
//			if(datos.organization){
//				if (datos.organization.imageUrl){
//					var html = '<div class="imageUrl">';
//					html += '<img src="'+datos.organization.imageUrl+'" alt="image" height="42" width="42">';
//					html += '</div>';
//					
//					contenido.append(html);
//				}
//				
//			}
//			
			
			if(datos.organization){
				if (datos.organization.description){
					var html = '<div class="description">';
					html += datos.organization.description;
					html += '</div>';
					
					contenido.append(html);
				}
				
			}
			
			
			
			if(datos.notes){
				var html = '<div class="notes">';
				html += 'Notas: ';
				html += datos.notes;
				html += '</div>';
				
				contenido.append(html);
			}
			
			
			if (datos.resources){
				if (datos.resources.length>0){
					var html = "<div class='resources'>";
					
					for (var i=0; i<datos.resources.length; i++){
						var resource = datos.resources[i];
						
						console.log(resource.format+" "+resource.url);
						
						if (resource.format==="CSV" || resource.format==="csv"){
							html += '<a href="'+resource.url+'"><img src="images/png/csv-file-format-symbol.png" alt="csv" height="42" width="42"></a>';
						}
						else if (resource.format==="PDF" || resource.format==="pdf"){
							html += '<a href="'+resource.url+'"><img src="images/png/pdf-file-format-symbol.png" alt="csv" height="42" width="42"></a>';
						}
						else if (resource.format==="XLS" || resource.format==="xls" || resource.format==="xlsx" || resource.format==="XLSX"){
							html += '<a href="'+resource.url+'"><img src="images/png/xls-file-format-symbol.png" alt="csv" height="42" width="42"></a>';
						}
						else if (resource.format==="ZIP" || resource.format==="zip"){
							html += '<a href="'+resource.url+'"><img src="images/png/zip-file.png" alt="zip" height="42" width="42"></a>';
						}
						else if (resource.format==="png" || resource.format==="PNG"){
							html += '<a href="'+resource.url+'"><img src="'+resource.url+'" alt="zip" height="42" width="42"></a>';
						}
						else{
							html += '<a href="'+resource.url+'"><img src="images/png/demon.png" alt="zip" height="42" width="42"></a>';
						}
					}
					html += "</div>";
					
					contenido.append(html);
				}
				
			}
			
			if(datos.metadataModified){
				var html = '<div class="metadataModified">';
				html += '&nbsp;Modificado: ';
				html += datos.metadataModified;
				html += '</div>';
				
				contenido.append(html);
			}
			
			if(datos.metadataCreated){
				var html = '<div class="metadataCreated">';
				html += ' Creado: ';
				html += datos.metadataCreated;
				html += '</div>';
				
				contenido.append(html);
			}
			
			
		}
		else {
			contenido.empty();
		}
	}
	
	
	
}