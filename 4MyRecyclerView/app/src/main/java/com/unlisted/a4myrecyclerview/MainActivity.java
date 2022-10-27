package com.unlisted.a4myrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvCategory;
    private ArrayList<President>list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCategory = (RecyclerView)findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);

        list = new ArrayList<>();
        list.addAll(PresidentData.getListData());

        showRecyclerList();


    }

    // Merubah nama bagian atas judul aplikasi menjadi nama mode tampilan (Mengganti String nama aplikasi).
    private void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }



    // Menampilkan Daftar Data Untuk Recycler dalam bentuk list
    private void showRecyclerList(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        ListPresidentAdapter listPresidentAdapter = new ListPresidentAdapter(this);
        listPresidentAdapter.setListPresident(list);
        rvCategory.setAdapter(listPresidentAdapter);

        // Listener untuk click setiap item yang ditampilkan
        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedPresident(list.get(position));
            }
        });
    }
    // Menampilkan Daftar Data Untuk Recycler dalam bentuk Grid
    private void showRecyclerGrid(){
        rvCategory.setLayoutManager(new GridLayoutManager(this, 2));
        GridPresidentAdapter gridPresidentAdapter = new GridPresidentAdapter(this);
        gridPresidentAdapter.setListPresident(list);
        rvCategory.setAdapter(gridPresidentAdapter);

        // Listener untuk click setiap item yang ditampilkan
        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedPresident(list.get(position));
            }
        });
    }
    // Menampilkan Daftar Data Untuk Recycler dalam bentuk Card
    private void showRecyclerCardView(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        CardViewPresidentAdapter cardViewPresidentAdapter = new CardViewPresidentAdapter(this);
        cardViewPresidentAdapter.setListPresident(list);
        rvCategory.setAdapter(cardViewPresidentAdapter);
    }

    private void showSelectedPresident(President president){                                                    // Digunakan untuk menampilkan Toast Ketika memilih (Melalui Panggil Fungsi)
        Toast.makeText(this, "Kamu memilih "+president.getName(), Toast.LENGTH_SHORT).show();
    }




    // Menampilkan Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String title = null;
        switch (item.getItemId()){
            case R.id.action_list:
                title = "Mode List";
                showRecyclerList();                                 // Klik menu dari atas menjalankan layout list (Fungsi dipanggil di class di atas)
                break;

            case R.id.action_grid:
                title = "Mode Grid";
                showRecyclerGrid();                                 // Klik menu dari atas menjalankan layout grid (Fungsi dipanggil di class di atas)
                break;

            case R.id.action_cardview:
                title = "Mode CardView";
                showRecyclerCardView();                             // Klik menu dari atas menjalankan layout card (Fungsi dipanggil di class di atas)
                break;
        }
        setActionBarTitle(title);
        return super.onOptionsItemSelected(item);
    }
}
class PresidentData {
    // Data Presiden Yang Ingin Ditampilkan
    public static String[][] data = new String[][]{             // Definisi Data yang akan tampil
            {"Soekarno", "Presiden Pertama RI", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/01/Presiden_Sukarno.jpg/418px-Presiden_Sukarno.jpg"},
            {"Soeharto", "Presiden Kedua RI", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/President_Suharto%2C_1993.jpg/468px-President_Suharto%2C_1993.jpg"},
            {"Bacharuddin Jusuf Habibie", "Presiden Ketiga RI", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f1/Bacharuddin_Jusuf_Habibie_official_portrait.jpg/520px-Bacharuddin_Jusuf_Habibie_official_portrait.jpg"},
            {"Abdurrahman Wahid", "Presiden Keempat RI", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/35/President_Abdurrahman_Wahid_-_Indonesia.jpg/486px-President_Abdurrahman_Wahid_-_Indonesia.jpg"},
            {"Megawati Soekarnoputri", "Presiden Kelima RI", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/President_Megawati_Sukarnoputri_-_Indonesia.jpg/540px-President_Megawati_Sukarnoputri_-_Indonesia.jpg"},
            {"Susilo Bambang Yudhoyono", "Presiden Keenam RI", "https://upload.wikimedia.org/wikipedia/commons/5/58/Presiden_Susilo_Bambang_Yudhoyono.png"},
            {"Joko Widodo", "Presiden Ketujuh RI", "https://upload.wikimedia.org/wikipedia/commons/1/1c/Joko_Widodo_2014_official_portrait.jpg"}
    };

    public static ArrayList<President> getListData(){           // Translate data Array menjadi data yanng busa dibaca oleh recycler
        President president = null;
        ArrayList<President> list = new ArrayList<>();
        for (int i = 0; i <data.length; i++) {
            president = new President();
            president.setName(data[i][0]);
            president.setRemarks(data[i][1]);
            president.setPhoto(data[i][2]);

            list.add(president);
        }

        return list;
    }
}
class President {                                              // Mendefinisikan data setiap president dalam bentuk Struct
    private String name, remarks, photo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
