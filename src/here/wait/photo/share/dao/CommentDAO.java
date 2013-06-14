package here.wait.photo.share.dao;

import here.wait.photo.share.bean.Comment;
import here.wait.photo.share.bean.DataListBean;

public interface CommentDAO
{
	public void save(Comment comment);
	/**
	 * 根据图片的id获取评论列表
	 * @param photoId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public DataListBean<Comment> getList(int photoId, int pageIndex, int pageSize);
	
	public Long getCount(int photoId);
}
