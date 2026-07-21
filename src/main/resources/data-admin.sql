-- ============================================================
-- Admin seed script
-- Run this ONCE after starting the backend for the first time.
--
-- Default admin credentials:
--   School ID : admin
--   Password  : admin123
--
-- To generate a new BCrypt hash for a different password, run
-- this in a Java snippet or use an online BCrypt generator:
--   new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode("yourpassword")
-- ============================================================

INSERT INTO users (school_id, email, password, first_name, last_name, role, status)
VALUES (
    'admin',
    'admin@cit.edu',
    'admin123456',
    'System',
    'Administrator',
    'ADMIN',
    'ACTIVE'
)
ON DUPLICATE KEY UPDATE school_id = school_id;
