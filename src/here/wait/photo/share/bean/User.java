package here.wait.photo.share.bean;

import java.util.Set;

import org.apache.commons.fileupload.FileItem;

public class User extends Data
{
	private int id;
	private String name;
	private String password;
	private Set<Photo> photos;
	private String headPicSrc;
	private String hardPicDiskPath;
	private FileItem headPic;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Set<Photo> getPhotos()
	{
		return photos;
	}

	public void setPhotos(Set<Photo> photos)
	{
		this.photos = photos;
	}

	public String object2Json()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("{").append("\"id\":").append(id).append(",")
				.append("\"name\":").append("\"" + name + "\"").append(",")
				.append("\"headPicSrc\":")
				.append((headPicSrc == null ? null : "\"" + headPicSrc + "\""))
				.append("}");
		return builder.toString();
	}

	public String getHeadPicSrc()
	{
		return headPicSrc;
	}

	public void setHeadPicSrc(String headPicSrc)
	{
		this.headPicSrc = headPicSrc;
	}

	public String getHardPicDiskPath()
	{
		return hardPicDiskPath;
	}

	public void setHardPicDiskPath(String hardPicDiskPath)
	{
		this.hardPicDiskPath = hardPicDiskPath;
	}

	public FileItem getHeadPic()
	{
		return headPic;
	}

	public void setHeadPic(FileItem headPic)
	{
		this.headPic = headPic;
	}

}
