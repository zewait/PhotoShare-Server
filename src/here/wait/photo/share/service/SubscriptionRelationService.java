package here.wait.photo.share.service;


public interface SubscriptionRelationService
{
	/**
	 * 存储订阅用户图片关系并存储粉丝关系
	 * 
	 * @param myId
	 *            本用户id
	 * @param subscriptionId
	 *            对方用户id
	 * @return
	 */
	public String addSubscriptionRelation(int myId, int subscriptionId);
	/**
	 * 根据用户id获取订阅列表
	 * @param userId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public String getSubscriptionRelationList(int userId, int pageIndex,
			int pageSize);
	
	public String deleteSubscriptionRelation(int myId, int subscriptionId);
	
	/**
	 * 根据用户id获取订阅量
	 * @param userId
	 * @return
	 */
	public String getSubscriptionRelationCount(int userId);
	
	/**
	 * 判断订阅与否
	 * @param userId
	 * @param subscriptionId
	 * @return
	 */
	public String isSubscriptionRelation(int userId, int subscriptionId);
	
}
