INSERT INTO online_learning_platform.multiple_choice_question
(id, correct_answer, answer1, answer2, answer3, answer4, content, create_date, explain_correct_answer, id_topic_test,
 question_number)
VALUES (1, 'Ngôn ngữ lập trình', 'Ngôn ngữ lập trình', 'Hệ điều hành', 'Trình duyệt web', 'Phần cứng máy tính',
        'Lập trình là gì?', '2025-12-05 20:19:51',
        'Lập trình là quá trình sử dụng ngôn ngữ lập trình để viết ra chương trình.', 1, 1);

INSERT INTO online_learning_platform.multiple_choice_question
VALUES (2, 'Ngôn ngữ lập trình', 'CPU', 'RAM', 'Ngôn ngữ lập trình', 'SSD',
        'Để viết phần mềm, chúng ta cần sử dụng gì?', '2025-12-05 20:19:51',
        'Phần mềm được tạo ra bằng ngôn ngữ lập trình.', 1, 2);

INSERT INTO online_learning_platform.multiple_choice_question
VALUES (3, 'Tất cả đều đúng', 'Chuỗi', 'Số nguyên', 'Boolean', 'Tất cả đều đúng',
        'Kiểu dữ liệu nào sau đây là kiểu dữ liệu cơ bản trong lập trình?', '2025-12-05 20:19:51',
        'Chuỗi, số nguyên và boolean đều là kiểu dữ liệu cơ bản.', 1, 3);

INSERT INTO online_learning_platform.multiple_choice_question
VALUES (4, 'print()', 'print()', 'echo()', 'console.log()', 'write()',
        'Hàm in ra màn hình trong Python là gì?', '2025-12-05 20:19:51',
        'Python dùng hàm print() để in ra màn hình.', 1, 4);

INSERT INTO online_learning_platform.multiple_choice_question
VALUES (5, 'Java', 'Java', 'HTML', 'CSS', 'Figma',
        'Ngôn ngữ nào là ngôn ngữ lập trình?', '2025-12-05 20:19:51',
        'Java là ngôn ngữ lập trình. HTML/CSS là ngôn ngữ đánh dấu.', 1, 5);

INSERT INTO online_learning_platform.multiple_choice_question
VALUES (6, 'Biến', 'Toán tử', 'Biến', 'Hàm', 'Vòng lặp',
        'Khái niệm nào dùng để lưu trữ giá trị trong lập trình?', '2025-12-05 20:19:51',
        'Biến được dùng để lưu trữ giá trị.', 1, 6);

INSERT INTO online_learning_platform.multiple_choice_question
VALUES (7, 'if', 'for', 'while', 'if', 'switch',
        'Câu lệnh nào dưới đây là câu lệnh rẽ nhánh?', '2025-12-05 20:19:51',
        'if dùng để rẽ nhánh trong lập trình.', 1, 7);

INSERT INTO online_learning_platform.multiple_choice_question
VALUES (8, 'Phân chia chương trình thành từng khối xử lý',
        'Phân chia chương trình thành từng khối xử lý', 'Lưu trữ dữ liệu', 'Kết thúc chương trình', 'Tối ưu RAM',
        'Hàm (function) dùng để làm gì?', '2025-12-05 20:19:51',
        'Hàm giúp chia chương trình thành từng phần nhỏ dễ quản lý.', 1, 8);

INSERT INTO online_learning_platform.multiple_choice_question
VALUES (9, '=', '==', '=', '!=', '>=',
        'Toán tử gán giá trị trong lập trình là ký hiệu nào?', '2025-12-05 20:19:51',
        'Dấu = dùng để gán giá trị.', 1, 9);

INSERT INTO online_learning_platform.multiple_choice_question
VALUES (10, 'Giúp đọc và hiểu chương trình',
        'Giúp đọc và hiểu chương trình', 'Giúp máy tính chạy nhanh hơn', 'Là lỗi của chương trình',
        'Là yêu cầu bắt buộc khi chạy chương trình',
        'Comment trong lập trình có tác dụng gì?', '2025-12-05 20:19:51',
        'Comment giúp người đọc hiểu code, không ảnh hưởng khi chạy.', 1, 10);


INSERT INTO online_learning_platform.multiple_choice_question
(answer1, answer2, answer3, answer4, content, correct_answer, create_date, explain_correct_answer, id_topic_test,
 question_number)
VALUES ('Ngôn ngữ lập trình', 'Hệ điều hành', 'Trình duyệt web', 'Phần mềm văn phòng',
        'Java là gì?', 'Ngôn ngữ lập trình', '2025-12-05 20:12:42', 'Java là một ngôn ngữ lập trình hướng đối tượng.',
        20, 1),

       ('James Gosling', 'Guido van Rossum', 'Dennis Ritchie', 'Bjarne Stroustrup',
        'Ai là cha đẻ của Java?', 'James Gosling', '2025-12-05 20:12:42',
        'Java được tạo bởi James Gosling tại Sun Microsystems.', 20, 2),

       ('1995', '2000', '1985', '2010',
        'Java được ra mắt vào năm nào?', '1995', '2025-12-05 20:12:42', 'Java chính thức ra mắt năm 1995.', 20, 3),

       ('JDK', 'JRE', 'JVM', 'IDE',
        'Thành phần nào thực thi chương trình Java?', 'JVM', '2025-12-05 20:12:42',
        'JVM là máy ảo Java, chạy bytecode.', 20, 4),

       ('javac', 'java', 'jvm', 'javadoc',
        'Lệnh nào dùng để biên dịch file .java?', 'javac', '2025-12-05 20:12:42',
        'javac biên dịch mã nguồn thành bytecode.', 20, 5),

       ('Biến để lưu dữ liệu', 'Thư viện', 'File ảnh', 'Plugin',
        'Variable trong Java là gì?', 'Biến để lưu dữ liệu', '2025-12-05 20:12:42',
        'Biến dùng để lưu trữ giá trị trong bộ nhớ.', 20, 6),

       ('int', 'image', 'pdf', 'folder',
        'Kiểu dữ liệu nào sau đây là kiểu nguyên trong Java?', 'int', '2025-12-05 20:12:42',
        'int là kiểu số nguyên cơ bản.', 20, 7),

       ('System.out.println()', 'System.log()', 'print()', 'console.write()',
        'Lệnh nào dùng để in ra màn hình trong Java?', 'System.out.println()', '2025-12-05 20:12:42',
        'System.out.println() dùng để xuất dữ liệu.', 20, 8),

       ('CamelCase', 'snake_case', 'kebab-case', 'dot.case',
        'Quy tắc đặt tên biến đúng chuẩn trong Java là gì?', 'CamelCase', '2025-12-05 20:12:42',
        'Java sử dụng CamelCase: myVariableName.', 20, 9),

       ('class', 'function', 'module', 'package',
        'Từ khóa nào dùng để khai báo lớp trong Java?', 'class', '2025-12-05 20:12:42',
        'Lớp trong Java được khai báo bằng keyword class.', 20, 10);

INSERT INTO online_learning_platform.multiple_choice_question
(answer1, answer2, answer3, answer4, content, correct_answer, create_date, explain_correct_answer, id_topic_test,
 question_number)
VALUES ('Framework', 'Library', 'Hệ điều hành', 'Ngôn ngữ lập trình',
        'Angular là gì?', 'Framework', '2025-12-05 20:12:42',
        'Angular là một framework front-end do Google phát triển.', 19, 1),

       ('Google', 'Facebook', 'Microsoft', 'Amazon',
        'Ai phát triển Angular?', 'Google', '2025-12-05 20:12:42', 'Angular được Google phát triển và duy trì.', 19, 2),

       ('TypeScript', 'Java', 'Python', 'Dart',
        'Angular được xây dựng dựa trên ngôn ngữ nào?', 'TypeScript', '2025-12-05 20:12:42',
        'Angular sử dụng TypeScript làm ngôn ngữ chính.', 19, 3),

       ('Component', 'Class', 'Object', 'Module',
        'Đơn vị cấu trúc cơ bản của Angular là gì?', 'Component', '2025-12-05 20:12:42',
        'Component là đơn vị xây dựng UI cơ bản của Angular.', 19, 4),

       ('NgModules', 'Packages', 'Controllers', 'Blocks',
        'Angular sử dụng gì để tổ chức các thành phần?', 'NgModules', '2025-12-05 20:12:42',
        'NgModules gom nhóm component, directive, pipe, service.', 19, 5),

       ('@Component', '@Module', '@Service', '@NgApp',
        'Decorator nào dùng để khai báo component?', '@Component', '2025-12-05 20:12:42',
        '@Component là decorator định nghĩa component.', 19, 6),

       ('HTML', 'CSS', 'JavaScript', 'XML',
        'Template của Angular được viết bằng gì?', 'HTML', '2025-12-05 20:12:42',
        'Template Angular dùng HTML kết hợp binding.', 19, 7),

       ('Two-way data binding', 'Triển khai API', 'Viết SQL', 'Thiết kế UI',
        'Tính năng nổi bật mà Angular hỗ trợ mạnh?', 'Two-way data binding', '2025-12-05 20:12:42',
        'Angular hỗ trợ two-way binding rất mạnh.', 19, 8),

       ('One-way & Two-way binding', 'CSS binding', 'Server binding', 'Image binding',
        'Angular hỗ trợ loại data binding nào?', 'One-way & Two-way binding', '2025-12-05 20:12:42',
        'Angular hỗ trợ cả one-way và two-way binding.', 19, 9),

       ('ng new', 'npm init', 'node create', 'ng generate-app',
        'Lệnh tạo dự án Angular mới là gì?', 'ng new', '2025-12-05 20:12:42', 'Lệnh đúng: ng new project-name.', 19,
        10);

INSERT INTO online_learning_platform.multiple_choice_question
(answer1, answer2, answer3, answer4, content, correct_answer, create_date, explain_correct_answer, id_topic_test,
 question_number)
VALUES ('Tăng tốc độ truy vấn', 'Lưu trữ thêm dữ liệu', 'Tăng kích thước bảng', 'Giảm tính toàn vẹn dữ liệu',
        'Mục đích chính của Index trong SQL là gì?', 'Tăng tốc độ truy vấn',
        '2025-12-05 20:12:42', 'Index giúp tăng tốc truy vấn bằng cách tối ưu việc tìm kiếm.', 18, 1),

       ('B-Tree Index', 'HTML Index', 'CSS Index', 'UI Index',
        'Loại index phổ biến nhất trong SQL là gì?', 'B-Tree Index',
        '2025-12-05 20:12:42', 'B-Tree là cấu trúc index mặc định trong nhiều hệ quản trị CSDL.', 18, 2),

       ('WHERE', 'GROUP BY', 'ORDER BY', 'INDEX BY',
        'Index hoạt động hiệu quả nhất khi kết hợp với câu lệnh nào?', 'WHERE',
        '2025-12-05 20:12:42', 'Index tăng tốc cho các điều kiện lọc trong WHERE.', 18, 3),

       ('Full Table Scan', 'Index Seek', 'Table Rewrite', 'Disk Rewrite',
        'Thuật ngữ nào mô tả việc truy vấn sử dụng index?', 'Index Seek',
        '2025-12-05 20:12:42', 'Index Seek là truy vấn dùng index để tìm nhanh dữ liệu.', 18, 4),

       ('Có thể làm chậm INSERT/UPDATE', 'Không ảnh hưởng hiệu năng', 'Tăng gấp đôi tốc độ ghi', 'Xóa dữ liệu tự động',
        'Nhược điểm của việc tạo quá nhiều index là gì?', 'Có thể làm chậm INSERT/UPDATE',
        '2025-12-05 20:12:42', 'Quá nhiều index khiến việc ghi dữ liệu phải cập nhật trên nhiều index.', 18, 5),

       ('Composite Index', 'Single Index', 'Partial Index', 'Hash Index',
        'Index chứa nhiều hơn một cột được gọi là gì?', 'Composite Index',
        '2025-12-05 20:12:42', 'Composite index bao gồm nhiều cột để tối ưu truy vấn phức tạp.', 18, 6),

       ('WHERE col1 = 10 AND col2 = 20', 'SELECT * FROM table', 'ORDER BY RAND()', 'SELECT COUNT(*)',
        'Trường hợp nào phù hợp sử dụng Composite Index?', 'WHERE col1 = 10 AND col2 = 20',
        '2025-12-05 20:12:42', 'Truy vấn lọc theo nhiều cột phù hợp với composite index.', 18, 7),

       ('EXPLAIN', 'CHECK', 'ANALYZE TABLE', 'REPAIR TABLE',
        'Câu lệnh nào dùng để xem kế hoạch thực thi truy vấn?', 'EXPLAIN',
        '2025-12-05 20:12:42', 'EXPLAIN cho biết truy vấn có dùng index hay không.', 18, 8),

       ('Clustered Index', 'Unclustered Index', 'Virtual Index', 'Random Index',
        'Loại index nào quy định cách dữ liệu được lưu vật lý trong bảng?', 'Clustered Index',
        '2025-12-05 20:12:42', 'Clustered index lưu dữ liệu theo thứ tự của index.', 18, 9),

       ('Hash Index', 'B-Tree Index', 'Bitmap Index', 'JSON Index',
        'Index nào tối ưu cho so sánh bằng (=) nhưng không tốt cho range query?', 'Hash Index',
        '2025-12-05 20:12:42', 'Hash index tối ưu cho lookup chính xác, không tốt cho các so sánh khoảng.', 18, 10);


INSERT INTO online_learning_platform.multiple_choice_question
(answer1, answer2, answer3, answer4, content, correct_answer, create_date, explain_correct_answer, id_topic_test,
 question_number)
VALUES ('Khả năng máy tự học từ dữ liệu', 'Một ngôn ngữ lập trình', 'Phần cứng AI', 'Một hệ điều hành',
        'Machine Learning là gì?', 'Khả năng máy tự học từ dữ liệu', '2025-12-05 20:12:42',
        'Machine Learning giúp máy tính học từ dữ liệu và đưa ra dự đoán.', 16, 1),

       ('Supervised Learning', 'Unsupervised Learning', 'Reinforcement Learning', 'Tất cả đều đúng',
        'Có bao nhiêu nhóm thuật toán ML phổ biến?', 'Tất cả đều đúng', '2025-12-05 20:12:42',
        'ML được chia thành 3 nhóm chính: Supervised, Unsupervised và Reinforcement.', 16, 2),

       ('Label', 'Feature', 'Dataset', 'Model',
        'Trong Supervised Learning, dữ liệu được gắn nhãn gọi là gì?', 'Label', '2025-12-05 20:12:42',
        'Label là giá trị mục tiêu mà mô hình cần dự đoán.', 16, 3),

       ('Feature', 'Algorithm', 'Label', 'Loss Function',
        'Thuộc tính mô tả dữ liệu trong ML được gọi là gì?', 'Feature', '2025-12-05 20:12:42',
        'Feature là các đặc trưng dùng làm đầu vào cho mô hình.', 16, 4),

       ('Classification', 'Regression', 'Clustering', 'Cả A và B',
        'Bài toán dự đoán giá nhà thuộc loại nào?', 'Regression', '2025-12-05 20:12:42',
        'Regression dùng để dự đoán giá trị số.', 16, 5),

       ('K-means', 'Linear Regression', 'Decision Tree', 'SVM',
        'Thuật toán nào dùng cho bài toán phân cụm?', 'K-means', '2025-12-05 20:12:42',
        'K-means là thuật toán phân cụm nổi tiếng.', 16, 6),

       ('Overfitting', 'Underfitting', 'Regularization', 'Activation function',
        'Khi mô hình học quá kỹ dữ liệu huấn luyện gọi là gì?', 'Overfitting', '2025-12-05 20:12:42',
        'Overfitting khiến mô hình không tổng quát tốt.', 16, 7),

       ('Train, Validation, Test', 'Input - Output', 'X - Y', 'Feature - Label',
        'Dữ liệu ML thường được chia thành các tập nào?', 'Train, Validation, Test', '2025-12-05 20:12:42',
        'Bộ dữ liệu được chia 3 phần để huấn luyện và đánh giá mô hình.', 16, 8),

       ('Accuracy', 'Loss', 'MAE', 'RMSE',
        'Chỉ số phổ biến dùng đánh giá bài toán phân loại là gì?', 'Accuracy', '2025-12-05 20:12:42',
        'Accuracy thể hiện tỷ lệ dự đoán đúng.', 16, 9),

       ('Gradient Descent', 'Backpropagation', 'Batching', 'Normalization',
        'Thuật toán nào tối ưu mô hình ML phổ biến nhất?', 'Gradient Descent', '2025-12-05 20:12:42',
        'Gradient Descent tối ưu tham số bằng cách giảm dần độ dốc.', 16, 10);
