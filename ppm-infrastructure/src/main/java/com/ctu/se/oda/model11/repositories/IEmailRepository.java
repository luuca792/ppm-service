package com.ctu.se.oda.model11.repositories;

import com.ctu.se.oda.model11.entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IEmailRepository extends JpaRepository<Email, UUID> {
}
