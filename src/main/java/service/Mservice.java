package service;

import java.util.List;

import dao.MemberDao;
import domain.MemberVO;
import domain.ProductVO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Mservice {
	
	MemberDao dao;
	
	
	public void addMember(MemberVO vo) {
		dao.insertMember(vo);
	}


	public boolean loginService(MemberVO vo) {
		return dao.loginCheck(vo);
	}


	public MemberVO findOne(String id) {

		return dao.findMember(id);
	}


	public String addBasket(int pno, String id) {
		return dao.insertBasket(pno,id);
	}


	public List<ProductVO> findBasket(String id) {
		return dao.basketList(id);
	}


	public String getMemberGrade(String id) {
		return dao.findMemberGrade(id);
	}


	public ProductVO productDetail(int pno) {
		return dao.findOneProduct(pno);
	}


	public void chage(MemberVO vo) {
		 dao.changeM(vo);
	}


	public List<MemberVO> list() {
		return dao.memberList();
	}


	public void attend(String id) {
		dao.chaeckedDate(id);
	}


	public void banMember(String mno) {
		dao.ban(mno);
		
	}

	
	
	
	
	
}
