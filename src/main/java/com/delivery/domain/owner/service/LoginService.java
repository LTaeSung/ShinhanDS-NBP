package com.delivery.domain.owner.service;

import com.delivery.domain.owner.entity.OwnerEntity;
import com.delivery.domain.owner.repository.OwnerRepository;

public class LoginService {

    private final OwnerRepository ownerRepository;

    public LoginService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public OwnerEntity Login(String ownerEmail, String ownerPassword){

        return ownerRepository.findByOwnerEmail(ownerEmail)
                .filter(m -> m.getOwnerPassword().equals(ownerPassword))
                .orElse(null);
    }
}
