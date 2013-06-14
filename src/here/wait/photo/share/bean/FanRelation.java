package here.wait.photo.share.bean;

public class FanRelation extends Data
{
	private int id;
	private int userId;
	private int fanId;
	public int getId()
	{
		return id;
	}
	public int getUserId()
	{
		return userId;
	}
	public void setUserId(int userId)
	{
		this.userId = userId;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	
	public int getFanId()
	{
		return fanId;
	}
	public void setFanId(int fanId)
	{
		this.fanId = fanId;
	}
	@Override
	public String object2Json()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("{")
		.append("\"id\":").append(id).append(",")
		.append("\"userId\":").append(userId).append(",")
		.append("\"fanId\":").append(fanId)
		.append("}");
		return builder.toString();
	}
}
