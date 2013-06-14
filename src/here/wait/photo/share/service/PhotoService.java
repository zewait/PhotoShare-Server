package here.wait.photo.share.service;

import here.wait.photo.share.bean.Photo;

import java.util.List;

public interface PhotoService
{
	public String upload(Photo photo);
	public String getList(int userId, int pageIndex, int pageSize);
	/**
	 * 根据用户id获取订阅的图片列表
	 * @param userId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public String getSubscriptionPhotoList(int userId, int pageIndex, int pageSize);
	/**
	 * 获取附近的图片
	 * @param userId 用户id
	 * @param longitude 经度
	 * @param latitude 纬度
	 * @return
	 */
	public String getNearbyPhotoList(int userId, double longitude, double latitude);
	
	/**
	 * 根据用户id获取图片的数量
	 * @param userId
	 * @return
	 */
	public String getCount(int userId);
	
	public String getRandomBlowPhoto();
}
