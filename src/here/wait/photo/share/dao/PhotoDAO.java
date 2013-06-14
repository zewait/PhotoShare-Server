package here.wait.photo.share.dao;

import here.wait.photo.share.bean.DataListBean;
import here.wait.photo.share.bean.Photo;

import java.util.List;

public interface PhotoDAO
{
	/**
	 * 保存图片
	 * @param photo
	 * @throws Exception
	 */
	public void save(Photo photo) throws Exception;
	/**
	 * 根据用户获取图片列表
	 * @param userId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public DataListBean<Photo> getList(int userId, int pageIndex, int pageSize);
	/**
	 * 根据用户id数据得到全部用户的图片列表,按时间降序
	 * @param userIds
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public DataListBean<Photo> getList(Integer[] userIds, int pageIndex, int pageSize);
	public Photo get(int photoId);
	/**
	 * 获取附近的图片
	 * @param longitude 经度
	 * @param latitude 纬度
	 * @return
	 */
	public DataListBean<Photo> getNearbyPhotoList(double longitude, double latitude);
	
	/**
	 * 根据用户id获取图片的总数
	 * @param userId
	 * @return
	 */
	public long getCount(int userId);
	/**
	 * 随机获取吹图片
	 * @return
	 */
	public Photo getRandomBlowPhoto();
}
