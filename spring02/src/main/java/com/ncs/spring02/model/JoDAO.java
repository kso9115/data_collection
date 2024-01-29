package com.ncs.spring02.model;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.ncs.spring02.domain.JoDTO;
import com.ncs.spring02.domain.MemberDTO;

@Repository
public class JoDAO {

	private static Connection cn = DBConnection.getConnection(); // 기본적으로 checked이기때문에 try~catch 예외처리 필수
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String sql;

	// 1) joList : selectList
	public List<JoDTO> selectList() {
		sql = "select * from jo";
		List<JoDTO> list = new ArrayList<JoDTO>();

		try {
			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery();
			// => 결과의 출력 여부
			if (rs.next()) {
				do {
					// setter
					JoDTO jodto = new JoDTO();
					jodto.setJno(rs.getInt(1));
					jodto.setJname(rs.getString(2));
					jodto.setCaptain(rs.getString(3));
					jodto.setProject(rs.getString(4));
					jodto.setSlogan(rs.getString(5));

					list.add(jodto);

				} while (rs.next());
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			// 에러가 발생했을 때에도 값이 존재하지 않으니까 null 반환해줘야한다.
			System.out.println("** selectJoList Exception => " + e.toString());
			return null;
		}
	}


	// 2. joDetail
	public JoDTO selectOne(int jno) {
		sql = "select * from jo where jno = ?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, jno);
			rs = pst.executeQuery();

			if (rs.next()) {
				JoDTO jodto = new JoDTO();
				jodto.setJno(rs.getInt(1));
				jodto.setJname(rs.getString(2));
				jodto.setCaptain(rs.getString(3));
				jodto.setProject(rs.getString(4));
				jodto.setSlogan(rs.getString(5));
				return jodto;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("** selectJoOne Exception => " + e.toString());
			return null;
		}
	}

	// 3. joInsert
	public int insert(JoDTO jdto) {
		sql = "insert into jo values(?,?,?,?,?)";
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, jdto.getJno());
			pst.setString(2, jdto.getJname());
			pst.setString(3, jdto.getCaptain());
			pst.setString(4, jdto.getProject());
			pst.setString(5, jdto.getSlogan());

			System.out.println("성공");
			return pst.executeUpdate();
			// int를 반환할 때 사용하는 메서드
		} catch (Exception e) {
			System.out.println(" ** joInsert Exception => " + e.toString());
			return 0;
		}
	}

	// 4. joUpdate
	public int update(JoDTO jodto) {
		sql = "update jo set jno=?, jname=?, captain=?, project=?, slogan=? where jno = ?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, jodto.getJno());
			pst.setString(2, jodto.getJname());
			pst.setString(3, jodto.getCaptain());
			pst.setString(4, jodto.getProject());
			pst.setString(5, jodto.getSlogan());
			pst.setInt(6, jodto.getJno());

			if (pst.executeUpdate() > 0) {
				return pst.executeUpdate();
			} else {
				System.out.println("** 조 데이터 업데이트 실패 **");
				return 0;
			}
		} catch (Exception e) {
			System.out.println(" ** joUpdate Exception => " + e.toString());
			return 0;
		}
	}// joUpdate

	// 6) delete
	public int delete(int jno) {
		sql = "delete from jo where jno=?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, jno);

			return pst.executeUpdate();

		} catch (Exception e) {
			System.out.println(" ** JO deleteList Exception => " + e.toString());
			return 0;
		}
	} // delete

}
