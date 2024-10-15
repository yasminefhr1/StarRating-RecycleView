package com.fihriyasmine.tp3_stars_recycleview;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // Lier les CircleImageView
        CircleImageView btnFilms = findViewById(R.id.btn_films);
        CircleImageView btnMeals = findViewById(R.id.btn_meals);
        CircleImageView btnAnimals = findViewById(R.id.btn_animals);
        CircleImageView btnCountries = findViewById(R.id.btn_countries);

        // Ajouter des listeners pour chaque CircleImageView
        btnFilms.setOnClickListener(v -> openListActivity("films"));
        btnMeals.setOnClickListener(v -> openListActivity("meals"));
        btnAnimals.setOnClickListener(v -> openListActivity("animals"));
        btnCountries.setOnClickListener(v -> openListActivity("countries"));
    }

    // Méthode pour ouvrir ListActivity avec la catégorie choisie
    private void openListActivity(String category) {
        Intent intent = new Intent(CategoryActivity.this, ListActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}
