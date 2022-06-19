package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.service.HouseMapService;

@RestController
@RequestMapping("/map")
@CrossOrigin("*")
public class HouseMapController {

	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);

	@Autowired
	private HouseMapService haHouseMapService;

	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		logger.debug("sido - 호출");
		logger.debug("sido : {}", haHouseMapService.getSido());
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getSido(), HttpStatus.OK);
	}

	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
		logger.debug("gugun - 호출");
		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getGugunInSido(sido), HttpStatus.OK);
	}

	@GetMapping("/dong")
	public ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		logger.debug("dong - 호출");
		List<HouseInfoDto> list = haHouseMapService.getDongInGugun(gugun);
		return new ResponseEntity<List<HouseInfoDto>>(list, HttpStatus.OK);
	}
	
	// vue에서 dongName으로만 받아와져서 guguncode와 dongName으로 dongCode찾기
	@GetMapping("/code/{gugun}/{dong}")
	public ResponseEntity<String> dong(@PathVariable("gugun") String guguncode,@PathVariable("dong") String dongname) throws Exception {
		logger.debug("code - 호출");
		Map<String,String> map = new HashMap<String, String>();
		map.put("guguncode", guguncode);
		map.put("dongname", dongname);
		String temp = haHouseMapService.getVueCode(map);
		System.out.println("temp : "+temp);
		return new ResponseEntity<String>(temp, HttpStatus.OK);
	}
	
	@GetMapping("/apt")
	public ResponseEntity<List<HouseInfoDto>> apt(@RequestParam("dongCode") String dong) throws Exception {
		logger.debug("apt - 호출");
		List<HouseInfoDto> list = haHouseMapService.getAptInDong(dong);
		return new ResponseEntity<List<HouseInfoDto>>(list, HttpStatus.OK);
	}
	
	//아파트 이름으로 검색
	@GetMapping("/search")
	public ResponseEntity<List<HouseInfoDto>> search(@RequestParam String aptName) throws Exception {
		logger.debug("search - 호출");
		List<HouseInfoDto> list = haHouseMapService.searchAptName(aptName);
		return new ResponseEntity<List<HouseInfoDto>>(list, HttpStatus.OK);
	}

}
