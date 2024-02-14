package mapperInterface;

import java.util.List;

import com.ncs.spring02.domain.JoDTO;

public interface JoMapper {
	
	List<JoDTO> selectList();

	JoDTO selectOne(int jno);

	int insert(JoDTO jdto);

	int update(JoDTO jdto);

	int delete(int jno);

}
