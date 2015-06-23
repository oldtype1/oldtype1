<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
 
<div style="position: relative; top: 100px; left: 150px; opacity: 0.7">
     <div class="section">
         <div class="container">
      <form:form class="form-horizontal" role="form" action="member_updateInfo.do" 
        enctype="multipart/form-data" method="post" commandName="memberVO">  
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
                             placeholder="${requestScope.memberInfo.memberPass}"value="${requestScope.memberInfo.memberPass}"type="text" name="memberPass">
                         </div>
                         <div class="form-group">
                             <label class="control-label" for="exampleInputEmail1">Phone</label>
                             <input class="form-control" id="exampleInputEmail1" placeholder="${requestScope.memberInfo.memberTel}" value="${requestScope.memberInfo.memberTel}"
                             type="text" name="memberTel">
                         </div>
                         
                          <div class="form-group">
							<label for="exampleinputInfo3" class="control-label">은행 / 계좌 번호</label>
							<select class="form-control" name="memberBank" id="memberBank">
               				 <option value="">은행을 선택하세요.</option>
 							 <option value="국민">국민은행</option>
 							 <option value="신한">신한은행</option>
 							 <option value="외환">외환은행</option>
 					   		  <option value="농협">농협</option>
 					  		  <option value="우체국">우체국</option>
  		       				   </select>
  						<input type="text" name="memberBankAcount" id="memberBankAcount" placeholder="${requestScope.memberInfo.memberBankAcount}"  
						 value="${requestScope.memberInfo.memberBankAcount}" class="form-control">
						</div>
			
					<div class="form-group">
							<label for="exampleinputInfo3" class="control-label">자기 소개</label>
							<textarea style="resize: none;" rows="4" cols="50" class="form-control"
								name="memberInfo" placeholder="${requestScope.memberInfo.memberInfo}" >${requestScope.memberInfo.memberInfo}</textarea>
						</div>
						
					<div class="form-group">
							<label for="exampleinputInfo3" class="control-label">비밀번호 체크 문제</label>
					  <select class="form-control" name="memberAnswerType" id="memberAnswerType" required="required">
               			 <option value="">질문을 선택하세요. 비밀번호 분실시 힌트를 제공하기 위함입니다.</option>
 						 <option value="나의 신발사이즈는?">나의 신발사이즈는?</option>
 						 <option value="나의 보물 1호는?">나의 보물 1호는?</option>
 						 <option value="나의 부모님 성함은?">나의 부모님 성함은?</option>
 					     <option value="나의 초등학교는?">나의 초등학교는?</option>
  		          </select>
					</div>
					
					<div class="form-group">
						<label for="exampleinputInfo3" class="control-label">비밀번호 체크 정답</label>
						<input type="text" class="form-control" id="memberPassAnswer"
								placeholder="질문에 대한 정답을 적어주세요." name="memberPassAnswer" required="required">
					</div>
						
				          
                       <br><button type="submit" class="btn btn-default">수정하기page</button>     
                               
                 </div>                   
             </div>
             </form:form>
         </div>
     </div>
   </div>