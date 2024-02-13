package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Entity
@Table(name = "invitation_links")
@Data
public class InvitationLinkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hash;
    private OffsetDateTime expireAt;
    private OffsetDateTime createdAt;
    private OffsetDateTime deletedAt;
}
