package here.wait.photo.share.servlet;

import here.wait.photo.share.bean.Photo;
import here.wait.photo.share.service.PhotoService;
import here.wait.photo.share.utils.AppliationContextUtility;
import here.wait.photo.share.utils.DebugUtility;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PhotoList4UserIdGet extends HttpServlet
{
	
	public PhotoList4UserIdGet()
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
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		int userId = Integer.parseInt(request.getParameter("userId"));
		int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		
		String result = ((PhotoService)AppliationContextUtility.getBean("photoService")).getList(userId, pageIndex, pageSize);
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
