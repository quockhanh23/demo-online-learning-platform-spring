package com.example.demoonlinelearningplatform.controllers;

import com.example.demoonlinelearningplatform.dtos.UserDTO;
import com.example.demoonlinelearningplatform.entities.User;
import com.example.demoonlinelearningplatform.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/admins")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    /**
     * API này dùng để lấy ra toàn bộ danh sách người dùng trong hệ thống
     *
     * @param idUserLogin: id của người đăng nhập
     * @param page:        trang hiện tại
     * @param size:        số phần tử trong 1 Trang
     * @param searchText:  search value
     * @return danh sách người dùng trong hệ thống (phân trang)
     */
    @GetMapping("/getAllUser")
    public ResponseEntity<Object> getAllUserPage(@RequestParam Long idUserLogin,
                                                 @RequestParam(defaultValue = "0", required = false) int page,
                                                 @RequestParam(defaultValue = "10", required = false) int size,
                                                 @RequestParam(required = false) String searchText) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserDTO> userDTOS = userService.getAllUserPage(idUserLogin, pageable, searchText);
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    /**
     * API này dùng để lấy ra toàn bộ danh sách người dùng trong hệ thống theo role
     *
     * @param idUserLogin: id của người đăng nhập
     * @param page:        trang hiện tại
     * @param size:        số phần tử trong 1 Trang
     * @param role:        tìm kiếm theo vai trò
     * @return danh sách người dùng trong hệ thống (phân trang)
     */
    @GetMapping("/getAllUserByRole")
    public ResponseEntity<Object> getAllUserByRole(@RequestParam Long idUserLogin,
                                                   @RequestParam(defaultValue = "0", required = false) int page,
                                                   @RequestParam(defaultValue = "10", required = false) int size,
                                                   @RequestParam(required = false) String role) {
        Pageable pageable = PageRequest.of(page, size);
        userService.getDetailUser(idUserLogin);
        Page<UserDTO> userDTOS = userService.getAllUserPageByRole(pageable, role);
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    /**
     * API này dùng để lấy ra toàn bộ danh sách người dùng trong hệ thống bộ môn
     *
     * @param idUserLogin:  id của người đăng nhập
     * @param page:         trang hiện tại
     * @param size:         số phần tử trong 1 Trang
     * @param idDepartment: tìm kiếm bộ môn
     * @return danh sách người dùng trong hệ thống (phân trang)
     */
    @GetMapping("/getAllUserByDepartment")
    public ResponseEntity<Object> getAllUserByDepartment(@RequestParam Long idUserLogin,
                                                         @RequestParam(defaultValue = "0", required = false) int page,
                                                         @RequestParam(defaultValue = "10", required = false) int size,
                                                         @RequestParam Long idDepartment) {
        Pageable pageable = PageRequest.of(page, size);
        userService.getDetailUser(idUserLogin);
        Page<UserDTO> userDTOS = userService.getAllUserByDepartment(pageable, idDepartment);
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    /**
     * API này dùng để cập nhật trạng thái của người dùng trong hệ thống
     *
     * @param idAdmin: id của admin
     * @param idUser:  id của người dùng bị cập nhật
     * @param action:  kích hoạt tài khoản, cấm tài khoản...
     * @return void
     */
    @PutMapping("/action")
    public ResponseEntity<Object> actionUser(@RequestParam Long idAdmin,
                                             @RequestParam Long idUser,
                                             @RequestParam String action) {
        userService.actionUser(idAdmin, idUser, action);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * API này dùng để đăng kí (tạo mới) tài khoản cho người dùng
     *
     * @param user: thông tin của người dùng sẽ được đăng kí tài khoản
     * @param role: vai trò của người dùng sẽ được đăng kí tài khoản
     * @return thông tin người dùng
     */
    @PostMapping("/createUser")
    public ResponseEntity<Object> createUser(@RequestBody User user, @RequestParam String role) {
        UserDTO userDTO = userService.createUser(user, role);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }
}
