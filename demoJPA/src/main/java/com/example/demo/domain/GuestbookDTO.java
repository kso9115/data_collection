package com.example.demo.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GuestbookDTO {

	private Long gno; // Auto_increment
	private String title;
	private String content;
	private String writer;
	private LocalDateTime regDate;	// 생성된 시간
	private LocalDateTime modDate;	// 수정된 시간
	

}
