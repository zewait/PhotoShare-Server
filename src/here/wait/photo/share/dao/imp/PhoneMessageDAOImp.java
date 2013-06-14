package here.wait.photo.share.dao.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import here.wait.photo.share.bean.PhoneMessage;
import here.wait.photo.share.dao.PhoneMessageDAO;

@Component("phoneMessageDAO")
public class PhoneMessageDAOImp implements PhoneMessageDAO
{
	private HibernateTemplate hibernateTemplate;
	public void save(PhoneMessage pm) 
	{
		hibernateTemplate.save(pm);
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

	public List<PhoneMessage> getAll() 
	{
		String sql = "from PhoneMessage";
		return hibernateTemplate.find(sql);
	}

}
