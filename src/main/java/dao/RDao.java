package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import domain.ReplVO;
import util.ConnectionUtil;

public class RDao {
	private DataSource dataSource;
	
	public RDao() {
		dataSource = ConnectionUtil.getDatasource();
	}

	//댓글 입력
	public void insertRepl(ReplVO vo) {

		String query="insert into repl_name(rno,pno,replcontent,id,ReplStar) values(seq_repl.nextval,?,?,?,?)";
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				){
				pstmt.setInt(1, vo.getPno());
				pstmt.setString(2, vo.getReplcontent());
				pstmt.setString(3, vo.getId());
				pstmt.setString(4, vo.getReplStar());
				pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//댓글 삭제
	public void deleteRepl(int rno) {
		
		String query ="delete from repl_name where rno=?";
		
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			){
				pstmt.setInt(1, rno);
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
		}
		
	}

	//댓글 수정
	public void mod(ReplVO vo) {
		
		String query ="update repl_name set replcontent=? where rno=?";
		
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				){
					pstmt.setString(1, vo.getReplcontent());
					pstmt.setInt(2, vo.getRno());
					pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}


}
