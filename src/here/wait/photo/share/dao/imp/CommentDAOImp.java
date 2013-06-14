package here.wait.photo.share.dao.imp;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import here.wait.photo.share.bean.Comment;
import here.wait.photo.share.bean.DataListBean;
import here.wait.photo.share.bean.SubscriptionRelation;
import here.wait.photo.share.dao.CommentDAO;

@Component("commentDAO")
public class CommentDAOImp implements CommentDAO
{
	private HibernateTemplate hibernateTemplate;
	public void save(Comment comment)
	{
		hibernateTemplate.save(comment);
	}
	
	@SuppressWarnings("all")
	public DataListBean<Comment> getList(int photoId, int pageIndex,
			final int pageSize)
	{
		List<Comment> subscriptionRelations;
		final String hql = "from Comment c where c.photo.id=" + photoId
				+ " order by c.id desc";

		long count =  getCount(photoId);
		long countPage = 0;

		if (0 == pageIndex || 0 == pageSize)
		{
			subscriptionRelations = hibernateTemplate.find(hql);
		} else
		{
			final int first = (pageIndex - 1) * pageSize;

			countPage = (count + pageSize - 1) / pageSize;
			subscriptionRelations = hibernateTemplate
					.executeFind(new HibernateCallback()
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

		DataListBean<Comment> dlb = new DataListBean<Comment>();
		dlb.setItems(subscriptionRelations);
		dlb.setPageCount(countPage);
		dlb.setPageIndex((pageIndex > countPage ? countPage : pageIndex));
		dlb.setPageSize((pageSize == 0 ? count : pageSize));
		return dlb;
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

	public Long getCount(int photoId)
	{
		String coutHql = "select count(*) from Comment c where c.photo.id="+ photoId;
		long count = (Long) hibernateTemplate.find(coutHql).get(0);
		return count;
	}

	
}
