package dao;

import java.util.List;

public interface GenericDao<V, K> {
	public void insert(List<Object> objects) throws Exception;

	public List<Object> select(K key) throws Exception;

	public List<List<Object>> selectByParentId(K key) throws Exception;

	public List<List<Object>> selectAll() throws Exception;

	public void update(V vo) throws Exception;

	public void delete(K key) throws Exception;
}
