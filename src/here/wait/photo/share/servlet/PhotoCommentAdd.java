package here.wait.photo.share.servlet;

import here.wait.photo.share.service.CommentService;
import here.wait.photo.share.utils.AppliationContextUtility;
import here.wait.photo.share.utils.DebugUtility;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PhotoCommentAdd extends HttpServlet
{

	public PhotoCommentAdd()
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

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		int photoId = Integer.parseInt(request.getParameter("photoId"));
		int ownerId = Integer.parseInt(request.getParameter("ownerId"));
		int toId = 0;
		String toIdStr = request.getParameter("toId");
		if (null != toIdStr && 0!=toIdStr.length())
			toId = Integer.parseInt(request.getParameter("toId"));
		String content = request.getParameter("content");
		String result = ((CommentService) AppliationContextUtility
				.getBean("commentService")).add(photoId, ownerId, toId,
				new Date(), content);

		DebugUtility.p("result " + result);
		out.print(result);

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
