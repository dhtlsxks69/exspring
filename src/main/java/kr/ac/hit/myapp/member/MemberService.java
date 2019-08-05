package kr.ac.hit.myapp.member;

import java.io.File;
import java.util.List;

import kr.ac.hit.myapp.comm.SearchInfo;

public interface MemberService {
	public int insert(MemberVo vo);
	public List<MemberVo> selectList(SearchInfo info);
	public MemberVo select(String memId);
	public int update(MemberVo vo);
	public int delete(MemberVo vo);
	public MemberVo selectLoginUser(MemberVo vo);
	public File getImgFile(MemberVo vo);
	public int selectCount(SearchInfo info);
}
