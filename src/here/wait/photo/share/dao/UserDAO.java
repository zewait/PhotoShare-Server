package here.wait.photo.share.dao;

import here.wait.photo.share.bean.DataListBean;
import here.wait.photo.share.bean.User;

public interface UserDAO
{
	public void save(User user);
	public User query(String name);
	public User get(int id);
	public void headPicUpload(User user);
	/**
	 * 根据用户名搜索用户(模糊查询)
	 * @param name
	 * @return
	 */
	public DataListBean<User> search(String name, int pageIndex, int pageSize);
}
