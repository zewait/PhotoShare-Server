package here.wait.photo.share.servlet;

import here.wait.photo.share.bean.User;
import here.wait.photo.share.dao.UserDAO;
import here.wait.photo.share.service.UserService;
import here.wait.photo.share.utils.AppliationContextUtility;
import here.wait.photo.share.utils.DebugUtility;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UserHeadPicUpload extends HttpServlet
{
	private final static String DIR = "photo_share";
	private final static String HEAD_PIC_NAME = "head.jpg";

	/**
	 * Constructor of the object.
	 */
	public UserHeadPicUpload()
	{
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy()
	{
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		User user = new User();
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
					user.setHeadPic(item);

				}
			}
		} catch (FileUploadException e)
		{
			e.printStackTrace();
		}
		
		int userId = Integer.parseInt((String)request.getAttribute("userId"));
		user.setId(userId);
		
		User u = ((UserDAO)AppliationContextUtility.getBean("userDAO")).get(userId);
		
		String hardDiskStoragePath = path + DIR + File.separator + userId + File.separator;
		File temp = new File(hardDiskStoragePath);
		if(!temp.exists())
			temp.mkdirs();
		String src = DIR + "/" + userId + "/" + HEAD_PIC_NAME;
		user.setHardPicDiskPath(hardDiskStoragePath + HEAD_PIC_NAME);
		user.setHeadPicSrc(src);
		
		String result = ((UserService)AppliationContextUtility.getBean("userService")).headPicUpload(user);
		
		DebugUtility.p("result " + result);
		out.print(result);
		
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException
	{
		// Put your code here
	}

}
