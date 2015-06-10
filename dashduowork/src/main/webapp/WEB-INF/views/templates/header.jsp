<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a href="home.do"><img src="smalllogo.gif" style="float: left;" height="45" width="45"></a>


<div id='cssmenu' style="margin-right:50px; float: right ; height: 50px; opacity=0.7; z-index:2 ">
<script type="text/javascript">
$(document).ready(function(){
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
         <li><a href='#'>내프로필</a>
		<!-- 참고 세부 메뉴입니다. <ul>
               <li><a href='#'>Sub Product</a></li>
               <li><a href='#'>Sub Product</a></li>
            </ul> -->
         </li>
         <li><a href='get_myinnlist.do'>등록숙소</a></li>
         <li><a href='#'>예약숙소</a></li>
         <li><a href='#'>거래내역</a></li>
         <li><a href='#'>위시리스트</a></li>
         <li><a href='#'>쪽지</a></li>        
      </ul>
   </li>
   <li><a href='logout.do'>로그아웃</a></li>
   <li><a href='#'>숙소등록하기</a></li>
   </c:if>
</ul>

</div>



<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
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
        <button type="button" class="btn btn-default" data-dismiss="modal"  id="close">Close</button>
        <button type="submit" class="btn btn-primary" id="login">login</button>
      </div>
     </form>
    </div>
  </div>
</div>

