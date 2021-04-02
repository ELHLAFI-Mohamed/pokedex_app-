package elhlafi.cs98.pokedex;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {
    private TextView nameTextView ;
    private TextView numberTextView ;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String name =getIntent().getStringExtra("name");
        int number =getIntent().getIntExtra("number",0);

        nameTextView=findViewById(R.id.pokemon_name);
        numberTextView=findViewById(R.id.pokemon_number);

        nameTextView.setText(name);
        numberTextView.setText(Integer.toString(number));





    }
}
