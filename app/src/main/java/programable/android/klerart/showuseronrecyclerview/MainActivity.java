package programable.android.klerart.showuseronrecyclerview;import android.content.Intent;import android.content.pm.PackageManager;import android.graphics.Bitmap;import android.graphics.BitmapFactory;import android.graphics.drawable.BitmapDrawable;import android.net.Uri;import android.os.Bundle;import android.view.Gravity;import android.view.View;import android.widget.Button;import android.widget.EditText;import android.widget.ImageView;import android.widget.Toast;import androidx.annotation.NonNull;import androidx.appcompat.app.AppCompatActivity;import androidx.recyclerview.widget.LinearLayoutManager;import androidx.recyclerview.widget.RecyclerView;import java.io.ByteArrayOutputStream;import java.io.FileNotFoundException;import java.io.InputStream;import programable.android.klerart.showuseronrecyclerview.Database.Database;import programable.android.klerart.showuseronrecyclerview.MyAdapter.SearchAdapter;import programable.android.klerart.showuseronrecyclerview.Trusha.kLerTry.LogInActivity;////public class MainActivity extends AppCompatActivity {    Database database;    EditText StudentID;    final int REQUEST_CODE_GALLERY = 999;    SearchAdapter adapter;    RecyclerView recyclerView;    ImageView imageView;    Button BtnView2Department,QueryID,button,otherBtn,OtherBtnEx,newActivity;    RecyclerView.LayoutManager layoutManager;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_main);        StudentID=findViewById(R.id.StudentID);       /* QueryID=findViewById(R.id.QueryID);        QueryID.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                ActivityCompat.requestPermissions(                        MainActivity.this,                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},                        REQUEST_CODE_GALLERY                );            }        });*/        newActivity=findViewById(R.id.newActivity);        button=findViewById(R.id.LogInForum);        button.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {               startActivity(new Intent(MainActivity.this, LogInActivity.class));                //Uri uri=Da;              //  InputStream inputStream=getContentResolver().openInputStream();            }        });        final Database databasee=new Database(getApplication());        BtnView2Department=findViewById(R.id.BtnView2Department);        BtnView2Department.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                startActivity(new Intent(MainActivity.this,InsertStudentData.class));            }        });        database=new Database(this);        recyclerView=findViewById(R.id.MyRecyleView);        adapter=new SearchAdapter(this,database.getAStudentBayg());        layoutManager=new LinearLayoutManager(this);        recyclerView.setLayoutManager(layoutManager);        recyclerView.setHasFixedSize(true);        recyclerView.setAdapter(adapter);        ////////////////////////////////////////////////////////////        otherBtn=findViewById(R.id.OtherBtn);        otherBtn.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                startActivity(new Intent(MainActivity.this, SearchIdViewCustom.class));            }        });//        recyclerView.addOnItemTouchListener();        //recyclerView.setOnFlingListener(new AdapterView.AdapterContextMenuInfo(int ));    }    public void NewActivity(View view){        Intent intent=new Intent(this,MainActivity.class);        intent.putExtra("heloo",StudentID.getText().toString());        startActivity(intent);    }    public void ButNewData(View view){        Database database=new Database(getApplicationContext());        String N="kLerArtArt";//        database.insertNote(imageViewToByte(imageView));        Toast.makeText(this, ""+N, Toast.LENGTH_SHORT).show();        return;    }    //=============================================    @Override    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {        if(requestCode == REQUEST_CODE_GALLERY){            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){                Intent intent = new Intent(Intent.ACTION_PICK);                intent.setType("image/*");                startActivityForResult(intent, REQUEST_CODE_GALLERY);            }            else {                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();            }            return;        }        super.onRequestPermissionsResult(requestCode, permissions, grantResults);    }    @Override    protected void onActivityResult(int requestCode, int resultCode, Intent data) {        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){            Uri uri = data.getData();            try {                InputStream inputStream = getContentResolver().openInputStream(uri);                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);               imageView.setImageBitmap(bitmap);            } catch (FileNotFoundException e) {                e.printStackTrace();            }        }}    public static byte[] imageViewToByte(ImageView image) {        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();        ByteArrayOutputStream stream = new ByteArrayOutputStream();        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);        byte[] byteArray = stream.toByteArray();        return byteArray;    }    //======================================    public void ViewViewRadioInsert(View view){        startActivity(new Intent(MainActivity.this,SearchViewCustom.class));    }    //=====================================    public void ViewbViewRadioInsert(View view){        Database database=new Database(getApplicationContext());        try {            String N="kLerArtArt Dazy";            database.addAlarm(N);            Toast toast=  Toast.makeText(getApplicationContext(),"Position Center "+N,Toast.LENGTH_LONG);            toast.setGravity(Gravity.CENTER,0,0);            toast.show();        }catch (Exception e){            Toast.makeText(getApplicationContext(),"Position Center "+e.getMessage(),Toast.LENGTH_LONG);            StudentID.setText(e.getMessage());        }    }    public void SetMain(View view){        startActivity(new Intent(MainActivity.this,MainActivity.class));    }}