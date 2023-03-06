package service;

import java.util.List;

import dao.ProductDao;
import domain.HashVO;
import domain.ProductVO;
import domain.ReplVO;
import domain.productBill;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Service {
	
	private ProductDao dao ;
	
	public List<ProductVO> list() {
		return dao.findList();
	}

	public ProductVO detail(int pno) {
		return dao.detail(pno);
	}

	public int addProduct(ProductVO vo) {
		return dao.insert(vo);
	}

	public void delProduct(int no) {
		 dao.remove(no);
	}

	public void modProduct(ProductVO vo) {
		dao.modify(vo);
	}

	public List<ProductVO> findList(String class1) {

		return dao.search(class1);
	}

	public void likeThis(int pno) {

		dao.like(pno);
	}

	public String buyTry(String[] ids, String[] cnts, String[] pnos) {
		return dao.tryIt(ids,cnts,pnos);
	}

	public List<ReplVO> ReflDetail(int pno) {
		return dao.repleFind(pno);
	}

	public List<ProductVO> findSearch(List<HashVO> searchList) {
		return dao.serchProduct(searchList);
	}

	public void addHash(int pno, String name, List<String> hashList) {
		dao.addHash(pno, name, hashList);
	}

	public List<HashVO> getHash(int pno) {
		return dao.findHashList(pno);
	}

	public List<HashVO> getHashName(String search) {
		return dao.findHashList(search);
	}
	public List<ReplVO> getreple() {
		return dao.findreple();
	}

	public void bill(String[] ids, String[] cnts, String[] pnos) {
		dao.billWrite(ids,cnts,pnos);
		
	}

	public List<productBill> getBill() {
		return dao.billList();
		
	}

	public void billCancle(int pbno) {
		dao.billDel(pbno);
	}

	public List<productBill> ownBill(String id) {
		return dao.oneBill(id);
	}

	public void feedbackPoint(String id, String cnt, int price) {
		 dao.backPoint(id,cnt,price);
	}



}
