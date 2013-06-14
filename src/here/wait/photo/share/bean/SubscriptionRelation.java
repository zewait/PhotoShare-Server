package here.wait.photo.share.bean;

public class SubscriptionRelation extends Data
{
	private int id;
	private int userId;
	private int subscriptionId;

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

	public int getSubscriptionId()
	{
		return subscriptionId;
	}

	public void setSubscriptionId(int subscriptionId)
	{
		this.subscriptionId = subscriptionId;
	}

	public String object2Json()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("{").append("\"id\":").append(id).append(",")
				.append("\"userId\":").append(userId).append(",")
				.append("\"subscriptionId\":").append(subscriptionId)
				.append("}");
		return builder.toString();
	}

}
