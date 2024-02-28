package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Member;

// JpaRepository<T, ID> 제네릭임 => 엔티티의 타입을 알려주어야하기 때문에 t > entity id > primary의 타입
public interface MemberRepository extends JpaRepository<Member, String> {
	
}
