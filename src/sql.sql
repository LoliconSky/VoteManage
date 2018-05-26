DROP TABLE if EXISTS user_choose;
DROP TABLE if EXISTS users;
DROP TABLE if EXISTS choose;
DROP TABLE if EXISTS topic;

# 用户表
CREATE TABLE users(
  id int PRIMARY KEY auto_increment,
  name VARCHAR(32) UNIQUE COMMENT '用户名，唯一',
  password VARCHAR(32) COMMENT '密码'
);

# 题目表
CREATE TABLE topic(
  id int PRIMARY KEY auto_increment,
  conte VARCHAR(32) COMMENT '题目的内容',
  single int(1) DEFAULT 0 COMMENT '是否是单选'
);

# 选项表
CREATE TABLE choose(
  id int PRIMARY KEY auto_increment,
  tid int COMMENT '对应的题目',
  conte VARCHAR(32) COMMENT '具体的选项',
  FOREIGN KEY (tid) REFERENCES topic(id)
);

# 用户-选项表
CREATE TABLE user_choose(
  id int PRIMARY KEY auto_increment,
  uid int COMMENT '用户id',
  cid int COMMENT '用户对应的一个选项',
  subdate datetime COMMENT '用户提交的时间',
  FOREIGN KEY (uid) REFERENCES users(id),
  FOREIGN KEY (cid) REFERENCES choose(id)
);

## 添加唯一约束
ALTER TABLE user_choose
  ADD UNIQUE KEY(uid, cid);

## 造数据
INSERT INTO users VALUES (null,'test','123');
INSERT INTO users VALUES (null,'admin','123');
INSERT INTO users VALUES (null,'loli','123');
INSERT INTO users VALUES (null,'ll','123');
INSERT INTO users VALUES (null,'abc','123');
INSERT INTO users VALUES (null,'aaa','123');
INSERT INTO users VALUES (null,'bbb','123');

INSERT INTO topic(conte,single) VALUES ('你帅么？', 0);
INSERT INTO topic(conte,single) VALUES ('你有钱么？', 0);
INSERT INTO topic(conte,single) VALUES ('你喜欢的电影？', 0);
INSERT INTO topic(conte,single) VALUES ('你喜欢的书？', 0);
INSERT INTO topic(conte,single) VALUES ('你的性别？', 0);

INSERT INTO choose VALUES (null, 1, '帅');
INSERT INTO choose VALUES (null, 1, '不帅');
INSERT INTO choose VALUES (null, 2, '有');
INSERT INTO choose VALUES (null, 2, '没有');
INSERT INTO choose VALUES (null, 3, '肖申克的救赎');
INSERT INTO choose VALUES (null, 3, '盗梦空间');
INSERT INTO choose VALUES (null, 3, '黑客帝国');
INSERT INTO choose VALUES (null, 3, '头号玩家');
INSERT INTO choose VALUES (null, 4, 'Java编程思想');
INSERT INTO choose VALUES (null, 4, '深入理解JVM');
INSERT INTO choose VALUES (null, 4, '重构');
INSERT INTO choose VALUES (null, 4, '代码简洁之道');
INSERT INTO choose VALUES (null, 4, '设计模式');
INSERT INTO choose VALUES (null, 5, '男');
INSERT INTO choose VALUES (null, 5, '女');
INSERT INTO choose VALUES (null, 5, '不详');

INSERT INTO user_choose(uid,cid,subdate) VALUES (1,1,NOW());
INSERT INTO user_choose(uid,cid,subdate) VALUES (1,3,NOW());
INSERT INTO user_choose(uid,cid,subdate) VALUES (1,5,NOW());
INSERT INTO user_choose(uid,cid,subdate) VALUES (1,6,NOW());
INSERT INTO user_choose(uid,cid,subdate) VALUES (1,7,NOW());
INSERT INTO user_choose(uid,cid,subdate) VALUES (2,2,NOW());
INSERT INTO user_choose(uid,cid,subdate) VALUES (2,7,NOW());
INSERT INTO user_choose(uid,cid,subdate) VALUES (3,2,NOW());
INSERT INTO user_choose(uid,cid,subdate) VALUES (3,12,NOW());
INSERT INTO user_choose(uid,cid,subdate) VALUES (4,1,NOW());
INSERT INTO user_choose(uid,cid,subdate) VALUES (5,10,NOW());

## 示例，用户所选的题目

SELECT u.id,u.name,t.conte '题目',c.conte '选项' FROM users u
  LEFT JOIN user_choose uc ON u.id = uc.uid
  LEFT JOIN choose c ON uc.cid = c.id
  LEFT JOIN topic t ON c.tid = t.id
WHERE u.id = 1

# 每个选项被选了多少次
SELECT c.id,conte,count(conte) FROM choose c
  LEFT JOIN user_choose uc ON c.id = uc.cid
WHERE c.id = 9
GROUP BY conte,c.id
#---------------------
SELECT c.id,conte,count(conte) FROM choose c
  LEFT JOIN user_choose uc ON c.id = uc.cid
GROUP BY conte,c.id



# 用户-选项表
-- CREATE TABLE user_choose(
-- 	uid int COMMENT '用户id',
-- 	cid int COMMENT '用户对应的一个选项',
-- 	subdate datetime COMMENT '用户提交的时间',
-- 	PRIMARY KEY (uid,cid),
-- 	FOREIGN KEY (uid) REFERENCES users(id),
-- 	FOREIGN KEY (cid) REFERENCES choose(id)
-- );

# 用户-选项表
# 数据库保证每一个人选择某个题目的一个答案就需要联合主键
-- CREATE TABLE user_choose(
-- 	id int PRIMARY KEY auto_increment,
-- 	uid int COMMENT '用户id',
-- 	cid int COMMENT '用户对应的一个选项',
-- 	subdate datetime COMMENT '用户提交的时间',
-- 	FOREIGN KEY (uid) REFERENCES users(id),
-- 	FOREIGN KEY (cid) REFERENCES choose(id)
-- );