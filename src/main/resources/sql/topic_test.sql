INSERT INTO online_learning_platform.topic_test
(create_date, id_course, id_lesson, id_teacher, status, test_name, type)
VALUES (NOW(), 1, 1, 1, 'ACTIVE', 'Bài kiểm tra Lesson 1', 'QUIZ');

INSERT INTO online_learning_platform.topic_test
(create_date, id_course, id_lesson, id_teacher, status, test_name, type)
SELECT
    NOW(),
    l.id_course,
    l.id,
    2,  -- id giáo viên, bạn có thể đổi
    'ACTIVE',
    CONCAT('Bài kiểm tra cho Lesson ', l.lesson_number, ' - Khóa học ', l.id_course),
    'QUIZ'
FROM online_learning_platform.lesson l;
