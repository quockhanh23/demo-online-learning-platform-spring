package com.example.demoonlinelearningplatform.controllers;

import com.example.demoonlinelearningplatform.commons.Common;
import com.example.demoonlinelearningplatform.commons.CommonConstant;
import com.example.demoonlinelearningplatform.entities.Notification;
import com.example.demoonlinelearningplatform.repositories.NotificationRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationRepository notificationRepository;

    @GetMapping("/getAllNotificationPage")
    public ResponseEntity<Object> getAllUserPage(@RequestParam Long idUserReceiver,
                                                 @RequestParam(defaultValue = "0", required = false) int page,
                                                 @RequestParam(defaultValue = "10", required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Notification> notifications = notificationRepository.getAllNotificationPage(idUserReceiver, pageable);
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    /**
     * Tạo mới thông báo
     *
     * @param notification: dữ liệu
     * @return void
     */
    @PostMapping("/createNotification")
    public ResponseEntity<Object> createNotification(@Valid @RequestBody Notification notification,
                                                     BindingResult bindingResult) {
        Common.commonHandlerError(bindingResult);
        notification.setCreatedDate(new Date());
        notification.setStatus(CommonConstant.ACTIVE);
        notificationRepository.save(notification);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Cập nhật thông tin thông báo
     *
     * @param idNotification: id của Notification
     * @return void
     */
    @PutMapping("/updateNotification")
    public ResponseEntity<Object> updateNotification(@RequestParam Long idNotification) {
        Optional<Notification> optionalNotification = notificationRepository.findById(idNotification);
        if (optionalNotification.isPresent()) {
            optionalNotification.get().setUpdatedDate(new Date());
            optionalNotification.get().setStatus(CommonConstant.INACTIVE);
            notificationRepository.saveAndFlush(optionalNotification.get());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Cập nhật trạng thái của toàn bộ thông báo
     *
     * @param ids: danh sách id của các đối tượng Notification
     * @return void
     */
    @PutMapping("/updateAllStatus")
    public ResponseEntity<Object> updateAllStatus(@RequestBody List<Long> ids) {
        List<Notification> list = notificationRepository.findAllByIdIn(ids);
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(notification -> notification.setStatus(CommonConstant.INACTIVE));
            notificationRepository.saveAll(list);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
