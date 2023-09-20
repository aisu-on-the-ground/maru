package com.lab.aisu.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab.aisu.dao.BoardDAO;
import com.lab.aisu.dto.BoardVO;
import com.lab.aisu.dto.Criteria;
import com.lab.aisu.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO boardDao;

	@Override
	public BoardVO get(long bno) {
		return boardDao.read(bno);
	}

	@Override
	public List<BoardVO> getList(Criteria cri) {
		System.out.println("* * * * * 서비스 임플");
		return boardDao.getListWithPaging(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		return boardDao.getTotalCount(cri);
	}

	@Override
	public void register(BoardVO board) {
		boardDao.insertSelectKey(board);
	}

	@Override
	public boolean modify(BoardVO board) {
		return (boardDao.update(board) == 1);
	}

	@Override
	public boolean remove(long bno) {
		return (boardDao.delete(bno) == 1);
	}

}
