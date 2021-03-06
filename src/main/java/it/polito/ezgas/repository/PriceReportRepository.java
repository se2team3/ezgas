package it.polito.ezgas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.polito.ezgas.entity.PriceReport;

@Repository
public interface PriceReportRepository extends JpaRepository<PriceReport, Integer>{

}
