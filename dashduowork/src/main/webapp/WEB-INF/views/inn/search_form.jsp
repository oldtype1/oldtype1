<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="http://code.jquery.com/jquery-1.11.3.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#searchBtn").click(function(){
		/* alert("보낸다") */
		$("#searchForm").submit();
	});
});
</script>
<div class="section">
    <div class="container">
        <div class="row">
            <form id="searchForm" action="selectInnByCheckedAmenity.do">
                <input type="checkbox" name="amenityWiFi" value="Y">WiFi
                <input type="checkbox" name="amenityBed" value="Y">Bed
                <input type="checkbox" name="amenityTV" value="Y">TV
                <input type="checkbox" name="amenityKitchen" value="Y">주방
                <input type="checkbox" name="amenityBBQ" value="Y">바베큐
                <input type="button" id="searchBtn" value="검색">
            </form>
        </div>
    </div>
</div>