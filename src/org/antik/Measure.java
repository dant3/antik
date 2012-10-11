/*
 * Measure.java
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

public class Measure {
  private Measureable m_measured;
  private String m_name;
  private static final String m_measureStrStartedFormat = "Measure [%s] started...";
  private static final String m_measureStrEndededFormat = "Measure [%s] finished. Result: %.03f sec";

  public Measure(String name, Measureable measured)
  {
    m_name = name;
    m_measured = measured;
  }


  public void start()
  {
    long startTime = System.nanoTime();
    Antik.log(String.format(m_measureStrStartedFormat, m_name));

    // ... the code being measured ...
    m_measured.run();

    double estimatedTimeSecs = ((double)(System.nanoTime() - startTime)) / 1000000000;
    Antik.log(String.format(m_measureStrEndededFormat, m_name, estimatedTimeSecs));
  }
}