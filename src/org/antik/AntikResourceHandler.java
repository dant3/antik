/*
 * AntikResourceHandler.java
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

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.ResourceHandler;

import android.util.*;

public class AntikResourceHandler extends ResourceHandler {


    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
      Log.e("AntikResourceHandler", "Target:[ " + target + " ]");

      if (target.equals("/") || target.equals("")) {
        Log.e("AntikResourceHandler", "modified handling");
        response.setStatus(404);
        baseRequest.setHandled(false);
      } else {
        Log.e("AntikResourceHandler", "default handling");
        super.handle(target, baseRequest, request, response);
      }

      Log.e("AntikResourceHandler", "contextPath: " + request.getContextPath() + ",  " + response.toString());

    }
}
