package here.wait.photo.share.dao.imp;

import here.wait.photo.share.bean.DataListBean;
import here.wait.photo.share.bean.SubscriptionRelation;
import here.wait.photo.share.dao.SubscriptionRelationDAO;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component("subscriptionRelationDAO")
public class SubscriptionRelationDAOImp implements SubscriptionRelationDAO
{
	private HibernateTemplate hibernateTemplate;

	public void addSubscriptionRelation(
			SubscriptionRelation subscriptionRelation)
	{
		hibernateTemplate.save(subscriptionRelation);
	}

	@SuppressWarnings("all")
	public DataListBean<SubscriptionRelation> getSubscriptionRelationList(
			int userId, int pageIndex, final int pageSize)
	{
		List<SubscriptionRelation> subscriptionRelations;
		final String hql = "from SubscriptionRelation s where s.userId=" + userId
				+ " order by s.id desc";

		long count = getSubscriptionRelationCount(userId);
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

		DataListBean<SubscriptionRelation> srb = new DataListBean<SubscriptionRelation>();
		srb.setItems(subscriptionRelations);
		srb.setPageCount(countPage);
		srb.setPageIndex((pageIndex > countPage ? countPage : pageIndex));
		srb.setPageSize((pageSize == 0 ? count : pageSize));
		return srb;

	}

	public SubscriptionRelation getSubscriptionRelation(int userId,
			int subscriptionId)
	{
		String hql = "from SubscriptionRelation s where s.userId=" + userId
				+ " and s.subscriptionId=" + subscriptionId;

		List<SubscriptionRelation> list = hibernateTemplate.find(hql);
		if (0 == list.size())
			return null;
		return list.get(0);
	}
	
	public void delete(SubscriptionRelation subscriptionRelation)
	{
		hibernateTemplate.delete(subscriptionRelation);
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

	public long getSubscriptionRelationCount(int userId)
	{
		String hql = "select count(*) from SubscriptionRelation s where s.userId=" + userId;
		long count = (Long) hibernateTemplate.find(hql).get(0);
		return count;
	}

}
