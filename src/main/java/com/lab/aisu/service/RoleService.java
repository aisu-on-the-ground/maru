package com.lab.aisu.service;

import java.util.Map;

public interface RoleService {

	public void roletest(int roleId, String description);
	
	public Map<String, Object> getRole(int roleId);
}
