package kr.ac.hit.myapp.member;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class MemberVo {
	@NotNull @Size(min=4, max=50)
	private String memId;
	@NotNull @Size(min=4, max=50)
	private String memPass;
	@NotNull @Size(min=2, max=50)
	private String memName;
	@NotNull @Digits(integer=10, fraction=0) @PositiveOrZero //정수 10자리이하의 0또는 양수
	private int memPoint;
	
	private String memImg; //데이터베이스 테이블의 컬럼값을 주고 받기 위한 변수
	private MultipartFile multipartFile; //폼에서 전송되는 파일을 받기위한 변수
	
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
	public String getMemImg() {
		return memImg;
	}
	public void setMemImg(String memImg) {
		this.memImg = memImg;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemPass() {
		return memPass;
	}
	public void setMemPass(String memPass) {
		this.memPass = memPass;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public int getMemPoint() {
		return memPoint;
	}
	public void setMemPoint(int memPoint) {
		this.memPoint = memPoint;
	}
}
