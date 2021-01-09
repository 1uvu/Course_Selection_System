CREATE DATABASE `course_system`

use course_system

CREATE TABLE `course` (
	`CourseID` INT(10) NOT NULL,
	`CourseName` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`CourseKind` VARCHAR(10) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`CourseHour` INT(10) NOT NULL DEFAULT '0',
	`ClassHour` INT(10) NOT NULL DEFAULT '0',
	`TestHour` INT(10) NOT NULL DEFAULT '0',
	`CourseCurrStudentNumber` INT(10) NOT NULL DEFAULT '0',
	`CourseMaxStudentNumber` INT(10) NOT NULL DEFAULT '0',
	`CourseScore` INT(10) NOT NULL DEFAULT '0',
	`CoursePeriod` VARCHAR(10) NOT NULL DEFAULT '0' COLLATE 'utf8mb4_0900_ai_ci',
	PRIMARY KEY (`CourseID`) USING BTREE
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
;


INSERT INTO `course` VALUES ('1', '高等数学', '公共课', '64', '64', '0', '10', '20', '20', '2021-第一学期');
INSERT INTO `course` VALUES ('2', '英语', '公共课', '32', '32', '0', '20', '20', '12', '2021-第一学期');
INSERT INTO `course` VALUES ('3', '中国特色社会主义', '公共课', '16', '24', '8', '25', '30', '8', '2021-第一学期');
INSERT INTO `course` VALUES ('4', 'JAVA程序设计', '必修课', '64', '48', '16', '7', '10', '10', '2021-第一学期');
INSERT INTO `course` VALUES ('5', '大数据挖掘', '必修课', '32', '24', '8', '5', '10', '6', '2021-第一学期');
INSERT INTO `course` VALUES ('6', '创新实践', '必修课', '24', '0', '24', '40', '50', '4', '2021-第一学期');
INSERT INTO `course` VALUES ('7', '经济学原理', '选修课', '32', '32', '0', '19', '25', '6', '2021-第一学期');
INSERT INTO `course` VALUES ('8', '博弈论', '选修课', '24', '24', '0', '14', '15', '6', '2021-第一学期');
INSERT INTO `course` VALUES ('9', '创业管理', '选修课', '16', '16', '0', '13', '25', '4', '2021-第一学期');
INSERT INTO `course` VALUES ('10', '高等数学', '公共课', '64', '64', '0', '10', '20', '20', '2021-第二学期');
INSERT INTO `course` VALUES ('11', '英语', '公共课', '32', '32', '0', '18', '20', '12', '2021-第二学期');
INSERT INTO `course` VALUES ('12', '中国特色社会主义', '公共课', '24', '16', '8', '25', '30', '8', '2021-第二学期');
INSERT INTO `course` VALUES ('13', 'JAVA程序设计', '必修课', '64', '48', '16', '7', '10', '10', '2021-第二学期');
INSERT INTO `course` VALUES ('14', '大数据挖掘', '必修课', '32', '24', '8', '5', '10', '6', '2021-第二学期');
INSERT INTO `course` VALUES ('15', '创新实践', '必修课', '0', '24', '24', '40', '50', '4', '2021-第二学期');
INSERT INTO `course` VALUES ('16', '经济学原理', '选修课', '32', '32', '0', '19', '25', '6', '2021-第二学期');
INSERT INTO `course` VALUES ('17', '博弈论', '选修课', '24', '24', '0', '14', '15', '6', '2021-第二学期');
INSERT INTO `course` VALUES ('18', '创业管理', '选修课', '16', '16', '0', '13', '25', '4', '2021-第二学期');



CREATE TABLE `student` (
	`StudentId` INT(10) NOT NULL,
	`StudentName` VARCHAR(50) NOT NULL DEFAULT '' COLLATE 'utf8mb4_0900_ai_ci',
	`StudentAge` INT(10) NOT NULL DEFAULT '0',
	`StudentMinCourseScore` INT(10) NOT NULL DEFAULT '60',
	`StudentCurrCourseScore` INT(10) NOT NULL DEFAULT '0',
	`StudentMaxCourseScore` INT(10) NOT NULL DEFAULT '70',
	`StudentNeedPubCourse` INT(10) NOT NULL DEFAULT '3',
	`StudentNeedReqCourse` INT(10) NOT NULL DEFAULT '3',
	`StudentNeedSelCourse` INT(10) NOT NULL DEFAULT '2',
	PRIMARY KEY (`StudentId`) USING BTREE
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
;


INSERT INTO `student` VALUES ('1', '邱鑫', '10', '60', '0', '70', '3', '3', '2');
INSERT INTO `student` VALUES ('2', '码云', '50', '60', '0', '70', '3', '3', '2');
INSERT INTO `student` VALUES ('3', '麻花滕', '40', '60', '0', '70', '3', '3', '2');
INSERT INTO `student` VALUES ('4', '章佚名', '30', '60', '0', '70', '3', '3', '2');
INSERT INTO `student` VALUES ('5', '芭菲忒', '90', '60', '0', '70', '3', '3', '2');
INSERT INTO `student` VALUES ('6', '特浪噗', '80', '60', '0', '70', '3', '3', '2');



CREATE TABLE `selection` (
	`CourseId` INT(10) NOT NULL,
	`StudentId` INT(10) NOT NULL,
	`SelectDateTime` DATETIME NOT NULL,
	PRIMARY KEY (`CourseId`, `StudentId`) USING BTREE
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
;

