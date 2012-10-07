package org.antik;

// android
import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.util.Log;
import android.content.Context;
import android.content.Intent;
// widgets
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    private static final String TAG = "Antik";

    // widgets
    Button m_startStopButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Log.e(TAG, "Antik MainActivity created");
        m_startStopButton = (Button) findViewById(R.id.startStopServerButton);

        m_startStopButton.setOnClickListener(new StartStopButtonListener());

        TextView ipText = (TextView) findViewById(R.id.yourIpText);
        ipText.setText(Antik.getLocalIpAddress());

        manageStartStopButtonState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity, menu);
        return true;
    }


    void manageStartStopButtonState() {
      if (isAntikServerServiceRunning()) {
        m_startStopButton.setText(R.string.stop_server);
      } else {
        m_startStopButton.setText(R.string.start_server);
      }
    }


    // start/stop button handler
    class StartStopButtonListener implements OnClickListener {
      public void onClick(View src) {
        switch (src.getId())
        {
          case R.id.startStopServerButton:
            if (!isAntikServerServiceRunning()) {
              Log.d(TAG, "onClick startStopServerButton: stopping service");
              startAntikServerService();
              manageStartStopButtonState();
            } else {
              Log.d(TAG, "onClick startStopServerButton: starting service");
              stopAntikServerService();
              manageStartStopButtonState();
            }
          break;
        }
      }
    }


    // service managing helpers
    private boolean isAntikServerServiceRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if ("org.antik.AntikServerService".equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private void startAntikServerService() {
      startService(new Intent(this, AntikServerService.class));
    }

    private void stopAntikServerService()  {
      stopService(new Intent(this, AntikServerService.class));
    }


}
