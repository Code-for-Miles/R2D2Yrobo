package mjsquared.r2d2yrobo;

import android.os.AsyncTask;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import static android.R.attr.delay;

/**
 * Created by Miles on 4/4/2018.
 */

public class bgLogin_Authen extends AsyncTask<String,Void,String> {
    private String data = "";
    List<Contacts> contact;
    StringBuilder stringBuilder;


    @Override
    protected String doInBackground(String... params) {
        //String data = "";
        try {
            URL url = new URL("http://10.117.131.134/login_auth.php?user_id");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            stringBuilder = new StringBuilder();

            String line = "";
            String nullCheck = "null";
            while ((line = bufferedReader.readLine()) != null) {
                //line = bufferedReader.readLine();
                //stringBuilder.a
                stringBuilder.append(line);
                System.out.println(data);
                data = data + line;
            }
            //data = stringBuilder.toString().trim();
            //if(data.startsWith(nullCheck)){
            //  data = data.substring(nullCheck.length(),data.length());
            //}
        /*if (data.endsWith(nullCheck)){
            data = data.substring(0,data.length()- nullCheck.length());
        }*/


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        //
       //contact.add(contacts);


        LoginActivity.dataC = result;
       // populateData(result);

        //LoginActivity.contact = contact;
        //LoginActivity.contact = this.contact;
        //LoginActivity._signupLink.setText(data);
    }
    /*private void populateData(String data){
        try {
            //JSONObject object = new JSONObject(data);
            JSONArray array = new JSONArray(data);
            String email_db = "";
            String password_db = "";
            String name_db = "";
            int user_id = 0;
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                //System.out.println(object.get("email"));
                user_id = object.getInt("user_id");
                email_db = (String) object.get("email");
                password_db = (String) object.get("password");
                name_db = (String) object.get("name");

                Log.i("Name is ", name_db);
                //if(LoginActivity._emailText.getText().toString().equals(email_db)){
                  //  LoginActivity.factCheck = true;
                    //break;
                //}
                //Contacts contacts = new Contacts(user_id, email_db, password_db, name_db);
                //LoginActivity.contact.add(contacts);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }*/
}
