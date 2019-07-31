package kr.ac.hit.myapp.bbs;

import java.io.File;
import java.util.List;

public interface BbsService {
	public int insert(BbsVo vo);
	public List<BbsVo> selectList();
	public BbsVo select(int bbsNo);
	public int update(BbsVo vo);
	public int delete(BbsVo vo);
	public AttachVo selectAttach(int attNo);
	public File getAttachFile(AttachVo vo);
}
