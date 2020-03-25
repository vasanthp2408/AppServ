package com.app.serv.work.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.app.serv.work.model.TestData;

public interface TestServiceDao extends JpaRepository<TestData, Integer>{
	
//	public List<TestData> getListtestData();
}
