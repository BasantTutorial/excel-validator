package com.spring.excel.validate.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {

	private int id;
	private String name;
	private String dept;
	private double salary;
	private String email;

}
