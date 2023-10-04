-- 插入投票項目資料
INSERT INTO vote_items (name) VALUES
    ('電腦'),
    ('滑鼠');

-- 插入投票紀錄資料
INSERT INTO vote_records (voter, vote_item_id) VALUES
    ('Leo', 1),
    ('Sandy', 1),
    ('Sandy', 2),
    ('Randy', 2),
    ('RSY', 2);