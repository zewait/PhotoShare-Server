package here.wait.photo.share.service;


public interface FanRelationService
{
	public String addFanRelation(int fanId, int myId);
	public String getFanRelationList(int userId, int pageIndex,
			int pageSize);
	public String deleteFanRelation(int fanId, int myId);
	/**
	 * 获取用户的粉丝数
	 * @param userId
	 * @return
	 */
	public String getFanRelationCount(int userId);
}
