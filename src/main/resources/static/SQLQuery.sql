/*1. chay doan nay*/
use master

go
drop database techway /*chay dong nay neu trong sqlserver da co databse techway*/
go
create database techway
go 
use techway

/*	
2.1. open file application.properties, chinh none thanh update:
	spring.jpa.hibernate.ddl-auto=none 
	--> spring.jpa.hibernate.ddl-auto=update
2.2. run ung dung spring boot
*/
go
insert into [dbo].[roles] values ('ROLE_CUST'), ('ROLE_DIRE'), ('ROLE_STAFF')
go

insert into categories(category_no, category_name) values ('phone', N'Điện thoại'), 
	('laptop', N'Laptop'), ('tablet', N'Tablet')
go

insert into manufacturer values (N'iphone'), (N'Samsung'), (N'Oppo'), 
	(N'Xiaomi'), (N'Vivo'), (N'realme'), (N'Nokia'), (N'TCL'),
	(N'mobell'), (N'itel'), (N'Masstel'), ('ASUS')
go 
select * from colors
insert into colors values (N'Vàng đồng'), (N'Bạc'), (N'Trắng'), (N'Đỏ'), (N'Xanh dương'), (N'Xanh lá'), (N'Xanh rêu'), (N'Cam'), (N'Xanh ngọc'), (N'Tím'), (N'Tím nhạt'), (N'Đen'), (N'Kem'), (N'Xanh')
go
insert into camera_features values (N'Quay video hiển thị kép'), 
	(N'HDR'),
	(N'Chuyên nghiệp (Pro)'),
	(N'Toàn cảnh (Panorama)'),
	(N'Nhãn dán (AR Stickers)'),
	(N'Ban đêm (Night Mode)'),
	(N'Làm đẹp'),
	(N'Siêu độ phân giải'),
	(N'AI Camera'),
	(N'Google Lens'),
	(N'Bộ lọc màu'),
	(N'Quay chậm (Slow Motion)'),
	(N'Xóa phông'),
	(N'Zoom kỹ thuật số'),
	(N'Trôi nhanh thời gian (Time Lapse)'),
	(N'Quay video Full HD')
go
insert into advanced_securities values (N'Mở khoá vân tay dưới màn hình'),
	 (N'Mở khoá khuôn mặt'),
	 (N'Mở khoá khuôn mặt Face ID')
go
insert into special_features values (N'Phát hiện va chạm (Crash Detection)'),
	(N'Loa kép'),
	(N'Màn hình luôn hiển thị AOD'),
	(N'Chạm 2 lần sáng màn hình'),
	(N'Apple Pay'),
	(N'Chế độ đơn giản (Giao diện đơn giản)'),
	(N'Cử chỉ thông minh'),
	(N'Ứng dụng kép (Nhân bản ứng dụng)'),
	(N'Chế độ trẻ em (Không gian trẻ em)'),
	(N'Đa cửa sổ (chia đôi màn hình)'),
	(N'Cử chỉ màn hình tắt'),
	(N'Mở rộng bộ nhớ RAM'),
	(N'Smart Switch (ứng dụng chuyển đổi dữ liệu)'),
	(N'Âm thanh Dolby Atmos'),
	(N'Đa cửa sổ (chia đôi màn hình)'),
	(N'Samsung DeX (Kết nối màn hình sử dụng giao diện tương tự PC)'),
	(N'Tối ưu game (Game Booster)'),
	(N'Trợ lý ảo Samsung Bixby'),
	(N'Âm thanh AKG'),
	(N'Màn hình luôn hiển thị AOD'),
	(N'Không gian thứ hai (Thư mục bảo mật)'),
	(N'Chặn cuộc gọi'),
	(N'Chặn tin nhắn')
	
go
insert into display_technologies values (N'Dynamic AMOLED 2X'),
	(N'Super Retina XDR'),
	(N'IPS LCD'),
	(N'Super AMOLED')

go
select * from orders
select * from products
select * from colors
select * from camera_features
select * from advanced_securities
select * from special_features
select * from categories
select * from manufacturer
select * from roles
select * from laptop_details
select * from screen_techs
select * from categories
insert into manufacturer (manufacturer_name) values ('ASUS')

delete from user_roles where user_id = 2


delete from user_roles where user_id = 6

select * from user_roles
select * from users
select * from roles
select * from comments
delete from user_roles
delete from users
select * from users
select* from orders
select * from comments
select * from products
insert into comments (content, product_id, created_by) values(N'dfhweioafhewi', 1, 1)
insert into products(product_no, name, [images], price, available, category_id, manufacturer_id, color_id) values('asdfads', 'ss s20', 'photos', 99, 1, 1, 1, 1)

select color_id from products o where o.product_no = 'ASUS15X'
select * from phone_details
insert into users (enabled, email, fullname, password) values (1, N'huytony616@gmail.com', N'Lee Huy',N'$2a$10$o0EP7RZRwaoVBfKZ2rx87uidWEDZ8tHWreuCKw8MwQ0JPkfHW0LDa')
insert into user_roles(user_id, role_id) values (2, 1), (2, 2), (2, 3)

