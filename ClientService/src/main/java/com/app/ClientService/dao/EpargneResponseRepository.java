package com.app.ClientService.dao;

import com.app.ClientService.models.EpargneResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpargneResponseRepository extends JpaRepository<EpargneResponse, Long> {
}
