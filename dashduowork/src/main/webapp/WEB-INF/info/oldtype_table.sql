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

-----------------멤버테이블-----------------------
create table member(
member_id varchar2(50) primary key,
member_name varchar2(50) not null,
member_tel number not null,
member_info varchar2(1000) not null,
member_pass number not null
)

-----------------숙소테이블-----------------------
create table inn(
inn_no number primary key,
inn_name varchar2(50) not null,
inn_city varchar2(50) not null,
inn_area varchar2(50) not null,
inn_address varchar2(50) not null,
inn_type varchar2(50) not null,
inn_acceptable_no number not null,
inn_price number not null,
inn_info clob not null,
inn_availability VARCHAR2(1)    
                        CHECK (inn_availability IN ('Y', 'N'))
)

insert into INN(inn_no, inn_name, inn_city, inn_area, inn_address, inn_type, inn_acceptable_no, inn_price, inn_info,inn_availability) values(2, '판교역', '성남','판교','판교동','대저택',10000,0,'정말 넓고 쾌적해요','D')

-----------------댓글테이블(member_id / inn_no ref) ----------------------
create table comments(
member_id varchar2(50) not null,
inn_no number not null,
comments_no number not null,
comments_writer varchar2(50) not null
comments_date date not null,
comments_content clob not null,
comments_point number not null,
constraint fk_member foreign key(member_id) references member,
constraint fk_inn foreign key(inn_no) references inn,
constraint pk_comment primary key(member_id,inn_no)
)

-----------------예약테이블(member_id / inn_no ref)-----------------------
create table book(
member_id varchar2(50) not null,
inn_no number not null,
book_no number not null,
book_checkin date not null,
book_checkout date not null,
book_count number not null,
constraint fk_member1 foreign key(member_id) references member,
constraint fk_inn1 foreign key(inn_no) references inn,
constraint pk_comment1 primary key(member_id,inn_no)
)

-----------------위시리스트 테이블(member_id / inn_no ref)----------------------
create table wishlist(
member_id varchar2(50) not null,
inn_no number not null,
wishlist_no number not null,
constraint fk_member2 foreign key(member_id) references member,
constraint fk_inn2 foreign key(inn_no) references inn,
constraint pk_comment2 primary key(member_id,inn_no)
)

-----------------프로필테이블(member_id ref)-----------------------
create table profile_pic(
profile_pic number primary key,
member_id varchar2(50) not null,
profile_pic_filepath varchar2(50) not null,
profile_pic_ogname varchar2(50) not null,
profile_pic_currname varchar2(50) not null,
constraint fk_member3 foreign key(member_id) references member(member_id)
)

-----------------숙소 사진 테이블(inn_no ref)-----------------------
create table inn_pic(
profile_pic number primary key,
inn_no number not null,
inn_pic_filepath varchar2(50) not null,
inn_pic_ogname varchar2(50) not null,
inn_pic_currname varchar2(50) not null,
constraint fk_inn4 foreign key(inn_no) references inn(inn_no)
)

-----------------예약가능일자 테이블(inn_no ref)-----------------------
create table availabledate(
availabledate_no number primary key,
inn_no number not null,
availabledate_st date not null,
availabledate_end date not null,
constraint fk_inn5 foreign key(inn_no) references inn(inn_no)
)

-----------------제공시설 테이블(inn_no ref) 오류-----------------------
create table amenity(
amenity_wifi CHAR(1) CHECK (id = "1" OR id = "2")  --몰라 구글 떴어--
amenity_bed CHAR(1) CHECK (id = "1" OR id = "2")  --몰라 구글 떴어--
amenity_tv CHAR(1) CHECK (id = "1" OR id = "2")  --몰라 구글 떴어--
amenity_kitchen CHAR(1) CHECK (id = "1" OR id = "2")  --몰라 구글 떴어--
amenity_bbq CHAR(1) CHECK (id = "1" OR id = "2")  --몰라 구글 떴어--
inn_no number number primary key constraint fk_inn6 foreign key(inn_no) references inn(inn_no)
)