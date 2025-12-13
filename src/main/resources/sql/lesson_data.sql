INSERT INTO online_learning_platform.lesson
(created_date, id_course, image, lesson_name, lesson_number, source_url, status, updated_date)
SELECT
    NOW(),
    c.id,
    CONCAT('lesson_image_', n.n, '.png'),
    CONCAT(c.course_name, ' - Lesson ', n.n),
    n.n,
    CONCAT('https://example.com/course/', c.id, '/lesson/', n.n),
    'ACTIVE',
    NOW()
FROM online_learning_platform.course c
         CROSS JOIN (
    SELECT 1 AS n UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5
    UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10
    UNION ALL SELECT 11 UNION ALL SELECT 12 UNION ALL SELECT 13 UNION ALL SELECT 14 UNION ALL SELECT 15
    UNION ALL SELECT 16 UNION ALL SELECT 17 UNION ALL SELECT 18 UNION ALL SELECT 19 UNION ALL SELECT 20
) AS n;
