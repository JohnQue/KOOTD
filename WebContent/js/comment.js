/**
 * 
 */

function confirmCheck(){
	var check = confirm("정말로 삭제하시겠습니까? 삭제시 되돌릴 수 없습니다.");
		if(check == true){
			alert("삭제되었습니다!");
			location.href="remove.do?boardNum=${dto.boardNum}";
		}
}
function removeComment(){
	var check = confirm("정말로 삭제하시겠습니까? 삭제시 되돌릴 수 없습니다.");
		if(check == true){
			alert("삭제되었습니다!");
			location.href="removeComment.do?boardNum=${dto.board}&commentNum=${com.commentNum}";
		}
}