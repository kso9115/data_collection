package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.GuestbookDTO;
import com.example.demo.domain.PageRequestDTO;
import com.example.demo.domain.PageResultDTO;
import com.example.demo.entity.Guestbook;

// ** JPA CRUD 구현
// => Entity 와 DTO를 용도별로 분리해서 사용하는경우 메서드 2개 추가 필요하다
// dtoToEntity() 와  entityToDto()
// 즉 default 메서드로 작성해야한다.
public interface GuestbookService {
	
	// JPA Pagable을 이용한 Paging 기능
	PageResultDTO<GuestbookDTO, Guestbook> pageList(PageRequestDTO requestDTO);
	
	
	// JPA CRUD 구현 : 우선 단일 객체만 전달받아 구현할 예정임
	List<Guestbook> selectList();
	Guestbook selectOne(Long gno);
	Long register(GuestbookDTO dto);	// insert,update 모두 사용 : 누구를 수정했는지 키를 리턴할 것이므로 Long 타입으로 지정
	void delete(Long gno);
	
	// dtoToEntity() : DTO에 있는 데이터를 Entity에 담아주기 위함
	// insert, update위해서 주로 사용되므로 regDate, modDate는 제외된다.
	default Guestbook dtoToEntity(GuestbookDTO dto) {
		Guestbook entity = Guestbook.builder()
									.gno(dto.getGno())
									.title(dto.getTitle())
									.content(dto.getContent())
									.writer(dto.getWriter()).build();
		
		return entity;
	}
	
	// entityToDto()
	// 결과 출력 시 주로 사용되므로 regDate, modDate을 포함
	default GuestbookDTO entityToDto(Guestbook entity) {
		return GuestbookDTO.builder()
							.gno(entity.getGno())
							.title(entity.getTitle())
							.content(entity.getContent())
							.writer(entity.getWriter())
							.regDate(entity.getRegDate())
							.modDate(entity.getModDate()).build();
	}

}//interface
