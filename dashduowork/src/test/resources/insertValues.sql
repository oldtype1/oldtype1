insert into INN
(inn_no, inn_name, inn_city, inn_area, inn_address, inn_type, inn_acceptable_no, 
inn_price, inn_info,inn_availability,member_id) 
values(inn_sequence.nextval,'판교역','성남','판교','판교동','집 전체',10000,0,'정말 넓고 쾌적해요','Y','oldtype');
insert into INN
(inn_no, inn_name, inn_city, inn_area, inn_address, inn_type, inn_acceptable_no, 
inn_price, inn_info,inn_availability,member_id) 
values(inn_sequence.nextval,'강남오피스텔','서초','강남','서초구','개인실',2,5000,'유흥가 가까움','Y','oldtype');
insert into INN
(inn_no, inn_name, inn_city, inn_area, inn_address, inn_type, inn_acceptable_no, 
inn_price, inn_info,inn_availability,member_id) 
values(inn_sequence.nextval,'용인촌','용인','역북','역북동 주공아파트','집 전체',6,10000,'동네가 매우 한적합니다.','Y','oldtype');
insert into INN
(inn_no, inn_name, inn_city, inn_area, inn_address, inn_type, inn_acceptable_no, 
inn_price, inn_info,inn_availability,member_id) 
values(inn_sequence.nextval,'영통메르디앙','수원','영통','영통메르디앙APT','집 전체',5,30000,'경희대학교 인접해있습니다.','Y','oldtype');
insert into INN
(inn_no, inn_name, inn_city, inn_area, inn_address, inn_type, inn_acceptable_no, 
inn_price, inn_info,inn_availability,member_id) 
values(inn_sequence.nextval,'영통메르디앙2','수원','영통','영통메르디앙APT2','집 전체',5,25000,'경희대학교 인접해있습니다.','Y','oldtype');
insert into INN
(inn_no, inn_name, inn_city, inn_area, inn_address, inn_type, inn_acceptable_no, 
inn_price, inn_info,inn_availability,member_id) 
values(inn_sequence.nextval,'영통메르디앙3','수원','영통','영통메르디앙APT3','집 전체',5,27000,'경희대학교 인접해있습니다.','Y','oldtype');
insert into INN
(inn_no, inn_name, inn_city, inn_area, inn_address, inn_type, inn_acceptable_no, 
inn_price, inn_info,inn_availability,member_id) 
values(inn_sequence.nextval,'영통메르디앙4','수원','영통','영통메르디앙APT4','집 전체',3,27000,'경희대학교 인접해있습니다.','Y','oldtype');

select*from inn;

insert into amenity(amenity_wifi,amenity_bed,amenity_tv,amenity_kitchen,amenity_bbq, amenity_no) values
('Y','Y','Y','Y','Y',9);
insert into amenity(amenity_wifi,amenity_bed,amenity_tv,amenity_kitchen,amenity_bbq, amenity_no) values
('Y','N','Y','N','Y',10);
insert into amenity(amenity_wifi,amenity_bed,amenity_tv,amenity_kitchen,amenity_bbq, amenity_no) values
('Y','N','N','N','Y',11);
insert into amenity(amenity_wifi,amenity_bed,amenity_tv,amenity_kitchen,amenity_bbq, amenity_no) values
('Y','N','N','N','Y',12);

insert into availabledate(availabledate_no, inn_no, availabledate_st, availabledate_end) values(availabledate_no_sequence.nextval, 9, '2015-05-18', '2015-05-25');
insert into availabledate(availabledate_no, inn_no, availabledate_st, availabledate_end) values(availabledate_no_sequence.nextval, 9, '2015-05-27', '2015-05-31');
insert into availabledate(availabledate_no, inn_no, availabledate_st, availabledate_end) values(availabledate_no_sequence.nextval, 10, '2015-05-20', '2015-05-24');
insert into availabledate(availabledate_no, inn_no, availabledate_st, availabledate_end) values(availabledate_no_sequence.nextval, 11, '2015-05-13', '2015-05-22');
insert into availabledate(availabledate_no, inn_no, availabledate_st, availabledate_end) values(availabledate_no_sequence.nextval, 11, '2015-06-02', '2015-06-10');
insert into availabledate(availabledate_no, inn_no, availabledate_st, availabledate_end) values(availabledate_no_sequence.nextval, 13, '2015-05-27', '2015-05-31');
insert into availabledate(availabledate_no, inn_no, availabledate_st, availabledate_end) values(availabledate_no_sequence.nextval, 13, '2015-05-27', '2015-05-31');
insert into availabledate(availabledate_no, inn_no, availabledate_st, availabledate_end) values(availabledate_no_sequence.nextval, 14, '2015-05-27', '2015-05-31');
insert into availabledate(availabledate_no, inn_no, availabledate_st, availabledate_end) values(availabledate_no_sequence.nextval, 14, '2015-05-27', '2015-05-31');
insert into availabledate(availabledate_no, inn_no, availabledate_st, availabledate_end) values(availabledate_no_sequence.nextval, 15, '2015-05-27', '2015-05-31');

select * from MEMBER;
select member_id, member_name, member_tel, member_info, member_pass, member_answer_type,  member_pass_answer from member
 where member_answer_type='나의 부모님 성함은?' and member_pass_answer='이정옥' and member_id='oldtype_1@hanmali.net'
 
 
 