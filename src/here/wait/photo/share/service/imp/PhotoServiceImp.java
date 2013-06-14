package here.wait.photo.share.service.imp;

import here.wait.photo.share.bean.DataInfoBean;
import here.wait.photo.share.bean.DataListBean;
import here.wait.photo.share.bean.LongData;
import here.wait.photo.share.bean.Photo;
import here.wait.photo.share.bean.SubscriptionRelation;
import here.wait.photo.share.dao.PhotoDAO;
import here.wait.photo.share.dao.SubscriptionRelationDAO;
import here.wait.photo.share.dao.UserLikePhotoDAO;
import here.wait.photo.share.service.PhotoService;
import here.wait.photo.share.utils.DistanceUtility;
import here.wait.photo.share.utils.GeohashUtility;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component("photoService")
public class PhotoServiceImp implements PhotoService
{
	private PhotoDAO photoDAO;
	private SubscriptionRelationDAO subscriptionRelationDAO;
	private UserLikePhotoDAO userLikePhotoDAO;

	public String upload(Photo photo)
	{
		DataInfoBean info;
		if (null != photo.getLongitude() && null != photo.getLatitude())
		{
			photo.setGeoHash(GeohashUtility.encode(photo.getLatitude(),
					photo.getLongitude()));
		}
		try
		{
			photoDAO.save(photo);
		} catch (Exception e)
		{
			e.printStackTrace();
			info = DataInfoBean.createErrorDataInfoBean("上传发生异常");
			return info.object2Json();
		}
		info = DataInfoBean.createSuccessDataInfoBean("上传成功", null);
		return info.object2Json();
	}

	public String getList(int userId, int pageIndex, int pageSize)
	{
		DataListBean<Photo> pb = photoDAO.getList(userId, pageIndex, pageSize);
		DataInfoBean info = DataInfoBean.createSuccessDataInfoBean("成功", pb);
		return info.object2Json();
	}

	public String getSubscriptionPhotoList(int userId, int pageIndex,
			int pageSize)
	{
		DataInfoBean db = DataInfoBean.createSuccessDataInfoBean("成功", null);
		List<SubscriptionRelation> subscriptionRelations = (List<SubscriptionRelation>) subscriptionRelationDAO
				.getSubscriptionRelationList(userId, 0, 0).getItems();
		if (subscriptionRelations.size() > 0)
		{
			Integer[] userIds = new Integer[subscriptionRelations.size()];
			for (int i = 0; i < userIds.length; i++)
			{
				userIds[i] = subscriptionRelations.get(i).getSubscriptionId();
			}

			DataListBean<Photo> data = photoDAO.getList(userIds, pageIndex,
					pageSize);
			db.setData(data);
		}

		return db.object2Json();
	}

	public String getNearbyPhotoList(int userId, double longitude,
			double latitude)
	{
		DataListBean<Photo> data = photoDAO.getNearbyPhotoList(longitude,
				latitude);
		List<Photo> list = (List<Photo>) data.getItems();
		for (Photo p : list)
		{
			int distance = (int) DistanceUtility.GetDistance(p.getLongitude(),
					p.getLatitude(), longitude, latitude);
			p.setDistance(distance);
			p.setLikeCount(userLikePhotoDAO.getUserCount(p.getId()));
			p.setIsLiked(null != userLikePhotoDAO.get(userId, p.getId()));
		}
		sort(list);

		DataInfoBean info = DataInfoBean.createSuccessDataInfoBean("成功", data);
		return info.object2Json();
	}

	private void sort(List<Photo> list)
	{
		for (int i = 0, len = list.size() - 1; i < len; i++)
		{
			for (int j = 0, len2 = list.size() - i - 1; j < len2; j++)
			{
				if (list.get(j).getDistance() > list.get(j + 1).getDistance())
				{
					Photo temp = list.get(j);
					list.set(j, list.get(j + 1));
					list.set(j + 1, temp);
				}
			}
		}
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

	public SubscriptionRelationDAO getSubscriptionRelationDAO()
	{
		return subscriptionRelationDAO;
	}

	@Resource
	public void setSubscriptionRelationDAO(
			SubscriptionRelationDAO subscriptionRelationDAO)
	{
		this.subscriptionRelationDAO = subscriptionRelationDAO;
	}

	public String getCount(int userId)
	{
		long count = photoDAO.getCount(userId);
		LongData data = new LongData();
		data.setNum(count);
		DataInfoBean info = new DataInfoBean();
		info.setCode(DataInfoBean.CODE_SUCCESS);
		info.setMessge("成功");
		info.setData(data);
		return info.object2Json();
	}

	public String getRandomBlowPhoto()
	{
		Photo data = photoDAO.getRandomBlowPhoto();
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
}
