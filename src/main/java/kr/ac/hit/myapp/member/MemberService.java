package kr.ac.hit.myapp.member;

import java.util.List;

public interface MemberService {
	public int insert(MemberVo vo);
	public List<MemberVo> selectList();
	public MemberVo select(String memId);
	public int update(MemberVo vo);
	public int delete(MemberVo vo);
}
