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
import eu.webtoolkit.jwt.Side;
import eu.webtoolkit.jwt.Signal;
import eu.webtoolkit.jwt.WBreak;
import eu.webtoolkit.jwt.WLineEdit;
import eu.webtoolkit.jwt.WPushButton;
import eu.webtoolkit.jwt.WText;

public class AntikWebInterfaceApplication extends WApplication {

    public AntikWebInterfaceApplication(WEnvironment env) {
        super(env);

        setTitle("Hello world");

        getRoot().addWidget(new WText("Your name, please ? "));
        final WLineEdit nameEdit = new WLineEdit(getRoot());
        nameEdit.setFocus();

        WPushButton button = new WPushButton("Greet me.", getRoot());
        button.setMargin(5, Side.Left);

        getRoot().addWidget(new WBreak());

        final WText greeting = new WText(getRoot());

        button.clicked().addListener(this, new Signal.Listener() {
            public void trigger() {
                greeting.setText("Hello there, " + nameEdit.getText());
            }
        });
    }

}
