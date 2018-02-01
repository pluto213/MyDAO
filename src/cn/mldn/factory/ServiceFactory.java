package cn.mldn.factory;

import cn.mldn.service.IMemberService;
import cn.mldn.service.impl.MemberServiceImpl;

public class ServiceFactory {
	public static IMemberService getIMemberServiceInstance(){
		return new MemberServiceImpl();
	}
}
