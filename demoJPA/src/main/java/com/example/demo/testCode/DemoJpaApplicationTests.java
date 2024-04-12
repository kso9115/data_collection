package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.domain.MemberRole;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
class DemoJpaApplicationTests {

	//@Test
	void contextLoads() {
	}
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// ** Test전 확인사항
	// => application.properties
	//	 member_member_role_list 테이블이 자동 생성될수 있도록 속성 변경하고
	//	 첫 실행후 생성되면 다시 원상태로 변경하기
	//	 spring.jpa.hibernate.ddl-auto=update	
	 
	public void insertMember(){
		
		Member member = Member.builder()
	              .id("roletest")
	              .password(passwordEncoder.encode("12345!"))
	              .name("가나다")
	              .age(22)
	              .jno(7)
	              .info("SpringBoot Security Test")
	              .point(300.5)
	              .birthday("2000-02-02")
	              .rid("apple")
	              .uploadfile("aaa.gif")
	              .build();
		// => Role 을 추가하기위해 Member 엔티티에 정의된 맴버설정 의해 
		//    member_role_list 테이블이 자동 생성됨
		//   (단, application.properties 의 hibernate.ddl-auto 속성확인)
		member.addRole(MemberRole.USER);
		member.addRole(MemberRole.MANAGER);
		memberRepository.save(member);
	} //insertMember
	
	
	public void testRead() {
		
		String id = "admin";
		Member member = memberRepository.getWithRoles(id);

	    log.info("-----------------");
	    log.info(member);
	    // => member(id=admin, ..., roleList=[ADMIN, MANAGER, USER])
	} //testRead

	@Test
	// => 모든 member 에 Role 부여하기 
	//    member_role_list 테이블 완성
	// => 최초 실행시
	//	-> member_role_list 테이블이 자동 생성됨
	//	-> 이때는 application.properties 의 hibernate.ddl-auto 속성 확인
	public void addRole() {
		List<Member> list = memberRepository.findAll();
		for (Member m:list) {
			
			Member member = Member.builder()
              .id(m.getId())
              .password(m.getPassword())
              .name(m.getName())
              .age(m.getAge())
              .jno(m.getJno())
              .info(m.getInfo())
              .point(m.getPoint())
              .birthday(m.getBirthday())
              .rid(m.getRid())
              .uploadfile(m.getUploadfile())
              .build();
			// => builder() 적용해야 Member 엔티티의 roleList가 생성됨.
			//	  즉, roleList 가 notNull 이어야 아래구문의 addRole() 메서드로 담을수있음.  	
			
			if (member.getId().equals("admin")) {
				member.addRole(MemberRole.ADMIN);
				member.addRole(MemberRole.MANAGER);
				member.addRole(MemberRole.USER);
			}else if  ( member.getId().equals("simsim916") ||
						member.getId().equals("agr4005") ||
						member.getId().equals("bamboo7") ||
						member.getId().equals("kso1") ) {
				member.addRole(MemberRole.MANAGER);
				member.addRole(MemberRole.USER);
			} else {
				member.addRole(MemberRole.USER);
			} //if
			
			memberRepository.save(member);
			
			/*	DB의 Table member_role_list 를 확인해보면 해당하는 Role의 값이 아래처럼
			 *  enum의 ordinal() 값인 int로 입력되어있다.
			 *  enum MemberRole 의 정의된 순서이므로 순서를 변경하면 
			 *  해당하는 출력값(enum의 name()값) 은 달라진다.
				+------------+-----------+
				| member_id  | role_list |
				+------------+-----------+
				| admin      |         0 | -> ADMIN
				| admin      |         1 | -> MANAGER
				| admin      |         2 | -> USER
				 
				| bamboo7    |         1 | -> 조장: MANAGER, USER
				| bamboo7    |         2 |  
				
				| banana     |         2 | -> 일반: USER
			*/
		} //for
	} //addRole
	
} //class
