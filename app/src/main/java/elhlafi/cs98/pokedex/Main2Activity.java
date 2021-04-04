package elhlafi.cs98.pokedex;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity {
    private TextView nameTextView ;
    private TextView numberTextView ;
    private TextView typeTextView1;
    private TextView typeTextView2;
    private String url ;
    private RequestQueue requestQueue;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        requestQueue= Volley.newRequestQueue(getApplicationContext());

         url =getIntent().getStringExtra("url");


        nameTextView=findViewById(R.id.pokemon_name);
        numberTextView=findViewById(R.id.pokemon_number);
        typeTextView1=findViewById(R.id.pokemon_type1);
        typeTextView2=findViewById(R.id.pokemon_type2);
        load();


    }
    public void load(){
        typeTextView1.setText("");
        typeTextView2.setText("");

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    nameTextView.setText(response.getString("name"));
                    numberTextView.setText(Integer.toString(response.getInt("id")));
                    JSONArray typeEntries = response.getJSONArray("types");
                    for(int i=0;i<typeEntries.length();i++){
                        JSONObject typeEntry =typeEntries.getJSONObject(i);
                        int slot = typeEntry.getInt("slot");
                        String type =typeEntry.getJSONObject("type").getString("name");
                        if(slot==1){
                            typeTextView1.setText(type);
                        }
                        else if(slot==2){
                            typeTextView2.setText(type);
                        }

                    }

                } catch (JSONException ex) {
                    Log.e("elhlafi.cs98", "pokemon jason error ");
                }
            }
            }



        , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("elhlafi.cs98","pokemon details  error ",error );
            }
        });
requestQueue.add(request);
    }
}
