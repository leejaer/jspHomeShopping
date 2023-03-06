<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../lay/header.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>CSS Template</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
.drag-over { background-color: rgba(160, 194, 255, 0.205); }
.thumb { width:200px; padding:5px; float:left; }
.thumb > img { width:100%; }
.thumb > .close { position:absolute; background-color:rgba(5, 255, 68, 0.089); cursor:pointer; }
</style>
</head>
<body>
  <input type="button" class="btnSubmit" value="업로드"/>
  <div id="drop" style="border:1px solid black; width:800px; height:300px; padding:3px">
    여기로 drag & drop
    <div id="thumbnails">
    </div>
  </div>
<script>
	var uploadFiles = [];
	var $drop = $("#drop");
	$drop.on("dragenter", function(e) {  //드래그 요소가 들어왔을떄
	  $(this).addClass('drag-over');
	}).on("dragleave", function(e) {  //드래그 요소가 나갔을때
	  $(this).removeClass('drag-over');
	}).on("dragover", function(e) {
	  e.stopPropagation();
	  e.preventDefault();
	}).on('drop', function(e) {  //드래그한 항목을 떨어뜨렸을때
	  e.preventDefault();
	  $(this).removeClass('drag-over');
	  var files = e.originalEvent.dataTransfer.files;  //드래그&드랍 항목
	  for(var i = 0; i < files.length; i++) {
	    var file = files[i];
	    var size = uploadFiles.push(file);  //업로드 목록에 추가
	    preview(file, size - 1);  //미리보기 만들기
	  }  
	});
	
	
	function preview(file, idx) {
	  var reader = new FileReader();
	  reader.onload = (function(f, idx) {
	    return function(e) {
	      var div = '<div class="thumb"> \
	        <div class="close" data-idx="' + idx + '">X</div> \
	        <img src="' + e.target.result + '" title="' + escape(f.name) + '"/> \
	      </div>';
	      $("#thumbnails").append(div);
	    };
	  })(file, idx);
	  reader.readAsDataURL(file);
	}
	
	
	$(".btnSubmit").on("click", function() {
	  var formData = new FormData();
	  $.each(uploadFiles, function(i, file) {
	    if(file.upload != 'disable')  //삭제하지 않은 이미지만 업로드 항목으로 추가
	      formData.append('upload-file', file, file.name);
	  });
	  
	  $.ajax({
		    url: `${contextPath}/member/test01`,
		    data : formData,
		    type : 'post',
		    contentType : false,
		    processData: false,
		    success : function(result) {
		      alert(result);
		    }
	  });
	});
	
	
	$("#thumbnails").on("click", ".close", function(e) {
	  var $target = $(e.target);
	  var idx = $target.attr('data-idx');
	  uploadFiles[idx].upload = 'disable';  //삭제된 항목은 업로드하지 않기 위해 플래그 생성
	  $target.parent().remove();  //프리뷰 삭제
	});
</script>
</body>
</html>