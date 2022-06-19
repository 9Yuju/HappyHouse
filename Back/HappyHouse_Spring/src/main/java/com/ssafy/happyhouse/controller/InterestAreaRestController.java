package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.InterestAreaDto;
import com.ssafy.happyhouse.model.service.InterestAreaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

//관심지역 처리용 rest controller
@RestController
@RequestMapping("/interest")
@CrossOrigin("*")
public class InterestAreaRestController {

	private static final Logger logger = LoggerFactory.getLogger(InterestAreaRestController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private InterestAreaService interestAreaService;

	// 관심지역 등록
	@ApiOperation(value = "관심지역 등록.", response = String.class)
	@PostMapping(value = "/register")
	public ResponseEntity<?> interestAreaRegister(@RequestBody InterestAreaDto area) throws Exception {
		logger.debug("interestAreaRegister - 호출");
		interestAreaService.registerArea(area);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}

	// 관심지역 목록
	@ApiOperation(value = "관심지역 목록.", response = List.class)
	@GetMapping(value = "/searchAll/{id}")
	public ResponseEntity<?> interestAreaList(@PathVariable String id, Model model) throws Exception {
		logger.debug("interestAreaList - 호출");
		List<InterestAreaDto> list = interestAreaService.searchAllArea(id);
		if (list != null && !list.isEmpty()) {
			return new ResponseEntity<List<InterestAreaDto>>(list, HttpStatus.OK);
		}else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	// 관심지역 인기 목록
	@ApiOperation(value = "관심지역 인기 목록 10개 검색.", response = List.class)
	@GetMapping(value = "/searchPopular")
	public ResponseEntity<?> interestAreaPopularList() throws Exception {
		logger.debug("interestAreaPopularList - 호출");
		List<InterestAreaDto> list = interestAreaService.searchPopular();
		if (list != null && !list.isEmpty()) {
			return new ResponseEntity<List<InterestAreaDto>>(list, HttpStatus.OK);
		}else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}

	// 관심지역 삭제
	@ApiOperation(value = "관심지역 삭제.", response = String.class)
	@DeleteMapping(value = "/delete/{userid}/{dongCode}")
	public ResponseEntity<String> interestAreaDelete(@PathVariable("userid") String userid, @PathVariable("dongCode") String dongCode)
			throws Exception {
		logger.debug("interestAreaDelete - 호출");
		Map<String, String> param = new HashMap<String, String>();
		param.put("id", userid);
		param.put("dongCode", dongCode);
		interestAreaService.deleteArea(param);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}


}
