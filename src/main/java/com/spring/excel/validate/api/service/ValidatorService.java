package com.spring.excel.validate.api.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.spring.excel.validate.api.dto.Employee;

@Service
public class ValidatorService {
	@SuppressWarnings("resource")
	public List<Map<String, List<String>>> excelToJavaBean(String xlxsFilePath) throws IOException {
		Employee employee = null;
		List<Map<String, List<String>>> excelErrors = new ArrayList<>();
		XSSFWorkbook workBook = new XSSFWorkbook(xlxsFilePath);
		XSSFSheet sheet = workBook.getSheetAt(0);
		int totalRows = sheet.getPhysicalNumberOfRows();
		System.out.println("Total Row find :" + totalRows);
		Row row;
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = (Row) sheet.getRow(i);
			int id = (int) row.getCell(0).getNumericCellValue();
			String name = row.getCell(1).getStringCellValue();
			String dept = row.getCell(2).getStringCellValue();
			double salary = row.getCell(3).getNumericCellValue();
			String email = row.getCell(4).getStringCellValue();

			employee = new Employee(id, name, dept, salary, email);
			excelErrors.add(emailValidator(employee, i, 0));
			// employees.add(employee);
		}
		return excelErrors;
	}

	private Map<String, List<String>> emailValidator(Employee employee, int row, int column) {
		Map<String, List<String>> errorMap = new HashMap<>();
		String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
		List<String> errors = new ArrayList<>();
		if (!employee.getEmail().matches(EMAIL_REGEX)) {
			errors.add("error in email field");
		}
		if (employee.getSalary() <= 1000) {
			errors.add("error in salary field  row");
		}
		errorMap.put("row Num " + row, errors);
		return errorMap;
	}
}
