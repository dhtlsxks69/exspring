package kr.ac.hit.myapp.bbs;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class BbsServiceImpl implements BbsService{
	@Resource
	private BbsDao bbsDao;
	
	@Override
	public int insert(BbsVo vo) {
		return bbsDao.insert(vo);
	}

	@Override
	public List<BbsVo> selectList() {
		return bbsDao.selectList();
	}

	@Override
	public BbsVo select(int bbsNo) {
		return bbsDao.select(bbsNo);
	}

	@Override
	public int update(BbsVo vo) {
		return bbsDao.update(vo);
	}

	@Override
	public int delete(int bbsNo) {
		return bbsDao.delete(bbsNo);
	}
}
