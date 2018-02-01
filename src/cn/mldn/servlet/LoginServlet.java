package cn.mldn.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.mldn.factory.ServiceFactory;
import cn.mldn.service.IMemberService;

public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1、接收用户发送来的请求参数
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		// 2、判断传入的参数是否正确，必须有参数才表示可以操作，如果现在接收的参数有错误，则应该把错误交给login.jsp显示
		// 定义一个map集合用于保存所有的错误提示信息，key设置为参数名称，value设置为错误信息
		Map<String, String> errors = new HashMap<String, String>();
		if (name == null || "".equals(name)) {
			errors.put("name", "登录名称不允许为空！");
		}
		if (password == null || "".equals(password)) {
			errors.put("password", "登录密码不允许为空！");
		}
		String url = "/login.jsp"; // 设置一个跳转页
		String msg = "用户登录失败，错误的用户名或密码！";
		// 3、如果没有错误则应该进行业务层检测
		if (errors.size() == 0) { // 所有的错误保存在errors集合里面，如果该集合没有内容则表示正确
			IMemberService memberService = ServiceFactory.getIMemberServiceInstance();
			if (memberService.login(name, password)) {
				url = "/welcome.jsp"; // 登录成功跳转到成功页
				msg = "用户登录成功，欢迎光临！";
			}
		} else { // 现在有错误信息，应该将错误信息交给login.jsp页面负责显示
			msg = null;
			request.setAttribute("errors", errors);
		}
		// 使用request属性传递信息到JSP页面上进行显示
		request.setAttribute("msg", msg);
		// 4、根据业务层的执行结果来决定最终跳转的显示层页面
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
