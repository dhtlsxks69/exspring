package kr.ac.hit.myapp.member;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.hit.myapp.comm.SearchInfo;

//@Component @Controller @Service @Repositoty 중 하나를 붙여서 스프링에 등록
@Service
public class MemberServiceImpl implements MemberService{
	@Resource
	private MemberDao memberDao;
	
	//게시판 첨부파일을 저장할 디렉토리 경로
	private String uploadImgDir = "C:/Temp/profile";
	{
		//uploadDir에 지정된 디렉토리가 없으면 만들기
		new File(uploadImgDir).mkdirs();
	}
	
	@Override
	public int insert(MemberVo vo) {
		MultipartFile f = vo.getMultipartFile();
		if(f != null && f.isEmpty() == false) { //파일을 받았다면,
			//파일을 서버의 파일시스템(하드디스크)에 저장
			String orgName = f.getOriginalFilename(); //원래파일명 "xxx.xxx.png"
			int idx = orgName.lastIndexOf('.'); //원래파일명에서 마지막 .의 위치
			String ext = idx < 0 ? "" : orgName.substring(idx); //마지막.이후의 문자열 == 확장자
			String newName = vo.getMemId()+ext; //저장하는 파일명
			File saveFile = new File(uploadImgDir, newName); //저장될 파일 정보
			try {
				f.transferTo(saveFile); //MultipartFile f의 내용을 saveFile에 복사
//				Files.copy(f.getInputStream(), Paths.get(saveFile.getAbsolutePath())); //JAVA API를 사용
//			    FileCopyUtils.copy(f.getInputStream(), new FileOutputStream(saveFile)); //Spring 사용
				vo.setMemImg(newName); //하드디스크에 저장한 파일명
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("프로필 이미지 파일 저장 실패", e);
			}
		}
		int num = memberDao.insert(vo);
		return num;
	}

	@Override
	public List<MemberVo> selectList(SearchInfo info) {
		List<MemberVo> list = memberDao.selectList(info);
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

	@Override
	public MemberVo selectLoginUser(MemberVo vo) {
		return memberDao.selectLoginUser(vo);
	}

	@Override
	public File getImgFile(MemberVo vo) {
		return new File(uploadImgDir, vo.getMemImg());
	}

	@Override
	public int selectCount(SearchInfo info) {
		return memberDao.selectCount(info);
	}
}
