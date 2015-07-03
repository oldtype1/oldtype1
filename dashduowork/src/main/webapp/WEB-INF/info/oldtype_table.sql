-- 6/9 수정
    -- 1. 테이블별로 시퀀스가 필요한 곳에 시퀀스를 삽입했습니다.
	--  inn_sequence, book_sequence, wishlist_sequence, profile_pic_sequence, inn_pic_sequence, availabledate_no_sequence  	

	--2. ON DELETE CASCADE : 옵션을 주면 부모 테이블의 데이터가 지워지면 자식테이블의 데이터도 함께 지우라는 옵션 --
   		 --ON DELETE SET NULL : 부모테이블의 데이터가 지워질 때 자식테이블의 값을 NULL로 설정하라는 옵션--

		-- ON DELETE CASCADE 옵션을 필요한 테이블에 적용했습니다.
		-- inn, book, comments, wishlist, profile_pic, inn_pic, availabledate, amenity
	
	-- 3. comment 테이블을 comments 로 변경하였습니다.(comment는 Oracle 명령어)
	
	-- 4. inn 테이블에 member_id 컬럼을 추가하였습니다.(ON DELETE CASCADE 적용을 위해)
	
	-- 5. 복합 pk를 제거하고 단일 pk로 만들었습니다.
-------------------------------------------------------------------------------------------------------------------
	
-- 6/10 수정
	-- 1. 프로필 사진 테이블 변경
		-- 시퀀스 삭제, pk는 멤버의 아이디를 참조, 파일 이름만 컬럼 외에는 모두 삭제

-------------------------------------------------------------------------------------------------------------------
--6/23 거래테이블 수정
-- 	      별점테이블 추가		  
----------------------------
drop table member;
drop table inn;
drop table comments;
drop table book;
drop table wishlist;
drop table profile_pic;
drop table inn_pic;
drop table availabledate;
drop table amenity;
drop table trade;
drop table rating

select* from member
select* from inn
select* from comments
select* from book
select* from wishlist
select* from profile_pic
select* from inn_pic
select* from availabledate
select* from amenity
select* from trade
select* from rating


CREATE SEQUENCE inn_sequence;
CREATE SEQUENCE book_sequence;
CREATE SEQUENCE wishlist_sequence;
CREATE SEQUENCE profile_pic_sequence;
CREATE SEQUENCE inn_pic_sequence;
CREATE SEQUENCE amenity_sequence;
CREATE SEQUENCE availabledate_no_sequence;
CREATE SEQUENCE trade_sequence;
CREATE SEQUENCE comment_no_sequence;

DROP SEQUENCE inn_sequence;
DROP SEQUENCE book_sequence;
DROP SEQUENCE wishlist_sequence;
DROP SEQUENCE profile_pic_sequence;
DROP SEQUENCE inn_pic_sequence;
DROP SEQUENCE amenity_sequence;
DROP SEQUENCE availabledate_no_sequence;
DROP SEQUENCE trade_sequence;
DROP SEQUENCE comment_no_sequence;

-----------------멤버테이블-----------------------
create table member(
member_id varchar2(200) primary key,
member_name varchar2(50) not null,
member_tel varchar2(50) not null,
member_info clob not null,
member_pass varchar2(50) not null,
member_answer_type varchar2(200) CHECK 
(member_answer_type IN('나의 신발사이즈는?','나의 보물 1호는?','나의 부모님 성함은?','나의 초등학교는?')) not null,
member_pass_answer varchar2(200) not null, 
member_bank varchar2(200) not null,
member_bank_acount varchar2(200) not null
)

alter table member add(member_bank varchar2(200))

insert into MEMBER
(member_id, member_name, member_tel, member_info, member_pass) 
values('oldtype','방은수','011-1234-5678','데헷','oldtype')

delete from member where member_id = 'oldtype';

select member_id, member_name, member_tel, member_info, member_pass 
from member where member_id='oldtype' and member_pass='oldtype'

select * from inn
update inn set inn_availability='N' where inn_no=1

-----------------숙소테이블-----------------------
create table inn(
inn_no number primary key,
inn_name varchar2(50) not null,
inn_area varchar2(50) not null,
inn_address varchar2(200) not null,
inn_type varchar2(50) CHECK (inn_type IN ('집 전체', '개인실', '다인실')),
inn_acceptable_no number not null,
inn_price number not null,
inn_info clob not null,
inn_availability VARCHAR2(1) CHECK (inn_availability IN ('Y','N')),
member_id varchar2(200) not null,
constraint fk_member_id foreign key(member_id) references member(member_id) on DELETE CASCADE
)
select * from inn
ALTER TABLE inn
DROP COLUMN inn_city;


insert into INN
(inn_no, inn_name, inn_city, inn_area, inn_address, inn_type, inn_acceptable_no, 
inn_price, inn_info,inn_availability,member_id) 
values(inn_sequence.nextval,'판교역','성남','판교','판교동','집 전체',10000,0,'정말 넓고 쾌적해요','Y','oldtype')

-----------------제공시설 테이블(inn_no ref) 오류-----------------------
create table amenity(
inn_no number not null,
amenity_item varchar2(200) not null,
constraint fk_inn6 foreign key(inn_no) references inn(inn_no) on DELETE CASCADE
)

ALTER TABLE amenity
DROP COLUMN amenity_no;

drop 
-----------------댓글테이블(member_id / inn_no ref) ----------------------
create table comments(
member_id varchar2(200) not null,
inn_no number not null,
comments_no number primary key,
comments_date date not null,
comments_content clob not null,
constraint fk_member foreign key(member_id) references member(member_id),
constraint fk_inn7 foreign key(inn_no) references inn(inn_no) on DELETE CASCADE
)

ALTER TABLE comments
DROP COLUMN amenity_no;

-----------------예약테이블(member_id / inn_no ref)-----------------------
create table book(
member_id varchar2(200) not null,
inn_no number not null,
book_no number primary key,
book_checkin date not null,
book_checkout date not null,
book_count number not null,
constraint fk_member1 foreign key(member_id) references member(member_id),
constraint fk_inn1 foreign key(inn_no) references inn(inn_no)
)
insert into book (member_id, inn_no, book_no, book_checkin, book_checkout, book_count)
values('oldtype', '4', book_sequence.nextval, '2015-06-16','2015-06-18',4)
-----------------위시리스트 테이블(member_id / inn_no ref)----------------------
create table wishlist(
member_id varchar2(50) not null,
inn_no number not null,
wishlist_no number primary key,
constraint fk_member2 foreign key(member_id) references member(member_id) on DELETE CASCADE,
constraint fk_inn2 foreign key(inn_no) references inn(inn_no) on DELETE CASCADE 
)

-----------------프로필테이블(member_id ref)-----------------------
create table profile_pic(
member_id varchar2(200) primary key,
file_path varchar2(200) not null,
constraint fk_member3 foreign key(member_id) references member(member_id) on DELETE CASCADE 
)

-----------------숙소 사진 테이블(inn_no ref)-----------------------
create table inn_pic(
inn_pic_no number primary key,
inn_no number not null,
file_path varchar2(200) not null,
constraint fk_inn4 foreign key(inn_no) references inn(inn_no) on DELETE CASCADE 
)

-----------------예약가능일자 테이블(inn_no ref)-----------------------
create table availabledate(
availabledate_no number primary key,
	 number not null,
availabledate_st date not null,
availabledate_end date not null,
constraint fk_inn5 foreign key(inn_no) references inn(inn_no) on DELETE CASCADE 
)


------------------------------------------------------------------------------

-----------------------------------줭신꺼
--임의 거래 내역 테이블--
----------------------------------

----------------------------------
--컬럼: 거래내역번호(pk),숙소명, 체크인, 체크아웃, 가격, 예약자(member_id),숙소주인(master)--
-- 예약자랑 세션 id랑 확인하여 같으면 예약이고
--아니면 모두 (사용자의 숙소)등록이다 -- 
create table Trade(
Trade_no number primary key,
inn_name varchar2(50) not null,
book_checkin date not null,
book_checkout date not null,
inn_price number not null,
member_id varchar2(200) not null,
master varchar2(50) not null,
inn_no number,
rating_check VARCHAR2(1) CHECK (rating_check IN ('Y','N')),
constraint fk_member10 foreign key(member_id) references member(member_id) --on DELETE CASCADE --삭제할것
)

alter table Trade add(ratingcheck varchar2(200))

--inn_no: inn_name 클릭시 숙소 상세보기로 넘어가기 위한 inn_no 추가
--rating_check: 별점 매겼는지 안매겻는지 위한 체크 칼럼을 추가

-----------------------------------


-----------------------------------
insert into Trade
(Trade_no, inn_name, book_checkin, book_checkout, inn_price,member_id,master,inn_no,rating_check) 
values(trade_sequence.nextval,'강남오피스텔','2015-06-14','2015-06-16',30000,'oldtype','jh4395@ahaha','1','N');
insert into Trade
(Trade_no, inn_name, book_checkin, book_checkout, inn_price,member_id,master,inn_no,rating_check) 
values(trade_sequence.nextval,'주형이네집','2015-06-15','2015-06-17',30000,'oldtype','jh4395@ahaha','2','Y');
-----------------------------------


delete from trade where book_checkout < sysdate
------------------------------------

drop table rating;

create table rating(
inn_no number primary key,
point number not null,
people_num number,
constraint fk_rating foreign key(inn_no) references inn(inn_no) on DELETE CASCADE
)	

create table report(
   word varchar2(400) primary key,
   cnt number(5) not null
)

