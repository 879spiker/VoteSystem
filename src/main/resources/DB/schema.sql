-- 创建投票项表
CREATE TABLE vote_item (
    vote_item_id INT AUTO_INCREMENT PRIMARY KEY,
    vote_item_name VARCHAR(255) NOT NULL
) ENGINE=MyISAM;

-- 创建投票记录表
CREATE TABLE vote_record (
    vote_record_id INT AUTO_INCREMENT PRIMARY KEY,
    voter_name VARCHAR(255) NOT NULL,
    vote_item_id INT NOT NULL,
    FOREIGN KEY (vote_item_id) REFERENCES vote_item (vote_item_id)
) ENGINE=MyISAM;