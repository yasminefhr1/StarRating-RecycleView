package com.fihriyasmine.tp3_stars_recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import com.fihriyasmine.tp3_stars_recycleview.adapter.StarAdapter;
import com.fihriyasmine.tp3_stars_recycleview.beans.Star;
import com.fihriyasmine.tp3_stars_recycleview.service.StarService;
import androidx.core.app.ShareCompat;

public class ListActivity extends AppCompatActivity {
    private List<Star> stars;
    private RecyclerView recyclerView;
    private StarAdapter starAdapter; // Correction : adapter correctement initialisé ici
    private StarService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Initialisation du service et de la liste des stars
        service = StarService.getInstance();
        stars = new ArrayList<>();

        // Initialiser les données de base
        init();

        // Lier le RecyclerView et configurer l'adaptateur
        recyclerView = findViewById(R.id.recycle_view);

        // Charger les éléments en fonction de la catégorie
        String category = getIntent().getStringExtra("category");
        loadItems(category);  // Charger les éléments dans la liste `stars`

        // Configurer l'adaptateur avec les données des stars
        starAdapter = new StarAdapter(this, stars);
        recyclerView.setAdapter(starAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (starAdapter != null) {
                    starAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.share) {
            String shareText = "Partagez votre texte ici"; // Remplacez par le texte que vous souhaitez partager
            shareText(shareText);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void shareText(String textToShare) {
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText(textToShare)
                .getIntent();
        if (shareIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(shareIntent);
        }
    }

    // Méthode d'initialisation des données
    public void init() {

    }

    // Méthode pour charger des données selon la catégorie

    private void loadItems(String category) {
        if (category.equals("films")) {
            stars.add(new Star("Inception", "https://www.fond-ecran.net/fonds/inception_004.jpg", 5));
            stars.add(new Star("Blue Beetle", "https://static1.srcdn.com/wordpress/wp-content/uploads/2023/07/blue-beetle-movie-poster.jpg", 5));
            stars.add(new Star("Dahmer", "https://m.media-amazon.com/images/M/MV5BYTQ0ODcyODUtMTA5ZS00YjgwLTg0ODUtOGNhMzQzNjM2MDg2XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg", 5));
            stars.add(new Star("Killer Heat", "https://m.media-amazon.com/images/M/MV5BYmI2MzhmZmEtZjMyZC00MzJiLTlkZTEtNGNhZDEwYWVmMWZhXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg", 5));
            stars.add(new Star("The Dark Knight", "https://images.prismic.io/batman-escape/Zi9QP93JpQ5PTO2O_the-dark-knight-mechant.jpeg?auto=format,compress", 5));
            stars.add(new Star("Avatar", "https://i.pinimg.com/736x/87/e9/9e/87e99eb0661a04d5350105727ac3be23.jpg",4));
            stars.add(new Star("The lord of the rings", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQwjvtARVQAFZvjgby4i1ev7I2h0OgHcG8YoQ&s",3));
        } else if (category.equals("meals")) {
            stars.add(new Star("Pasta", "https://www.lordbyronskitchen.com/wp-content/uploads/2022/02/FB-18-800x533.jpg", 5));
            stars.add(new Star("Burger", "https://th.bing.com/th/id/OIP.A0xoyQOdCXiqvKB8i9fQFgHaHa?pid=ImgDet&w=195&h=195&c=7", 4));
            stars.add(new Star("Sushi", "https://www.trendmantra.com/wp-content/uploads/2016/03/article205_1.jpg", 5));
            stars.add(new Star("Pizza", "https://images7.alphacoders.com/596/596343.jpg", 5));
            stars.add(new Star("Burrito","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmqGN1TWRvKGbvFR-rF2BrilTVz5yG2cIVSQ&s",4));
            stars.add(new Star("Rfissa","https://www.liligo.fr/magazine-voyage/wp-content/uploads/sites/42/2023/05/iStock-1470312888.jpg",5));
            stars.add(new Star("Tajine","https://lacuisinedelya.com/wp-content/uploads/2021/02/IMG_20210221_132327-02-scaled.jpeg",4));
        } else if (category.equals("animals")) {
            stars.add(new Star("Lion", "https://th.bing.com/th/id/OIP.KH14x2o0juyB3XFb5Y1s-QHaE8?rs=1&pid=ImgDetMain", 5));
            stars.add(new Star("Elephant", "https://th.bing.com/th/id/R.cd60cabbe3943f19321e2ccc8197f7a2?rik=D41hFxHaqYJb8g&riu=http%3a%2f%2fwww.baltana.com%2ffiles%2fwallpapers-2%2fIndian-Elephant-Background-Wallpaper-07945.jpg&ehk=wfmMY3VUg7C%2fsolgozv5ht4EPDMQKjG5c0YZc%2fWL7Ts%3d&risl=&pid=ImgRaw&r=0", 5));
            stars.add(new Star("Tiger", "https://th.bing.com/th/id/OIP.zClQzFgozroXIIvdsHz9mgHaGY?w=500&h=431&rs=1&pid=ImgDetMain", 5)); // Placeholder for the tiger image
            stars.add(new Star("Giraffe", "https://static.pexels.com/photos/158832/giraffe-reticulated-giraffe-neck-beautiful-158832.jpeg", 5)); // Placeholder for the giraffe image
            stars.add(new Star("Singe","https://i.pinimg.com/736x/ec/48/ed/ec48ed10c444d75268eb102a6bc883ba.jpg",4));
            stars.add(new Star("Poisson","https://i.pinimg.com/736x/a6/7e/0e/a67e0ea6f63a62b910ca2838b1699283.jpg",2));
            stars.add(new Star("Chien","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwyXeKDN29AmZgZPLS7n0Bepe8QmVappBwZCeA3XWEbWNdiDFB",3));
        } else if (category.equals("countries")) {
            stars.add(new Star("Maroc", "https://th.bing.com/th/id/OIP.QKIurMHxk2r4hBBUOckxlgHaKX?rs=1&pid=ImgDetMain", 5)); // Placeholder for the France image
            stars.add(new Star("Japan", "https://th.bing.com/th/id/OIP.COlACG6GLrkySGx2sHPlKgHaEo?rs=1&pid=ImgDetMain", 5)); // Placeholder for the Japan image
            stars.add(new Star("Brazil", "https://th.bing.com/th/id/OIP.AeM3Bt8UfUiEQc22Df6cjgHaEK?w=395&h=187&c=7&r=0&o=5&pid=1.7", 5)); // Placeholder for the Brazil image
            stars.add(new Star("Australia", "https://th.bing.com/th/id/R.8011d3e6b33a95316ffaaab4a0175f57?rik=qOsZjL3Bj1n5DA&riu=http%3a%2f%2fs1.bwallpapers.com%2fwallpapers%2f2014%2f05%2f29%2faustralia-flag_121222469.jpg&ehk=lF7JPgkJfxfdL%2fEtC85k1dvOEaFPGIvqvbS0dl4eQkI%3d&risl=&pid=ImgRaw&r=0", 3));
            stars.add(new Star("l’Argentine", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/Flag_of_Argentina.svg/1024px-Flag_of_Argentina.svg.png", 4));
            stars.add(new Star("l’Autriche","https://upload.wikimedia.org/wikipedia/commons/thumb/4/41/Flag_of_Austria.svg/1024px-Flag_of_Austria.svg.png",2));
            stars.add(new Star("Bahamas","https://upload.wikimedia.org/wikipedia/commons/thumb/9/93/Flag_of_the_Bahamas.svg/1280px-Flag_of_the_Bahamas.svg.png",3));

        }
    }
}