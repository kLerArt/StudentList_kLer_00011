package programable.android.klerart.showuseronrecyclerview.Trusha.kLerTry;import androidx.annotation.NonNull;import androidx.appcompat.app.AppCompatActivity;import android.content.Intent;import android.content.pm.PackageManager;import android.graphics.Bitmap;import android.graphics.BitmapFactory;import android.graphics.drawable.BitmapDrawable;import android.net.Uri;import android.os.Bundle;import android.widget.Button;import android.widget.EditText;import android.widget.ImageView;import android.widget.Toast;import java.io.ByteArrayOutputStream;import java.io.FileNotFoundException;import java.io.InputStream;import programable.android.klerart.showuseronrecyclerview.R;public class InsertDataActivityOrginal_kLer extends AppCompatActivity {    EditText  edtName,edtPrice;    ImageView imageView;    Button btnChoose,btnAdd,btnList;    SQLiteHelper sqLiteHelper;    final  int REQUEST_CODE_GALLERY=999;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_insert_data_orginal_k_ler);        btnChoose=findViewById(R.id.btnChoose);        //===========================================================================    }    //===========================================    public static byte[] imageViewToByte(ImageView image) {        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();        ByteArrayOutputStream stream = new ByteArrayOutputStream();        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);        byte[] byteArray = stream.toByteArray();        return byteArray;    }    @Override    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {        if(requestCode == REQUEST_CODE_GALLERY){            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){                Intent intent = new Intent(Intent.ACTION_PICK);                intent.setType("image/*");                startActivityForResult(intent, REQUEST_CODE_GALLERY);            }            else {                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();            }            return;        }        super.onRequestPermissionsResult(requestCode, permissions, grantResults);    }    @Override    protected void onActivityResult(int requestCode, int resultCode, Intent data) {        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){            Uri uri = data.getData();            try {                InputStream inputStream = getContentResolver().openInputStream(uri);                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);                imageView.setImageBitmap(bitmap);            } catch (FileNotFoundException e) {                e.printStackTrace();            }        }        super.onActivityResult(requestCode, resultCode, data);    }    //============================================    private void init(){        edtName = findViewById(R.id.edtName);        edtPrice = findViewById(R.id.edtPrice);        btnChoose = findViewById(R.id.btnChoose);        btnAdd = findViewById(R.id.btnAdd);        btnList = findViewById(R.id.btnList);        imageView = findViewById(R.id.imageView);    }}