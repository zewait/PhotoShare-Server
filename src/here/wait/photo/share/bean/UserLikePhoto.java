package here.wait.photo.share.bean;

public class UserLikePhoto extends Data
{
	private int id;
	private int userId;
	private int photoId;
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getUserId()
	{
		return userId;
	}
	public void setUserId(int userId)
	{
		this.userId = userId;
	}
	public int getPhotoId()
	{
		return photoId;
	}
	public void setPhotoId(int photoId)
	{
		this.photoId = photoId;
	}
	@Override
	public String object2Json()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("{").append("\"userId\":").append(userId).append(",").append("\"photoId\":").append(photoId).append("}");
		return builder.toString();
	}
}
