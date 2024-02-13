package mapperInterface;

import java.util.List;

import com.ncs.spring02.domain.MemberDTO;

public interface MemberMapper {
	
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
