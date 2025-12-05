package com.example.demoonlinelearningplatform.repositories;

import com.example.demoonlinelearningplatform.entities.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByIdIn(List<Long> ids);

    @Query(value = "select * FROM notification WHERE (id_user_receiver = :idUserReceiver) order by id desc", nativeQuery = true)
    Page<Notification> getAllNotificationPage(Long idUserReceiver, Pageable pageable);

}
