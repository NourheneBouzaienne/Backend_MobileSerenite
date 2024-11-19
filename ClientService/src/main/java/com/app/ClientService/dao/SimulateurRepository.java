package com.app.ClientService.dao;

import com.app.ClientService.models.Simulateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimulateurRepository extends JpaRepository<Simulateur, Long> {

}
