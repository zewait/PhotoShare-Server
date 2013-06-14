package here.wait.photo.share.service.imp;

import here.wait.photo.share.bean.BooleanData;
import here.wait.photo.share.bean.DataInfoBean;
import here.wait.photo.share.bean.DataListBean;
import here.wait.photo.share.bean.LongData;
import here.wait.photo.share.bean.SubscriptionRelation;
import here.wait.photo.share.bean.User;
import here.wait.photo.share.dao.SubscriptionRelationDAO;
import here.wait.photo.share.dao.UserDAO;
import here.wait.photo.share.service.FanRelationService;
import here.wait.photo.share.service.SubscriptionRelationService;
import here.wait.photo.share.utils.DebugUtility;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component("subscriptionRelationService")
public class SubscriptionRelationServiceImp implements
		SubscriptionRelationService
{
	private FanRelationService fanRelationService;
	private SubscriptionRelationDAO subscriptionRelationDAO;
	private UserDAO userDAO;
	
	@SuppressWarnings("all")
	public String addSubscriptionRelation(int myId, int subscriptionId)
	{
		DataInfoBean info;
		if(myId == subscriptionId)
		{
			info = DataInfoBean.createErrorDataInfoBean("不能订阅自己");
			return info.object2Json();
		}
		SubscriptionRelation subscriptionRelation = subscriptionRelationDAO.getSubscriptionRelation(myId, subscriptionId);
		if(null!=subscriptionRelation)
		{
			info = DataInfoBean.createErrorDataInfoBean("已经订阅过");
			return info.object2Json();
		} 
		
		User myUser = userDAO.get(myId);
		if(null==myUser)
		{
			info = DataInfoBean.createErrorDataInfoBean("不存在此用户");
			return info.object2Json();
		}
		
		User subscriptionUser = userDAO.get(subscriptionId);
		if(null==subscriptionUser)
		{
			info = DataInfoBean.createErrorDataInfoBean("不存在此用户");
			return info.object2Json();
		}
		
		String fanRelationServiceResult = fanRelationService.addFanRelation(myId, subscriptionId);
		
		DebugUtility.p("result " + fanRelationServiceResult);
		
		SubscriptionRelation sr = new SubscriptionRelation();
		sr.setUserId(myId);
		sr.setSubscriptionId(subscriptionId);
		subscriptionRelationDAO.addSubscriptionRelation(sr);
		
		info = DataInfoBean.createSuccessDataInfoBean("订阅成功", null);
		return info.object2Json();
	}
	
	
	public String getSubscriptionRelationList(int userId, int pageIndex, int pageSize)
	{
		DataListBean<SubscriptionRelation> data = subscriptionRelationDAO.getSubscriptionRelationList(userId, pageIndex, pageSize);
		DataInfoBean info = DataInfoBean.createSuccessDataInfoBean("成功", data);
		return info.object2Json();
	}
	
	public String deleteSubscriptionRelation(int myId, int subscriptionId)
	{
		SubscriptionRelation sr = subscriptionRelationDAO.getSubscriptionRelation(myId, subscriptionId);
		if(null!=sr)
			subscriptionRelationDAO.delete(sr);
		fanRelationService.deleteFanRelation(myId, subscriptionId);
		DataInfoBean info = DataInfoBean.createSuccessDataInfoBean("成功取消了订阅", null);
		return info.object2Json();
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




	public UserDAO getUserDAO()
	{
		return userDAO;
	}

	@Resource
	public void setUserDAO(UserDAO userDAO)
	{
		this.userDAO = userDAO;
	}


	public FanRelationService getFanRelationService()
	{
		return fanRelationService;
	}

	@Resource
	public void setFanRelationService(FanRelationService fanRelationService)
	{
		this.fanRelationService = fanRelationService;
	}


	public String getSubscriptionRelationCount(int userId)
	{
		LongData data = new LongData();
		data.setNum(subscriptionRelationDAO.getSubscriptionRelationCount(userId));
		DataInfoBean info = DataInfoBean.createSuccessDataInfoBean("成功", data);
		return info.object2Json();
	}


	public String isSubscriptionRelation(int userId, int subscriptionId)
	{
		SubscriptionRelation sr = subscriptionRelationDAO.getSubscriptionRelation(userId, subscriptionId);
		BooleanData data = new BooleanData();
		if(null == sr)
		{
			data.setData(false);
		}
		else
		{
			data.setData(true);
		}
		DataInfoBean info = DataInfoBean.createSuccessDataInfoBean("成功", data);
		return info.object2Json();
	}

}
