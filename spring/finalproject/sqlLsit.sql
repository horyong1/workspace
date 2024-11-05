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

DROP TABLE fp_article;
CREATE TABLE fp_article(
	id INT PRIMARY KEY AUTO_INCREMENT,
	user_id INT,
	title VARCHAR(100),
	content VARCHAR(4000),
	read_count INT DEFAULT 0,
	created_at DATETIME DEFAULT now()
);
DROP TABLE fp_comments;
CREATE TABLE fp_comments(
	id INT PRIMARY KEY AUTO_INCREMENT,
	article_id INT,
	user_id INT,
	nickname VARCHAR(30),
	comment VARCHAR(1000),
	created_at DATETIME DEFAULT now()
);

DROP TABLE fp_post_likes;

CREATE TABLE fp_post_likes(
	id INT PRIMARY KEY AUTO_INCREMENT,
	article_id INT,
	user_id INT,
	article_like INT,
	created_at DATETIME DEFAULT now()
); 

DROP TABLE fp_post_likes;

CREATE TABLE fp_post_likes(
	id INT PRIMARY KEY AUTO_INCREMENT,
	article_id INT,
	user_id INT,
	article_like INT,
	created_at DATETIME DEFAULT now()
); 

DROP TABLE fp_hobby_category;
CREATE TABLE fp_hobby_category(
	id INT PRIMARY KEY AUTO_INCREMENT,
	hobby_name VARCHAR(50),
	created_at DATETIME DEFAULT now()
);

DROP TABLE fp_user_hobby;
CREATE TABLE fp_user_hobby(
	id INT PRIMARY KEY AUTO_INCREMENT,
	user_id INT,
	hobby_id Int,
	created_at DATETIME DEFAULT now()
);
