<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a href="index.jsp"><img src="smalllogo.gif" style="float: left;" height="45" width="45"></a>

<div id='cssmenu' style="margin-right:40px; float: right ">
<ul>
   <li><a href='#'>회원가입</a></li>
   <li data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo"><a href='#'>로그인</a>
   <li><a href='#'>mypage</a>
      <ul>
         <li><a href='#'>내프로필</a>
		<!-- 참고 세부 메뉴입니다. <ul>
               <li><a href='#'>Sub Product</a></li>
               <li><a href='#'>Sub Product</a></li>
            </ul> -->
         </li>
         <li><a href='#'>등록숙소</a></li>
         <li><a href='#'>예약숙소</a></li>
         <li><a href='#'>거래내역</a></li>
         <li><a href='#'>위시리스트</a></li>
         <li><a href='#'>쪽지</a></li>        
      </ul>
   </li>
   <li><a href='#'>로그아웃</a></li>
   <li><a href='#'>숙소등록하기</a></li>
</ul>
</div>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">로그인</h4>
      </div>
      <form action="">
      <div class="modal-body">
          <div class="form-group">
            <label for="recipient-name" class="control	-label" >아이디:</label>
            <input type="text" class="form-control" id="memberId"  name="memberId"  value="이메일" onfocus="this.value=''">
          </div>
          <div class="form-group">
            <label for="message-text" class="control-label">비밀번호:</label>
            <input type="text" class="form-control" id="memberPassword" name="memberPassword"  onfocus="this.value=''">
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

