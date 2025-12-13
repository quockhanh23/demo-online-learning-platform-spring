INSERT INTO online_learning_platform.essay_question
(content, create_date, id_topic_test, question_number)
SELECT
    CONCAT('Hãy trình bày kiến thức quan trọng nhất trong Lesson ', l.id),
    NOW(),
    l.id,
    1
FROM online_learning_platform.lesson l;
