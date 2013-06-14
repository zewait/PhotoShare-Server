package here.wait.photo.share.service;

public interface UserLikePhotoService
{
	/**
	 * 是否存在此喜欢关系
	 * @param userId
	 * @param photoId
	 * @return 存在返回true,否则false
	 */
	public String isLiked(int userId, int photoId);
	public String add(int userId, int photoId);
	public String delete(int userId, int photoId);
	public String getPhotoCount(int userId);
	public String getUserCount(int photoId);
	public String getPhotoList(int userId, int pageIndex, int pageSize);
}
