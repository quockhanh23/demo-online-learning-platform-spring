package com.example.demoonlinelearningplatform.services.impls;

import com.example.demoonlinelearningplatform.commons.CommonConstant;
import com.example.demoonlinelearningplatform.commons.RoleConstant;
import com.example.demoonlinelearningplatform.dtos.ChangePassword;
import com.example.demoonlinelearningplatform.dtos.UserDTO;
import com.example.demoonlinelearningplatform.entities.Notification;
import com.example.demoonlinelearningplatform.entities.Role;
import com.example.demoonlinelearningplatform.entities.User;
import com.example.demoonlinelearningplatform.exceptions.InvalidException;
import com.example.demoonlinelearningplatform.repositories.NotificationRepository;
import com.example.demoonlinelearningplatform.repositories.RoleRepository;
import com.example.demoonlinelearningplatform.repositories.UserRepository;
import com.example.demoonlinelearningplatform.services.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final NotificationRepository notificationRepository;

    @Override
    public UserDTO login(User userRequest) {
        User user = userRepository.findUserByUsername(userRequest.getUsername());
        if (Objects.isNull(user)) throw new InvalidException("Sai tên đăng nhập");
        if (!user.getPassword().equals(userRequest.getPassword())) {
            throw new InvalidException("Sai mật khẩu");
        }
        if (CommonConstant.BANED.equals(user.getStatus())) {
            throw new InvalidException("Bạn đã bị cấm vui lòng liên hệ với admin để được hỗ trợ");
        }
        UserDTO userDTO = UserDTO.builder().build();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    @Override
    public UserDTO createUser(User userRequest, String role) {
        if (!RoleConstant.ROLE_STUDENT.equalsIgnoreCase(role)
                && !RoleConstant.ROLE_TEACHER.equalsIgnoreCase(role)) {
            throw new InvalidException("Không có role này");
        }
        userRequest.setCreatedDate(new Date());
        userRequest.setStatus(CommonConstant.ACTIVE);

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByRoleName(role));
        userRequest.setRoles(roles);

        User user = userRepository.save(userRequest);

        UserDTO userDTO = UserDTO.builder().build();
        BeanUtils.copyProperties(user, userDTO);
        userDTO.setRoles(roles);
        return userDTO;
    }

    @Override
    public UserDTO getDetailUser(Long idUser) {
        Optional<User> userOptional = userRepository.findById(idUser);
        if (userOptional.isEmpty()) throw new InvalidException("Không có người dùng này");
        UserDTO userDTO = UserDTO.builder().build();
        BeanUtils.copyProperties(userOptional.get(), userDTO);
        return userDTO;
    }

    @Override
    public UserDTO updateUser(Long idUser, User user) {
        Optional<User> userOptional = userRepository.findById(idUser);
        if (userOptional.isEmpty()) throw new InvalidException("Không có người dùng này");

        if (validateField(userOptional.get().getFullName(), user.getFullName())) {
            userOptional.get().setFullName(user.getFullName());
        }
        if (validateField(userOptional.get().getEmail(), user.getEmail())) {
            userOptional.get().setEmail(user.getEmail());
        }
        if (validateField(userOptional.get().getPhoneNumber(), user.getPhoneNumber())) {
            userOptional.get().setPhoneNumber(user.getPhoneNumber());
        }
        UserDTO userDTO = UserDTO.builder().build();
        BeanUtils.copyProperties(userOptional.get(), userDTO);
        return userDTO;
    }

    @Override
    public Page<UserDTO> getAllUserPage(Long idUserLogin, Pageable pageable, String searchText) {
        Page<User> page = userRepository.getAllUserPage(pageable, searchText, idUserLogin);
        if (CollectionUtils.isEmpty(page.getContent())) return new PageImpl<>(List.of());
        return new PageImpl<>(getListUserDTO(page), pageable, page.getTotalElements());
    }

    @Override
    public Page<UserDTO> getAllUserPageByRole(Pageable pageable, String role) {
        Page<User> page = userRepository.getAllUserPageByRole(pageable, role);
        if (CollectionUtils.isEmpty(page.getContent())) return new PageImpl<>(List.of());
        return new PageImpl<>(getListUserDTO(page), pageable, page.getTotalElements());
    }

    @Override
    public Page<UserDTO> getAllUserByDepartment(Pageable pageable, Long idDepartment) {
        Page<User> page = userRepository.getAllUserByDepartment(pageable, idDepartment);
        if (CollectionUtils.isEmpty(page.getContent())) return new PageImpl<>(List.of());
        return new PageImpl<>(getListUserDTO(page), pageable, page.getTotalElements());
    }

    // Map dữ liệu từ entity sang DTO
    private List<UserDTO> getListUserDTO(Page<User> page) {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (int i = 0; i < page.getContent().size(); i++) {
            User user = page.getContent().get(i);
            UserDTO userDTO = UserDTO.builder().build();
            BeanUtils.copyProperties(user, userDTO);
            userDTO.setOnline(true);
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    @Override
    public void actionUser(Long idAdmin, Long idUser, String action) {
        Optional<User> admin = userRepository.findById(idAdmin);
        if (admin.isEmpty()) throw new InvalidException("Không có người dùng này");
        Set<Role> roles = admin.get().getRoles();
        List<String> strings = roles.stream().map(Role::getRoleName).filter(StringUtils::isNotEmpty).toList();
        if (!strings.contains(RoleConstant.ROLE_ADMIN)) {
            throw new InvalidException("Bạn không phải là admin");
        }
        Optional<User> userOptional = userRepository.findById(idUser);
        if (userOptional.isEmpty()) throw new InvalidException("Không có người dùng này");
        List<String> listAction = List.of(CommonConstant.ACTIVE, CommonConstant.BANED, CommonConstant.INACTIVE);
        if (!listAction.contains(action)) {
            throw new InvalidException("Không có action này");
        }
        userOptional.get().setStatus(action);
        userRepository.save(userOptional.get());

        Notification notification1 = new Notification();
        notification1.setCreatedDate(new Date());
        notification1.setContent("Bạn vừa " + action + " với người dùng " + userOptional.get().getUsername());
        notification1.setIdUserReceiver(idAdmin);

        notification1.setStatus(CommonConstant.ACTIVE);
        Notification notification2 = new Notification();
        notification2.setCreatedDate(new Date());
        notification2.setContent("Bạn vừa bị admin " + action);
        notification2.setIdUserReceiver(userOptional.get().getId());
        notification2.setStatus(CommonConstant.ACTIVE);

        notificationRepository.saveAll(List.of(notification1, notification2));
    }

    @Override
    public void changePassword(ChangePassword changePassword, Long idUser) {
        Optional<User> userOptional = userRepository.findById(idUser);
        if (userOptional.isEmpty()) throw new InvalidException("Không có người dùng này");

        if (!userOptional.get().getPassword().equals(changePassword.getOldPassword())) {
            throw new InvalidException("Mật khẩu không đúng");
        }
        if (!changePassword.getNewPassword().equals(changePassword.getConfirmPassword())) {
            throw new InvalidException("Mật khẩu mới và xác nhận mật khẩu mới không trùng khớp");
        }
        userOptional.get().setPassword(changePassword.getNewPassword());
        userOptional.get().setConfirmPassword(changePassword.getConfirmPassword());
        userOptional.get().setUpdatedDate(new Date());
        userRepository.save(userOptional.get());
    }

    private boolean validateField(String oldValue, String newValue) {
        if (StringUtils.isEmpty(newValue)) return false;
        return !oldValue.equalsIgnoreCase(newValue);
    }
}
