package kr.ac.hit.myapp.prod;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class ProdServiceImpl implements ProdService{
	@Resource
	private ProdDao prodDao;

	@Override
	public int insert(ProdVo vo) {
		return prodDao.insert(vo);
	}

	@Override
	public List<ProdVo> selectList() {
		return prodDao.selectList();
	}

	@Override
	public ProdVo select(int prodNo) {
		return prodDao.select(prodNo);
	}

	@Override
	public int update(ProdVo vo) {
		return prodDao.update(vo);
	}

	@Override
	public int delete(int prodNo) {
		return prodDao.delete(prodNo);
	}
}
