package com.learning.spring.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.spring.model.Sector;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long>{

	public Optional<Sector> findBySectorName(String sectorName);
}
