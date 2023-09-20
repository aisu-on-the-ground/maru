package com.lab.aisu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.lab.aisu.dto.BoardVO;
import com.lab.aisu.dto.Criteria;

@Mapper
public interface BoardDAO {

	public List<BoardVO> getList();

	public List<BoardVO> getListWithPaging(Criteria cri);

	public int getTotalCount(Criteria cri);

	public void insert(BoardVO board);

	public long insertSelectKey(BoardVO board);

	public BoardVO read(long bno);

	public int delete(long bno);

	public int update(BoardVO board);

	public List<BoardVO> getSearchList(Map<String, Object> map);
}