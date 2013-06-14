package here.wait.photo.share.bean;

public class StringData extends Data
{
	private String data;
	@Override
	public String object2Json()
	{
		return "\"" + data + "\"";
	}
	public String getData()
	{
		return data;
	}
	public void setData(String data)
	{
		this.data = data;
	}

}
