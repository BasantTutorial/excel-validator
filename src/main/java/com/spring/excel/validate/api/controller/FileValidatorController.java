package com.spring.excel.validate.api.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.excel.validate.api.dto.InputRequest;
import com.spring.excel.validate.api.service.ValidatorService;

@RestController
public class FileValidatorController {
	@Autowired
	private ValidatorService service;

	@PostMapping("/validate")
	public List<Map<String, List<String>>> validateExcel(@RequestBody InputRequest request) throws IOException {
		File excelFile = new File(request.getFilePath() + "/" + request.getFileName());
		List<Map<String, List<String>>> emps = service.excelToJavaBean(excelFile.getAbsolutePath());
		return emps;
	}
}
