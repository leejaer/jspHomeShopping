let textarea = document.getElementById("messageWindow");
let webSocket = new WebSocket('ws:localhost:9090');//

let inputMessage = document.getElementById('inputMessage');

webSocket.onerror = function(e){
	onError(e);
}

webSocket.onopen = function(e){
	onOpen(e);
}

webSocket.onmessage = function(e){
	onMessage(e);
}

function onMessage(e){
	let chatMsg = e.data;
	let date = new Date();
	let dateInfo = date.getHours() + ":" +date.getMinutes() +":" + date.getSeconds();
	
	if(chatMsg.subString(0,6) ==server){
		let chat = $("<div class='chat notice'>" + chatMsg + "</div>");
		$('#chat-container').append(chat);
	}else{
		let chat = $("<div class='chat-box'>" + chatMsg + "</div><div class='chat-info chat-box'>"+dateInfo+"</div></div>");
		$('#chat-container').append(chat);
	} 
	
	$('#chat-container').scrollTop($('#chat-container')[0].scrollHeight+20);
	
}

function onOpen(e){
	
}
function onError(e){
	alert(e.data);
}

function send(){
	let chatMag = inputMessage.value;
	if(chatMag=='') return;
	
	let date = new Date();
	let dateInfo = date.getHours() + ":" +date.getMinutes() +":" + date.getSeconds();
	let chat = $("<div class='chat-box'>" + chatMsg + "</div><div class='chat-info chat-box'>"+dateInfo+"</div></div>");
	$('#chat-container').append(chat);
	webSocket.send(chatMag);
	inputMessage.value ="";
	$('#chat-container').scrollTop($('#chat-container')[0].scrollHeight+20);
}
$(function(){
	$('#inputMessage').keydown(function(key){
		if(key.keycode ==13){
			$('#inputMessage').focus();
			send();
		}
	});
	$('#btn-submit').click(function(){
		send();
	})
	
})
