INSERT INTO users (SchoolID, email, firstname, lastname, password, role)
VALUES (
    'admin',
    'admin@cit.edu',
    'System',
    'Administrator',
    '$2b$12$pkkudNPQ6ELUIniY8KQG4..qo5GQQojfriWjK1ie1xcAMuid0hCBO',
    'ADMIN'
)
ON DUPLICATE KEY UPDATE SchoolID = SchoolID;
