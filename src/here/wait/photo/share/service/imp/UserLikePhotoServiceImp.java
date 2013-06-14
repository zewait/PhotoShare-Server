package here.wait.photo.share.service.imp;

import here.wait.photo.share.bean.BooleanData;
import here.wait.photo.share.bean.DataInfoBean;
import here.wait.photo.share.bean.DataListBean;
import here.wait.photo.share.bean.LongData;
import here.wait.photo.share.bean.Photo;
import here.wait.photo.share.bean.UserLikePhoto;
import here.wait.photo.share.dao.PhotoDAO;
import here.wait.photo.share.dao.UserDAO;
import here.wait.photo.share.dao.UserLikePhotoDAO;
import here.wait.photo.share.service.UserLikePhotoService;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component("userLikePhotoService")
public class UserLikePhotoServiceImp implements UserLikePhotoService
{
	private UserLikePhotoDAO userLikePhotoDAO;
	private UserDAO userDAO;
	private PhotoDAO photoDAO;

	public String add(int userId, int photoId)
	{

		DataInfoBean info;
		// 不存在此关系,添加
		if (null == userLikePhotoDAO.get(userId, photoId)
				&& null != userDAO.get(userId) && null != photoDAO.get(photoId))
		{
			userLikePhotoDAO.add(userId, photoId);
			info = DataInfoBean.createSuccessDataInfoBean("成功", null);
			
		}
		else
		{
			info = DataInfoBean.createErrorDataInfoBean("失败");
		}
		return info.object2Json();
	}

	public String delete(int userId, int photoId)
	{
		userLikePhotoDAO.delete(userId, photoId);
		DataInfoBean info = DataInfoBean.createSuccessDataInfoBean("成功", null);
		return info.object2Json();
	}

	public String getPhotoCount(int userId)
	{
		LongData data = new LongData();
		data.setNum(userLikePhotoDAO.getPhotoCount(userId));
		DataInfoBean info = DataInfoBean.createSuccessDataInfoBean("成功", data);
		return info.object2Json();
	}

	public String getUserCount(int photoId)
	{
		LongData data = new LongData();
		data.setNum(userLikePhotoDAO.getUserCount(photoId));
		DataInfoBean info = DataInfoBean.createSuccessDataInfoBean("成功", data);
		return info.object2Json();
	}

	public String getPhotoList(int userId, int pageIndex, int pageSize)
	{
		DataListBean<Photo> data = userLikePhotoDAO.getPhotoList(userId,
				pageIndex, pageSize);
		DataInfoBean info = DataInfoBean.createSuccessDataInfoBean("成功", data);
		return info.object2Json();
	}
	
	public String isLiked(int userId, int photoId)
	{
		UserLikePhoto userLikePhoto = userLikePhotoDAO.get(userId, photoId);
		BooleanData data = new BooleanData();
		data.setData(null!=userLikePhoto);
		DataInfoBean info = DataInfoBean.createSuccessDataInfoBean("成功", data);
		return info.object2Json();
	}

	public UserLikePhotoDAO getUserLikePhotoDAO()
	{
		return userLikePhotoDAO;
	}

	@Resource
	public void setUserLikePhotoDAO(UserLikePhotoDAO userLikePhotoDAO)
	{
		this.userLikePhotoDAO = userLikePhotoDAO;
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

	public PhotoDAO getPhotoDAO()
	{
		return photoDAO;
	}

	@Resource
	public void setPhotoDAO(PhotoDAO photoDAO)
	{
		this.photoDAO = photoDAO;
	}

	

}
