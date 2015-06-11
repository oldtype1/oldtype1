<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<div style="position: relative; top: 100px; left: 150px; opacity: 0.7">
     <div class="section">
         <div class="container">
             <div class="col-md-4">
                 <img src="http://pingendo.github.io/pingendo-bootstrap/assets/placeholder.png"
                 class="img-responsive">
             </div>
             <div class="row">
                 <div class="col-md-4">
                     <form role="form" >
                         <div class="form-group">
                             <label class="control-label" for="memberName">Name</label>
                             <input class="form-control" id="memberName" 
                             type="text" value="${requestScope.memberInfo.memberName}">
                         </div>
                         <div class="form-group">
                             <label class="control-label" for="memberId">Email address</label>
                             <input class="form-control" id="memberId"
                             value="${requestScope.memberInfo.memberId}" type="email" readonly="readonly" name="memberId">
                         </div>
                         <div class="form-group">
                             <label class="control-label" for="memberPass">Password</label>
                             <input class="form-control" id="memberPass"
                             value=${requestScope.memberInfo.memberPass}" type="password">
                         </div>
                         <div class="form-group">
                             <label class="control-label" for="memberTel">Tel</label>
                             <input class="form-control" id="memberTel" value="${requestScope.memberInfo.memberTel}"
                             type="text" >
                         </div>
                          <div class="form-group">
                             <label for="inputInfo3" class="control-label">자기 소개</label>
                             <textarea rows="4" cols="70" class="form-control"placeholder="${requestScope.memberInfo.memberInfo}" 
                          >${requestScope.memberInfo.memberInfo}</textarea>
                         </div>
         
                     </form>
                 
                 </div>
                    
             </div>
         </div>
     </div>
   </div>