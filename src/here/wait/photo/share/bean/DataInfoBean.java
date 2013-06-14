package here.wait.photo.share.bean;

public class DataInfoBean
{
	/**
	 * 成功
	 */
	public static final int CODE_SUCCESS = 0;
	/**
	 * 错误
	 */
	public static final int CODE_ERROR = 1;

	private int code;
	private String messge;
	private Data data;

	public int getCode()
	{
		return code;
	}

	public void setCode(int code)
	{
		this.code = code;
	}

	public String getMessge()
	{
		return messge;
	}

	public void setMessge(String messge)
	{
		this.messge = messge;
	}

	public Data getData()
	{
		return data;
	}

	public void setData(Data data)
	{
		this.data = data;
	}

	public String object2Json()
	{
		StringBuilder builder = new StringBuilder();
		// {"Code":3,"Message":"Parameters Not Complete","Data":{id:1}}
		builder.append("{").append("\"code\":" + code).append(",")
				.append("\"message\":" + "\"" + messge + "\"").append(",")
				.append("\"data\":")
				.append(data == null ? null : data.object2Json())
				.append("}");
		return builder.toString();

	}
	
	public static DataInfoBean createErrorDataInfoBean(String msg)
	{
		DataInfoBean info = new DataInfoBean();
		info.setCode(CODE_ERROR);
		info.setMessge(msg);
		return info;
	}
	
	public static DataInfoBean createSuccessDataInfoBean(String msg, Data data)
	{
		DataInfoBean info = new DataInfoBean();
		info.setCode(CODE_SUCCESS);
		info.setMessge(msg);
		info.setData(data);
		return info;
	}

}
