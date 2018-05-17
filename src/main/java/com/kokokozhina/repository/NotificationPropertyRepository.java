package com.kokokozhina.repository;


import com.kokokozhina.model.NotificationProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationPropertyRepository extends JpaRepository<NotificationProperty, Long> {
    NotificationProperty findById(Long id);

    void deleteById(Long id);
}
