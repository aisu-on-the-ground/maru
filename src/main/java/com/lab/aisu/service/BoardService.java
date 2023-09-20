package com.lab.aisu.service;

import java.util.List;

import com.lab.aisu.dto.BoardVO;
import com.lab.aisu.dto.Criteria;

public interface BoardService {

	public BoardVO get(long bno);

//	public List<BoardVO> getList();
	public List<BoardVO> getList(Criteria cri);
	
	public int getTotal(Criteria cri);

	public void register(BoardVO board);

	public boolean modify(BoardVO board);

	public boolean remove(long bno);

}
