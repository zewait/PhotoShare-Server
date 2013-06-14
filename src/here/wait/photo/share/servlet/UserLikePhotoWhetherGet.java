package here.wait.photo.share.servlet;

import here.wait.photo.share.bean.DataInfoBean;
import here.wait.photo.share.service.UserLikePhotoService;
import here.wait.photo.share.utils.AppliationContextUtility;
import here.wait.photo.share.utils.DebugUtility;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserLikePhotoWhetherGet extends HttpServlet
{

	/**
	 * Constructor of the object.
	 */
	public UserLikePhotoWhetherGet()
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
		request.getParameter("utf-8");
		PrintWriter out = response.getWriter();

		String result;
		int userId = 0;
		int photoId = 0;
		try
		{
			userId = Integer.parseInt(request.getParameter("userId"));
			photoId = Integer.parseInt(request.getParameter("photoId"));
		} catch (Exception e)
		{
			e.printStackTrace();
			DataInfoBean info = DataInfoBean.createErrorDataInfoBean("请求数据不正确");
			result = info.object2Json();
			
			DebugUtility.p("result " + result);
			out.print(result);
			out.flush();
			out.close();
			return;
		}
		
		result = ((UserLikePhotoService)AppliationContextUtility.getBean("userLikePhotoService")).isLiked(userId, photoId);
		
		
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
