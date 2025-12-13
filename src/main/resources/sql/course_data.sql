INSERT INTO online_learning_platform.course
(course_description, course_name, created_date, end_date, id_user, logo, start_date, status, updated_date)
VALUES ('Khóa học lập trình Java cơ bản cho người mới bắt đầu',
        'Java Basic', NOW(), DATE_ADD(NOW(), INTERVAL 3 MONTH), 1,
        'java_basic.png', NOW(), 'ACTIVE', NOW()),

       ('Học Angular từ A-Z với project thực tế',
        'Angular Full Course', NOW(), DATE_ADD(NOW(), INTERVAL 4 MONTH), 2,
        'angular_logo.png', NOW(), 'ACTIVE', NOW()),

       ('SQL nâng cao: indexing, tối ưu performance',
        'Advanced SQL', NOW(), DATE_ADD(NOW(), INTERVAL 2 MONTH), 3,
        'sql_advanced.png', NOW(), 'INACTIVE', NOW()),

       ('Python cho phân tích dữ liệu với pandas',
        'Python Data Analysis', NOW(), DATE_ADD(NOW(), INTERVAL 5 MONTH), 4,
        'python_data.png', NOW(), 'ACTIVE', NOW()),

       ('Machine Learning cơ bản đến nâng cao',
        'Machine Learning Overview', NOW(), DATE_ADD(NOW(), INTERVAL 6 MONTH), 5,
        'ml_overview.png', NOW(), 'ACTIVE', NOW()),

       ('Khóa học Spring Boot xây dựng REST API',
        'Spring Boot API', NOW(), DATE_ADD(NOW(), INTERVAL 3 MONTH), 1,
        'spring_boot.png', NOW(), 'ACTIVE', NOW()),

       ('ReactJS cho người mới bắt đầu',
        'React Basic', NOW(), DATE_ADD(NOW(), INTERVAL 3 MONTH), 2,
        'react_basic.png', NOW(), 'INACTIVE', NOW()),

       ('Docker và Kubernetes cơ bản',
        'DevOps Fundamental', NOW(), DATE_ADD(NOW(), INTERVAL 4 MONTH), 3,
        'devops_fundamental.png', NOW(), 'ACTIVE', NOW()),

       ('NodeJS fullstack với Express',
        'NodeJS Fullstack', NOW(), DATE_ADD(NOW(), INTERVAL 4 MONTH), 4,
        'nodejs_full.png', NOW(), 'ACTIVE', NOW()),

       ('Khóa học HTML CSS từ căn bản đến nâng cao',
        'HTML CSS Master', NOW(), DATE_ADD(NOW(), INTERVAL 2 MONTH), 5,
        'html_css.png', NOW(), 'ACTIVE', NOW()),

       ('Thiết kế UI/UX với Figma',
        'UI UX Figma', NOW(), DATE_ADD(NOW(), INTERVAL 1 MONTH), 1,
        'figma_uiux.png', NOW(), 'INACTIVE', NOW()),

       ('C++ cơ bản cho sinh viên CNTT',
        'C++ Basic', NOW(), DATE_ADD(NOW(), INTERVAL 2 MONTH), 2,
        'cpp_basic.png', NOW(), 'ACTIVE', NOW()),

       ('Lập trình C# Winform cơ bản',
        'C# Winform', NOW(), DATE_ADD(NOW(), INTERVAL 3 MONTH), 3,
        'csharp_winform.png', NOW(), 'ACTIVE', NOW()),

       ('Tư duy thuật toán và cấu trúc dữ liệu',
        'DSA Foundation', NOW(), DATE_ADD(NOW(), INTERVAL 6 MONTH), 4,
        'dsa_foundation.png', NOW(), 'ACTIVE', NOW()),

       ('Khóa học Flutter cho mobile dev',
        'Flutter Mobile', NOW(), DATE_ADD(NOW(), INTERVAL 5 MONTH), 5,
        'flutter_mobile.png', NOW(), 'INACTIVE', NOW()),

       ('Lập trình PHP Laravel cơ bản',
        'Laravel Basic', NOW(), DATE_ADD(NOW(), INTERVAL 3 MONTH), 1,
        'laravel_basic.png', NOW(), 'ACTIVE', NOW()),

       ('Tự học Git & GitHub chuyên nghiệp',
        'Git Mastery', NOW(), DATE_ADD(NOW(), INTERVAL 1 MONTH), 2,
        'git_mastery.png', NOW(), 'ACTIVE', NOW()),

       ('Khóa học API Testing với Postman',
        'API Testing', NOW(), DATE_ADD(NOW(), INTERVAL 1 MONTH), 3,
        'postman_api.png', NOW(), 'ACTIVE', NOW()),

       ('AI căn bản cho người bắt đầu',
        'AI Fundamentals', NOW(), DATE_ADD(NOW(), INTERVAL 4 MONTH), 4,
        'ai_fundamental.png', NOW(), 'ACTIVE', NOW()),

       ('Khóa học phân tích dữ liệu với Excel nâng cao',
        'Excel Advanced', NOW(), DATE_ADD(NOW(), INTERVAL 1 MONTH), 5,
        'excel_advanced.png', NOW(), 'ACTIVE', NOW());
