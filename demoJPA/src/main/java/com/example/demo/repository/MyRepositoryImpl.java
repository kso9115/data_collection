package com.example.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Member;

@Transactional	// 엔티티 매니저에 접근하여 직접 사용하기때문에 클래스 단위의 트랜잭션 
@Repository
public class MyRepositoryImpl implements MyRepository {

	private final EntityManager em;
	
	// 생성자 주입
	public MyRepositoryImpl(EntityManager em) {
		this.em=em;
	};
	
//	@Override
//	public List<Member> emMemberList2() {
//		// table : Entity명으로
//		return em.createQuery("select m from Member m order by id asc", Member.class)
//		.getResultList();
//		// 최종적으로 List<Member> 이 형태의 데이터가 필요하기 때문에 
//		// 기본적으로 builder타입으로 되어있는 em에 .getResultList() 사용
//		
//		// JPQL 적용
//		// "select * from Member order by id asc" 구문은 오류가 발생한다.
//		// Entity를 통해 접근하기 때문에 * 사용 불가, 엘리어스를 사용해 컬럼명에 접근해야한다.
//	}
	
	// Parameter 적용
	@Override
	public List<Member> emMemberList() {
		// table : Entity명으로
		return em.createQuery("select m from Member m where jno < :jno", Member.class)
				.setParameter("jno", 7) // jno가 7보다 작은 list를 출력
				.getResultList();
	}

	
	@Override
	public Member emMemberDetail(String id) {
		return em.createQuery("select m from Member m where id=:idDetail" , Member.class)
				.setParameter("idDetail", id)
				.getSingleResult();
	}

}
