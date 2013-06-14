package here.wait.photo.share.dao.imp;

import here.wait.photo.share.bean.DataListBean;
import here.wait.photo.share.bean.FanRelation;
import here.wait.photo.share.bean.SubscriptionRelation;
import here.wait.photo.share.dao.FanRelationDAO;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component("fanRelationDAO")
public class FanRelationDAOImp implements FanRelationDAO
{
	private HibernateTemplate hibernateTemplate;
	public void addFanRelation(FanRelation fanRelation)
	{
		hibernateTemplate.save(fanRelation);
	}
	public FanRelation getFanRelation(int fanId, int userId)
	{
		String hql = "from FanRelation f where f.userId=" + userId + " and f.fanId=" + fanId;
		List<FanRelation> fanRelations = hibernateTemplate.find(hql);
		if(0==fanRelations.size())
			return null;
		return fanRelations.get(0);
	}
	
	@SuppressWarnings("all")
	public DataListBean<FanRelation> getFanRelationList(int userId, int pageIndex,
			final int pageSize)
	{
		List<FanRelation> fanRelations;
		final String hql = "from FanRelation s where s.userId=" + userId
				+ " order by s.id desc";

		long count = getFanRelationCount(userId);
		long countPage = 0;

		if (0 == pageIndex || 0 == pageSize)
		{
			fanRelations = hibernateTemplate.find(hql);
		} else
		{
			final int first = (pageIndex - 1) * pageSize;
			countPage = (count + pageSize - 1) / pageSize;
			fanRelations = hibernateTemplate.executeFind(new HibernateCallback()
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
		

		DataListBean<FanRelation> srb = new DataListBean<FanRelation>();
		srb.setItems(fanRelations);
		srb.setPageCount(countPage);
		srb.setPageIndex((pageIndex > countPage ? countPage : pageIndex));
		srb.setPageSize((pageSize == 0 ? count : pageSize));
		return srb;
	}
	
	public void delete(FanRelation fanRelation)
	{
		hibernateTemplate.delete(fanRelation);
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
	public long getFanRelationCount(int userId)
	{
		String hql = "select count(*) from FanRelation f where f.userId=" + userId;
		long count = (Long) hibernateTemplate.find(hql).get(0);
		return count;
	}

}
