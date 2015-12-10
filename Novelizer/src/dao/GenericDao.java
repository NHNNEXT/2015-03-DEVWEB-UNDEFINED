package dao;

import java.util.List;

public interface GenericDao<V> {
	public int insert(List<Object> objects) throws Exception;

	public List<Object> select(int key) throws Exception;

	public List<List<Object>> selectByParentId(int key) throws Exception;

	public List<List<Object>> selectAll() throws Exception;

	public void update(V vo) throws Exception;

	public void delete(int key) throws Exception;
}
