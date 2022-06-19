package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.UserInfoDto;

@Mapper
public interface UserInfoMapper {

	void save(UserInfoDto userinfo) throws SQLException;
	List<UserInfoDto> findAll() throws SQLException;
	void updateUser(UserInfoDto userinfo)throws SQLException;
	UserInfoDto searchById(String id)throws SQLException;
	void delete(String id) throws SQLException;
	UserInfoDto selectLogin(Map<String, String> map) throws SQLException;
	
}
