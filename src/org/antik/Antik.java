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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Arrays;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
//android
import android.util.Log;
import android.content.Context;
import android.content.res.AssetManager;

public class Antik {
  public static final String TAG = "Antik";

  public static void log(String string)
  {
    Log.e(TAG, string);
  }

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
          log(ex.toString());
      }
    return null;
  }

  public static void deployResources(Context context, File resDir) throws IOException
  {

  }

  public static class ZipUtils {
  // --- ZipUtils

    public static void unzip(File file, File targetDir) throws ZipException, IOException
    {
      targetDir.mkdirs();
      ZipFile zipFile = new ZipFile(file);
      try {
          Enumeration<? extends ZipEntry> entries = zipFile.entries();
          while (entries.hasMoreElements()) {
              ZipEntry entry = entries.nextElement();
              File targetFile = new File(targetDir, entry.getName());
              if (entry.isDirectory()) {
                  targetFile.mkdirs();
              } else {
                  InputStream input = zipFile.getInputStream(entry);
                  try {
                      OutputStream output = new FileOutputStream(targetFile);
                      try {
                          FileUtils.copyFile(input, output);
                      } finally {
                          output.close();
                      }
                  } finally {
                      input.close();
                  }
              }
          }
      } finally {
          zipFile.close();
      }
    }

    public static void unzipAsset(Context context, String assetFilePath, File assetTargetFile, File unzipDir) throws ZipException, IOException
    {
      FileUtils.copyAsset(context, assetFilePath, assetTargetFile);
      unzip(assetTargetFile, unzipDir);
    }

    private static void unassetZipFile(Context context, File destFile) throws IOException
    {
      AssetManager am = context.getAssets();
      OutputStream os = new FileOutputStream(destFile);
      //resDir.createNewFile();
      byte []b = new byte[1024];
      int i, r;
      String []Files = am.list("");
      Arrays.sort(Files);
      for(i=1;i<10;i++)
      {
        String fn = String.format("resources.%d.zip", i);
        if(Arrays.binarySearch(Files, fn) < 0)
              break;
        InputStream is = am.open(fn);
        while((r = is.read(b)) != -1)
            os.write(b, 0, r);
        is.close();
      }
      os.close();
    }
  // --- ZipUtils
  }


  public static class FileUtils {
  // --- FileUtils
    public static void copyAsset(Context context, String assetName, File destFile) {
      AssetManager assetManager = context.getAssets();
      InputStream in = null;
      OutputStream out = null;
      try {
        in = assetManager.open(assetName, AssetManager.ACCESS_BUFFER);
        out = new FileOutputStream(destFile);
        copyFile(in, out);
        in.close();
        in = null;
        out.flush();
        out.close();
        out = null;
      } catch(Exception e) {
          log(e.getMessage());
      }
    }

    public static void copyFile(InputStream input, OutputStream output) throws IOException
    {
        byte[] buffer = new byte[4096];
        int size;
        while ((size = input.read(buffer)) != -1)
            output.write(buffer, 0, size);
    }
  // --- FileUtils
  }
}