package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.InterestAreaDto;

@Mapper
public interface InterestAreaMapper {

	void registerArea(InterestAreaDto area) throws SQLException;
	List<InterestAreaDto> searchAllArea(String id) throws SQLException;
	void deleteArea(InterestAreaDto area) throws SQLException;
	
}
