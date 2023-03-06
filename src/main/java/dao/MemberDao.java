package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import domain.MemberVO;
import domain.ProductVO;
import util.ConnectionUtil;

public class MemberDao {

	private DataSource dataSource ;
	public MemberDao() {
		dataSource = ConnectionUtil.getDatasource();
	}
	
	//입력
	public void insertMember(MemberVO vo) {
		String query="insert into member_table1(mno,name,id,pwd,addr,buymount,email,tell,point,basket) values(mnono_1.nextval,?,?,?,?,?,?,?,?,?)";
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				){
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getId());
				pstmt.setString(3, vo.getPwd());
				pstmt.setString(4, vo.getAddr());
				pstmt.setInt(5, 0);
				pstmt.setString(6, vo.getEmail());
				pstmt.setInt(7, vo.getTell());
				pstmt.setInt(8, 0);
				pstmt.setString(9, "b");
				pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//로그인
	public boolean loginCheck(MemberVO vo) {
		boolean result = false;
		String query ="select decode(count(*),1,'true','false') as result from member_table1 where id=? and pwd=?";
		try 
		(
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				
				)
		{
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			try(ResultSet rs=pstmt.executeQuery()){
				if(rs.next()) {
					result =Boolean.parseBoolean(rs.getString("result"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	//맴버 찾기
	public MemberVO findMember(String id) {
		MemberVO vo = null;
		String query = "select * from member_table1 where id=?";
		
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				){
			pstmt.setString(1, id);
			try(ResultSet rs=pstmt.executeQuery()){
				if(rs.next()) {
					vo = MemberVO.builder().mno(rs.getInt("mno"))
							.name(rs.getString("name"))
							.id(id)
							.addr(rs.getString("addr"))
							.buymount(rs.getInt("buymount"))
							.email(rs.getString("email"))
							.tell(rs.getInt("tell"))
							.point(rs.getInt("point"))
							.basket(rs.getString("basket")).
							joinDate(rs.getDate("joinDate")).build();
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	
		return vo;
	}

	//장바구니
	public String insertBasket(int pno, String id) {
		MemberVO vo = findMember(id);
		String basket = "";
		
		basket = vo.getBasket();
		if(basket.length()>1) {
			String baksetCheck ="";
			baksetCheck= basket.substring(1);
			String[] basketSplit = baksetCheck .split("!");
			for(String a: basketSplit) {
				if(Integer.parseInt(a)==pno) {
					return "이미 추가하신 상품입니다";
				}
			}
		}
		basket += pno+"!";
		
		String query ="update member_table1 set basket=? where id=?";
		
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				){
				pstmt.setString(1, basket);
				pstmt.setString(2, id);
				pstmt.executeUpdate();	
			pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
		}
		
		return "추가를 완료하였습니다";
		
	}

	//장바구니 리스트
	public List<ProductVO> basketList(String id) {
		MemberVO vo = findMember(id);
		String basket = "";
		List<ProductVO> pvo = new ArrayList<ProductVO>();
		List<Integer> plist = new ArrayList<>();
		basket = vo.getBasket();
		
		if(basket.length()>1) {
			basket= basket.substring(1);
			String[] basketSplit = basket.split("!");
			for(String a: basketSplit) {
				int int1 = Integer.parseInt(a);
				plist.add(int1);
			}
		}
		
		if(plist.isEmpty()) {
			return pvo;
		}else {
			for(int a: plist) {
				String query = "select * from product_table where pno=?";
				try (
					Connection conn = dataSource.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(query);
					){
					pstmt.setInt(1, a);
					try(ResultSet rs =pstmt.executeQuery();){
						while (rs.next()) {
							ProductVO provo = ProductVO.builder().writer(rs.getString("writer"))
									.pno(rs.getInt("pno")).name(rs.getString("name")).class1(rs.getString("class1"))
									.like1(rs.getInt("like1")).sellmount(rs.getInt("sellmount")).discount(rs.getInt("discount")).image(rs.getString("image")).currentmount(rs.getInt("currentmount")).price(rs.getInt("price")).content(rs.getString("content")).build();
									;
							pvo.add(provo);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
			return pvo;
		}
		
		
		
	}
	
	//회원등급 (운영자만 설정함)
	public String findMemberGrade(String id) {
		String grade =null;
		String query ="select grade from member_table1 where id=?";
		
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				){
				pstmt.setString(1, id);
				try(ResultSet rs =pstmt.executeQuery()){
					if(rs.next()) grade =rs.getString("grade");
				}
			pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
		}
	
		return grade;
	}

	//디테일
	public ProductVO findOneProduct(int pno) {
		
		ProductVO vo =null;
		String query ="select * from product_table where pno=?";
		
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			){
				pstmt.setInt(1, pno);
				try(ResultSet rs =pstmt.executeQuery();){
					if(rs.next()) {
						vo = ProductVO.builder().writer(rs.getString("writer")).pno(rs.getInt("pno")).name(rs.getString("name")).class1(rs.getString("class1"))
								.like1(rs.getInt("like1")).sellmount(rs.getInt("sellmount")).discount(rs.getInt("discount")).image(rs.getString("image")).currentmount(rs.getInt("currentmount")).price(rs.getInt("price")).content(rs.getString("content")).build();
					}
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return vo;
		
		
	}

	//수정
	public void changeM(MemberVO vo) {
		String query ="update member_table1 set pwd=?,addr=?,email=?,tell=? where mno=?";
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				){
					pstmt.setString(1, vo.getPwd());
					pstmt.setString(2, vo.getAddr());
					pstmt.setString(3, vo.getEmail());
					pstmt.setInt(4, vo.getTell());
					pstmt.setInt(5, vo.getMno());
					pstmt.executeUpdate();
		} catch (Exception e) {
				e.printStackTrace();
		}
	
	}

	//리스트(운영자 전용)
	public List<MemberVO> memberList() {
		String query = "select * from member_table1 order by mno desc";
		List<MemberVO> list = new ArrayList();
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs =pstmt.executeQuery();
			){
				while (rs.next()) {
					String grade = rs.getString("grade");
					System.out.println(grade);
					if(grade ==null) {
					MemberVO vo = MemberVO.builder().mno(rs.getInt("mno")).name(rs.getString("name")).addr(rs.getString("addr")).buymount(rs.getInt("buymount")).
							joinDate(rs.getDate("joinDate")).build();
					list.add(vo);
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void chaeckedDate(String id) {
		String query="insert into loginDateCheck (id) values(?)";
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				){
				pstmt.setString(1, id);
				pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void ban(String mno) {
		String query = "delete from member_table1 where mno=?";
		try(
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			){
			pstmt.setString(1, mno);
			pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

}
