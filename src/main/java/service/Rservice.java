package service;

import dao.RDao;
import domain.ReplVO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Rservice {
	
	RDao dao;

	public void addRepl(ReplVO vo) {
		dao.insertRepl(vo);
	}

	public void removeReple(int rno) {

		dao.deleteRepl(rno);
	}

	public void modRepl(ReplVO vo) {
		dao.mod(vo);
	}

}
