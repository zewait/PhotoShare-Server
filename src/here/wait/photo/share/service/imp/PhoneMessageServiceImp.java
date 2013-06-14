package here.wait.photo.share.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import here.wait.photo.share.bean.DataInfoBean;
import here.wait.photo.share.bean.PhoneMessage;
import here.wait.photo.share.dao.PhoneMessageDAO;
import here.wait.photo.share.service.PhoneMessageService;

@Component("phoneMessageService")
public class PhoneMessageServiceImp implements PhoneMessageService 
{
	private PhoneMessageDAO phoneMessageDAO; 
	public String add(PhoneMessage pm) 
	{
		phoneMessageDAO.save(pm);
		DataInfoBean dataInfoBean = DataInfoBean.createSuccessDataInfoBean("添加记录成功", null);
		return dataInfoBean.object2Json();
	}
	public PhoneMessageDAO getPhoneMessageDAO() 
	{
		return phoneMessageDAO;
	}
	@Resource
	public void setPhoneMessageDAO(PhoneMessageDAO phoneMessageDAO) 
	{
		this.phoneMessageDAO = phoneMessageDAO;
	}
	public List<PhoneMessage> getAll() 
	{
		return phoneMessageDAO.getAll();
	}

}
