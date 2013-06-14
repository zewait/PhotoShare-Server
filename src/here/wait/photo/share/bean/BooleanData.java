package here.wait.photo.share.bean;

public class BooleanData extends Data
{
	private boolean data;

	public boolean isData()
	{
		return data;
	}

	public void setData(boolean data)
	{
		this.data = data;
	}

	@Override
	public String object2Json()
	{
		return Boolean.toString(data);
	}
	
}
