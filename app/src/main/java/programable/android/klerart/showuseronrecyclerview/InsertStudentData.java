package programable.android.klerart.showuseronrecyclerview;import android.Manifest;import android.content.Intent;import android.content.pm.PackageManager;import android.graphics.Bitmap;import android.graphics.BitmapFactory;import android.graphics.drawable.BitmapDrawable;import android.net.Uri;import android.os.Bundle;import android.view.View;import android.widget.Button;import android.widget.CheckBox;import android.widget.EditText;import android.widget.ImageView;import android.widget.RadioButton;import android.widget.RadioGroup;import android.widget.Spinner;import android.widget.TextView;import android.widget.Toast;import androidx.annotation.NonNull;import androidx.appcompat.app.AppCompatActivity;import androidx.core.app.ActivityCompat;import java.io.ByteArrayOutputStream;import java.io.FileNotFoundException;import java.io.InputStream;import programable.android.klerart.showuseronrecyclerview.Database.Database;public class InsertStudentData extends AppCompatActivity {    final  int REQUEST_CODE_GALLERY=999;    //extra Try    RadioGroup radioSexGroup;    //    EditText edtName,editID;    ImageView imageView;    Button InsertBtn;    int Smester,mGroup,Department,StuSeason,SYStudentYear=0;    RadioGroup Seson;    RadioButton SeasoonSelected;    Spinner SSimester,SGroup,SDepartment,SStudentYear;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_insert_student_data);        init();        InsertBtn.setOnClickListener(new View.OnClickListener() {            Database database=new Database(getApplicationContext());            @Override            public void onClick(View view) {                if (!checkMyFields()){                    return;                }                if ( SDepartment.getSelectedItem().toString()=="تحكم"){                    Department=1;                }else  if (SDepartment.getSelectedItem().toString()=="أتصالات"){                    Department=2;                }else                if ( SDepartment.getSelectedItem().toString()=="احاسب"){                    Department=3;                }else  if (SDepartment.getSelectedItem().toString()=="عام"){                    Department=4;                }else Department=0;                if ( SGroup.getSelectedItem().toString()=="المجموعة الاول"){                    mGroup=1;                }else  if (SGroup.getSelectedItem().toString()=="المجموعة التاني"){                    mGroup=2;                }else                if ( SGroup.getSelectedItem().toString()=="المجموعة الثالث"){                    mGroup=3;                }else  if (SGroup.getSelectedItem().toString()=="المجموعة الرابع"){                    mGroup=4;                }else if ( SGroup.getSelectedItem().toString()=="المجموعة الخامس"){                    mGroup=5;                }else  if (SGroup.getSelectedItem().toString()=="المجموعة السادس"){                    mGroup=6;                }else     if ( SGroup.getSelectedItem().toString()=="المجموعة السابع"){                    mGroup=7;                }else  if (SGroup.getSelectedItem().toString()=="المجموعة الثامن"){                    mGroup=8;                }else if (SGroup.getSelectedItem().toString()=="المجموعة غير مدرجة"){                mGroup=0;  }                //semester                if ( SSimester.getSelectedItem().toString()=="الفصل الاول"){                    Smester=1;                }else if (SSimester.getSelectedItem().toString()=="الفصل التاني"){                    Smester=2;                }else                if ( SSimester.getSelectedItem().toString()=="الفصل الثالث"){                    Smester=3;                }else  if (SSimester.getSelectedItem().toString()=="الفصل الرابع"){                    Smester=4;                }else if ( SSimester.getSelectedItem().toString()=="الفصل الخامس"){                    Smester=5;                }else  if (SSimester.getSelectedItem().toString()=="الفصل السادس"){                    Smester=6;                }else  if ( SSimester.getSelectedItem().toString()=="الفصل السابع"){                    Smester=7;                }else if (SSimester.getSelectedItem().toString()=="الفصل الثامن"){                    Smester=8;                }else  Smester=0;                if (SeasoonSelected.getText()=="خريفي"){                    StuSeason=1;                }else  if (SeasoonSelected.getText()=="ربيعي"){                    StuSeason=2;                }else StuSeason=0;//                SYStudentYear=SStudentYear.getSelectedItem().toString().trim();                if (SStudentYear.getSelectedItem().toString()=="2020"){                    SYStudentYear=2020;                }else  if (SeasoonSelected.getText()=="2021"){                    SYStudentYear=2021;                }else                if (SStudentYear.getSelectedItem().toString()=="2022"){                    SYStudentYear=2022;                }else  if (SeasoonSelected.getText()=="2023"){                    SYStudentYear=2023;                }else if (SStudentYear.getSelectedItem().toString()=="2018"){                    SYStudentYear=2018;                }else  if (SeasoonSelected.getText()=="2019"){                    SYStudentYear=2019;  }else SYStudentYear=2020;                try {//                   int Smester,mGroup,Department,StuSeason=0;getSelectedItemPosition()      SSimester,SGroup,SDepartment,SStudentYear;                    database.inserStudentDataInfo(edtName.getText().toString().trim(),editID.getText().toString(),                            StuSeason,SYStudentYear,SDepartment.getSelectedItemPosition(),SSimester.getSelectedItemPosition(),SGroup.getSelectedItemPosition(),imageViewToByte(imageView));                    Toast.makeText(InsertStudentData.this,"U got This"+  SSimester.getSelectedItemPosition(),Toast.LENGTH_LONG).show();//                    database.insertNote(edtName.getText().toString().trim(),editID.getText().toString(),imageViewToByte(imageView));                }catch (Exception e){                    e.getMessage();                    Toast.makeText(InsertStudentData.this,"error is "+e.getMessage(),Toast.LENGTH_LONG).show();                }            }        });        imageView.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                ActivityCompat.requestPermissions(                        InsertStudentData.this,                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},                        REQUEST_CODE_GALLERY                );            }        });    }    //==================Old But Like Gold    @Override    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {        if(requestCode == REQUEST_CODE_GALLERY){            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){                Intent intent = new Intent(Intent.ACTION_PICK);                intent.setType("image/*");                startActivityForResult(intent, REQUEST_CODE_GALLERY);            }            else {                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();            }            return;        }        super.onRequestPermissionsResult(requestCode, permissions, grantResults);    }    @Override    protected void onActivityResult(int requestCode, int resultCode, Intent data) {        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){            Uri uri = data.getData();            try {                InputStream inputStream = getContentResolver().openInputStream(uri);                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);                imageView.setImageBitmap(bitmap);            } catch (FileNotFoundException e) {                e.printStackTrace();            }        }    }    public static byte[] imageViewToByte(ImageView image) {        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();        ByteArrayOutputStream stream = new ByteArrayOutputStream();        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);        byte[] byteArray = stream.toByteArray();        return byteArray;    }//=========================extra Try    private void init(){        radioSexGroup = findViewById(R.id.radioSex);        InsertBtn=findViewById(R.id.InsertBtn);        edtName = findViewById(R.id.edtName);        editID = findViewById(R.id.editID);        SSimester=findViewById(R.id.editSemester);        SDepartment=findViewById(R.id.editDepartment);        SGroup=findViewById(R.id.editGroup);        SStudentYear=findViewById(R.id.StudentYear);        Seson=findViewById(R.id.radioSeason);        int selectedId = Seson.getCheckedRadioButtonId();        SeasoonSelected= findViewById(selectedId);        imageView = findViewById(R.id.StudentIMG);    }//     if (!checkFields()){//        return;//    }    private boolean checkMyFields() {        // check if all questions are answered        boolean isQuestionsAnswered = true;        if (edtName.getText().toString().isEmpty()) {            edtName.setError("أدخل أسم الطالب");            isQuestionsAnswered = false;        } else {            edtName.setError(null);        }        if (editID.getText().toString().isEmpty()){            editID.setError("أدخل رقم القيد");            isQuestionsAnswered = false;        }else {            editID.setError(null);        }        return isQuestionsAnswered;    }    //==================================================    TextView Q1,Q2,Q3,Q4,Q5;    RadioGroup Q1_Gr, Q4_Gr;    EditText Q2_Asnwer;    CheckBox Q3_England, Q3_Virgin, Q3_Northen, Q3_Wales, Q3_Australia;    EditText Q5_Answer;    private boolean checkFields(){        // check if all questions are answered        boolean isQuestionsAnswered = true;        if(edtName.getText().toString().isEmpty()){            edtName.setError("enter Data");            isQuestionsAnswered=false;        }else  {            edtName.setError(null);        }        if (Q5_Answer.getText().toString().isEmpty()){            Q5.setError(getString(R.string.pleaseWrite));            Q5.requestFocus();            isQuestionsAnswered = false;        } else {            Q5.setError(null);        }        if (Q4_Gr.getCheckedRadioButtonId() == -1){            Q4.setError(getString(R.string.PleaseCheck));            Q4.requestFocus();            isQuestionsAnswered = false;        } else {            Q4.setError(null);        }        if (!Q3_Australia.isChecked() && !Q3_England.isChecked() && !Q3_Northen.isChecked() &&                !Q3_Virgin.isChecked() && !Q3_Wales.isChecked()){            Q3.setError(getString(R.string.pleaseCheckatLeast));            Q3.requestFocus();            isQuestionsAnswered = false;        } else {            Q3.setError(null);        }        if (Q2_Asnwer.getText().toString().isEmpty()){            Q2.setError(getString(R.string.pleaseWrite));            Q2.requestFocus();            isQuestionsAnswered = false;        } else {            Q2.setError(null);        }        if (Q1_Gr.getCheckedRadioButtonId() == -1){            Q1.setError(getString(R.string.PleaseCheck));            Q1.requestFocus();            isQuestionsAnswered = false;        } else {            Q1.setError(null);        }        return isQuestionsAnswered;    }}