package mjsquared.r2d2yrobo;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Miles on 2/28/2018.
 */

public class backgroundDatabaseHelper extends AsyncTask<String,Void,String>{

    private Context context;
    private AlertDialog alertDialog;
    public String outcome;
    backgroundDatabaseHelper(Context cntxt){
        context = cntxt;
    }
    backgroundDatabaseHelper(){}
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://10.117.131.134/login.php";
        String signup_url = "http://10.117.131.134/signup.php";
        String request_url = "http://10.117.131.134/request.php";
        String result = "";
        if (type.equals("login")) {
            try {
                String email = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(
                        new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("email", "UTF-8") + "=" +
                        URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" +
                        URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(inputStream, "iso-8859-1")
                );
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;

        } else if (type.equals("signup")) {
            try {
                String name = params[1];
                String email = params[2];
                String password = params[3];
                URL url = new URL(signup_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(
                        new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data =
                        URLEncoder.encode("name", "UTF-8") + "=" +
                                URLEncoder.encode(name, "UTF-8") + "&" +
                                URLEncoder.encode("email", "UTF-8") + "=" +
                                URLEncoder.encode(email, "UTF-8") + "&" +
                                URLEncoder.encode("password", "UTF-8") + "=" +
                                URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(inputStream, "iso-8859-1")
                );
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        } else if (type.equals("request")) {
            try {
                String email = params[1];
                String item = params[2];
                String roomNum = params[3];
                URL url = new URL(request_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(
                        new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data =
                        URLEncoder.encode("email", "UTF-8") + "=" +
                                URLEncoder.encode(email, "UTF-8") + "&" +
                                URLEncoder.encode("item", "UTF-8") + "=" +
                                URLEncoder.encode(item, "UTF-8") + "&" +
                                URLEncoder.encode("roomnum", "UTF-8") + "=" +
                                URLEncoder.encode(roomNum, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(inputStream, "iso-8859-1")
                );
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
        //alertDialog.setMessage("Jas why did you make your \nemail so long!!?");
        //alertDialog.show();
    }

    @Override
    protected void onPostExecute(String result) {
       // alertDialog.setMessage(result);
        //alertDialog.show();

        StringBuilder st = new StringBuilder(result);
        //System.out.println(result);
        outcome = st.toString();//st.toString();
        //returnStatus();

        //System.out.println(outcome);
        bgLogin_Authen bgLogin_authen = new bgLogin_Authen();
        bgLogin_authen.execute();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    public String returnStatus(){
        //String toBeReturned = outcome;
        //System.out.println(outcome);
        return outcome;
    }
}
