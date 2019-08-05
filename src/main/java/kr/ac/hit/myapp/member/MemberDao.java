package kr.ac.hit.myapp.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.ac.hit.myapp.comm.SearchInfo;

//mybatis-spring 연동 모듈이 이 인터페이스를 찾아서 구현체를 생성하도록 표시
@Mapper
public interface MemberDao {
	public int insert(MemberVo vo);
	public List<MemberVo> selectList(SearchInfo info);
	public MemberVo select(String memId);
	public int update(MemberVo vo);
	public int delete(MemberVo vo);
	public MemberVo selectLoginUser(MemberVo vo);
	public int selectCount(SearchInfo info);
}
