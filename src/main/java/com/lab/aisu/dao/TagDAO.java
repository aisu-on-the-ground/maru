package com.lab.aisu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.lab.aisu.dto.TagDTO;

@Mapper
public interface TagDAO {

	public int insertTag(TagDTO dto);

	public TagDTO selectTag(int tagNo);

	public int updateTag(TagDTO dto);

	public List<TagDTO> selectTagList(int memberNo);

	public TagDTO selectSameTagName(Map<String, Object> map);

}
