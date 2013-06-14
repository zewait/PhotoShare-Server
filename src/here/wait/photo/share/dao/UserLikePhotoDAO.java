package here.wait.photo.share.dao;

import here.wait.photo.share.bean.DataListBean;
import here.wait.photo.share.bean.Photo;
import here.wait.photo.share.bean.UserLikePhoto;

public interface UserLikePhotoDAO
{
	public void add(int userId, int photoId);
	public void delete(int userId, int photoId);
	public UserLikePhoto get(int userId, int photoId);
	/**
	 * 获取用户喜欢相片的总数
	 * @param userId 用户id
	 * @return
	 */
	public long getPhotoCount(int userId);
	/**
	 * 获取相片被喜欢的人数
	 * @param photoId 相片id
	 * @return
	 */
	public long getUserCount(int photoId);
	/**
	 * 获取用户喜欢相片的列表
	 * @param userId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public DataListBean<Photo> getPhotoList(int userId, int pageIndex, int pageSize);
}
