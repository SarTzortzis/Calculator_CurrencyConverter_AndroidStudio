package com.example.desquaredproject;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;



import org.json.JSONException;
import org.json.JSONObject;

public class ConverterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String api = "http://data.fixer.io/api/latest?access_key=8c3ee5b7673d8ecc22181c298aa57c49";
    //Our two Spinners
    Spinner fromSp,toSp;
    //User input
    EditText input;
    //TextView which will show the result
    TextView converted;
    //"Submit" button
    Button convertBtn;
    //currencyFrom,currencyTo variables are used to store the currencies that user has selected
    String currencyFrom,currencyTo;
    //positionFrom, positionTo variables are used to store the id of currencies in order to switch them
    int positionFrom,positionTo;
    //Change/Switch ImageButton
    ImageButton change;
    //currencies String Array stores 100 currencies
    String[] currencies = new String[100];
    //mQueue is used to do Requests
    private RequestQueue mQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        setupVariables();
        setupListeners();
    }

    private void setupVariables() {
        fromSp=findViewById(R.id.spinnerFrom);
        toSp=findViewById(R.id.spinnerTo);
        input=findViewById(R.id.amountFrom);
        converted=findViewById(R.id.amountTo);
        convertBtn=findViewById(R.id.convertBtn);
        change=findViewById(R.id.changeBtn);
        mQueue = Volley.newRequestQueue(this);

        //Requesting currencies from API
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, api,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    //jsonRates = rates json object
                    JSONObject jsonRates = response.getJSONObject("rates");
                    String myRates=jsonRates.toString();

                    //filtering keys => "AED"=1.3, -> AED
                    myRates=myRates.replaceAll("[{:},]","");
                    myRates=myRates.replaceAll("[0-9.]","");
                    myRates=myRates.replaceAll("\"\""," ");
                    myRates=myRates.replaceAll("\"","");

                    //Saving currencies to String Array in order to pass them to Spinner
                    currencies=myRates.split(" ");

                    //inserting the currencies to Spinners

                    ArrayAdapter<CharSequence> adapterFrom = new ArrayAdapter(ConverterActivity.this,
                            android.R.layout.simple_spinner_item,
                            currencies);
                    adapterFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    // Spinner with currencies to change from
                    fromSp.setAdapter(adapterFrom);
                    fromSp.setOnItemSelectedListener(ConverterActivity.this);

                    // Spinner with currencies to change to
                    toSp.setAdapter(adapterFrom);
                    toSp.setOnItemSelectedListener(ConverterActivity.this);

                }
               catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    private void setupListeners() {
        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //if there is an input
                if (!input.getText().toString().isEmpty()) {
                    //Parsing the user input to Double in order to do the conversion
                    double inputValue = Double.parseDouble(input.getText().toString());

                    //Request for conversion
                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, api, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                //jsonRates = Json Object with rates
                                JSONObject jsonRates = response.getJSONObject("rates");

                                //We need to Divide the currencyFrom value because fixer.io
                                //provides currency values with base=EUR
                                double fromValue = 1 / jsonRates.getDouble(currencyFrom);
                                double toValue = jsonRates.getDouble(currencyTo);
                                double sum = inputValue * toValue * fromValue;
                                if (currencyFrom.equals(currencyTo)) {
                                    // No need to change
                                    sum = inputValue;
                                }
                                //rounding the final result
                                sum = Math.round(sum * 100.0) / 100.0;
                                //Insert the result to TextView
                                converted.setText(inputValue + " " + currencyFrom + " = " + sum + " " + currencyTo);

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

                    mQueue.add(request);
                }
                else{
                    showToast("Please insert amount first.");
                }

            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromSp.setSelection(positionTo);
                toSp.setSelection(positionFrom);
                showToast("Currencies Switched");
            }
        });
    }

    @Override
    //Setting the positions of selected currencies
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId() == R.id.spinnerFrom) {
            currencyFrom = parent.getItemAtPosition(position).toString();
            positionFrom = position;
        } else if(parent.getId() == R.id.spinnerTo) {
            currencyTo = parent.getItemAtPosition(position).toString();
            positionTo = position;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void showToast(String message){
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context,message,Toast.LENGTH_SHORT);
        toast.show();
    }
}