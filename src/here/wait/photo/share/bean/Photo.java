package here.wait.photo.share.bean;

import here.wait.photo.share.utils.Utilities;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.fileupload.FileItem;

public class Photo extends Data
{
	private int id;
	// 经度
	private Double longitude = null;
	// 纬度
	private Double latitude = null;
	// 位置的一维码
	private String geoHash;
	private String address;
	private String path;
	private String hardDiskPath;
	private String hardDiskZoomPath;
	private String src;
	private String zoomSrc;
	private String fileName;
	private String content;
	private Date createTime;
	private FileItem photo;
	private User user;
	private boolean isBlow = false;
	// 距离(单位:米)
	private int distance;
	// 喜欢的人数
	private long likeCount;
	// 是否被该用户喜欢
	private boolean isLiked;

	// private Set<Comment> comments;

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

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	// public Set<Comment> getComments()
	// {
	// return comments;
	// }
	//
	// public void setComments(Set<Comment> comments)
	// {
	// this.comments = comments;
	// }

	public Double getLongitude()
	{
		return longitude;
	}

	public void setLongitude(Double longitude)
	{
		this.longitude = longitude;
	}

	public Double getLatitude()
	{
		return latitude;
	}

	public void setLatitude(Double latitude)
	{
		this.latitude = latitude;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public FileItem getPhoto()
	{
		return photo;
	}

	public void setPhoto(FileItem photo)
	{
		this.photo = photo;
	}

	public boolean getIsBlow()
	{
		return isBlow;
	}

	public void setIsBlow(boolean isBlow)
	{
		this.isBlow = isBlow;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public String getHardDiskPath()
	{
		return hardDiskPath;
	}

	public String getHardDiskZoomPath()
	{
		return hardDiskZoomPath;
	}

	public void setHardDiskZoomPath(String hardDiskZoomPath)
	{
		this.hardDiskZoomPath = hardDiskZoomPath;
	}

	public void setHardDiskPath(String hardDiskPath)
	{
		this.hardDiskPath = hardDiskPath;
	}

	public String getSrc()
	{
		return src;
	}

	public void setSrc(String src)
	{
		this.src = src;
	}

	public String getGeoHash()
	{
		return geoHash;
	}

	public long getLikeCount()
	{
		return likeCount;
	}

	public void setLikeCount(long likeCount)
	{
		this.likeCount = likeCount;
	}

	public void setGeoHash(String geoHash)
	{
		this.geoHash = geoHash;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getZoomSrc()
	{
		return zoomSrc;
	}

	public void setZoomSrc(String zoomSrc)
	{
		this.zoomSrc = zoomSrc;
	}

	public int getDistance()
	{
		return distance;
	}

	public void setDistance(int distance)
	{
		this.distance = distance;
	}

	public boolean getIsLiked()
	{
		return isLiked;
	}

	public void setIsLiked(boolean isLiked)
	{
		this.isLiked = isLiked;
	}

	public String object2Json()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("{")
				.append("\"userId\":")
				.append(user.getId())
				.append(",")
				.append("\"id\":")
				.append(id)
				.append(",")
				.append("\"longitude\":")
				.append(longitude)
				.append(",")
				.append("\"latitude\":")
				.append(latitude)
				.append(",")
				.append("\"address\":")
				.append("\"" + address + "\"")
				.append(",")
				.append("\"content\":")
				.append("\"" + content + "\"")
				.append(",")
				.append("\"isBlow\":")
				.append(isBlow)
				.append(",")
				.append("\"createTime\":")
				.append("\""
						+ (createTime == null ? null : Utilities
								.getDataFormat().format(createTime)) + "\"")
				.append(",").append("\"src\":").append("\"" + src + "\"")
				.append(",").append("\"zoomSrc\":")
				.append("\"" + zoomSrc + "\"").append(",")
				.append("\"distance\":").append(distance).append(",")
				.append("\"likeCount\":").append(likeCount).append(",")
				.append("\"isLiked\":").append(isLiked).append(",")
				.append("\"user\":").append(user.object2Json()).append("}");
		return builder.toString();
	}

}
