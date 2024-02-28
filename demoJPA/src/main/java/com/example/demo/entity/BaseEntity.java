package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

//** BaseEntity
//=> 자료 등록시간, 수정시간 등 자동으로 추가되고 변경되는 값들을 자동으로 처리하기위한 BaseEntity 클래스 
//=> 추상클래스로 작성     
//=> @MappedSuperclass: 테이블로 생성되지않음
//=> @EntityListeners : 엔티티객체의 변화를 감지하는 리스너설정 (AuditingEntityListener.class 가 담당)
// AuditingEntityListener 를 활성화 시키기 위해서는 
//  DemoJpaApplication.java 에 @EnableJpaAuditing 설정추가해야함.

@MappedSuperclass	// 매핑정보만 상속받는 Superclass
@EntityListeners(value={ AuditingEntityListener.class }) // 리스너 설정(등록해주어야한다) : 변경되는 값을 자동으로 적용
@Getter
abstract class BaseEntity {
	
	@CreatedDate
	@Column(name="regdate", updatable = false)
	private LocalDateTime regDate;	// 생성된 시간
	
	@LastModifiedDate
	@Column(name="modDate")	// 필요할때마다 업데이트 되어야할 값이므로
	private LocalDateTime modDate;	// 수정된 시간
	
}
