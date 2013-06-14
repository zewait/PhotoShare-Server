package here.wait.photo.share.service.imp;

import here.wait.photo.share.bean.DataInfoBean;
import here.wait.photo.share.bean.DataListBean;
import here.wait.photo.share.bean.FanRelation;
import here.wait.photo.share.bean.LongData;
import here.wait.photo.share.bean.User;
import here.wait.photo.share.dao.FanRelationDAO;
import here.wait.photo.share.dao.UserDAO;
import here.wait.photo.share.service.FanRelationService;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component("fanRelationService")
public class FanRelationServiceImp implements FanRelationService
{
	private FanRelationDAO fanRelationDAO;
	private UserDAO userDAO;
	
	public String addFanRelation(int fanId, int myId)
	{
		DataInfoBean dataInfoBean;
		if(fanId == myId)
		{
			dataInfoBean = DataInfoBean.createErrorDataInfoBean("不能添加自己为粉丝");
			return dataInfoBean.object2Json();
		}
		
		FanRelation fanRelation = fanRelationDAO.getFanRelation(fanId, myId);
		if(null != fanRelation)
		{
			dataInfoBean = DataInfoBean.createErrorDataInfoBean("已经存在此粉丝关系");
			return dataInfoBean.object2Json();
		}
		
		User fanUser = userDAO.get(fanId);
		if(null == fanUser)
		{
			dataInfoBean = DataInfoBean.createErrorDataInfoBean("不存在此用户");
			return dataInfoBean.object2Json();
		}
		User myUser = userDAO.get(myId);
		if(null == myUser)
		{
			dataInfoBean = DataInfoBean.createErrorDataInfoBean("不存在此用户");
			return dataInfoBean.object2Json();
		}
		
		FanRelation saveFanRelation = new FanRelation();
		saveFanRelation.setFanId(fanId);
		saveFanRelation.setUserId(myId);
		fanRelationDAO.addFanRelation(saveFanRelation);
		dataInfoBean = DataInfoBean.createSuccessDataInfoBean("添加粉丝关系成功", null);
		return dataInfoBean.object2Json();
	}
	
	public String getFanRelationList(int userId, int pageIndex, int pageSize)
	{
		DataListBean<FanRelation> data = fanRelationDAO.getFanRelationList(userId, pageIndex, pageSize);
		DataInfoBean info = DataInfoBean.createSuccessDataInfoBean("成功", data);
		return info.object2Json();
	}
	
	public String deleteFanRelation(int fanId, int myId)
	{
		FanRelation fanRelation = fanRelationDAO.getFanRelation(fanId, myId);
		if(null != fanRelation)
			fanRelationDAO.delete(fanRelation);
		DataInfoBean info = DataInfoBean.createSuccessDataInfoBean("成功", null);
		return info.object2Json();
	}

	
	public FanRelationDAO getFanRelationDAO()
	{
		return fanRelationDAO;
	}
	@Resource
	public void setFanRelationDAO(FanRelationDAO fanRelationDAO)
	{
		this.fanRelationDAO = fanRelationDAO;
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

	public String getFanRelationCount(int userId)
	{
		LongData data = new LongData();
		data.setNum(fanRelationDAO.getFanRelationCount(userId));
		DataInfoBean info = DataInfoBean.createSuccessDataInfoBean("成功", data);
		return info.object2Json();
	}

}
