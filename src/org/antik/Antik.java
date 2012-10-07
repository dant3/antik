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
