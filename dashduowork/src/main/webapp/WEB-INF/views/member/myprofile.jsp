<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
 
 <script>
 $(document).ready(function(){
    $("#memberSecession").click(function(){
       location.href="memberSecession.do?memberId=${requestScope.memberInfo.memberId}";
    });
    
 });
 </script>
<div style="position: relative; top: 100px; left: 150px; opacity: 0.7">
     <div class="section">
         <div class="container">
             <div class="col-md-4">
                 <img src="http://pingendo.github.io/pingendo-bootstrap/assets/placeholder.png"
                 class="img-responsive">
             </div>
             <div class="row">
                 <div class="col-md-4">
                     <form role="form" action="member_myprofile_update_form.do">
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
                             value=${requestScope.memberInfo.memberPass}" type="password">
                         </div>
                         <div class="form-group">
                             <label class="control-label" for="memberTel">Tel</label>
                             <input class="form-control" id="memberTel" value="${requestScope.memberInfo.memberTel}"
                             type="text"  readonly="readonly">
                         </div>
                          <div class="form-group">
                             <label for="inputInfo3" class="control-label">자기 소개</label>
                             <textarea readonly="readonly" rows="4" cols="70" class="form-control"placeholder="${requestScope.memberInfo.memberInfo}"></textarea>
                         </div>
                         <input type="hidden" name="memberId" id="memberId" value="${requestScope.memberInfo.memberId}">
                    <button type="submit" class="btn btn-default">수정하러 가기</button>
               <button type="button" id="memberSecession" class="btn btn-default">회원 탈퇴</button>
                     </form>            
                 </div>                   
             </div>
         </div>
     </div>
   </div>