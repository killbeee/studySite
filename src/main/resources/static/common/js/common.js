/*
	COMMON.JS 공용함수 처리

*/
//서머노트 호출(basic)
function setSummernoteBasic(id){
	
	$('#'+id).summernote({
		  height: 300,                 // 에디터 높이
		  tabsize: 2,
   		  prettifyHtml:false,
		  minHeight: null,             // 최소 높이
		  maxHeight: null,             // 최대 높이
		  focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
		  lang: "ko-KR",					// 한글 설정
		  /*placeholder: '최대 2048자까지 쓸 수 있습니다',	//placeholder 설정*/
		  callbacks: {
		  		onImageUpload: function(files, editor, welEditable) {
			        for (var i = files.length - 1; i >= 0; i--) {
			          sendFile(files[i], this);
			        }
			      }
		   },
		  toolbar: [
			    // [groupName, [list of button]]
			    // Add highlight plugin
        		['highlight', ['highlight']],
			    ['fontname', ['fontname']],
			    ['fontsize', ['fontsize']],
			    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
			    ['color', ['forecolor','color']],
			    ['table', ['table']],
			    ['para', ['ul', 'ol', 'paragraph']],
			    ['height', ['height']],
			    ['insert',['picture','link','video']],
			    ['view', ['fullscreen', 'help']]
			  ],
			fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
			fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72']
          
	});
	
}

    
function sendFile(file, el) {
  var form_data = new FormData();
  form_data.append('file', file);
  $.ajax({
    data: form_data,
    type: "POST",
    url: '/summerNoteImg',
    cache: false,
    contentType: false,
    enctype: 'multipart/form-data',
    processData: false,
    success: function(url) {
      $(el).summernote('editor.insertImage', url);
    }
  });
} 
var fileArr;
var fileInfoArr=[];

/*
*	이미지 업로드 섹션
*
*
*/

//썸네일 클릭시 삭제.
function fileRemove(index) {
    console.log("index: "+index);
    fileInfoArr.splice(index,1);
 
    var imgId="#img_id_"+index;
    $(imgId).remove();
    console.log(fileInfoArr);
}

//썸네일 미리보기.
function previewImage(targetObj, imgArea) {
    var files=targetObj.files;
    if(files.length > 1 ){
		Swal.fire({
		  icon: "error",
		  title: "이런....",
		  text: "사진은 하나만 올려주세요",
		  didClose: () => {
		    return false;
		  }
		});
	}else{
    fileArr=Array.prototype.slice.call(files);
    
    var preview = document.getElementById(imgArea); //div id
    var ua = window.navigator.userAgent;
 
    //ie일때(IE8 이하에서만 작동)
    if (ua.indexOf("MSIE") > -1) {
        targetObj.select();
        try {
            var src = document.selection.createRange().text; // get file full path(IE9, IE10에서 사용 불가)
            var ie_preview_error = document.getElementById("ie_preview_error_" + imgArea);
 
 
            if (ie_preview_error) {
                preview.removeChild(ie_preview_error); //error가 있으면 delete
            }
 
            var img = document.getElementById(imgArea); //이미지가 뿌려질 곳
 
            //이미지 로딩, sizingMethod는 div에 맞춰서 사이즈를 자동조절 하는 역할
            img.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+src+"', sizingMethod='scale')";
        } catch (e) {
            if (!document.getElementById("ie_preview_error_" + imgArea)) {
                var info = document.createElement("<p>");
                info.id = "ie_preview_error_" + imgArea;
                info.innerHTML = e.name;
                preview.insertBefore(info, null);
            }
        }
        //ie가 아닐때(크롬, 사파리, FF)
    } else {
        var files = targetObj.files;
        for ( var i = 0; i < files.length; i++) {
            var file = files[i];
            fileInfoArr.push(file);
 
            var imageType = /image.*/; //이미지 파일일경우만.. 뿌려준다.
            if (!file.type.match(imageType))
                continue;
            // var prevImg = document.getElementById("prev_" + imgArea); //이전에 미리보기가 있다면 삭제
            // if (prevImg) {
            //     preview.removeChild(prevImg);
            // }
 
            var span=document.createElement('span');
            span.id="img_id_" +i;
            span.style.width = '100px';
            span.style.height = '100px';
            preview.appendChild(span);
 
            var img = document.createElement("img");
            img.className="addImg";
            img.classList.add("obj");
            img.file = file;
            img.style.width='inherit';
            img.style.height='inherit';
            img.style.cursor='pointer';
            const idx=i;
            img.onclick=()=>fileRemove(idx);   //이미지를 클릭했을 때 remove함수 실행.
            span.appendChild(img);
 
            if (window.FileReader) { // FireFox, Chrome, Opera 확인.
                var reader = new FileReader();
                reader.onloadend = (function(aImg) {
                    return function(e) {
                        aImg.src = e.target.result;
                    };
                })(img);
                reader.readAsDataURL(file);
            } else { // safari is not supported FileReader
                //alert('not supported FileReader');
                if (!document.getElementById("sfr_preview_error_"+ imgArea)) {
                    var info = document.createElement("p");
                    info.id = "sfr_preview_error_" + imgArea;
                    info.innerHTML = "not supported FileReader";
                    preview.insertBefore(info, null);
                }
            }
        }
    }
   }
}
