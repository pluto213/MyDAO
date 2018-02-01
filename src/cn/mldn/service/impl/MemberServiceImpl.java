package cn.mldn.service.impl;

import cn.mldn.service.IMemberService;

public class MemberServiceImpl implements IMemberService {

	@Override
	public boolean login(String name, String password) {
		return "mldn".equals(name) && "java".equals(password);
	}

}
