<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script  type="text/javascript">
	$(document).ready(function(){ 
		$("#memberPasswordcheck").click(function(){
			if($("#memberPassWord1").val()==""){
				alert("패스워드를 입력하세요");
				//$("#memberPass").focus();
				return false;
			}
			else{
				$("#memberPasswordcheckForm").submit();
			}
		});	
});// ready 
</script>
	 <c:if test="${sessionScope.mvo!=null}">
        <div class="container">
          <div class="row text-center">
            <div class="col-md-12 text-center">
              <ul class="lead nav nav-justified nav-tabs">
                <li>
                  <a href='member_myprofile.do?memberId=${sessionScope.mvo.memberId}'>내프로필</a>
                </li>
                <li>
                  <a href='get_myinnlist.do'>숙소등록</a>
                </li>
                <li>
                  <a href='get_mybooklist.do'>예약숙소</a>
                </li>
                <li>
                  <a href="get_mytradelist.do">거래내역</a>
                </li>
                <li>
                  <a href='get_mywishlist.do'>위시리스트</a>
                </li>
                <li>
                  <a href="get_innReservation_list.do">예약관리</a>
                </li>
              </ul>
            </div>
          </div>
        </div>
        </c:if>
        

        <div class="container" style="margin-top: 50px">
          <div class="row text-center">
            <div class="col-md-12 text-center">
        <h2>"${requestScope.memberInfo.memberName}"님의 프로필입니다.</h2>
        </div>
        </div>
        </div>

<div style="position: relative; top: 100px; left: 150px; opacity: 0.7">
     <div class="section">
         <div class="container">
         <form role="form" action="member_myprofile_update_form.do">        
            <div class="col-md-4">
               <img src="${requestScope.memberInfo.profilePicVO.filePath}"
                class="img-circle img-responsive" width="200" height="200">
             </div>            
             <div class="row">            
                 <div class="col-md-4">         
                         <div class="form-group">
                             <label class="control-label" for="memberName">Name</label>
                             <input class="form-control" id="memberName"  readonly="readonly"type="text" value="${requestScope.memberInfo.memberName}">
                         </div>
                         <div class="form-group">
                             <label class="control-label" for="memberId">Email address</label>
                             <input class="form-control" id="memberId"  readonly="readonly"
                             value="${requestScope.memberInfo.memberId}" type="email">
                         </div>
                         <div class="form-group">
                             <label class="control-label" for="memberPass">Password</label>
                             <input class="form-control" id="memberPass"  readonly="readonly"
                             value="${requestScope.memberInfo.memberPass}" type="password">
                         </div>
                         <div class="form-group">
                             <label class="control-label" for="memberTel">Tel</label>
                             <input class="form-control" id="memberTel" value="${requestScope.memberInfo.memberTel}"
                             type="text"  readonly="readonly">
                         </div>
                          <div class="form-group">
                             <label for="inputInfo3" class="control-label">자기 소개</label>
                             <textarea readonly="readonly" rows="4" cols="70" class="form-control"placeholder=
                             "${requestScope.memberInfo.memberInfo}
                            파일경로: ${requestScope.memberInfo.profilePicVO.filePath}"></textarea>
                         </div>
                         <input type="hidden" name="memberId" id="memberId" value="${requestScope.memberInfo.memberId}">
                    <button type="submit" class="btn btn-default">수정하러 가기</button>
               <button type="button" data-target="#exampleModal2" data-toggle="modal" id="memberSecession" class="btn btn-default">회원 탈퇴</button>          
                 </div>                                   
             </div>
             </form> 
         </div>        
     </div>
   </div>
   
   
<div class="modal fade" id="exampleModal2" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel2" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel2">비밀번호 재확인</h4>
      </div>
      <form action="memberPasswordcheck.do" id="memberPasswordcheckForm" method="post" >
      <div class="modal-body" id="checkpassword">
          <div class="form-group">
            <label for="message-text" class="control-label">비밀번호:</label>
            <input type="password" class="form-control" id="memberPassWord1" name="memberPass" placeholder="password">
          </div>
          <input type="hidden" value="${requestScope.memberInfo.memberId}" name="memberId">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal"  id="close">Close</button>
        <button type=button class="btn btn-primary" id="memberPasswordcheck">회원탈퇴</button>
      </div>
     </form>
    </div>
  </div>
</div>
