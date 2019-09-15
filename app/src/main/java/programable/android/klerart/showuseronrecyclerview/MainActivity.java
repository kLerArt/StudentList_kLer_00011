package programable.android.klerart.showuseronrecyclerview;import androidx.appcompat.app.AppCompatActivity;import androidx.recyclerview.widget.LinearLayoutManager;import androidx.recyclerview.widget.RecyclerView;import android.content.Intent;import android.net.Uri;import android.os.Bundle;import android.view.View;import android.widget.Button;import java.io.InputStream;import programable.android.klerart.showuseronrecyclerview.Database.Database;import programable.android.klerart.showuseronrecyclerview.MyAdapter.SearchAdapter;public class MainActivity extends AppCompatActivity {    Database database;    SearchAdapter adapter;    RecyclerView recyclerView;    Button BtnView2Department,BasicVBtn,button;    RecyclerView.LayoutManager layoutManager;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_main);        button=findViewById(R.id.button);        button.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {               Intent intent=new Intent(MainActivity.this,StoreImageActivity.class);                //Uri uri=Da;              //  InputStream inputStream=getContentResolver().openInputStream();            }        });        BasicVBtn=findViewById(R.id.BasicVBtn);        BasicVBtn.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                startActivity(new Intent(MainActivity.this,BasicView.class));            }        });        BtnView2Department=findViewById(R.id.BtnView2Department);        BtnView2Department.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                startActivity(new Intent(MainActivity.this,Main2Activity.class));            }        });        database=new Database(this);        recyclerView=findViewById(R.id.MyRecyleView);        adapter=new SearchAdapter(this,database.getAStudentBayg());        layoutManager=new LinearLayoutManager(this);        recyclerView.setLayoutManager(layoutManager);        recyclerView.setHasFixedSize(true);        recyclerView.setAdapter(adapter);    }}