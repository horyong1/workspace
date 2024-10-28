DROP TABLE fp_user;

CREATE TABLE fp_user(
	id INT PRIMARY KEY AUTO_INCREMENT,
	user_id VARCHAR(100),
	password VARCHAR(200),
	nickname VARCHAR(30),
	email VARCHAR(100),
	gender VARCHAR(1),
	birth DATETIME,
	phone VARCHAR(30),
	created_at DATETIME DEFAULT now()
);