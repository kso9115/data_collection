package com.ncs.spring02.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ncs.spring02.domain.BoardDTO;
import com.ncs.spring02.domain.JoDTO;

// ** 게시판
//	=>CRUD 구현 
@Repository
public class BoardDAO {

	private static Connection cn = DBConnection.getConnection(); // 기본적으로 checked이기때문에 try~catch 예외처리 필수
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String sql;

	// 1. selectList
	public List<BoardDTO> selectList() {
		// 최신순으로 글을 가져오기위해 order by seq desc 내림차순
		// 답글달기를 추가한 후 출력 순서를 수정해줘야하므로 order by 변경
		sql = "select * from board order by root desc, step asc";
		// 전체 데이터를 담아줄 list 생성
		List<BoardDTO> list = new ArrayList<BoardDTO>();

		try {
			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery();

			// 데이터가 있을때만 담기위한 처리
			if (rs.next()) {
				do {
					BoardDTO dto = new BoardDTO();
					dto.setSeq(rs.getInt(1));
					dto.setId(rs.getString(2));
					dto.setTitle(rs.getString(3));
					dto.setContent(rs.getString(4));
					dto.setRegdate(rs.getString(5));
					dto.setCnt(rs.getInt(6));
					dto.setRoot(rs.getInt(7));
					dto.setStep(rs.getInt(8));
					dto.setIndent(rs.getInt(9));
					list.add(dto);
				} while (rs.next());
				return list;
			} else {
				System.out.println("BoardDAO SelectList Error => 출력할 자료가 업서");
				return null;
			}
		} catch (Exception e) {
			System.out.println("BoardDAO SelectList Error => " + e.toString());
		}
		return null;
	} // selectList

	// 2. selectOne
	public BoardDTO selectOne(int seq) {
		sql = "select * from board where seq = ?";

		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, seq);
			rs = pst.executeQuery();

			if (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setSeq(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getString(5));
				dto.setCnt(rs.getInt(6));
				dto.setRoot(rs.getInt(7));
				dto.setStep(rs.getInt(8));
				dto.setIndent(rs.getInt(9));
				System.out.println(dto);
				return dto;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("** BoardDAO selectJoOne Exception => " + e.toString());

		}
		return null;
	}

	// 3. insert : 원글 입력
	// => 입력 컬럼: id, title, content 
	//    default값: regdate, cnt, step, indent
	// => root : seq 와 동일한 값   
	// => Auto_Inc: seq (계산: auto 보다 IFNULL(max(seq)...) 를 적용)
	public int insert(BoardDTO dto) {
		sql = "insert into board values(\r\n"
				+ "(select * from (select ifNull(max(seq),0)+1 from board) as temp),\r\n"
				+ "?, ?, ?, CURRENT_TIMESTAMP ,0,\r\n"
				+ "(select * from (select ifNull(max(seq),0)+1 from board) as temp), 0, 0)";

		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getId());
			pst.setString(2, dto.getTitle());
			pst.setString(3, dto.getContent());
			
			System.out.println(pst);
			return pst.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(" ** Board Update Exception => " + e.toString());
			return 0;
		}
	}//insert
	
	// 3-1. replyInsert : 답글 입력
	// => seq : IFNULL 이용
	// => 입력 컬럼: id, title, content, root, step, indent
	// => JDBC subQuery 구문 적용시 주의사항
	//     -> MySql: select 구문으로 한번더 씌워 주어야함 (insert 의 경우에도 동일)   
	// => stepUpdate 가 필요함
	//    댓글 입력 성공후 실행
	//     -> 현재 입력된 답글의 step 값은 수정되지 않도록 sql 구문의 조건 주의
	// => boardList 의 출력순서를 확인하기
	//		-> ~~ order by root desc, step asc 
	public int rinsert(BoardDTO dto) {
		sql = "insert into board(seq,id,title,content,root,step,indent) values("
				+ " (select * from (select ifNull(max(seq),0)+1 from board) as temp), "
				+ " ?, ?, ?, ?, ?, ?) ";

		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getId());
			pst.setString(2, dto.getTitle());
			pst.setString(3, dto.getContent());
			pst.setInt(4, dto.getRoot());
			pst.setInt(5, dto.getStep());
			pst.setInt(6, dto.getIndent());
			
			pst.executeUpdate();	// 답글 등록 성공 : stepUpdate 추가적으로 진행해줘야함
			System.out.println(" ** stepUpdate Count => "+stepUpdate(dto));
			return 1;
			
		} catch (Exception e) {
			System.out.println(" ** Board replyInsert Exception => " + e.toString());
			return 0;
		}
	}//rinsert
	
	// stepUpdate : 증가를 위한 메서드(update문)
	// => 조건
	//	-> root가 동일해야하며 && step >= (step 값은 같거나 커야한다.) && 새 글은 제외되어야한다.
	public int stepUpdate(BoardDTO dto) {
		sql = "update board set step=step+1 where root=? and step>=? "
				//현재값을 찾기 위해서 서브쿼리 사용하기
				+ "and seq <> (select * from (select ifNull(max(seq),0) from board) as temp) ";
		try {
			pst=cn.prepareStatement(sql);
			pst.setInt(1, dto.getRoot());
			pst.setInt(2, dto.getStep());
			return pst.executeUpdate();	// 수정된 데이터의 갯수 리턴
			
		} catch (Exception e) {
			System.out.println(" ** Board stepUpdate Exception => " + e.toString());
			return 0;
		}
	}//stepUpdate
	

	// 4. update
	public int update(BoardDTO dto) {
		sql = "update board set seq=?, title=?, content=?, id=?, regdate=?, cnt=? where seq = ?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, dto.getSeq());
			pst.setString(2, dto.getTitle());
			pst.setString(3, dto.getContent());
			pst.setString(4, dto.getId());
			pst.setString(5, dto.getRegdate());
			pst.setInt(6, dto.getCnt());
			pst.setInt(7, dto.getSeq());

			if (pst.executeUpdate() > 0) {
				return pst.executeUpdate();
			} else {
				System.out.println("** Board 글 수정 실패 **");
				return 0;
			}
		} catch (Exception e) {
			System.out.println(" ** Board Update Exception => " + e.toString());
			return 0;
		}
	}// Update

	// 5. delete => 원글에 대한 삭제인지, 답글에 대한 삭제인지를 판별한 후 삭제해주어야함다.
	// => 답글 추가 후 : 원글과 답글의 구분이 필요하다.
	//	-> 원글 삭제 : where root=? (root가 동일할 때 삭제)
	//	-> 답글 삭제 : where seq=? (해당하는 답글만 삭제)
	public int delete(BoardDTO dto) {
		if(dto.getSeq()==dto.getRoot()) {	// 원글일 때
			// 원글이므로, root = seq이니까 pst.setInt(1,seq) 처리가능
			sql = " delete from board where root=? ";
			
		} else { // 답글일 때 삭제
			sql = " delete from board where seq=? ";
		}
		
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1,dto.getSeq());
			
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println(" ** Board Delete Exception => " + e.toString());
			return 0;
		}
	}//delete
}
