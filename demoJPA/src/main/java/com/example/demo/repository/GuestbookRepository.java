package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Guestbook;


//=> JpaRepository의 save()메서드 동작원리
//- 새로운 entity이면 EntityManager의 persist() 를 
//	아니면 merge()를 호출 EntityManager merge( )를 호출
//	persist / merge => 엔티티 매니저에 있는 메서드 사용

// ** EntityManager

public interface GuestbookRepository 
					extends JpaRepository<Guestbook, Long>{
					// Guestbook : from Guestbook

}

