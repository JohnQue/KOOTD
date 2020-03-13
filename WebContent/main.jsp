<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <!-- Site made with Mobirise Website Builder v4.8.7, https://mobirise.com -->
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="generator" content="Mobirise v4.8.7, mobirise.com">
  <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1">
  <link rel="shortcut icon" href="assets/images/logo4.png" type="image/x-icon">
  <meta name="description" content="Web Page Creator Description">
  <title>KOOTD : Kyonggi Outfit Of The Day</title>
  <link rel="stylesheet" href="assets/web/assets/mobirise-icons/mobirise-icons.css">
  <link rel="stylesheet" href="assets/tether/tether.min.css">
  <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="assets/bootstrap/css/bootstrap-grid.min.css">
  <link rel="stylesheet" href="assets/bootstrap/css/bootstrap-reboot.min.css">
  <link rel="stylesheet" href="assets/socicon/css/styles.css">
  <link rel="stylesheet" href="assets/dropdown/css/style.css">
  <link rel="stylesheet" href="assets/animatecss/animate.min.css">
  <link rel="stylesheet" href="assets/theme/css/style.css">
  <link rel="stylesheet" href="assets/mobirise/css/mbr-additional.css" type="text/css">
  <link rel="stylesheet" href="css/main.css" type="text/css">
  
  
</head>
<body>
  <section class="menu cid-rbQu1Bna7S" once="menu" id="menu1-n">

    

    <nav class="navbar navbar-expand beta-menu navbar-dropdown align-items-center navbar-fixed-top navbar-toggleable-sm bg-color transparent">
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <div class="hamburger">
                <span></span>
                <span></span>
                <span></span>
                <span></span>
            </div>
        </button>
        <div class="menu-logo">
            <div class="navbar-brand">
                
                <span class="navbar-caption-wrap"><a class="navbar-caption text-white display-2" href="main.do">KOOTD</a></span>
            </div>
        </div>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav nav-dropdown" data-app-modern-menu="true">
            	<li class="nav-item dropdown open">
  					<a class="nav-link link text-white dropdown-toggle display-4" href="" data-toggle="dropdown-submenu" aria-expanded="true">이 달의</a>
                    <div class="dropdown-menu">
                    	<a class="menu-font" href="board.do?boardKind=best" aria-expanded="false">패셔니스타</a>
	                    <a class="menu-font" href="board.do?boardKind=funBest" aria-expanded="false">개성만점자</a>
	                    <a class="menu-font" href="board.do?boardkind=coord" aria-expanded="false">코디네이터</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
	                <a class="nav-link link text-white dropdown-toggle display-4" href="" aria-expanded="false" data-toggle="dropdown-submenu">누가누가 잘입나</a>
	                <div class="dropdown-menu">
	                	<a class="menu-font" href="board.do?boardKind=faKing" aria-expanded="false">도전! 패션킹<br></a>
	                	<a class="menu-font" href="board.do?boardKind=faQueen" aria-expanded="false">도전! 패션퀸<br></a>
	                </div>
               	</li>
               	<li class="nav-item dropdown">
               		<a class="nav-link link text-white dropdown-toggle display-4" href="" aria-expanded="false" data-toggle="dropdown-submenu">이게 패션이다</a>
               		<div class="dropdown-menu">
               			<a class="menu-font" href="board.do?boardKind=funKing" aria-expanded="false">도전! 개성남</a>
               			<a class="menu-font" href="board.do?boardKind=funQueen" aria-expanded="false">도전! 개성녀</a>
               		</div>
               	</li>
               	<li class="nav-item dropdown">
               		<a class="nav-link link text-white dropdown-toggle display-4" href="" aria-expanded="false" data-toggle="dropdown-submenu">이렇게만 입어줘</a>
               		<div class="dropdown-menu">
               			<a class="menu-font" href="board.do?boardKind=toMan" aria-expanded="false">남자들아<br></a>
               			<a class="menu-font" href="board.do?boardKind=toWoman" aria-expanded="false">여자들아<br></a>
               		</div>
               	</li>
               	<li class="nav-item dropdown">
               		<a class="nav-link link text-white dropdown-toggle display-4" href="" aria-expanded="false" data-toggle="dropdown-submenu">옷 정보</a>
               		<div class="dropdown-menu">
               			<a class="menu-font" href="board.do?boardKind=manClothes" aria-expanded="false">남성옷</a>
               			<a class="menu-font" href="board.do?boardKind=womanClothes" aria-expanded="false">여성옷</a>
               		</div>
               	</li>
                <li class="nav-item dropdown">
                	<a class="nav-link link text-white dropdown-toggle display-4" href="" aria-expanded="false" data-toggle="dropdown-submenu">피부</a>
                	<div class="dropdown-menu">
                		<a class="menu-font" href="board.do?boardKind=makeup" aria-expanded="false">메이크업</a>
                		<a class="menu-font" href="board.do?boardKind=trouble" aria-expanded="false">트러블</a>
                	</div>
                </li>
                </ul>
            <c:choose>
            	<c:when test="${info.userID eq null}">
            		<a class="btn btn-sm btn-primary display-4" href="login.do"><span class="mbri-key mbr-iconfont mbr-iconfont-btn"></span>Login</a>
            	</c:when>
            	<c:otherwise>
            		<a class="btn btn-sm btn-primary display-4" href="myPage.do"><span class="mbri-lock mbr-iconfont mbr-iconfont-btn"></span>MyPage</a>
            	</c:otherwise>
            </c:choose>
        </div>
    </nav>
</section>

<section class="engine"><a href="https://mobirise.info/q">responsive website templates</a></section><section class="mbr-section content5 cid-rbQu1AEolb" id="content5-m">

    

    <div class="mbr-overlay" style="opacity: 0.6; background-color: rgb(35, 35, 35);">
    </div>

    <div class="container">
        <div class="media-container-row">
            <div class="title col-12 col-md-8">
                <h2 class="align-center mbr-bold mbr-white pb-3 mbr-fonts-style display-1"><span style="font-weight: normal;">Much better look</span></h2>
                <h3 class="mbr-section-subtitle align-center mbr-light mbr-white pb-3 mbr-fonts-style display-5">경기대 생의 자신감 가득찬 모습을 기대합니다.</h3>
                
                
            </div>
        </div>
    </div>
</section>
<!-- 팝업 띄우기 -->
<div style="background-color: #efefef;">
      <div id=widget style="float: left !important;">
         <div style="margin: 5px !important;">
            <!-- weather widget start -->
            <a target="_blank" onclick="showPopup();">
            <img src="https://w.bookcdn.com/weather/picture/32_35267_1_1_34495e_250_2c3e50_ffffff_ffffff_1_2071c9_ffffff_0_6.png?scode=2&domid=&anc_id=50538" alt="booked.net" /></a>
            <!-- weather widget end -->
         </div>
         <script>
  			function showPopup() { window.open("please.html", "a", " width=300, height=600, left=100, top=50"); }
  		</script>
	</div>
	     <div>
            <!------Do not change code------>
            <a target="_blank" id="calendian" href=""><img id="calendian"
               border="0" src="http://www.calendian.com/gen/ko.png"
               alt="년캘린더 - 한국어, Calendian"></a>
            <!------Do not change code------>
         </div>
</div>
<section class="features3 cid-rbQu1CaI9p" id="features3-o">

    

    
    <div class="container">
        <div class="media-container-row">
            <div class="card p-3 col-12 col-md-6">
                <div class="card-wrapper">
                    <div class="card-img">
                        <img src="${pictures[1]}" style="width:523px; height:350px;" alt="Mobirise">
                    </div>
                    <div class="card-box">
                        <h4 class="card-title mbr-fonts-style display-7">
                            이 달의 패셔니스타 - 남자편</h4>
                        <p class="mbr-text mbr-fonts-style display-7" style="text-align:center;">${mainList[1].userName}/${mainList[1].userMajor}</p>
                    </div>
                    <div class="mbr-section-btn text-center"><a href="boardView.do?boardNum=${mainList[1].boardNum}" class="btn btn-primary display-4">더 보기</a></div>
                </div>
            </div>

            <div class="card p-3 col-12 col-md-6">
                <div class="card-wrapper">
                    <div class="card-img">
                        <img src="${pictures[0]}" style="width:523px; height:350px;" alt="Mobirise">
                    </div>
                    <div class="card-box">
                        <h4 class="card-title mbr-fonts-style display-7">
                            이 달의 패셔니스타 - 여자편</h4>
						<p class="mbr-text mbr-fonts-style display-7" style="text-align:center;">${mainList[0].userName}/${mainList[0].userMajor}</p>
                    </div>
                    <div class="mbr-section-btn text-center"><a href="boardView.do?boardNum=${mainList[0].boardNum}" class="btn btn-secondary display-4">
                            더 보기</a></div>
                </div>
            </div>

            

            
        </div>
    </div>
</section>

<section class="features3 cid-rbQu1DgHIW" id="features3-p">

    

    
    <div class="container">
        <div class="media-container-row">
            <div class="card p-3 col-12 col-md-6">
                <div class="card-wrapper">
                    <div class="card-img">
                        <img src="${pictures[3]}" style="width:523px; height:350px;" alt="Mobirise">
                    </div>
                    <div class="card-box">
                        <h4 class="card-title mbr-fonts-style display-7">이 달의 개성만점자 - 남자 편</h4>
                        <p class="mbr-text mbr-fonts-style display-7" style="text-align:center;">${mainList[3].userName}/${mainList[3].userMajor}</p>
                    </div>
                    <div class="mbr-section-btn text-center"><a href="boardView.do?boardNum=${mainList[3].boardNum}" class="btn btn-primary display-4">
                           더 보기
                        </a></div>
                </div>
            </div>

            <div class="card p-3 col-12 col-md-6">
                <div class="card-wrapper">
                    <div class="card-img">
                        <img src="${pictures[2]}" style="width:523px; height:350px;" alt="Mobirise">
                    </div>
                    <div class="card-box">
                        <h4 class="card-title mbr-fonts-style display-7">이 달의 개성 만점자 - 여자 편</h4>
						<p class="mbr-text mbr-fonts-style display-7" style="text-align:center;">${mainList[2].userName}/${mainList[2].userMajor}</p>
                    </div>
                    <div class="mbr-section-btn text-center"><a href="boardView.do?boardNum=${mainList[2].boardNum}" class="btn btn-secondary display-4">
                            더 보기
                        </a></div>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="features3 cid-rbQu1EwGkd" id="features3-q">

    

    
    <div class="container">
        <div class="media-container-row">
            <div class="card p-3 col-12 col-md-6">
                <div class="card-wrapper">
                    <div class="card-img">
                        <img src="${pictures[4]}" style="width:523px; height:350px;" alt="Mobirise">
                    </div>
                    <div class="card-box">
                        <h4 class="card-title mbr-fonts-style display-7">여성이 추천하는 데이트룩 - 남자 편</h4>
                        <p class="mbr-text mbr-fonts-style display-7" style="text-align:center;">${mainList[5].userName}/${mainList[5].userMajor}</p>
                    </div>
                    <div class="mbr-section-btn text-center"><a href="boardView.do?boardNum=${mainList[5].boardNum}" class="btn btn-primary display-4">
                            더 보기
                        </a></div>
                </div>
            </div>

            <div class="card p-3 col-12 col-md-6">
                <div class="card-wrapper">
                    <div class="card-img">
                        <img src="${pictures[5]}" style="width:523px; height:350px;" alt="Mobirise">
                    </div>
                    <div class="card-box">
                        <h4 class="card-title mbr-fonts-style display-7">
                            남성이 추천하는 데이트룩 - 여자 편</h4>
                        <p class="mbr-text mbr-fonts-style display-7" style="text-align:center;">${mainList[4].userName}/${mainList[4].userMajor}</p>
                    </div>
                    <div class="mbr-section-btn text-center"><a href="boardView.do?boardNum=${mainList[4].boardNum}" class="btn btn-secondary display-4">
                            더 보기
                        </a></div>
                </div>
            </div>

            

            
        </div>
    </div>
</section>

<section class="cid-rbQu1FWjUa" id="social-buttons2-r">

    

    

    <div class="container">
        <div class="media-container-row">
            <div class="col-md-8 align-center">
                <h2 class="pb-3 mbr-fonts-style display-1">
                    FOLLOW ME!
                </h2>
                <div class="social-list pl-0 mb-0">
                    <a href="https://twitter.com/mobirise" target="_blank">
                        <span class="px-2 socicon-twitter socicon mbr-iconfont mbr-iconfont-social"></span>
                    </a>
                    <a href="https://www.facebook.com/jonghan.park.7982">
                        <span class="px-2 mbr-iconfont mbr-iconfont-social socicon-facebook socicon"></span>
                    </a>
                    <a href="https://instagram.com/mobirise" target="_blank">
                        <span class="px-2 socicon-instagram socicon mbr-iconfont mbr-iconfont-social"></span>
                    </a>
                    <a href="https://www.youtube.com/c/mobirise" target="_blank">
                        
                    </a>
                    <a href="https://plus.google.com/u/0/+Mobirise" target="_blank">
                        
                    </a>
                    <a href="https://www.behance.net/Mobirise" target="_blank">
                        
                    </a>
                </div>
            </div>
        </div>
    </div>
</section>
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

  <script src="assets/web/assets/jquery/jquery.min.js"></script>
  <script src="assets/popper/popper.min.js"></script>
  <script src="assets/tether/tether.min.js"></script>
  <script src="assets/bootstrap/js/bootstrap.min.js"></script>
  <script src="assets/smoothscroll/smooth-scroll.js"></script>
  <script src="assets/viewportchecker/jquery.viewportchecker.js"></script>
  <script src="assets/dropdown/js/script.min.js"></script>
  <script src="assets/touchswipe/jquery.touch-swipe.min.js"></script>
  <script src="assets/sociallikes/social-likes.js"></script>
  <script src="assets/theme/js/script.js"></script>
  
  
 <div id="scrollToTop" class="scrollToTop mbr-arrow-up"><a style="text-align: center;"><i></i></a></div>
    <input name="animation" type="hidden">
  </body>
</html>