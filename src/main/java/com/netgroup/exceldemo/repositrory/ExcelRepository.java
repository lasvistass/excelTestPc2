package com.netgroup.exceldemo.repositrory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.netgroup.exceldemo.data.Excel;

@Repository
public interface ExcelRepository extends JpaRepository<Excel, Integer>{
	
	@Query(value = "SELECT * FROM excel WHERE excel.utente = utenti.id AND excel.utente = ?1", nativeQuery = true)
	public List<Excel> listaExcelByUtente(int id);

}
