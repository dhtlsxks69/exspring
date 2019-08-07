package kr.ac.hit.myapp.reply;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyDao {
	public int insert(ReplyVo vo);
}