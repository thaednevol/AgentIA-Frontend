window.onload = init;
var socket = new WebSocket("ws://localhost:8080/AgentIA-FrontEnd/actions");
socket.onmessage = onMessage;

function onMessage(event) {
    var mensaje = JSON.parse(event.data);
    
    console.log('HOLA! '+event);
    
    console.log(event);
	
    printDeviceElement(mensaje);    
}

function showResults(){
	$("#logo").css("display","none");
	$("#formWrapper").css("display","none");
	$("#sfbg").css("visibility","visible");
	
	$('#content').accordion();
}


function buscar(busqueda, tipo){
	
	var envio={
			action:"buscar",
			datos:{
				abuscar:busqueda,
				metodo:tipo
			}
	};
	socket.send(JSON.stringify(envio));
}

function printDeviceElement(device) {
	$('#content').append('<div class="accordion-title">'+device.name+'</div><div class="accordion-content"><p>'+device.type+'</p></div>');
}

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

function init(){
	$("#logo").toggleClass("logoinicial");
}
