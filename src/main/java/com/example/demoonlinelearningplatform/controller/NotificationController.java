package com.example.demoonlinelearningplatform.controller;

import com.example.demoonlinelearningplatform.common.CommonConstant;
import com.example.demoonlinelearningplatform.entity.Notification;
import com.example.demoonlinelearningplatform.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationRepository notificationRepository;

    @PostMapping("/createNotification")
    public ResponseEntity<Object> createNotification(@RequestBody Notification notification) {
        notification.setCreatedDate(new Date());
        notification.setStatus(CommonConstant.ACTIVE);
        notificationRepository.save(notification);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getNotification")
    public ResponseEntity<Object> getNotification(@RequestParam Long idNotification) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updateNotification")
    public ResponseEntity<Object> updateNotification(@RequestBody Notification notification) {
        notification.setUpdatedDate(new Date());
        notificationRepository.save(notification);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<Object> updateStatus(@RequestBody List<Long> ids) {
        List<Notification> list = notificationRepository.findAllByIdIn(ids);
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(notification -> notification.setStatus(CommonConstant.INACTIVE));
            notificationRepository.saveAll(list);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
