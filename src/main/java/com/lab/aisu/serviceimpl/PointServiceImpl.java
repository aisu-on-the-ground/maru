package com.lab.aisu.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab.aisu.dao.PointDAO;
import com.lab.aisu.service.PointService;

@Service
public class PointServiceImpl implements PointService {
	
	@Autowired
	private PointDAO dao;

}
