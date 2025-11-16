# Spring Boot Token Project

## Key Features
- User registration, login, and role-based authorization using JWT
- Email verification with OTP sent via email
- OTP and JWT token storage in Redis (caching and session management)
- Automatic default role initialization on application startup

## Database Migration Guide
If you add the `emailVerified` field to the User entity, run the following command on PostgreSQL:
```sql
ALTER TABLE users ADD COLUMN email_verified boolean NOT NULL DEFAULT false;
```

## API Testing Guide
1. **Sign Up:**
   - POST `/api/auth/signup` with user JSON body
   - Check email for OTP code
2. **Verify OTP:**
   - POST `/api/auth/verify-otp` with email + otp
3. **Sign In:**
   - POST `/api/auth/signin` with username + password
4. **Resend OTP:**
   - POST `/api/auth/resend-otp` with email
5. **Logout:**
   - POST `/api/auth/logout` with userId

## Email Configuration
Add the following to `application.properties`:
```
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=YOUR_EMAIL@gmail.com
spring.mail.password=YOUR_APP_PASSWORD
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

## Important Notes
- Ensure database migration is completed before running the application.
- Check application logs if OTP email is not received.
- Redis must be running before starting the application.
