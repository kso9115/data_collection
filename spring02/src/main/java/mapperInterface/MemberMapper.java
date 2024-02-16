package mapperInterface;

import java.util.List;

import com.ncs.spring02.domain.BoardDTO;
import com.ncs.spring02.domain.MemberDTO;

import pageTest.Criteria;
import pageTest.SearchCriteria;

public interface MemberMapper {
	
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
