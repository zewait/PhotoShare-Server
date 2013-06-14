package here.wait.photo.share.service;

import java.util.Date;

public interface CommentService
{
	/**
	 * 添加评论
	 * @param photoId
	 * @param ownerId
	 * @param toId
	 * @param createDate
	 * @param content
	 * @return
	 */
	public String add(int photoId, int ownerId, int toId, Date createDate, String content);
	
	public String getList(int photoId, int pageIndex, int pageSize);
	
	public String getCount(int photoId);
}
