package com.lab.aisu.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab.aisu.dao.TagDAO;
import com.lab.aisu.service.TagService;

@Service
public class TagServiceImpl implements TagService {
	
	@Autowired
	private TagDAO dao;

}
