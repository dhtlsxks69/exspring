package kr.ac.hit.myapp.prod;

import java.util.List;

public interface ProdService {
	public int insert(ProdVo vo);
	public List<ProdVo> selectList();
	public ProdVo select(int prodNo);
	public int update(ProdVo vo);
	public int delete(int prodNo);
}
