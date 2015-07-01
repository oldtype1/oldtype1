<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a href="home.do"><img src="${initParam.root}img/home.jpg" style="float: left;" height="50" width="100"></a>


<div id='cssmenu' style="margin-right:50px; float: right ; height: 50px; opacity=0.7; z-index:2 ">

<script type="text/javascript">
$(document).ready(function(){
	var text = "";
	$("#loginForm").submit(function(){
		//alert("val:"+$("#memberId").val());
		if($("#memberId").val()==""){
			alert("아이디를 입력하세요");
			$("#memberId").focus();
			return false;
		}else if($("#memberPassword").val()==""){
			alert("패스워드를 입력하세요");
			$("#memberPassword").focus();
			return false;
		}
	}); //#loginForm
	
/* 	function searchPass(){
		$.ajax({ //db에 저장되어있는 값들 ajax로 반환
			type : "post",
			url : "searchPass.do",
			data : $("#searchPassForm").serialize(),
			dataType : "json",
			success : function(data) {
				if(data==null){
					alert(data)
				}else{
					alert("rerere");
				}
			}//success
		}); //ajax
		
	} //searchPassForm */
	
});// ready
</script>
<ul>
 <c:if test="${sessionScope.mvo==null}">
   <li id="member_register"><a href='member_register_form.do'>회원가입</a></li>
   <li data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo"><a href='#'>로그인</a>
  </c:if>
   <c:if test="${sessionScope.mvo!=null}">
   <li><a href='#'>${sessionScope.mvo.memberName}님</a></li>
   <li><a href='#'>MyPage</a>
      <ul>
        <li><a href='member_myprofile.do?memberId=${sessionScope.mvo.memberId}'>내프로필</a>
		<!-- 참고 세부 메뉴입니다. <ul>
               <li><a href='#'>Sub Product</a></li>
               <li><a href='#'>Sub Product</a></li>
            </ul> -->
         </li>
         <li><a href='get_myinnlist.do'>등록숙소</a></li>
    <li><a href='get_mybooklist.do'>예약숙소</a></li>
         <li><a href='get_mytradelist.do'>거래내역</a></li>
                <li><a href='get_mywishlist.do'>위시리스트</a></li>
         <li><a href='get_innReservation_list.do'>내가 등록한 숙소의 예약 목록</a></li>        
      </ul>
   </li>
   <li><a href='logout.do'>로그아웃</a></li>
   <li><a href='inn_register_from.do'>숙소등록하기</a></li>
   </c:if>
</ul>

</div>


<!-- 로그인 modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" >
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" >&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">로그인</h4>
      </div>
      <form action="login.do" id="loginForm" method="post">
      <div class="modal-body">
          <div class="form-group">
            <label for="recipient-name" class="control	-label" >아이디:</label>
            <input type="text" class="form-control" id="memberId"  name="memberId"  placeholder="e-mail" onfocus="this.value=''">
          </div>
          <div class="form-group">
            <label for="message-text" class="control-label">비밀번호:</label>
            <input type="password" class="form-control" id="memberPass" name="memberPass" placeholder="password"  onfocus="this.value=''">
          </div>
      </div>
      <div class="modal-footer">
      	<button type="button" class="btn btn-info" data-toggle="modal" data-target="#passAnswerModal" data-whatever="@mdo" >Search Password</button>
        <button type="button" class="btn btn-default" data-dismiss="modal"  id="close">Close</button>
        <button type="submit" class="btn btn-primary" id="login">login</button>
      </div>
     </form>
    </div>
  </div>
</div>

<!-- 패스워드 찾기 modal -->
<div class="modal fade" id="passAnswerModal" tabindex="-2" >

  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" >&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">비밀번호 찾기</h4>
      </div>
      <form action="searchPass.do" id="searchPassForm" method="post">
      <div class="modal-body">
        
         <div class="form-group">
            <label for="message-text" class="control-label">아이디:</label>
            <input type="email" class="form-control" id="memberId" name="memberId" placeholder="본인의 이메일을 입력하세요"  onfocus="this.value=''" required="required">
          </div>
       <div class="form-group">
            <label for="recipient-name" class="control-label" >질문:</label>
               <select class="form-control" name="memberAnswerType" id="memberAnswerType" required="required">
               			 <option value="">질문을 선택하세요.</option>
 						 <option value="나의 신발사이즈는?">나의 신발사이즈는?</option>
 						 <option value="나의 보물 1호는?">나의 보물 1호는?</option>
 						 <option value="나의 부모님 성함은?">나의 부모님 성함은?</option>
 					     <option value="나의 초등학교는?">나의 초등학교는?</option>
  		     </select>
          </div>
          <div class="form-group">
            <label for="message-text" class="control-label">정답:</label>
            <input type="text" class="form-control" id="memberPassAnswer" name="memberPassAnswer" placeholder="정답을 입력해주세요"  onfocus="this.value=''" required="required">
          </div>
      </div>
      <div class="modal-footer">
      	<button type="submit" class="btn btn-primary" id="inputAnswer">input Answer</button>
        <button type="button" class="btn btn-default" data-dismiss="modal"  id="answerClose">Close</button>
      </div>
      
     </form>

    </div>
  </div>
</div>

