package com.netgroup.exceldemo.repositrory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.netgroup.exceldemo.data.dao.Excel;

@Repository
public interface ExcelRepository extends JpaRepository<Excel, Integer>{
	
	

}
