package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import domain.HashVO;
import domain.MemberVO;
import domain.ProductVO;
import domain.ReplVO;
import domain.productBill;
import util.ConnectionUtil;


public class ProductDao {
	private DataSource dataSource;
	
	public ProductDao() {
		dataSource = ConnectionUtil.getDatasource();
	}
	//리스트
	public List<ProductVO> findList() {
		
		String query = "select * from product_table order by like1 desc";
		List<ProductVO> list = new ArrayList<>();
		
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs =pstmt.executeQuery();
			){
			while (rs.next()) {
				ProductVO vo = ProductVO.builder()
						.pno(rs.getInt("pno")).name(rs.getString("name")).class1(rs.getString("class1"))
						.like1(rs.getInt("like1")).sellmount(rs.getInt("sellmount")).discount(rs.getInt("discount")).image(rs.getString("image")).currentmount(rs.getInt("currentmount")).price(rs.getInt("price")).content(rs.getString("content")).build();
						;
				list.add(vo);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;

	}
	//상세
	public ProductVO detail(int pno) {
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
	//입력
	public int insert(ProductVO vo) {
		
		String query="insert into product_table(pno,class1,like1,sellmount,discount,image,currentmount,price,content,name,writer) values(pnono_1.nextval,?,?,?,?,?,?,?,?,?,?)";
		int productNo =getNewPno();//번호 얻어오기
		
		try 
		(
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);	
				){
			pstmt.setString(1, vo.getClass1());
			pstmt.setInt(2, vo.getLike1());
			pstmt.setInt(3, vo.getSellmount());
			pstmt.setInt(4, vo.getDiscount());
			pstmt.setString(5, vo.getImage());
			pstmt.setInt(6, vo.getCurrentmount());
			pstmt.setInt(7, vo.getPrice());
			pstmt.setString(8, vo.getContent());
			pstmt.setString(9, vo.getName());
			pstmt.setString(10, vo.getWriter());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return productNo;
		
	}
	
	//번호
	private int getNewPno() {
		int pnum =0;
		String query ="select max(pno) as pnum from product_table";

		try(
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs =pstmt.executeQuery();
		) {
			if(rs.next()) {
				pnum =rs.getInt("pnum")+1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pnum;
	}

	//삭제
	public void remove(int pno) {
	
		String query ="delete from product_table where pno=?";
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				){
				pstmt.setInt(1, pno);
				pstmt.executeUpdate();
		} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
	
	//수정
	public void modify(ProductVO vo) {
		String image = vo.getImage();
		int pno = vo.getPno();
		String query ="update product_table set name=?,currentmount=?,price=?,content=?";
		if(image!=null) {
			query+=",image=? where pno=?";
		}else {
			query+=" where pno=?";
		}
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				){
					pstmt.setString(1, vo.getName());
					pstmt.setInt(2, vo.getCurrentmount());
					pstmt.setInt(3, vo.getPrice());
					pstmt.setString(4, vo.getContent());
					if(image!=null) {
						pstmt.setString(5,image);
						pstmt.setInt(6, pno);
					}else {
						pstmt.setInt(5, pno);
					}
					pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
	// 분류별 검색
	public List<ProductVO> search(String class1) {
		String query = "select * from product_table where class1=?";
		List<ProductVO> list = new ArrayList();
		
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				
			){
			pstmt.setString(1, class1);
			try(ResultSet rs =pstmt.executeQuery()){
			while (rs.next()) {
				ProductVO vo = ProductVO.builder()
						.pno(rs.getInt("pno")).name(rs.getString("name")).class1(rs.getString("class1"))
						.like1(rs.getInt("like1")).sellmount(rs.getInt("sellmount")).discount(rs.getInt("discount")).image(rs.getString("image")).currentmount(rs.getInt("currentmount")).price(rs.getInt("price")).content(rs.getString("content")).build();
						;
				list.add(vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	//좋아요
	public void like(int pno) {
		
		ProductVO vo = detail(pno);
		int like1 = vo.getLike1();
		like1 ++;
		
		String query ="update product_table set like1=? where pno=?";
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				){
					pstmt.setInt(1, like1);
					pstmt.setInt(2, pno);
					pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
		}
		
	}
	
	//결제 시도
	public String tryIt(String[] ids, String[] cnts, String[] pnos) {
		int point =findPoint(ids[0]);
		List<Integer> priceList = findPrice(pnos); 
		int findMount = findMount(ids[0]);
		int price =0;
		for (int i = 0; i < cnts.length; i++) {
			price +=priceList.get(i)*Integer.parseInt(cnts[i]);
		}
		String answer ="";
		if(point>=price) {//포인트가 높으면 결제
			String query ="update member_table1 set point=?, buymount=? where id=?";
			
			try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				){
					pstmt.setInt(1, point-price);
					pstmt.setInt(2, findMount+price);
					pstmt.setString(3, ids[0]);
					pstmt.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			answer ="결제 되었습니다.";
			answer +="남은 포인트는" + (point-price)+"입니다";
			
		}else {
			
			 answer ="잔액이 부족합니다";
			 
		}
		
		return answer;
	}
	
	//가격 불러오기
	private List<Integer> findPrice(String[] pnos) {
		List<Integer> list = new ArrayList<Integer>();
		for(String pno1:pnos) {
			int pno = Integer.parseInt(pno1);
			ProductVO vo = detail(pno);
			list.add(vo.getPrice());
		}
		return list;
	}

	//포인트 찾기
	private int findPoint(String id) {
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
		
		return vo.getPoint();
	}
	private int findMount(String id) {
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
		return vo.getBuymount();
	}
	
	//댓들 찾기
	public List<ReplVO> repleFind(int pno) {
		
		String query = "select * from repl_name where pno=? order by replyDate desc ";
		List<ReplVO> list = new ArrayList();
		
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				
			){	pstmt.setInt(1, pno);
			try(ResultSet rs =pstmt.executeQuery()){
				while (rs.next()) {
					ReplVO vo = ReplVO.builder().rno(rs.getInt("rno")). pno(rs.getInt("pno")).id(rs.getString("id")).replcontent(rs.getString("replcontent")).replStar(rs.getString("replStar")).replyDate(rs.getDate("replyDate")).build();
					list.add(vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	//물건 검색
	public List<ProductVO> serchProduct(List<HashVO> searchList) {
		
		String query = "select * from product_table where name like ?";
		List<ProductVO> list = new ArrayList();
		
		for(HashVO v: searchList) {
		
			try(
					Connection conn = dataSource.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(query);
					){
				pstmt.setString(1,"%"+v.getName()+"%");
				try(ResultSet rs=pstmt.executeQuery()){
					
					while(rs.next()) {
						ProductVO vo = ProductVO.builder()
								.pno(rs.getInt("pno")).name(rs.getString("name")).class1(rs.getString("class1"))
								.like1(rs.getInt("like1")).sellmount(rs.getInt("sellmount")).discount(rs.getInt("discount")).image(rs.getString("image")).currentmount(rs.getInt("currentmount")).price(rs.getInt("price")).content(rs.getString("content")).build();
						list.add(vo);
					}
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	public void addHash(int pno, String name, List<String> hashList) {
		String query="insert into hash_Tag(tagId,pno,name) values(?,?,?)";
		
		for(String hash: hashList) {
			try 
			(
					Connection conn = dataSource.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(query);	
					){
				pstmt.setString(1, hash);
				pstmt.setInt(2, pno);
				pstmt.setString(3, name);
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
	}
	
	
	public List<HashVO> findHashList(int pno) {
		String query = "select tagId from hash_Tag where pno=?";
		List<HashVO> hash = new ArrayList<>();
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				){
			pstmt.setInt(1, pno);
			try(ResultSet rs=pstmt.executeQuery()){
				while(rs.next()) {
					HashVO vo = HashVO.builder().tagId(rs.getString("tagId")).build();
					hash.add(vo);
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return hash ;
		
	}
	public List<HashVO> findHashList(String search) {
		String query = "select * from hash_Tag where tagId=?";
		List<HashVO> hash = new ArrayList<>();
		try(
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				){
			pstmt.setString(1, search);
			try(ResultSet rs=pstmt.executeQuery()){
				while(rs.next()) {
					HashVO vo = HashVO.builder().name(rs.getString("name")).pno(rs.getInt("pno")).build();
					hash.add(vo);
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return hash ;
	}
	public List<ReplVO> findreple() {
		String query = "select * from repl_name order by ReplStar desc";
		List<ReplVO> list = new ArrayList();
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs =pstmt.executeQuery();
			){
			while (rs.next()) {
				ReplVO vo = ReplVO.builder().pno(rs.getInt("pno")).id(rs.getString("id")).replStar(rs.getString("replStar")).replcontent(rs.getString("replcontent")).build();
				list.add(vo);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	
	}
	public void billWrite(String[] ids, String[] cnts, String[] pnos) {
		List<Integer> findPrice = findPrice(pnos);
		for (int i = 0; i < ids.length; i++) {
			String query = "insert into product_bill(pbno,id,pno,cnt,price) values(seq_bill.nextval,?,?,?,?)";
			try 
			(
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);	
					){
				pstmt.setString(1, ids[i]);
				pstmt.setString(2, pnos[i]);
				pstmt.setInt(3, Integer.parseInt(cnts[i]));
				pstmt.setInt(4, findPrice.get(i));
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	public List<productBill> billList() {
		List<productBill> billList = new ArrayList<productBill>();
		String query = "select * from product_bill";
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs =pstmt.executeQuery();
			){
			while (rs.next()) {
				productBill vo = productBill.builder().pbno(rs.getInt("pbno")).id(rs.getString("id")).cnt(rs.getString("cnt")).pno(rs.getInt("pno")).price(rs.getInt("price")).build();
				billList.add(vo);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return billList;
	}
	public void billDel(int pbno) {
		String query ="delete from product_bill where pbno=?";
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				){
				pstmt.setInt(1, pbno);
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
		}
	}
	public List<productBill> oneBill(String id) {
		List<productBill> billList = new ArrayList<productBill>();
		String query = "select * from product_bill where id=? ";
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				
			){
				pstmt.setString(1, id);
			try(ResultSet rs =pstmt.executeQuery();){
				if(rs.next()) {
					productBill vo = productBill.builder().pbno(rs.getInt("pbno")).id(rs.getString("id")).cnt(rs.getString("cnt")).pno(rs.getInt("pno")).price(rs.getInt("price")).build();
					billList.add(vo);
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return billList;
	}
	public void backPoint(String id, String cnt, int price) {
		String query ="update member_table1 set point=?, buymount=? where id=?";
		int point = findPoint(id);
		int mount = findMount(id);
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			){
				pstmt.setInt(1, point+(price*Integer.parseInt(cnt)));
				pstmt.setInt(2, mount-(price*Integer.parseInt(cnt)));
				pstmt.setString(3, id);
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
	




