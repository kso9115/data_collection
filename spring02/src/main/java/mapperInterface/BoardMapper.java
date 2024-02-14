package mapperInterface;

import java.util.List;

import com.ncs.spring02.domain.BoardDTO;

public interface BoardMapper {
	
	public List<BoardDTO> selectList();
	// 2. selectOne
	public BoardDTO selectOne(int seq);
	
	// 3. insert
	public int insert(BoardDTO dto);
	// 3-1. replyInsert
	public int rinsert(BoardDTO dto);
	// 3-2. stepUpdate
	public int stepUpdate(BoardDTO dto);
	
	// 4. update
	public int update(BoardDTO dto);
	// 5. delete
	public int delete(BoardDTO dto);
}
