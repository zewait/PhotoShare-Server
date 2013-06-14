package here.wait.photo.share.dao;

import here.wait.photo.share.bean.DataListBean;
import here.wait.photo.share.bean.FanRelation;

public interface FanRelationDAO
{
	public void addFanRelation(FanRelation fanRelation);
	public FanRelation getFanRelation(int fanId, int myId);
	public DataListBean<FanRelation> getFanRelationList(int userId, int pageIndex, int pageSize);
	public void delete(FanRelation fanRelation);
	/**
	 * 获取用户的粉丝数
	 * @param userId
	 * @return
	 */
	public long getFanRelationCount(int userId);
}
