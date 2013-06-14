package here.wait.photo.share.service.imp;

import here.wait.photo.share.bean.Comment;
import here.wait.photo.share.bean.DataInfoBean;
import here.wait.photo.share.bean.DataListBean;
import here.wait.photo.share.bean.LongData;
import here.wait.photo.share.bean.Photo;
import here.wait.photo.share.bean.User;
import here.wait.photo.share.dao.CommentDAO;
import here.wait.photo.share.dao.PhotoDAO;
import here.wait.photo.share.dao.UserDAO;
import here.wait.photo.share.service.CommentService;
import here.wait.photo.share.utils.DebugUtility;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component("commentService")
public class CommentServiceImp implements CommentService
{
	private CommentDAO commentDAO;
	private PhotoDAO photoDAO;
	private UserDAO userDAO;
	public String add(int photoId, int ownerId, int toId, Date createTime,
			String content)
	{
		DataInfoBean info;
		Photo photo = photoDAO.get(photoId);
		if(null == photo)
		{
			info = DataInfoBean.createErrorDataInfoBean("不存在该图片");
			return info.object2Json();
		}
		User ownerUser = userDAO.get(ownerId);
		if(null == ownerUser)
		{
			info = DataInfoBean.createErrorDataInfoBean("请登录");
			return info.object2Json();
		}
		
		
		User toUser = null;
		if(toId>0)
		
		{
			toUser = userDAO.get(toId);
			if(null == toUser)
			{
				info = DataInfoBean.createErrorDataInfoBean("回复的用户不存在");
				return info.object2Json();
			}
			
		}
		
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setCreateTime(createTime);
		comment.setOwner(ownerUser);
		comment.setToId(toId);
		comment.setPhoto(photo);
		commentDAO.save(comment);
		info = DataInfoBean.createSuccessDataInfoBean("回复成功", comment);
		return info.object2Json();
	}
	
	public String getList(int photoId, int pageIndex, int pageSize)
	{
		DataListBean<Comment> data = commentDAO.getList(photoId, pageIndex, pageSize);
		DataInfoBean info = DataInfoBean.createSuccessDataInfoBean("成功", data);
		return info.object2Json();
	}
	
	public PhotoDAO getPhotoDAO()
	{
		return photoDAO;
	}
	@Resource
	public void setPhotoDAO(PhotoDAO photoDAO)
	{
		this.photoDAO = photoDAO;
	}

	public UserDAO getUserDAO()
	{
		return userDAO;
	}
	@Resource
	public void setUserDAO(UserDAO userDAO)
	{
		this.userDAO = userDAO;
	}

	public CommentDAO getCommentDAO()
	{
		return commentDAO;
	}
	@Resource
	public void setCommentDAO(CommentDAO commentDAO)
	{
		this.commentDAO = commentDAO;
	}

	public String getCount(int photoId)
	{
		LongData data = new LongData();
		data.setNum(commentDAO.getCount(photoId));
		DataInfoBean info = DataInfoBean.createSuccessDataInfoBean("成功", data);
		return info.object2Json();
	}
}
