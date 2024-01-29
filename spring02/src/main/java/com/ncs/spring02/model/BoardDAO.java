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
		sql = "select * from board order by seq desc";
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

	// 3. insert
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
	}

	// 4. update
	public int update(BoardDTO dto) {
		sql = "update board set seq=?, title=?, content=?, id=?, regdate=? where seq = ?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, dto.getSeq());
			pst.setString(2, dto.getTitle());
			pst.setString(3, dto.getContent());
			pst.setString(4, dto.getId());
			pst.setString(5, dto.getRegdate());
			pst.setInt(6, dto.getSeq());

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

	// 5. delete
	public int delete(int seq) {
		return 0;
	}
}
