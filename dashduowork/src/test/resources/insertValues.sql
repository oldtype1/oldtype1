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
('Y','Y','Y','Y','Y',1);
insert into amenity(amenity_wifi,amenity_bed,amenity_tv,amenity_kitchen,amenity_bbq, amenity_no) values
('Y','N','Y','N','Y',2);
insert into amenity(amenity_wifi,amenity_bed,amenity_tv,amenity_kitchen,amenity_bbq, amenity_no) values
('Y','N','N','N','Y',3);
insert into amenity(amenity_wifi,amenity_bed,amenity_tv,amenity_kitchen,amenity_bbq, amenity_no) values
('Y','N','N','N','Y',4);

insert into availabledate(availabledate_no, inn_no, availabledate_st, availabledate_end) values(availabledate_no_sequence.nextval, 1, '2015-05-18', '2015-05-25');
insert into availabledate(availabledate_no, inn_no, availabledate_st, availabledate_end) values(availabledate_no_sequence.nextval, 1, '2015-05-27', '2015-05-31');
insert into availabledate(availabledate_no, inn_no, availabledate_st, availabledate_end) values(availabledate_no_sequence.nextval, 2, '2015-05-20', '2015-05-24');
insert into availabledate(availabledate_no, inn_no, availabledate_st, availabledate_end) values(availabledate_no_sequence.nextval, 3, '2015-05-13', '2015-05-22');
insert into availabledate(availabledate_no, inn_no, availabledate_st, availabledate_end) values(availabledate_no_sequence.nextval, 3, '2015-06-02', '2015-06-10');
insert into availabledate(availabledate_no, inn_no, availabledate_st, availabledate_end) values(availabledate_no_sequence.nextval, 5, '2015-05-27', '2015-05-31');
insert into availabledate(availabledate_no, inn_no, availabledate_st, availabledate_end) values(availabledate_no_sequence.nextval, 5, '2015-05-27', '2015-05-31');
insert into availabledate(availabledate_no, inn_no, availabledate_st, availabledate_end) values(availabledate_no_sequence.nextval, 6, '2015-05-27', '2015-05-31');
insert into availabledate(availabledate_no, inn_no, availabledate_st, availabledate_end) values(availabledate_no_sequence.nextval, 6, '2015-05-27', '2015-05-31');
insert into availabledate(availabledate_no, inn_no, availabledate_st, availabledate_end) values(availabledate_no_sequence.nextval, 7, '2015-05-27', '2015-05-31');