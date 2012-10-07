package org.antik;

import eu.webtoolkit.jwt.WApplication;
import eu.webtoolkit.jwt.WEnvironment;
import eu.webtoolkit.jwt.WtServlet;

@SuppressWarnings("serial")
public class AntikWebInterfaceServlet extends WtServlet {

	public AntikWebInterfaceServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public WApplication createApplication(WEnvironment env) {
		// TODO Auto-generated method stub
		return new AntikWebInterfaceApplication(env);
	}

}
