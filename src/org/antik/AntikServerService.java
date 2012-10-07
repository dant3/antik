package org.antik;

// android imports
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

// jetty  imports
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.servlet.*;

import org.antik.webui.filetreetable.*;
// wedbav imports
//import net.sf.webdav.*;

public class AntikServerService extends Service {
    //
    private static final String TAG = "AntikServerService";
    //
    private static final int    NOTIFICATION_ID = 1;

    Server _webServer;
    NotificationManager _nm;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "service created");
        // initialize service here - read config, etc
        try {
            _webServer = new Server(8088);


            ServletContextHandler servletContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
            servletContext.setContextPath("/");


            servletContext.addServlet(new ServletHolder(new AntikWebInterfaceServlet()) , "/");
            servletContext.addServlet(new ServletHolder(new FileTreeExample()) , "/fs/");

            _webServer.setHandler(servletContext);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        _nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.d(TAG, "starting service");
        createNotification();
        // start working here
        if (_webServer != null) {
            try {
                _webServer.start();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "service destroyed");
        _nm.cancelAll();
        // stop working and deinitialize service here
        if (_webServer != null) {
            try {
                _webServer.stop();
            } catch (Exception e) {
            // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void createNotification() {
        Notification notification = new Notification(R.drawable.ic_launcher, getString(R.string.app_name), System.currentTimeMillis());
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(getApplicationContext(),
                                                    MainActivity.class), Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR);
        notification.flags = Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;
        notification.setLatestEventInfo(this, getString(R.string.app_name), "http://" + Antik.getLocalIpAddress() + ":8088" , contentIntent);
        _nm.notify(NOTIFICATION_ID, notification);
    }
}
