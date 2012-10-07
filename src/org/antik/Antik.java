package org.antik;

//java
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
//android
import android.util.Log;

public class Antik {
    private static final String TAG = "Antik";

    public static String getLocalIpAddress() {
      try {
        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();)
        {
          NetworkInterface intf = en.nextElement();
          for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
          {
            InetAddress inetAddress = enumIpAddr.nextElement();
            Inet4Address inetV4Address = (Inet4Address) inetAddress;
              if (!inetAddress.isLoopbackAddress() && inetV4Address != null) {
                return inetV4Address.getHostAddress().toString();
              }
            }
          }
	    } catch (SocketException ex) {
	        Log.e(TAG, ex.toString());
	    }
	    return null;
	}
}
