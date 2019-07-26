package kr.ac.hit.myapp.member;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

//@Component @Controller @Service @Repositoty 중 하나를 붙여서 스프링에 등록
@Service
public class MemberServiceImpl implements MemberService{
	@Resource
	private MemberDao memberDao;
	
	@Override
	public int insert(MemberVo vo) {
		int num = memberDao.insert(vo);
		return num;
	}

	@Override
	public List<MemberVo> selectList() {
		List<MemberVo> list = memberDao.selectList();
		return list;
	}

	@Override
	public MemberVo select(String memId) {
		MemberVo vo = memberDao.select(memId);
		return vo;
	}

	@Override
	public int update(MemberVo vo) {
		int num = memberDao.update(vo);
		return num;
	}

	@Override
	public int delete(MemberVo vo) {
		int num = memberDao.delete(vo);
		return num;
	}
}
