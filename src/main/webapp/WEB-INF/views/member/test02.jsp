<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../lay/header.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="tr_hashTag_area">
    <p><strong>해시태그 구현</strong></p>
           <div class="form-group">
                <input type="hidden" value="" name="tag" id="rdTag" />
            </div>
        
             <ul id="tag-list"></ul>
                        
            <div class="form-group">
            	<input type="text" id="tag" size="7" placeholder="엔터로 해시태그를 등록해주세요." style="width: 300px"/>
           </div>
           		<button class="hashBtn">전송</button>
</div>


<script>
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
    	
        
        $('.hashBtn').on('click',function(){
    	

    		paramList = {
    				val  : JSON.stringify(val)
    		}
    		alert(val)
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


</script>

</body>
</html>