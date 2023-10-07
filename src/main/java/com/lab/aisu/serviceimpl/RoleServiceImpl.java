package com.lab.aisu.serviceimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab.aisu.dao.RoleDAO;
import com.lab.aisu.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDAO roleDao;

	@Override
	public void roletest(int roleId, String description) {
		roleDao.insertRole(roleId, description);
	}

	@Override
	public Map<String, Object> getRole(int roleId) {
		Map<String, Object> map = roleDao.selectRole(roleId);
		return map;
	}

}
