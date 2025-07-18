create database nexus_bank;
use nexus_bank;
show tables;
select * from user_info;
insert into user_info (id,name,pwd,role) value (2,"aariz","{bcrypt}$2a$12$kuNzL.7b8yVKe88a/H6WZ.F9OEAmjN/d7aGd2QuCH0c/PgObJHGB.","admin"); -- aariz1234

update nexus_customer set pwd="{bcrypt}$2a$12$aydRSPYm7WbdHJf.we8.hOlEDVzfnFJUkz.Z861Q9mKt3vYGjOvqa" where email="daraksha@gmail.com";  -- Arbaaz#7781

desc nexus_customer;
select * from nexus_customer; 
select * from nexus_customer where email="daraksha@gmail.com";
insert into nexus_customer (customer_id,created_date,email,mobile_number,name,pwd,role)values
(1,"2025-04-14 10:46:49.671241","md.rocks788@gmail.com","8591237781","arbaaz","{bcrypt}$2a$12$Bz3Gt8Yl1plrtpyW4c8eOuNSqz.kSkGcOX8h5F88BuTrJZmtPc6xS","ADMIN"); -- Arbaaz#7781

delete from nexus_customer where name ="adc";
 SET SQL_SAFE_UPDATES = 0;

select * from notices;

INSERT INTO `notices` (`notice_id`, `notice_summary`, `notice_details`, `notice_beg_dt`, `notice_end_dt`, `create_dt`, `update_dt`)
VALUES ('1','Home Loan Interest rates reduced', 'Home loan interest rates are reduced as per the goverment guidelines. The updated rates will be effective immediately',
CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `notices` (`notice_id`, `notice_summary`, `notice_details`, `notice_beg_dt`, `notice_end_dt`, `create_dt`, `update_dt`)
VALUES ('2','Net Banking Offers', 'Customers who will opt for Internet banking while opening a saving account will get a $50 amazon voucher',
CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `notices` (`notice_id`, `notice_summary`, `notice_details`, `notice_beg_dt`, `notice_end_dt`, `create_dt`, `update_dt`)
VALUES ('3','Mobile App Downtime', 'The mobile application of the EazyBank will be down from 2AM-5AM on 12/05/2020 due to maintenance activities',
CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `notices` (`notice_id`, `notice_summary`, `notice_details`, `notice_beg_dt`, `notice_end_dt`, `create_dt`, `update_dt`)
VALUES ('4','E Auction notice', 'There will be a e-auction on 12/08/2020 on the Bank website for all the stubborn arrears.Interested parties can participate in the e-auctions',
CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `notices` (`notice_id`, `notice_summary`, `notice_details`, `notice_beg_dt`, `notice_end_dt`, `create_dt`, `update_dt`)
VALUES ('5','Launch of Millennia Cards', 'Millennia Credit Cards are launched for the premium customers of EazyBank. With these cards, you will get 5% cashback for each purchase',
CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

-- INSERT INTO `notices` (`notice_id`, `notice_summary`, `notice_details`, `notice_beg_dt`, `notice_end_dt`, `create_dt`, `update_dt`)
-- VALUES ('4','COVID-19 Insurance', 'EazyBank launched an insurance policy which will cover COVID-19 expenses. Please reach out to the branch for more details',
-- CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

select * from contact;

select *from account_transactions;
desc account_transactions;

-- inserting details about daraksha 
select * from nexus_customer where customer_id=52;
desc accounts;
select * from accounts;

INSERT INTO `accounts` (`account_number`, `account_type`, `branch_address`, `created_date`, `customer_id`) 
VALUES ('457896321', 'saving account', 'Rourkela, Sec-19, 769003', NOW(), 52);

desc card;
select * from card;

INSERT INTO `card` (`card_id`, `card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `created_date`) 
VALUES (101, '4578-9632-1456-7890', 52, 'Credit Card', 50000, 15000, 35000, NOW());

INSERT INTO `card` (`card_id`, `card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `created_date`) 
VALUES (102, '1234-5678-9012-3456', 52, 'Debit Card', 100000, 25000, 75000, NOW());

select * from loans;
desc loans;

INSERT INTO `loans` (`loan_number`, `customer_id`, `loan_type`, `total_loan`, `start_dt`, `outstanding_amount`, `amount_paid`, `create_dt`) 
VALUES (20250419001, 52, 'Home Loan', 500000, '2024-01-15', 350000, 150000, NOW());

INSERT INTO `loans` (`loan_number`, `customer_id`, `loan_type`, `total_loan`, `start_dt`, `outstanding_amount`, `amount_paid`, `create_dt`) 
VALUES (20250419002, 52, 'Car Loan', 800000, '2023-09-10', 500000, 300000, NOW());

select * from    account_transactions;
desc account_transactions;
-- delete from account_transactions where customer_id=52;
INSERT INTO `account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_dt`, `transaction_type`, `transaction_amt`, `closing_balance`, `create_dt`, `transaction_summary`) 
VALUES 
('TXN001', 457896321, 52, '2025-04-18 10:30:00', 'Deposit', 20000, 120000, CURDATE(), 'Initial deposit'),
('TXN002', 457896321, 52, '2025-04-18 14:45:00', 'Withdrawal', 5000, 115000, CURDATE(), 'ATM withdrawal'),
('TXN003', 457896321, 52, '2025-04-19 09:10:00', 'Transfer', 15000, 100000, CURDATE(), 'Fund transfer'),
('TXN004', 457896321, 52, '2025-04-19 12:25:00', 'Deposit', 10000, 110000, CURDATE(), 'Salary credited'),
('TXN005', 457896321, 52, '2025-04-19 16:00:00', 'Withdrawal', 8000, 102000, CURDATE(), 'POS transaction');

INSERT INTO `account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_dt`, `transaction_type`, `transaction_amt`, `closing_balance`, `create_dt`, `transaction_summary`) 
values ('TXN007', 457896321, 52, '2025-04-19 12:25:00', 'Deposit', 170000, 110000, CURDATE(), 'ATM withdrawal');

select * from authority; 

insert into authority (id,name,customer_id) values (1,"ADMIN",1);