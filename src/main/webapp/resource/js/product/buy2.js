$(function(){
	
	let array = new Array();
	let check = new Array();
	let id = new Array();
	
	$('.pcheckbox').on('change',function(){
		if($(this).prop('checked')){
			array.push($(this).closest('tr').find('.buycount').val())
			check.push($(this).closest('tr').find('.ppp').val())
			id.push($(this).closest('tr').find('.pid').val());
		}else{
			array.pop($(this).closest('tr').find('.buycount').val())
			check.pop($(this).closest('tr').find('.ppp').val())
			id.pop($(this).closest('tr').find('.pid').val());
		}
	})
	
	
	$('.productB').on('click',function(){
		var paramList={};

		paramList = {
   			array : JSON.stringify(array),//count
   			check : JSON.stringify(check),//pno
   			id : JSON.stringify(id)
		}
		product.buy(paramList);
	})
	
	
	
	$('#allSelect').on('click',function(){
		$(".pcheckbox").prop("checked", true);
	})

	$('#allSolve').on('click',function(){
		$(".pcheckbox").prop("checked", false); 
		array =[];
	})
});


let product ={
	buy : function(VO){
		$.ajax({
			type: 'post',
			url:`${contextPath}/product/buy`,
			data: VO,
			success: function(result){
				
				alert(result);
				if(result="정확한 값을 입력하세요"){
						
				}else{
					if(result=='잔액이 부족합니다'){
					
					}else{
						location.href = '../main'
					}
				}
				
				
			},
			error: function(){
				alert('에러')
			}
		});
	}
}
