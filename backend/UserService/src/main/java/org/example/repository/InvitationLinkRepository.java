package org.example.repository;

import org.example.entity.InvitationLinkEntity;
import org.springframework.data.repository.CrudRepository;

public interface InvitationLinkRepository extends CrudRepository<InvitationLinkEntity, Long> {

    InvitationLinkEntity findByHash(String hash);
}
