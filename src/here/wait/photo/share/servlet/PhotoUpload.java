package here.wait.photo.share.servlet;

import here.wait.photo.share.bean.DataInfoBean;
import here.wait.photo.share.bean.Photo;
import here.wait.photo.share.bean.User;
import here.wait.photo.share.dao.UserDAO;
import here.wait.photo.share.service.PhotoService;
import here.wait.photo.share.service.UserService;
import here.wait.photo.share.utils.AppliationContextUtility;
import here.wait.photo.share.utils.DebugUtility;
import here.wait.photo.share.utils.Utilities;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class PhotoUpload extends HttpServlet
{

	private final static String DIR = "photo_share";

	public PhotoUpload()
	{
		super();
	}

	public void destroy()
	{
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		try
		{
		Photo photo = new Photo();

		DiskFileItemFactory factory = new DiskFileItemFactory();

		String path = getServletContext().getRealPath("/");

		factory.setRepository(new File(path));
		// 设定当不超过1M时直接先存储在内存中，再进行内存对硬盘的IO
		// 当超过时候先存储在临时目录path当中
		factory.setSizeThreshold(1024 * 1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> list;
		try
		{
			list = upload.parseRequest(request);
			for (FileItem item : list)
			{
				// 判断是否文件类型,是否form表单
				if (item.isFormField())
				{
					String name = item.getFieldName();
					String value = item.getString("utf-8");
					request.setAttribute(name, value);
				} else
				{
					String value = item.getName();
					if (null == value || 0 == value.length())
						continue;
					// 是文件
					photo.setPhoto(item);
					// String name = item.getFieldName();
					//

					// System.out.println(name);
					// int start = value.lastIndexOf("//");
					//
					// String fileName = value.substring(start + 1);
					//
					// request.setAttribute(name, fileName);

					// item.write(new File(path, fileName));

				}
			}
		} catch (FileUploadException e)
		{
			e.printStackTrace();
		}

		String longitudeStr = (String) request.getAttribute("longitude");
		if (null != longitudeStr)
		{
			try
			{
				Double longitude = Double.parseDouble(longitudeStr);
				photo.setLongitude(longitude);
			} catch (Exception e)
			{
			}
		}
		String latitudeStr = (String) request.getAttribute("latitude");
		if (null != latitudeStr)
		{
			try
			{
				Double latitude = Double.parseDouble(latitudeStr);
				photo.setLatitude(latitude);
			} catch (Exception e)
			{
			}
		}
		String address = (String)request.getAttribute("address");
		if(null != address && address.length() > 0)
			photo.setAddress(address);
		String content = (String) request.getAttribute("content");

		int userId = Integer.parseInt((String) request.getAttribute("userId"));
		User user = ((UserDAO) AppliationContextUtility
				.getBean("userDAO")).get(userId);

		if (null == user)
		{
			DataInfoBean db = DataInfoBean.createErrorDataInfoBean("无此用户");
			String error = db.object2Json();
			DebugUtility.p("result " + error);
			out.print(error);
			return;
		}
		
		boolean isBlow = Boolean.parseBoolean((String)request.getAttribute("isBlow"));
		
		Date date = new Date();
		if (null != photo.getPhoto())
		{
			String hardDiskStoragePath = path + DIR + File.separator
					+ userId + File.separator;
			String preFileName = Utilities.getPhotoNameDataFormat().format(date);
			String filename = preFileName + ".png";
			String zoomFileName = preFileName + "_zoom.png";
			String src = DIR + "/" + userId + "/" + filename;
			String zoomSrc = DIR + "/" + userId + "/" + zoomFileName;

			photo.setSrc(src);
			photo.setZoomSrc(zoomSrc);
			photo.setPath(hardDiskStoragePath);
			photo.setHardDiskZoomPath(hardDiskStoragePath + zoomFileName);
			photo.setHardDiskPath(hardDiskStoragePath + filename);
		}
		photo.setIsBlow(isBlow);
		photo.setContent(content);
		photo.setCreateTime(date);
		photo.setUser(user);

		PhotoService service = (PhotoService) AppliationContextUtility
				.getBean("photoService");
		String result = service.upload(photo);

		DebugUtility.p("result " + result);
		out.print(result);
		}
		catch(Exception e)
		{
			DataInfoBean info = DataInfoBean.createErrorDataInfoBean("上传失败");
			String result = info.object2Json();
			DebugUtility.p("result " + result);
			out.print(result);
		}

		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException
	{
		// Put your code here
	}

}
