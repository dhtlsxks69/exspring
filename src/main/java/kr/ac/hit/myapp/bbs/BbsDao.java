package kr.ac.hit.myapp.bbs;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.ac.hit.myapp.comm.PageInfo;

@Mapper
public interface BbsDao {
	public int insert(BbsVo vo);
	public List<BbsVo> selectList();
	public BbsVo select(int bbsNo);
	public int update(BbsVo vo);
	public int delete(BbsVo vo);
	public int selectCount();
	public List<BbsVo> selectList(PageInfo info);
}
