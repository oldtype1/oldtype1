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
drop table member;
drop table inn;
drop table comments;
drop table book;
drop table wishlist;
drop table profile_pic;
drop table inn_pic;
drop table availabledate;
drop table amenity;

select* from member
select* from inn
select* from comments
select* from book
select* from wishlist
select* from profile_pic
select* from inn_pic
select* from availabledate
select* from amenity

CREATE SEQUENCE inn_sequence;
CREATE SEQUENCE book_sequence;
CREATE SEQUENCE wishlist_sequence;
CREATE SEQUENCE profile_pic_sequence;
CREATE SEQUENCE inn_pic_sequence;
CREATE SEQUENCE availabledate_no_sequence;

DROP SEQUENCE inn_sequence;
DROP SEQUENCE book_sequence;
DROP SEQUENCE wishlist_sequence;
DROP SEQUENCE profile_pic_sequence;
DROP SEQUENCE inn_pic_sequence;
DROP SEQUENCE availabledate_no_sequence;

-----------------멤버테이블-----------------------
create table member(
member_id varchar2(50) primary key,
member_name varchar2(50) not null,
member_tel varchar2(50) not null,
member_info clob not null,
member_pass varchar2(50) not null
)

insert into MEMBER
(member_id, member_name, member_tel, member_info, member_pass) 
values('oldtype','방은수','011-1234-5678','데헷','oldtype')

delete from member where member_id = 'oldtype';

select member_id, member_name, member_tel, member_info, member_pass 
from member where member_id='oldtype' and member_pass='oldtype'


-----------------숙소테이블-----------------------
create table inn(
inn_no number primary key,
inn_name varchar2(50) not null,
inn_city varchar2(50) not null,
inn_area varchar2(50) not null,
inn_address varchar2(50) not null,
inn_type varchar2(50) CHECK (inn_type IN ('집 전체', '개인실', '다인실')),
inn_acceptable_no number not null,
inn_price number not null,
inn_info clob not null,
inn_availability VARCHAR2(1) CHECK (inn_availability IN ('Y','N')),
member_id varchar2(50) not null,
constraint fk_member_id foreign key(member_id) references member(member_id) on DELETE CASCADE
)

insert into INN
(inn_no, inn_name, inn_city, inn_area, inn_address, inn_type, inn_acceptable_no, 
inn_price, inn_info,inn_availability,member_id) 
values(inn_sequence.nextval,'판교역','성남','판교','판교동','집 전체',10000,0,'정말 넓고 쾌적해요','Y','oldtype')

-----------------제공시설 테이블(inn_no ref) 오류-----------------------
create table amenity(
amenity_wifi VARCHAR2(1) CHECK (amenity_wifi IN ('Y', 'N')),
amenity_bed VARCHAR2(1) CHECK (amenity_bed IN ('Y', 'N')),
amenity_tv VARCHAR2(1) CHECK (amenity_tv IN ('Y', 'N')),
amenity_kitchen VARCHAR2(1) CHECK (amenity_kitchen IN ('Y', 'N')),
amenity_bbq VARCHAR2(1) CHECK (amenity_bbq IN ('Y', 'N')),
amenity_no number primary key,
constraint fk_inn6 foreign key(amenity_no) references inn(inn_no) on DELETE CASCADE
)

insert into amenity(amenity_wifi,amenity_bed,amenity_tv,amenity_kitchen,amenity_bbq, amenity_no) values
	('Y','Y','Y','Y','Y',7)


-----------------댓글테이블(member_id / inn_no ref) ----------------------
create table comments(
member_id varchar2(50) not null,
inn_no number not null,
comments_no number primary key,
comments_writer varchar2(50) not null
comments_date date not null,
comments_content clob not null,
comments_point number not null,
constraint fk_member foreign key(member_id) references member(member_id),
constraint fk_inn foreign key(inn_no) references inn(inn_no) on DELETE CASCADE
)

-----------------예약테이블(member_id / inn_no ref)-----------------------
create table book(
member_id varchar2(50) not null,
inn_no number not null,
book_no number primary key,
book_checkin date not null,
book_checkout date not null,
book_count number not null,
constraint fk_member1 foreign key(member_id) references member(member_id),
constraint fk_inn1 foreign key(inn_no) references inn(inn_no)
)

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
member_id varchar2(50) primary key,
file_path varchar2(200) not null,
constraint fk_member3 foreign key(member_id) references member(member_id) on DELETE CASCADE 
)

-----------------숙소 사진 테이블(inn_no ref)-----------------------
create table inn_pic(
inn_no number primary key,
file_path varchar2(50),
constraint fk_inn4 foreign key(inn_no) references inn(inn_no) on DELETE CASCADE 
)

-----------------예약가능일자 테이블(inn_no ref)-----------------------
create table availabledate(
availabledate_no number primary key,
inn_no number not null,
availabledate_st date not null,
availabledate_end date not null,
constraint fk_inn5 foreign key(inn_no) references inn(inn_no) on DELETE CASCADE 
)

