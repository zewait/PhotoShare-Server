package here.wait.photo.share.dao.imp;

import here.wait.photo.share.bean.DataListBean;
import here.wait.photo.share.bean.Photo;
import here.wait.photo.share.dao.PhotoDAO;
import here.wait.photo.share.utils.GeohashUtility;
import here.wait.photo.share.utils.ImageZoomUtility;
import here.wait.photo.share.utils.Utilities;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.fileupload.FileItem;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component("photoDAO")
public class PhotoDAOImp implements PhotoDAO
{
	private HibernateTemplate hibernateTemplate;

	public void save(Photo photo) throws Exception
	{
		FileItem fi = photo.getPhoto();
		//不为null就保存图片
		if (null != fi)
		{
			File path = new File(photo.getPath());
			if (!path.exists())
			{
				path.mkdirs();
			}
			File saveFile = new File(photo.getHardDiskPath());
			if (!saveFile.exists())
				saveFile.createNewFile();
			fi.write(saveFile);
			
			ImageZoomUtility zoom = new ImageZoomUtility();
			zoom.setIntputPath(photo.getHardDiskPath());
			zoom.setOutputPath(photo.getHardDiskZoomPath());
			zoom.zoomPicture();
		}
		hibernateTemplate.save(photo);
	}

	@SuppressWarnings("all")
	public DataListBean<Photo> getList(final Integer[] userIds, int pageIndex,
			final int pageSize)
	{
		List<Photo> photos;
		final String hql = "from Photo p where p.user.id in(:userId) order by p.createTime desc";
		final String countHql = "select count(*) from Photo p where p.user.id in(:userId)";

		long count = (Long) hibernateTemplate.executeFind(
				new HibernateCallback()
				{
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException
					{
						Query query = session.createQuery(countHql);
						query.setParameterList("userId", userIds);
						List list = query.list();
						return list;
					}
				}).get(0);
		long countPage = 0;
		if (0 == pageIndex || 0 == pageSize)
		{
			photos = hibernateTemplate.executeFind(new HibernateCallback()
			{
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException
				{
					Query query = session.createQuery(hql);
					query.setParameterList("userId", userIds);
					List list = query.list();
					return list;
				}
			});
		} else
		{
			final int first = (pageIndex - 1) * pageSize;
			countPage = (count + pageSize - 1) / pageSize;
			photos = hibernateTemplate.executeFind(new HibernateCallback()
			{
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException
				{
					Query query = session.createQuery(hql);
					query.setParameterList("userId", userIds);
					query.setFirstResult(first);
					query.setMaxResults(pageSize);
					List list = query.list();
					return list;
				}
			});
		}

		DataListBean<Photo> pb = new DataListBean<Photo>();
		pb.setItems(photos);
		pb.setPageCount(countPage);
		pb.setPageIndex((pageIndex > countPage ? countPage : pageIndex));
		pb.setPageSize((pageSize == 0 ? count : pageSize));
		return pb;
	}

	@SuppressWarnings("unchecked")
	public DataListBean<Photo> getList(int userId, int pageIndex,
			final int pageSize)
	{
		List<Photo> photos;
		final String hql = "from Photo p where p.user.id=" + userId
				+ " order by p.id desc";

		long count = getCount(userId);
		long countPage = 0;

		if (0 == pageIndex || 0 == pageSize)
		{
			photos = hibernateTemplate.find(hql);
		} else
		{
			final int first = (pageIndex - 1) * pageSize;
			countPage = (count + pageSize - 1) / pageSize;
			photos = hibernateTemplate.executeFind(new HibernateCallback()
			{
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException
				{
					Query query = session.createQuery(hql);
					query.setFirstResult(first);
					query.setMaxResults(pageSize);
					List list = query.list();
					return list;
				}
			});
		}

		DataListBean<Photo> pb = new DataListBean<Photo>();
		pb.setItems(photos);
		pb.setPageCount(countPage);
		pb.setPageIndex((pageIndex > countPage ? countPage : pageIndex));
		pb.setPageSize((pageSize == 0 ? count : pageSize));
		return pb;
	}

	public DataListBean<Photo> getNearbyPhotoList(double longitude,
			double latitude)
	{
		DataListBean<Photo> data = new DataListBean<Photo>();
		String geoHash = GeohashUtility.encode(latitude, longitude);
		System.out.println(longitude + ", " + latitude);
		System.out.println("geo: " + geoHash);
		String subGeoHash = geoHash.substring(0, 4);
		String hql = "from Photo p where p.geoHash like '" + subGeoHash + "%'";
		System.out.println("hql:" + hql);
		try
		{
			List<Photo> photos = hibernateTemplate.find(hql);
			data.setItems(photos);
			data.setPageSize(photos.size());
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		data.setPageCount(0);
		data.setPageCount(0);
		return data;
	}

	public Photo get(int photoId)
	{
		return hibernateTemplate.get(Photo.class, photoId);
	}

	public HibernateTemplate getHibernateTemplate()
	{
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate)
	{
		this.hibernateTemplate = hibernateTemplate;
	}

	public long getCount(int userId)
	{
		String hql = "select count(*) from Photo p where p.user.id=" + userId;
		long count = (Long) hibernateTemplate.find(hql).get(0);
		return count;
	}

	public Photo getRandomBlowPhoto()
	{
		String hql = "from Photo p where p.isBlow=" + true;
		List<Photo> list = hibernateTemplate.find(hql);
		
		int ran = Utilities.getRandom().nextInt(list.size());
		return list.get(ran);
	}

}
