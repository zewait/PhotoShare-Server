package here.wait.photo.share.service.imp;

import here.wait.photo.share.bean.DataInfoBean;
import here.wait.photo.share.bean.DataListBean;
import here.wait.photo.share.bean.StringData;
import here.wait.photo.share.bean.User;
import here.wait.photo.share.dao.UserDAO;
import here.wait.photo.share.service.UserService;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component("userService")
public class UserServiceImp implements UserService
{
	private UserDAO userDAO;

	public String add(User user)
	{
		DataInfoBean info;

		if (null == user || null == user.getName()
				|| 0 == user.getName().length())
		{
			info = DataInfoBean.createErrorDataInfoBean("请填写用户名");
			return info.object2Json();
		}
		if (null != userDAO.query(user.getName()))
		{
			info = DataInfoBean.createErrorDataInfoBean("用户名已存在");
			return info.object2Json();
		}
		if (null == user.getPassword() || user.getPassword().length() < 6)
		{
			info = DataInfoBean.createErrorDataInfoBean("密码长度要大于等于6");
			return info.object2Json();
		}
		userDAO.save(user);
		info = DataInfoBean.createSuccessDataInfoBean("注册成功", null);
		return info.object2Json();
	}

	public String login(User user)
	{
		DataInfoBean info;
		if (null == user || null == user.getName() || null == user.getName()
				|| 0 == user.getName().length())
		{
			info = DataInfoBean.createErrorDataInfoBean("请填写用户名");
			return info.object2Json();
		}
		if (null == user.getPassword() || 0 == user.getPassword().length())
		{
			info = DataInfoBean.createErrorDataInfoBean("请填写密码");
			return info.object2Json();
		}
		User u = query(user.getName());
		if(null==u)
		{
			info = DataInfoBean.createErrorDataInfoBean("无此用户");
			return info.object2Json();
		}
		if (!user.getPassword().equals(u.getPassword()))
		{
			info = DataInfoBean.createErrorDataInfoBean("密码错误");
			return info.object2Json();
		}
		info = DataInfoBean.createSuccessDataInfoBean("登录成功", u);
		return info.object2Json();
	}

	public String get(int id)
	{
		User user = userDAO.get(id);
		DataInfoBean info = DataInfoBean.createSuccessDataInfoBean("成功", user);
		return info.object2Json();
	}

	public User query(String name)
	{
		return userDAO.query(name);
	}

	public UserDAO getUserDAO()
	{
		return userDAO;
	}

	@Resource
	public void setUserDAO(UserDAO userDAO)
	{
		this.userDAO = userDAO;
	}

	public String headPicUpload(User user)
	{
		userDAO.headPicUpload(user);
		StringData data = new StringData();
		data.setData(user.getHeadPicSrc());
		DataInfoBean info = DataInfoBean.createSuccessDataInfoBean("成功", data);
		return info.object2Json();
	}

	public String search(String name, int pageIndex, int pageSize)
	{
		DataListBean<User> data = userDAO.search(name, pageIndex, pageSize);
		DataInfoBean info = DataInfoBean.createSuccessDataInfoBean("成功", data);
		return info.object2Json();
	}

}
