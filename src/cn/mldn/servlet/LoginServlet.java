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
		// 1�������û����������������
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		// 2���жϴ���Ĳ����Ƿ���ȷ�������в����ű�ʾ���Բ�����������ڽ��յĲ����д�����Ӧ�ðѴ��󽻸�login.jsp��ʾ
		// ����һ��map�������ڱ������еĴ�����ʾ��Ϣ��key����Ϊ�������ƣ�value����Ϊ������Ϣ
		Map<String, String> errors = new HashMap<String, String>();
		if (name == null || "".equals(name)) {
			errors.put("name", "��¼���Ʋ�����Ϊ�գ�");
		}
		if (password == null || "".equals(password)) {
			errors.put("password", "��¼���벻����Ϊ�գ�");
		}
		String url = "/login.jsp"; // ����һ����תҳ
		String msg = "�û���¼ʧ�ܣ�������û��������룡";
		// 3�����û�д�����Ӧ�ý���ҵ�����
		if (errors.size() == 0) { // ���еĴ��󱣴���errors�������棬����ü���û���������ʾ��ȷ
			IMemberService memberService = ServiceFactory.getIMemberServiceInstance();
			if (memberService.login(name, password)) {
				url = "/welcome.jsp"; // ��¼�ɹ���ת���ɹ�ҳ
				msg = "�û���¼�ɹ�����ӭ���٣�";
			}
		} else { // �����д�����Ϣ��Ӧ�ý�������Ϣ����login.jspҳ�渺����ʾ
			msg = null;
			request.setAttribute("errors", errors);
		}
		// ʹ��request���Դ�����Ϣ��JSPҳ���Ͻ�����ʾ
		request.setAttribute("msg", msg);
		// 4������ҵ����ִ�н��������������ת����ʾ��ҳ��
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
