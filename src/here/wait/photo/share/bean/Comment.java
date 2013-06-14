package here.wait.photo.share.bean;

import here.wait.photo.share.utils.Utilities;

import java.util.Date;

public class Comment extends Data
{
	private int id;
	private String content;
	private Date createTime;
	private User owner;
	private int toId;
	private Photo photo;
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public Date getCreateTime()
	{
		return createTime;
	}
	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}
	public User getOwner()
	{
		return owner;
	}
	public int getToId()
	{
		return toId;
	}
	public void setToId(int toId)
	{
		this.toId = toId;
	}
	public void setOwner(User owner)
	{
		this.owner = owner;
	}
	public Photo getPhoto()
	{
		return photo;
	}
	public void setPhoto(Photo photo)
	{
		this.photo = photo;
	}
	
	@Override
	public String object2Json()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("{")
		.append("\"id\":").append(id).append(",")
		.append("\"content\":").append("\"" + content + "\"").append(",")
		.append("\"createTime\":").append("\"" + Utilities.getDataFormat().format(createTime) + "\"").append(",")
		.append("\"owner\":").append(owner.object2Json()).append(",")
		.append("\"toId\":").append(toId).append(",")
		.append("\"photoId\":").append(photo.getId())
		.append("}");
		return sb.toString();
	}
}
