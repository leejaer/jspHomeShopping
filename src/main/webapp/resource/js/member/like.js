$(function(){
	$('.productlike').on('click',function(){
		let pno =$('.pnoClass').val()
		productd.update(pno)
	})
	
	
	 $('.repleBut').on('click',function(){
		let pno =$('.pnoClass').val()
		let id = $('.userid').val()
		let replp=$('.replName').val()
		let replcontent=$('.repl').val()
		let vo={
			pno:pno,
			id:id,
			replp:replp,
			replcontent:replcontent
			
		}
		
		productd.reply(vo,pno)
	})
	
	

});

let productd ={
	update : function(pno){
		$.ajax({
			type: 'post',
			data: {pno:pno},
			url:`${contextPath}/product/like`,
			success: function(result){
				alert(result);
			},
			error: function(){
				alert('에러')
			}
		});
	},
	
	reply : function(vo,pno){
		$.ajax({
			type: 'post',
			data: vo,
			url:`${contextPath}/repl/add`,
			success: function(result){
				alert(result);
				location.href = 'detail?pno='+pno;
			},
			error: function(){
				alert('에러')
			}
		});
	}
}