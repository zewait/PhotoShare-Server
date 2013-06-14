package here.wait.photo.share.bean;

import java.util.ArrayList;
import java.util.List;

public class DataListBean<T extends Data> extends Data
{
	private List<? extends Data> items = new ArrayList<Data>();
	private long pageIndex;
	private long pageSize;
	private long pageCount;
	public List<? extends Data> getItems()
	{
		return items;
	}
	public void setItems(List<? extends Data> items)
	{
		this.items = items;
	}
	public long getPageIndex()
	{
		return pageIndex;
	}
	public void setPageIndex(long pageIndex)
	{
		this.pageIndex = pageIndex;
	}
	public long getPageSize()
	{
		return pageSize;
	}
	public void setPageSize(long pageSize)
	{
		this.pageSize = pageSize;
	}
	public long getPageCount()
	{
		return pageCount;
	}
	public void setPageCount(long pageCount)
	{
		this.pageCount = pageCount;
	}
	public String object2Json()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("{").append("\"pageIndex\":").append(pageIndex).append(",")
		.append("\"pageCount\":").append(pageCount).append(",")
		.append("\"pageSize\":").append(pageSize).append(",")
		.append("\"items\":")
		.append("[");
		if(null == items || 0 == items.size())
		{
			
			builder.append("null");
			
		}
		else
		{
			for(int i=0,len=items.size()-1; i<len; i++)
			{
				builder.append(items.get(i).object2Json()).append(",");
			}
			builder.append(items.get(items.size()-1).object2Json());
		}
		builder.append("]").append("}");
		return builder.toString();
	}
}
