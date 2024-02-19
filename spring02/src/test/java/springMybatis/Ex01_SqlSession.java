package springMybatis;

import static org.junit.Assert.assertNotNull;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)	// 스프링이 로딩 되도록
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")	// 어떤파일을 설정파일로 할 것인지, 절대적인 위치 삽입
public class Ex01_SqlSession {

	@Autowired
	SqlSessionFactory sqlSessionFactory;
	
//	@Test
	@Before
	public void sqlSessionFactoryTest() {
		System.out.println("sqlSessionFactory 주입 테스트 =>"+sqlSessionFactory);
		assertNotNull(sqlSessionFactory);
	}
	
	@Test
	public void sqlSessionTest() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		// sqlSessionFactory이 기본적으로 notNull이어야 실행이 가능하다.
		
		System.out.println("sqlSession 테스트 =>"+sqlSession);
		assertNotNull(sqlSession);
	}
}

