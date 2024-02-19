package mapperInterface;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ncs.spring02.domain.BoardDTO;
import com.ncs.spring02.domain.MemberDTO;

import pageTest.Criteria;
import pageTest.SearchCriteria;

public interface MemberMapper {
	
	// ** JUnit TEST
	// => selectDTO
	// Mybatis와 참조형 매개변수 사용 비교를 위해
	@Select("select * from member where id=#{id}")
	MemberDTO selectDTO(MemberDTO dto);
	
	// selectParam
	@Select("select * from member where id=#{ii} AND jno=#{jno}")
	MemberDTO selectParam(@Param("ii") String id,@Param("jno") int jno);
	
	
	// ** board Check_List
	public List<BoardDTO> mCheckList(SearchCriteria cri);
	public int mCheckRowsCount(SearchCriteria cri);
	
	// mPageList
	List<MemberDTO> mPageList(Criteria cri);
	public int mtotalRowsCount(Criteria cri);

	// mSearchList
	List<MemberDTO> mSearchList(SearchCriteria cri);
	public int mSearchRowsCount(SearchCriteria cri);
		
	// selectList
	List<MemberDTO> selectList();

	// 리스트 멤버 출력을 위한 서비스추가
	List<MemberDTO> selectJoList(int jno);

	// selectOne
	MemberDTO selectOne(String id);

	// insert
	int insert(MemberDTO dto);

	// update
	int update(MemberDTO dto);

	// pwUpdate
	int pwUpdate(MemberDTO dto);
	
	
	// delete
	int delete(String id);
	
}
