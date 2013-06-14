package here.wait.photo.share.service;

import here.wait.photo.share.bean.User;

public interface UserService
{
	public static final int MSG_NAME_EXIT = 0;
	public static final int MSG_PWD_LENGTH_ERROR = 1;
	public static final int MSG_ADD_SUCCEED = 3;
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public String add(User user);
	/**
	 * 登录
	 * @param user
	 * @return json数据
	 */
	public String login(User user);
	public User query(String name);
	public String get(int id);
	public String headPicUpload(User user);
	/**
	 * 根据用户名查找用户(模糊查询)
	 * @param name
	 * @return
	 */
	public String search(String name, int pageIndex, int pageSize);
	
}
