package com.example.demoonlinelearningplatform.service.impl;

import com.example.demoonlinelearningplatform.common.CommonConstant;
import com.example.demoonlinelearningplatform.common.RoleConstant;
import com.example.demoonlinelearningplatform.dto.UserDTO;
import com.example.demoonlinelearningplatform.entity.Role;
import com.example.demoonlinelearningplatform.entity.User;
import com.example.demoonlinelearningplatform.exption.InvalidException;
import com.example.demoonlinelearningplatform.repository.UserRepository;
import com.example.demoonlinelearningplatform.service.UserService;
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

    @Override
    public UserDTO login(User userRequest) {
        User user = userRepository.findUserByUsername(userRequest.getUsername());
        if (Objects.isNull(user)) throw new InvalidException("Sai tên đăng nhập");
        if (!user.getPassword().equals(userRequest.getPassword())) {
            throw new InvalidException("Sai mật khẩu");
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
        Role roleObject = new Role();
        roleObject.setRoleName(role);
        roles.add(roleObject);

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
        List<UserDTO> userDTOS = new ArrayList<>();
        for (int i = 0; i < page.getContent().size(); i++) {
            User user = page.getContent().get(i);
            UserDTO userDTO = UserDTO.builder().build();
            BeanUtils.copyProperties(user, userDTO);
            userDTOS.add(userDTO);
        }
        return new PageImpl<>(userDTOS, pageable, page.getTotalElements());
    }

    private boolean validateField(String oldValue, String newValue) {
        if (StringUtils.isEmpty(newValue)) return false;
        return !oldValue.equalsIgnoreCase(newValue);
    }
}
