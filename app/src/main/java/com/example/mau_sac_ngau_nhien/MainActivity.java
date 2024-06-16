package com.example.mau_sac_ngau_nhien;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private View View1, View2, View3;
    private Button btnSave, btnLoad;
    private void findviews(){
        btnSave = findViewById(R.id.btnSave);
        btnLoad = findViewById(R.id.btnLoad);
        View1 = findViewById(R.id.View1);
        View2 = findViewById(R.id.View2);
        View3 = findViewById(R.id.View3);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findviews();

        Random_Color();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("my_save", MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putInt("color_view1",((ColorDrawable) View1.getBackground()).getColor());
                editor.putInt("color_view2",((ColorDrawable) View2.getBackground()).getColor());
                editor.putInt("color_view3",((ColorDrawable) View3.getBackground()).getColor());

                editor.apply();
            }
        });

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("my_save", MODE_PRIVATE);

                int View1_Color = sharedPreferences.getInt("color_view1", R.color.black);
                int View2_Color = sharedPreferences.getInt("color_view2", R.color.black);
                int View3_Color = sharedPreferences.getInt("color_view3", R.color.black);

                View1.setBackground(new ColorDrawable(View1_Color));
                View2.setBackground(new ColorDrawable(View2_Color));
                View3.setBackground(new ColorDrawable(View3_Color));
            }
        });
    }
    public void Random_Color(){
        int red = getResources().getColor(R.color.red);
        int green = getResources().getColor(R.color.green);
        int yellow = getResources().getColor(R.color.yellow);

        //Tao mot List cac colors
        List<Integer> colors = new ArrayList<>();
        colors.add(red);
        colors.add(green);
        colors.add(yellow);
        //Xao tron cac mau len
        Collections.shuffle(colors);

        View1.setBackground(new ColorDrawable(colors.get(0)));
        View2.setBackground(new ColorDrawable(colors.get(1)));
        View3.setBackground(new ColorDrawable(colors.get(2)));
    }
}