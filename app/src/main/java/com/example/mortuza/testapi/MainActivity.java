package com.example.mortuza.testapi;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText us_name, us_phone, us_email;
    Button addContact, show_contact;
    JSONArray jsonArray;
    ArrayList<UserModel> Listarray;
    CustomAdapter adapter;
    ListView ls;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        us_name = (EditText) findViewById(R.id.u_name);
        us_phone = (EditText) findViewById(R.id.u_phone);
        us_email = (EditText) findViewById(R.id.u_email);
        addContact = (Button) findViewById(R.id.u_add_contact);
        show_contact = (Button) findViewById(R.id.showContact);
        ls = (ListView) findViewById(R.id.listView);
        show_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://192.168.0.107/TestDemo/action.php?f_name=Getdata", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("check", "Test" + response);
                        try {
                            jsonArray = new JSONArray(response);
                            Listarray = new ArrayList();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jo = jsonArray.getJSONObject(i);
                                String Id = jo.get("id").toString();
                                String name = jo.get("Name").toString();
                                String Phone = jo.get("Phone").toString();
                                String email = jo.get("Email").toString();
                                Listarray.add(new UserModel(Id, name, Phone, email));
                                Log.d("access", "" + Id);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("dsada", "check");

                        adapter = new CustomAdapter(Listarray, MainActivity.this);
                        ls.setAdapter(adapter);


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                        error.printStackTrace();

                    }

                });
                stringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                TestVolly.getInstance().addToRequest(stringRequest);
            }
        });


        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String u_name = us_name.getText().toString();
                final String u_phone = us_phone.getText().toString();
                final String u_email = us_email.getText().toString();
                final StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.0.107/TestDemo/action.php?f_name=Insert", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response", response);
                        if (response.equals("insert complete")) {
                            final Dialog dialog=new Dialog(MainActivity.this);
                            dialog.setContentView(R.layout.customdialog);


                        }

                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                    }


                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        HashMap<String, String> hm = new HashMap<String, String>();
                        hm.put("name", u_name);
                        hm.put("phone", u_phone);
                        hm.put("email", u_email);
                        return hm;
                    }
                };

                request.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                TestVolly.getInstance().addToRequest(request);

            }

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
