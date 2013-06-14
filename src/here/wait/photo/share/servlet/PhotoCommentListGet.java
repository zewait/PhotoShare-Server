package here.wait.photo.share.servlet;

import here.wait.photo.share.service.CommentService;
import here.wait.photo.share.utils.AppliationContextUtility;
import here.wait.photo.share.utils.DebugUtility;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PhotoCommentListGet extends HttpServlet
{

	public PhotoCommentListGet()
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
		int photoId = Integer.parseInt(request.getParameter("photoId"));
		int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		String result = ((CommentService)AppliationContextUtility.getBean("commentService")).getList(photoId, pageIndex, pageSize);
		
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
