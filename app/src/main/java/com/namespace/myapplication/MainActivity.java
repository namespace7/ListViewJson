package com.namespace.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    ArrayList<Integer> idArray = new ArrayList<Integer>();
    ArrayList<String> firstNameArray = new ArrayList<String>();
    ArrayList<String> lastNameArray = new ArrayList<String>();
    ArrayList<String> emailArray = new ArrayList<String>();
    private RequestQueue requestQueue;
    List<ItemsModel> itemsModelList = new ArrayList<>();
    ListView listView;
    String[] charSequence;
    private String[] lv_arr = {};
    private String[] lv_fname = {};
    private String[] lv_lname = {};
    private String[] lv_email = {};

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem menuItem = menu.findItem(R.id.search_view);

        SearchView searchView = (SearchView) menuItem.getActionView();
        return super.onCreateOptionsMenu(menu);



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //requesting json
        requestQueue = Volley.newRequestQueue(this);
        jsonParse();

        //delaying until data is fetched
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                Toast yourToast = Toast.makeText(getApplicationContext(), "Fetching data", Toast.LENGTH_SHORT);
                yourToast.show();
                display();
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("" + idArray + "\n" + firstNameArray + "\n" + lastNameArray + "\n" + emailArray);
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        }, 3000);

        final Handler handler1 = new Handler(Looper.getMainLooper());
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                //declearing listview

                ListView lv = (ListView) findViewById(R.id.listview);
                charSequence = new String[idArray.size()];
                for (int k = 0; k<idArray.size(); k++) {
                    charSequence[k] = String.valueOf(idArray.get(k));
                }

                // Convert ArrayList to array
                lv_fname = firstNameArray.toArray(new String[firstNameArray.size()]);
                lv_lname = lastNameArray.toArray(new String[lastNameArray.size()]);
                lv_email = emailArray.toArray(new String[emailArray.size()]);
               lv.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, charSequence));


            }
        }, 3000);
      //  setListAdapter(new ItemsModel(this, charSequence,lv_fname,lv_lname,lv_email));



    }


    private void jsonParse() {
        String url = "https://reqres.in/api/users";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i = 0; i <= jsonArray.length(); i++) {
                        JSONObject users = jsonArray.getJSONObject(i);
                        int id = users.getInt("id");
                        String email = users.getString("email");
                        String firstname = users.getString("first_name");
                        String lastName = users.getString("last_name");
                        String lastlName = users.getString("avatar");

                        idArray.add(id);
                        firstNameArray.add(firstname);
                        lastNameArray.add(lastName);
                        emailArray.add(email);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);

    }

    private void display() {

        Log.d("this is my idarray", "arr: " + idArray);
        Log.d("this is my fname array", "arr: " + firstNameArray);
        Log.d("this is my lname array", "arr: " + lastNameArray);
        Log.d("this is my emailarray", "arr: " + emailArray);
    }


}
