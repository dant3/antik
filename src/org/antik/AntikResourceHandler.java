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
