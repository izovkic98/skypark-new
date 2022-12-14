package com.tvz.skypark.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tvz.skypark.enums.VehicleManufacturerEnum;
import com.tvz.skypark.enums.VehicleTypeEnum;

import io.jsonwebtoken.lang.Collections;

@RestController
@RequestMapping("rac/enums")
@CrossOrigin(origins = "http://localhost:3000")
public class EnumController {
	
	@SuppressWarnings("unchecked")
	@GetMapping("/types")
    public List<VehicleTypeEnum> getAllTypes() {
        return Collections.arrayToList(VehicleTypeEnum.values());
    }
	
	@SuppressWarnings("unchecked")
	@GetMapping("/manufacturers")
    public List<VehicleManufacturerEnum> getAllManufacturers() {
        return Collections.arrayToList(VehicleManufacturerEnum.values());
    }

}
