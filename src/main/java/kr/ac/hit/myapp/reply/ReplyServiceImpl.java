package kr.ac.hit.myapp.reply;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Resource
	private ReplyDao replyDao;
		
	@Override
	public int insert(ReplyVo vo) {
		return replyDao.insert(vo);
	}
}