package com.rungroup.web.repository;

import com.rungroup.web.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club, Long> {
    Optional<Club> findByTitle(String title);
}
