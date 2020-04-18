package com.example.meetfinance;

import android.content.Context;
import android.util.Log;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class ApiRequest {
    private RequestQueue queue;
    private Context context;

    public ApiRequest(RequestQueue queue, Context context) {
        this.context = context;
        this.queue = queue;
    }

    public void checkCompanyName(final String symbol, final ApiRequest.CheckCompanyCallback callback) {
        String url = "https://financialmodelingprep.com/api/v3/stock/real-time-price/" + symbol;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("APP", response.toString());
                try {
                    JSONObject json = response.getJSONObject(symbol.toUpperCase());
                    String name = json.getString("symbol");
                    double price = json.getDouble("price");
                    callback.onSuccess(symbol, price);
                } catch (JSONException e) {
                    Log.d("APP", "EXCEPTION = " + e);
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError) {
                    callback.onError("Connection failed !");
                } else if (error instanceof ServerError) {
                    callback.notExist("This company doesn't exist !");
                }
                Log.d("APP", "error : " + error);
            }
        });

        queue.add(request);
    }

    public interface CheckCompanyCallback {
        void onSuccess(String symbol, double price);

        void notExist(String message);

        void onError(String message);
    }
}

