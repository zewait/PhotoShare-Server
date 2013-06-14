package here.wait.photo.share.dao;

import here.wait.photo.share.bean.DataListBean;
import here.wait.photo.share.bean.SubscriptionRelation;

public interface SubscriptionRelationDAO
{
	/**
	 * 存储订阅用户图片关系
	 * 
	 * @param myId
	 *            本用户id
	 * @param subscriptionId
	 *            对方用户id
	 */
	public void addSubscriptionRelation(SubscriptionRelation subscriptionRelation);

	/**
	 * 根据用户id获取订阅列表
	 * @param userId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public DataListBean<SubscriptionRelation> getSubscriptionRelationList(int userId, int pageIndex,
			int pageSize);

	/**
	 * 获取订阅关系
	 * @param myId
	 * @param subscriptionId
	 * @return 没有就返回null
	 */
	public SubscriptionRelation getSubscriptionRelation(int myId,
			int subscriptionId);
	
	public void delete(SubscriptionRelation subscriptionRelation);
	
	public long getSubscriptionRelationCount(int userId);

}
