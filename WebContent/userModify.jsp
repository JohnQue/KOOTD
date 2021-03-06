<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/custom.css">
	<title>KOOTD : Kyonggi Outfit Of The Day</title>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/joinCheck.js" charset='UTF-8'></script>
</head>
<body>
	<c:if test="${info eq null}" >
			<c:set var="messageContent" value="로그인을 먼저 해야합니다." scope="session" />
			<c:set var="messageType" value="오류 메시지"  scope="session" />
			<c:redirect url="login.do" />
	</c:if>
	<nav class="navbar navbar-inverse navbar-fixed-top" id="banner">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="main-header" href="main.do">KOOTD</a>
		</div>
		<div class="container-fluid" id="bs-example-navbar-collapse-1">
			<div class="nav navbar-nav navbar-right">
				<div>
					<input id="login-button" type="button" value="로그아웃" onclick="location.href='logout.do'">
				</div>
			</div>
				<div class="nav navbar-nav navbar-right">
					<div class="dropdown">
						<button class="dropbtn">
							이 달의<span class="caret"></span>
						</button>
						<div class="dropdown-content">
							<a href="board.do?boardKind=best">패셔니스타</a>
							<a href="board.do?boardKind=funBest">박장대소룩</a>
							<a href="board.do?boardKind=coord">코디네이터</a>
						</div>
					</div>
					<div class="dropdown">
						<button class="dropbtn">
							누가누가 잘입나<span class="caret"></span>
						</button>
						<div class="dropdown-content">
							<a href="board.do?boardKind=faKing">도전! 패션킹</a>
							<a href="board.do?boardKind=faQueen">도전! 패션퀸</a>
						</div>
					</div>
					<div class="dropdown">
						<button class="dropbtn">
							이게 패션이다<span class="caret"></span>
						</button>
						<div class="dropdown-content">
							<a href="board.do?boardKind=funKing">도전! 웃긴남</a>
							<a href="board.do?boardKind=funQueen">도전! 웃긴녀</a>
						</div>
					</div>
					<div class="dropdown">
						<button class="dropbtn">
							이렇게만 입어줘<span class="caret"></span>
						</button>
						<div class="dropdown-content">
							<a href="board.do?boardKind=toMan">남자에게</a>
							<a href="board.do?boardKind=toWoman">여자에게</a>
						</div>
					</div>
					<div class="dropdown">
						<button class="dropbtn">
							옷정보<span class="caret"></span>
						</button>
						<div class="dropdown-content">
							<a href="board.do?boardKind=manClothes">남자옷</a>
							<a href="board.do?boardKind=womanClothes">여자옷</a>
						</div>
					</div>
					<div class="dropdown">
						<button class="dropbtn">
							피부<span class="caret"></span>
						</button>
						<div class="dropdown-content">
							<a href="board.do?boardKind=makeUp">화장품</a>
							<a href="board.do?boardKind=trouble">트러블</a>
						</div>
					</div>
				</div>
		</div>
	</nav>
	<div class="container" style="margin-top: 150px;">
		<form method="post" action="modifyOk.do">
			<table class="table table-bordered table-hover " style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="3"><h4>개인정보 수정</h4></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width: 110px;"><h5>아이디</h5></td>
						<td><input class="form-control" type="text" id="userID" name="userID" maxlength="20" placeholder="${info.userID}" disabled></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>전공</h5></td>
						<td colspan="2">
						<div class="style-select">	
							<select name="userUniv" onchange="showMajor(this.value)" required>
								<option value="">대학선택</option>
								<option value="융합교육대학">융합교육대학</option>
								<option value="휴먼인재융합대학">휴먼인재융합대학</option>
								<option value="지식정보서비스대학">지식정보서비스대학</option>
								<option value="융합과학대학">융합과학대학</option>
								<option value="창의공과대학">창의공과대학</option>
								<option value="관광문화대학">관광문화대학</option>
							</select>
							<span id="selMajor" class="style-select"></span>
						</div>
						</td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>현재 비밀번호</h5></td>
						<td colspan="2"><input class="form-control" type="password" id="userPassword" name="userPassword" maxlength="20" placeholder="비밀번호를 입력하세요."></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>새비밀번호</h5></td>
						<td colspan="2"><input onkeyup="passwordCheckFunction()" class="form-control" type="password" id="userPassword1" name="userPassword1" maxlength="20" placeholder="비밀번호를 입력하세요."></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>비밀번호확인</h5></td>
						<td colspan="2"><input onkeyup="passwordCheckFunction()" class="form-control" type="password" id="userPassword2" name="userPassword2" maxlength="20" placeholder="한번 더 비밀번호를 입력하세요."></td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>이름</h5></td>
						<td colspan="2"><input class="form-control" type="text" id="userName" name="userName" maxlength="20" placeholder="${info.userName}" disabled></td>	
					</tr>
					<tr>
						<td style="width: 110px;"><h5>성별</h5></td>
						<td colspan="2">
							<div class="form-group" style="text-align: center; margin: 0 auto;">
								<div class="btn-group" data-toggle="buttons">
									<label class="btn btn-success disabled">
										<input type="radio" name="userGender" autocomplete="off" value="남자"> 남자
									</label>
									<label class="btn btn-success disabled">
										<input type="radio" name="userGender" autocomplete="off" value="여자"> 여자
									</label>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td style="width: 110px;"><h5>나이</h5></td>
						<td colspan="2"><input class="form-control" type="number" id="userAge" name="userAge" maxlength="20" placeholder="${info.userAge}" disabled></td>	
					</tr>
					<tr>
						<td style="width: 110px;"><h5>이메일</h5></td>
						<td colspan="2"><input class="form-control" type="email" id="userEmail" name="userEmail" maxlength="50" value="${info.userEmail}"></td>	
					</tr>
					<tr>
						<td style="text-align: left;" colspan="3">
							<h5 style="color: red;" id="passwordCheckMessage"></h5>
							<input class="btn btn-success pull-right" type="submit" value="등록">
						</td>	
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	
	<c:set var="messageContent" value="${messageContent}" />
	<c:set var="messageType" value="${messageType}" />
	<c:if test="${messageContent ne null}">
		<div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-hidden="true">
			<div class="vertical-alignment-helper">
				<div class="modal-dialog vertical-align-center">
					<div class="modal-content <c:choose>
					<c:when test='${messageType eq "오류 메시지"}'>
						<c:out value='panel-warning' />
					</c:when>
					<c:otherwise>
							<c:out value='panel-success' />
					</c:otherwise>
					</c:choose>">
						<div class="modal-header panel-heading">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times</span>
								<span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title">
								<c:out value="${messageType}" />
							</h4>
						</div>
						<div class="modal-body">
							<c:out value="${messageContent}" />
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script>
			$('#messageModal').modal("show");
		</script>
	<c:remove var="messageContent" />
	<c:remove var="messageType" />
	</c:if>
	<div class="modal fade" id="checkModal" tabindex="-1" role="dialog" aria-hidden="true">
			<div class="vertical-alignment-helper">
				<div class="modal-dialog vertical-align-center">
					<div id="checkType" class="modal-content panel-info">
						<div class="modal-header panel-heading">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times</span>
								<span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title">
								확인 메시지
							</h4>
						</div>
						<div id="checkMessage" class="modal-body">
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
						</div>
					</div>
				</div>
			</div>
	</div>
	
</body>
</html>