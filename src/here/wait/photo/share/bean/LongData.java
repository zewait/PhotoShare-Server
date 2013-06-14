package here.wait.photo.share.bean;

public class LongData extends Data
{
	private long num;
	
	@Override
	public String object2Json()
	{
		return Long.toString(num);
	}

	public long getNum()
	{
		return num;
	}

	public void setNum(long num)
	{
		this.num = num;
	}

}
