package programable.android.klerart.showuseronrecyclerview;import androidx.appcompat.app.AppCompatActivity;import androidx.recyclerview.widget.LinearLayoutManager;import androidx.recyclerview.widget.RecyclerView;import android.content.Intent;import android.os.Bundle;import android.view.View;import android.widget.Button;import programable.android.klerart.showuseronrecyclerview.Database.Database;import programable.android.klerart.showuseronrecyclerview.MyAdapter.SearchAdapter;public class Main2Activity extends AppCompatActivity {    Database database;    SearchAdapter adapter;    RecyclerView recyclerView;    RecyclerView.LayoutManager layoutManager;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_main2);        database=new Database(this);        recyclerView=findViewById(R.id.MyRecyleView);        adapter=new SearchAdapter(this,database.getAllStudent());        layoutManager=new LinearLayoutManager(this);        recyclerView.setLayoutManager(layoutManager);        recyclerView.setHasFixedSize(true);        recyclerView.setAdapter(adapter);    }}