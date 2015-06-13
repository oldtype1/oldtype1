<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <!-- regiet_from -->
 <div class="section">
      <div class="container">
        <div class="row">
        <div class="col-md-12">
    <center>
      <h2>숙소 등록</h2>
    </center>
    </div>
    </div>
    </div>
    </div>
    <div class="section">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <form class="form-horizontal" role="form" action="inn_register.do" enctype="multipart/form-data"  method="post">
              <div class="form-group">
                <div class="col-sm-2">
                  <label class="control-label">집유형</label>
                </div>
                <div class="col-sm-10">
                  <select class="form-control input-lg" name="innType">
                    <option>집 전체</option>
                    <option>개인실</option>
                    <option>다인실</option>
                  </select>
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputPassword3" class="control-label">숙소명</label>
                </div>
                <div class="col-sm-10">
                  <input type="text" class="form-control input-lg" id="inputPassword3" placeholder="숙소명입력" name="innName" >
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputPassword3" class="control-label" >주소</label>
                </div>
                <div class="col-sm-10">
                  <input type="text" class="form-control input-lg" id="inputPassword3" placeholder="주소입력" name="innAddress">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputPassword3" class="control-label">대지역</label>
                </div>
                <div class="col-sm-10">
                  <input type="text" class="form-control input-lg" id="inputPassword3" placeholder="대지역입력" name="innCity">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputPassword3" class="control-label">소지역</label>
                </div>
                <div class="col-sm-10">
                  <input type="text" class="form-control input-lg" id="inputPassword3" placeholder="소지역입력" name="innArea">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputPassword3" class="control-label">인원수</label>
                </div>
                <div class="col-sm-10">
                  <input type="text" class="form-control input-lg" id="inputPassword3" placeholder="인원수입력" name="acceptableNo" >
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputPassword3" class="control-label">가격</label>
                </div>
                <div class="col-sm-10">
                  <input type="text" class="form-control input-lg" id="inputPassword3" placeholder="가격을 입력" name="innPrice" >
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label class="control-label">날짜</label>
                </div>
                <div class="col-sm-3">
                  <input type="text" class="form-control input-lg" placeholder="체크인"  name="availableDateSt">
                </div>
                <div class="col-sm-3">
                  <input type="text" class="form-control input-lg" placeholder="체크아웃" name="availableDateEnd">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label for="inputPassword3" class="control-label">상세설명</label>
                </div>
                <div class="col-sm-10">
                  <div class="checkbox">
                    <label class="checkbox-inline">
                      <input type="checkbox" name="amenityWiFi" value="Y">wi-fi</label>
                    <label class="checkbox-inline">
                      <input type="checkbox" name="amenityBed" value="Y">bed</label>
                    <label class="checkbox-inline">
                      <input type="checkbox" name="amenityTV" value="Y">tv</label>
                    <label class="checkbox-inline">
                      <input type="checkbox" name="amenityKitchen" value="Y">kitchen</label>
                    <label class="checkbox-inline">
                      <input type="checkbox" name="amenityBBQ" value="Y">bbq</label>
                  </div>
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label class="control-label">사진</label>
                </div>
                <div class="col-sm-3">
                  <input type="file" class="form-control"  name="file[0]">
                </div>
                <div class="col-sm-3">
                  <input type="file" class="form-control" name="file[1]">
                </div>
                <div class="col-sm-3">
                  <input type="file" class="form-control" name="file[2]">
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label class="control-label">설명</label>
                </div>
                <div class="col-sm-10">
                  <textarea class="form-control" placeholder="설명을 입력" name="innInfo"></textarea>
                </div>
              </div>
              <div class="form-group">
                <div class="col-sm-2">
                  <label class="control-label">숙소 가능 여부</label>
                </div>
                <div class="col-sm-10">
                  <select class="form-control" name="innAvailability">
                    <option>Y</option>
                    <option>N</option>
                  </select>
                </div>
              </div>
              <!-- <div class="form-group"></div> -->
            <center>
              <button type="submit"  class="btn btn-lg btn-primary  col-sm-5 col-sm-offset-3" >등록</a></button>
            </center>
            </form>
          </div>
        </div>
      </div>
    </div>
 