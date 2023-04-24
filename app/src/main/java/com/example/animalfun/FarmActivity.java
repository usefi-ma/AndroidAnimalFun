package com.example.animalfun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class FarmActivity extends AppCompatActivity {
    private  RequestQueue requestQueue;

    private static FarmActivity mInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm);

        TextView res=(TextView) findViewById(R.id.textView3);
        mInstance=this;



//URL of the request we are sending
        String url = "https://api.api-ninjas.com/v1/animals?name=cheetah";
        String name = "cheetah";
        //String url = "https://api.api-ninjas.com/v1/animals?name={}".format(name);

        JsonObjectRequest jsonObjectReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                       // res.setText(response.toString());


                      try{
                            String title=response.getString("taxonomy");
                            res.setText(title);
                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        res.setText("error"+error);
                        //Failure Callback
                    }
                })

        {

            /** Passing some request headers* */
            @Override
            public Map getHeaders() throws AuthFailureError  {
                HashMap headers = new HashMap();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("X-Api-Key", "cLxu9UBwRvVkAAYmZwPHSA==f65xC5CC3HNP0xqa");
                return headers;
            }
        };

// Adding the request to the queue along with a unique string tag
        FarmActivity.getInstance().addToRequestQueue(jsonObjectReq,"headerRequest");





    }



    public static synchronized FarmActivity getInstance()
    {
        return mInstance;
    }
    public RequestQueue getRequestQueue()
    {
        if (requestQueue==null)
            requestQueue= Volley.newRequestQueue(getApplicationContext());

        return requestQueue;
    }

    public void addToRequestQueue(Request request, String tag)
    {
        request.setTag(tag);
        getRequestQueue().add(request);

    }
    public void cancelAllRequests(String tag)
    {
        getRequestQueue().cancelAll(tag);
    }






}