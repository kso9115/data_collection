package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "jo")

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Jo {
	
	@Id
	private int jno;	// Integer 로 받네..?
	
	private String jname;
	private String captain;
	private String project;
	private String slogan;
	
	@Transient
	private String name;	// 조장 이름

}
