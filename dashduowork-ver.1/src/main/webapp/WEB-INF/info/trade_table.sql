-----------------------------------줭신꺼
--임의 거래 내역 테이블--
----------------------------------
drop table Trade;
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
member_id varchar2(50) not null,
master varchar2(50) not null,
constraint fk_member10 foreign key(member_id) references member(member_id) on DELETE CASCADE
)
-----------------------------------
CREATE SEQUENCE trade_sequence;
drop SEQUENCE trade_sequence;
-----------------------------------
insert into Trade
(Trade_no, inn_name, book_checkin, book_checkout, inn_price,member_id,master) 
values(trade_sequence.nextval,'강남오피스텔','2015-06-14','2015-06-16',30000,'oldtype','jh4395@ahaha');
insert into Trade
(Trade_no, inn_name, book_checkin, book_checkout, inn_price,member_id,master) 
values(trade_sequence.nextval,'영통메르','2015-06-15','2015-06-17',30000,'oldtype','jh4395@ahaha');
insert into Trade
(Trade_no, inn_name, book_checkin, book_checkout, inn_price,member_id,master) 
values(trade_sequence.nextval,'영통메르','2015-06-16','2015-06-18',30000,'oldtype','jh4395@ahaha');
insert into Trade
(Trade_no, inn_name, book_checkin, book_checkout, inn_price,member_id,master) 
values(trade_sequence.nextval,'영통메르','2015-06-15','2015-06-17',30000,'jh4395@ahaha','oldtype');
insert into Trade
(Trade_no, inn_name, book_checkin, book_checkout, inn_price,member_id,master) 
values(trade_sequence.nextval,'영통메르','2015-06-15','2015-06-16',30000,'oldtype','1qwqw');
-----------------------------------
select * from trade
delete from trade where Trade_no=5

delete from trade where book_checkout < sysdate
------------------------------------
	
	
	
	
	
	
