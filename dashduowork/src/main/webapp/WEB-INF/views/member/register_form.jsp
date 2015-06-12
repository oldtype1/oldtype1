<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%-- Spring Expression Language (SpEL) 선언부 --%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!--여기서부터 register_form 시작-->
<div class="section">
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <form:form class="form-horizontal" role="form" action="member_register.do" 
        enctype="multipart/form-data" method="post" commandName="memberVO">
          <div class="form-group">
          <h1 align="center">회원가입</h1>
            <div class="col-sm-2">
              <label class="control-label">프로필 사진</label>
            </div>
            <div class="col-sm-8">
              <input type="file" class="form-control" name="file">
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-2">
              <label for="inputEmail3" class="control-label">id</label>
            </div>
            <div class="col-sm-8">
              <form:input type="email" class="form-control" id="inputEmail3" placeholder="Email" name="memberId" path="memberId"/>
            </div>
                      <font color="red"><form:errors path="memberId"></form:errors></font>
          </div>
          <div class="form-group">
            <div class="col-sm-2">
              <label for="inputPassword3" class="control-label">password</label>
            </div>
            <div class="col-sm-8">
              <input type="password" class="form-control" id="inputPassword3" placeholder="Password" name="memberPass">
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-2">
              <label for="inputcheckPassword3" class="control-label">check password</label>
            </div>
            <div class="col-sm-8">
              <input type="password" class="form-control" id="inputcheckPassword3" placeholder="password again">
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-2">
              <label for="inputName3" class="control-label">name</label>
            </div>
            <div class="col-sm-8">
              <input type="text" class="form-control" id="inputName3" placeholder="name" name="memberName">
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-2">
              <label for="inputPhone3" class="control-label">phone</label>
            </div>
            <div class="col-sm-8">
              <input type="text" class="form-control" id="inputPhone3" placeholder="phone" name="memberTel">
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-2">
              <label for="inputInfo3" class="control-label">자기 소개</label>
            </div>
            <div class="col-sm-8">
                <textarea rows="4" cols="50" class="form-control" name="memberInfo" placeholder="자기소개"></textarea>
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-offset-4 col-sm-8">
              <button type="submit" class="btn btn-default">회원가입</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <button type="button"
              class="btn btn-default">취소</button>
            </div>
          </div>
        </form:form>
      </div>
    </div>
  </div>
</div>