package here.wait.photo.share.dao.imp;

import here.wait.photo.share.bean.DataListBean;
import here.wait.photo.share.bean.Photo;
import here.wait.photo.share.bean.UserLikePhoto;
import here.wait.photo.share.dao.PhotoDAO;
import here.wait.photo.share.dao.UserLikePhotoDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component("userLikePhotoDAO")
public class UserLikePhotoDAOImp implements UserLikePhotoDAO
{
	private HibernateTemplate hibernateTemplate;
	private PhotoDAO photoDAO;

	public void add(int userId, int photoId)
	{
		UserLikePhoto userLikePhoto = new UserLikePhoto();
		userLikePhoto.setUserId(userId);
		userLikePhoto.setPhotoId(photoId);
		hibernateTemplate.save(userLikePhoto);
	}

	public void delete(int userId, int photoId)
	{
		final String hql = "delete from UserLikePhoto u where u.userId="
				+ userId + " and u.photoId=" + photoId;
		hibernateTemplate.execute(new HibernateCallback<Object>()
		{

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException
			{
				session.createQuery(hql).executeUpdate();
				return null;
			}
		});
	}

	public UserLikePhoto get(int userId, int photoId)
	{
		String hql = "from UserLikePhoto u where u.userId=" + userId
				+ " and u.photoId=" + photoId;
		List<UserLikePhoto> list = hibernateTemplate.find(hql);
		if(0==list.size()) return null;
		UserLikePhoto userLikePhoto = list.get(0);
		return userLikePhoto;
	}

	public long getPhotoCount(int userId)
	{
		String hql = "select count(*) from UserLikePhoto u where u.userId="
				+ userId;
		long result = (Long) hibernateTemplate.find(hql).get(0);
		return result;
	}

	public long getUserCount(int photoId)
	{
		String hql = "select count(*) from UserLikePhoto u where u.photoId="
				+ photoId;
		long result = (Long) hibernateTemplate.find(hql).get(0);
		return result;
	}

	public DataListBean<Photo> getPhotoList(int userId, final int pageIndex,
			final int pageSize)
	{

		final String hql = "from UserLikePhoto u where u.userId=" + userId
				+ " order by u.id desc";
		List<UserLikePhoto> list;
		final long count = getPhotoCount(userId);
		long pageCount = 0;
		if (pageIndex <= 0 || pageSize <= 0)
		{
			list = hibernateTemplate.find(hql);
		} else
		{
			final int first = (pageIndex - 1) * pageSize;
			pageCount = (count + pageSize - 1) / pageSize;
			list = hibernateTemplate
					.execute(new HibernateCallback<List<UserLikePhoto>>()
					{
						public List<UserLikePhoto> doInHibernate(Session session)
								throws HibernateException, SQLException
						{

							Query query = session.createQuery(hql);
							query.setFirstResult(first);
							query.setMaxResults(pageSize);
							return query.list();
						}
					});
		}

		List<Photo> data = new ArrayList<Photo>();
		for (UserLikePhoto ulp : list)
		{
			if (null == ulp)
				continue;
			Photo p = photoDAO.get(ulp.getPhotoId());
			data.add(p);
		}
		DataListBean<Photo> db = new DataListBean<Photo>();
		db.setItems(data);
		db.setPageCount(pageCount);
		db.setPageIndex(pageIndex > pageCount ? pageCount : pageIndex);
		db.setPageSize(0 == pageIndex ? count : pageIndex);
		return db;
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
