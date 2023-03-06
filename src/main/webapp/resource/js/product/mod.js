$(function() {
	let detail =$('.productDetail');
	
	$('.productMod').on('click',function(e){
		e.preventDefault;
		detail.attr({
			"action":`${contextPath}/product/modForm`,
			"method": "post"})
		.submit();
	})
	
    $(document).ready(function () {
    var tag = {};
    var counter = 0;
    var val = [];
    function addTag (value) {
        tag[counter] = value;
        counter++; 
    }

    function marginTag () {
        return Object.values(tag).filter(function (word) {
            return word !== "";
        });
    }

    $("#tag-form").on("submit", function (e) {
        var value = marginTag(); 
        $("#rdTag").val(value); 

        $(this).submit();
    });

    $("#tag").on("keypress", function (e) {
        var self = $(this);
        if (e.key === "Enter" || e.keyCode == 32) {
            var tagValue = self.val(); 
            if (tagValue !== "") {
                var result = Object.values(tag).filter(function (word) {
                    return word === tagValue;
                })
            
                if (result.length == 0) { 
                    $("#tag-list").append("<div class='tag-item'>"+tagValue+"<span class='del-btn' idx='"+counter+"'>x</span></div>");
                    addTag(tagValue);
                    self.val("");
                    val.push(tagValue);
                    
                } else {
                    alert("태그값이 중복됩니다.");
                }
            }
            e.preventDefault(); 
        }
    });


    $(document).on("click", ".del-btn", function (e) {
        var index = $(this).attr("idx");
        tag[index] = "";
        $(this).parent().remove();
        val.pop(tag[index]);
    });
	
    
    $('.hashTag').on('click',function(){
		let name =$('.name').val()
		let pno =$('.pnoClass').val()
		
	
		paramList = {
				val  : JSON.stringify(val),
				name: name,
				pno : pno
		}
		product.val(paramList)
	})
        
        
        
})

let product ={
	val : function(paramList){
		$.ajax({
			type: 'post',
			url:`${contextPath}/product/val`,
			data: paramList,
			success: function(result){
				alert(result);
			},
			error: function(){
				alert('에러')
			}
		});
	}
}

	


	
});