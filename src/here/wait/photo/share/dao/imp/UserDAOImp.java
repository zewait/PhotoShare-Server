package here.wait.photo.share.dao.imp;

import here.wait.photo.share.bean.DataListBean;
import here.wait.photo.share.bean.User;
import here.wait.photo.share.dao.UserDAO;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.fileupload.FileItem;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component("userDAO")
public class UserDAOImp implements UserDAO
{
	private HibernateTemplate hibernateTemplate;

	public void save(User user)
	{
		hibernateTemplate.save(user);
	}

	public User get(int id)
	{
		return hibernateTemplate.get(User.class, id);
	}


	public User query(String name)
	{
		List<User> users = hibernateTemplate.find("from User u where u.name='" + name + "'");
		if(users.size()<=0)
			return null;
		return users.get(0);
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

	public void headPicUpload(User user)
	{
		File saveFile = new File(user.getHardPicDiskPath());
		try
		{
			saveFile.createNewFile();
			FileItem fi = user.getHeadPic();
			fi.write(saveFile);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		User getUser = hibernateTemplate.get(User.class, user.getId());
		getUser.setHardPicDiskPath(user.getHardPicDiskPath());
		getUser.setHeadPicSrc(user.getHeadPicSrc());
		hibernateTemplate.update(getUser);
	}

	@SuppressWarnings("unchecked")
	public DataListBean<User> search(String name, int pageIndex, final int pageSize)
	{
		List<User> photos;
		final String hql = "from User u where u.name like '%" + name
				+ "%' order by u.id desc";
		final String countHql = "select count(*) from User u where u.name like '%" + name
				+ "%'";

		long count = (Long)hibernateTemplate.find(countHql).get(0);
		long countPage = 0;

		if (pageIndex <= 0|| pageSize <= 0)
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
					List<User> list = query.list();
					return list;
				}
			});
		}

		DataListBean<User> pb = new DataListBean<User>();
		pb.setItems(photos);
		pb.setPageCount(countPage);
		pb.setPageIndex((pageIndex > countPage ? countPage : pageIndex));
		pb.setPageSize((pageSize == 0 ? count : pageSize));
		return pb;
	}


}
