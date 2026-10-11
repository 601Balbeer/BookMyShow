package com.bookmyshow.models;

import com.bookmyshow.enums.NotificationChannel;
import com.bookmyshow.enums.NotificationPriority;
import com.bookmyshow.enums.NotificationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

@SuperBuilder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notifications")
public class Notification extends GlobalRecord{
    private String notificationId;
    @Enumerated(EnumType.STRING)
    private NotificationChannel notificationChannel;
    @Enumerated(EnumType.STRING)
    private NotificationStatus notificationStatus;
    @Enumerated(EnumType.STRING)
    private NotificationPriority notificationPriority;
    @ManyToMany
    private List<User> receipts;
    private UUID templateId;
}
