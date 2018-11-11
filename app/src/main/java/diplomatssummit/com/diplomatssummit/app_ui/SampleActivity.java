package diplomatssummit.com.diplomatssummit.app_ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import diplomatssummit.com.diplomatssummit.R;

public class SampleActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        new RestTask().execute("GET");
        /*new RestTask().execute("PUT");*/
        setContentView(R.layout.betatest);
        initialiseWidgets();
    }

    public void initialiseWidgets(){
        Button button=findViewById(R.id.button);
        button.setText("GET");
        TextView tv=findViewById(R.id.tv);
    }

    private class RestTask extends AsyncTask<String, Boolean, String> {

        String actionType = "N. A";

        @Override
        protected String doInBackground(String... strings) {

            String response = null;

            try {

                actionType = strings[0];

                URL obj = new URL("https://diplomatssummit.com/volley/restactivity.php");
                HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

                con.setRequestMethod(actionType);
                con.setRequestProperty("Content-Type", "application/json");

                if(actionType.equalsIgnoreCase("PUT")) {

                    con.setDoOutput(true);
                    con.setDoInput(true);

                    String str =  "{\"flag\":false}";
                    OutputStream os = con.getOutputStream();
                    os.write(str.getBytes("UTF-8"));
                    os.close();
                }

                int responseCode = con.getResponseCode();
                InputStream in;

                if (responseCode >= 400)
                    in = con.getErrorStream();
                else
                    in = con.getInputStream();

                ByteArrayOutputStream result = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) != -1) {
                    result.write(buffer, 0, length);
                }

                in.close();

                response = result.toString("UTF-8");
            }
            catch (Exception e) {

                e.printStackTrace();
            }

            return response;
        }

        @Override
        protected void onPostExecute(String response) {

            Log.d(TAG, "web response - " + actionType + ": " + response);
            TextView tv=findViewById(R.id.tv);
            tv.setText(response);
        }
    }
}
