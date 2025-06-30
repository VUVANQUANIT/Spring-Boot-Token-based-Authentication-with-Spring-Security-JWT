# Spring Boot Token Project

## Tính năng nổi bật
- Đăng ký, đăng nhập, phân quyền bằng JWT
- Xác thực email bằng OTP gửi qua email
- Lưu OTP và JWT token vào Redis (cache, kiểm soát session)
- Khởi tạo role mặc định tự động khi start app

## Hướng dẫn migrate DB
Nếu bạn thêm trường `emailVerified` vào entity User, cần chạy lệnh sau trên PostgreSQL:
```sql
ALTER TABLE users ADD COLUMN email_verified boolean NOT NULL DEFAULT false;
```

## Hướng dẫn test API
1. **Đăng ký:**
   - POST `/api/auth/signup` với body JSON user
   - Kiểm tra email nhận OTP
2. **Xác thực OTP:**
   - POST `/api/auth/verify-otp` với email + otp
3. **Đăng nhập:**
   - POST `/api/auth/signin` với username + password
4. **Gửi lại OTP:**
   - POST `/api/auth/resend-otp` với email
5. **Logout:**
   - POST `/api/auth/logout` với userId

## Cấu hình gửi mail
Thêm vào `application.properties`:
```
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=YOUR_EMAIL@gmail.com
spring.mail.password=YOUR_APP_PASSWORD
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

## Lưu ý
- Đảm bảo đã migrate DB trước khi chạy app.
- Kiểm tra log nếu không nhận được email OTP.
- Redis phải chạy trước khi start app. 