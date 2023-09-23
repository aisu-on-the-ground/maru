package com.lab.aisu.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleDAO {
	
	public void insertRole(@Param("roleId") int roleId, @Param("description") String description);

}
