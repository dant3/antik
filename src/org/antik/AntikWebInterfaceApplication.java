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
