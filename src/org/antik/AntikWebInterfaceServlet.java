/*
 * Antik.java
 *
 * Copyright (c) 2012 Vyacheslav Blinov <blinov . vyacheslav at gmail.com>.
 *
 * This file is part of Antik.
 *
 * Antik is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Antik is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Antik.  If not, see <http ://www.gnu.org/licenses/>.
 */

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
