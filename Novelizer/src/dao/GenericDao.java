package dao;

public interface GenericDao<V,K> {
	public void insert(V vo) throws Exception;
	public void select(K key) throws Exception;
	public void update(V vo) throws Exception;
	public void delete(K key) throws Exception;
}
