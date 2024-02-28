package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.domain.GuestbookDTO;
import com.example.demo.domain.PageRequestDTO;
import com.example.demo.domain.PageResultDTO;
import com.example.demo.entity.Guestbook;
import com.example.demo.repository.GuestbookRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

//** JPA 의 CRUD 메서드
//=> ~Repository 를 통해 JPA 의 EntityManager 에 접근됨.
//=> EntityManager : 영속 계층에 접근하여 엔티티에 대한 DB 작업을 제공함.
//=> 주요 메서드
//   - Insert : save(엔티티 객체)
//    - Select : findAll(), findById(키), getOne(키) ..
//    - Update : save(엔티티 객체)
//    - Delete : deleteById(키), delete(엔티티 객체)
//=> Insert, Update 모두 save(엔티티 객체)를 사용하는 이유
//     - JpaRepository 의 save는 비교후 없으면 insert, 
//     있으면 update를 동작시키고, entity를 return.   
//   - deleteById(키) 삭제의 경우에도 select 후 없으면 ~~DataAccessException 발생시키고
//     있으면 삭제하고 void 로 정의되어 return값 없음. 

// 엔티티 매니저를 사용하여 어플리케이션의 레포지토리에 접근
// 엔티티 매니저가 영속계층에 접근해서 여러가지 엔티티를 제공해주는 역할을 한다.
// Repository인터페이스의 메서드를 확인하게 되면 여러가지 crud메서드가 존재한다.

@Service
@Log4j2
@RequiredArgsConstructor	// 원하는 생성자만 주입을 할 떄
//=> 각필드에 대해 1개의 매개변수가 있는 생성자를 생성함.
//=> 초기화 되지 않은 모든 final 필드에만 적용됨. 
public class GuestbookServiceImpl implements GuestbookService {

	
	private final GuestbookRepository repository;
	// JPA SQL문 처리를 위해 정의한 인터페이스
	// 생성자 주입 => @RequiredArgsConstructor
	
	
	// JPA Pagable을 이용한 Paging 기능
	@Override
	public PageResultDTO<GuestbookDTO, Guestbook> pageList(PageRequestDTO requestDTO) {
		
		// => 조건완성
		Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());
		
		// => repository 실행
		// Page : 인터페이스
		// repository.findAll(pageable) => Guestbook 타입이니까 타입을 일치시켜주고
		Page<Guestbook> result = repository.findAll(pageable);
		
		// => Function<EN, DTO> 정의
		// 일반적인 객체일경우 new Function 의 구현객체를 생성하면 된다
		// Functional 인터페이스의 경우 익명 클래스를 생성 : 이 위치에서 익명 클래스를 정의해서 바로 사용(?)
		// 익명 클래스의 경우 메서드 명까지도 생략이 가능하다.
		
		// 익명함수에 entity를 매개변수로 받고나서 -> entityToDto() 전달받은 매개변수를 넣어줌
		Function<Guestbook, GuestbookDTO> fn = (entity -> entityToDto(entity));
		//인자가 하나이므로 소괄호 생략할 수 있음
//		Function<Guestbook, GuestbookDTO> fn = entity -> entityToDto(entity);
		// => Service 에 정의한 entityToDto 메서드를 이용해서 entity를 Dto로
		
		return new PageResultDTO<>(result,fn);
		// 인자를 바로 전달할 수 있음
//		return new PageResultDTO<>(result,entity -> entityToDto(entity));
	}
	
	@Override
	public List<Guestbook> selectList() {
		return repository.findAll();
		// findAll() : select * 
		// sql 구문을 만들어주는 메서드
	}

	
	// ** Optional<T> : null이 올수 있는 값을 감싸는 Wrapper클래스 => null값 관리
    // => Java8부터 Optional<T>클래스를 사용해 NullPointerException(이하 NPE)를 방지할수 있도록 지원.
    //     즉, Optional<T>는 null이 올수 있는 값을 감싸는 Wrapper클래스로, 참조하더라도 NPE가 발생하지 않도록 도와줌.
    //     제공되는 메소드로 복잡한 조건문 없이 NPE를 회피할 수 있어록 해줌
    // => isPresent() : Optional객체에 저장된 값이 null인지 아닌지 확인
    // => get() : Optional객체에 저장된 값 제공
    // => 참고 https://esoongan.tistory.com/95
	@Override
	public Guestbook selectOne(Long gno) {
//		return repository.findById(gno);	// 리턴타입이 Guestbook이 아님
		Optional<Guestbook> result = repository.findById(gno);	// 리턴타입을 Guestbook으로
		
		if(result.isPresent()) return result.get();
		else return null;
	}

	// => insert, update
	@Override
	public Long register(GuestbookDTO dto) {
		log.info("** GuestbookServiceImpl의 register 메서드 매개변수 dto 확인"+dto);
		Guestbook entity = dtoToEntity(dto);
		repository.save(entity);	// 처리 후 entity를 return한다.
		
		return entity.getGno();	// 없다면 입력, 있다면 수정
	}


	@Override
	public void delete(Long gno) {
		repository.deleteById(gno);
	}


	



}
