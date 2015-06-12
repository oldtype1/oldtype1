<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
 
 <div class="container">
          <div class="row text-center">
            <div class="col-md-12 text-center">
              <ul class="lead nav nav-justified nav-tabs">
                <li>
                  <a href="#">내프로필</a>
                </li>
                <li>
                  <a href="#">숙소등록</a>
                </li>
                <li>
                  <a href="#">예약숙소</a>
                </li>
                <li>
                  <a href="#">거래내역</a>
                </li>
                <li>
                  <a href="#">위시리스트</a>
                </li>
                <li>
                  <a href="#">쪽지</a>
                </li>
              </ul>
            </div>
          </div>
        </div>
 
<div style="position: relative; top: 100px; left: 150px; opacity: 0.7">
     <div class="section">
         <div class="container">
         <form role="form" action="member_updateInfo.do">
             <div class="col-md-4">
               <img src="${requestScope.memberInfo.profilePicVO.filePath}"
                class="img-circle img-responsive" width="200" height="200" style="margin-bottom: 25px">
                <input type="file" class="form-control" name="file">
             </div>  
             <div class="row">
                 <div class="col-md-4">                    
                         <div class="form-group">
                             <label class="control-label" for="exampleInputEmail1">Name</label>
                             <input class="form-control" id="exampleInputEmail1"  placeholder="${requestScope.memberInfo.memberName}"
                             type="text" name="memberName" value="${requestScope.memberInfo.memberName}">
                         </div>
                         <div class="form-group">
                             <label class="control-label" for="exampleInputEmail1">Email address</label>
                             <input class="form-control" id="exampleInputEmail1"
                             placeholder="${requestScope.memberInfo.memberId}" type="text" name="memberId" value="${requestScope.memberInfo.memberId}">
                         </div>
                         <div class="form-group">
                             <label class="control-label" for="exampleInputPassword1">Password</label>
                             <input class="form-control" id="exampleInputPassword1"
                             placeholder="${requestScope.memberInfo.memberPass}"value="${requestScope.memberInfo.memberPass}"type="password" name="memberPass">
                         </div>
                         <div class="form-group">
                             <label class="control-label" for="exampleInputEmail1">Phone</label>
                             <input class="form-control" id="exampleInputEmail1" placeholder="${requestScope.memberInfo.memberTel}" value="${requestScope.memberInfo.memberTel}"
                             type="text" name="memberTel">
                         </div>
                          <div class="form-group">
                             <label for="inputInfo3" class="control-label">자기 소개</label>
                             <textarea rows="4" cols="70" class="form-control"placeholder="${requestScope.memberInfo.memberInfo}" name="memberInfo" >${requestScope.memberInfo.memberInfo}</textarea>
                         </div>
          
                       <br><button type="submit" class="btn btn-default">수정하기page</button>                                     
                 </div>                   
             </div>
             </form>
         </div>
     </div>
   </div>