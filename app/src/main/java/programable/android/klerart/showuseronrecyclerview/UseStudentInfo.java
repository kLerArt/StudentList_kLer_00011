package programable.android.klerart.showuseronrecyclerview;import android.content.Intent;import android.graphics.Bitmap;import android.graphics.BitmapFactory;import android.os.Bundle;import android.view.Display;import android.view.View;import android.widget.Button;import android.widget.ImageView;import android.widget.TextView;import android.widget.Toast;import androidx.appcompat.app.AppCompatActivity;import programable.android.klerart.showuseronrecyclerview.Database.Database;public class UseStudentInfo extends AppCompatActivity {    TextView StudenName,StudentGroup,StuId,StuSemester,StuDepartment,OrginalId;    ImageView MyImga;    String DepartmentNN,SemesterNN,GroupNN="";    Button DeletField,UpDataData;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_user_info);        UpDataData=findViewById(R.id.Updata);        DeletField=findViewById(R.id.DeletThis);        setTitle("helooo wee");        StudenName=findViewById(R.id.StuName);        StudentGroup=findViewById(R.id.StuGeoup);        StuId=findViewById(R.id.StuID);        StuSemester=findViewById(R.id.StuSemester);        OrginalId=findViewById(R.id.OrginalID);        StuDepartment=findViewById(R.id.StuDepartment);        MyImga=findViewById(R.id.MyImga);        UpDataData.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                Intent intent=getIntent();//                String Name=intent.getStringExtra("name");                int MyId=intent.getIntExtra("StID",0);                int StuSemesterI=intent.getIntExtra("StuSemester",0);                int StuDepartmentI=intent.getIntExtra("StDepartment",0);                int SgroupI=intent.getIntExtra("Sgroup",0);                int orginalID=  intent.getIntExtra("OrginalID",0);                Intent intentPut=new Intent(UseStudentInfo.this,ChangeStudentInfo.class);//        intentPut.putExtra("myImga", BitmapFactory.decodeByteArray(intent.getByteArrayExtra("myImga"),0,intent.getByteArrayExtra("myImga").length));                intentPut.putExtra("name",Name);                intentPut.putExtra("StID",MyId);                intentPut.putExtra("StuSemester",StuSemesterI);                intentPut.putExtra("StDepartment",StuDepartmentI);                intentPut.putExtra("Sgroup",SgroupI);                intentPut.putExtra("OrginalID",orginalID);                MyImga.buildDrawingCache();                Bitmap bitmap = MyImga.getDrawingCache();                intent.putExtra("BitmapImage", bitmap);//                intentPut.putExtra("myImga",BitmapFactory.decodeByteArray(intent.getByteArrayExtra("myImga"),0,intent.getByteArrayExtra("myImga").length));                startActivity(intentPut);            }        });        DeletField.setOnClickListener(new View.OnClickListener() {            Intent intent=getIntent();            int orginalID=  intent.getIntExtra("OrginalID",0);            Database database=new Database(getApplicationContext());            @Override            public void onClick(View view) {                Toast.makeText(UseStudentInfo.this,"heloo  OOOOO",Toast.LENGTH_LONG).show();                database.DeletRow(orginalID);                startActivity(new Intent(UseStudentInfo.this,MainActivity.class));            }        });        Intent intent=getIntent();        String Name=intent.getStringExtra("name");        int MyId=intent.getIntExtra("StID",0);        int StuSemesterI=intent.getIntExtra("StuSemester",0);        int StuDepartmentI=intent.getIntExtra("StDepartment",0);        int SgroupI=intent.getIntExtra("Sgroup",0);        int orginalID=intent.getIntExtra("OrginalID",0);        if(SgroupI==0){            GroupNN="الافتراضة";        }else        if(SgroupI==1){            GroupNN="الاولي";        }else        if(SgroupI==2){            GroupNN="الثانية";        }else        if(SgroupI==3){            GroupNN="الثالثة";        }else        if(SgroupI==4){            GroupNN="الرابعة";        }else        if(SgroupI==5){            GroupNN="الخامسة";        }else        if(SgroupI==6){            GroupNN="السادسة";        }else        if(SgroupI==7){            GroupNN="السابعة";        }else        if(SgroupI==8){            GroupNN="الثامنة";        }else{            GroupNN="غير مدرج";        }        if(StuSemesterI==1){            SemesterNN="الاول";        }else if(StuSemesterI==2){            SemesterNN="التاني";        }else if(StuSemesterI==3){            SemesterNN="الثالث";        }else if(StuSemesterI==4){            SemesterNN="الرابع";        }else if(StuSemesterI==5){            SemesterNN="الخامس";        }else if(StuSemesterI==6){            SemesterNN="السادس ";        }else if(StuSemesterI==7){            SemesterNN="السابع";        }else if(StuSemesterI==8){            SemesterNN="الثامن";        }else {            SemesterNN="غير مدرج";        }        if(StuDepartmentI==1){            DepartmentNN="تحكم";        }else if(StuDepartmentI==2){            DepartmentNN="أتصالات";        }else if(StuDepartmentI==3){            DepartmentNN="حاسب ";        }else if(StuDepartmentI==4){            DepartmentNN="عام ";        }else {            DepartmentNN="غير مدرج";        }        StudentGroup.setText("المجموعة :"+GroupNN);        OrginalId.setText("OrginalId:"+orginalID);        StuDepartment.setText("تخصص :"+DepartmentNN);        StuSemester.setText("الفصل :"+SemesterNN);        StudenName.setText("الاسم:"+Name);        StuId.setText(" رقم القيد"+MyId);        MyImga.setImageBitmap(BitmapFactory.decodeByteArray(intent.getByteArrayExtra("myImga"),0,intent.getByteArrayExtra("myImga").length));    }//================    private Void ScaleImg(ImageView img ,int pic){        Display screen =getWindowManager().getDefaultDisplay();        BitmapFactory.Options options=new BitmapFactory.Options();        options.inJustDecodeBounds=true;        BitmapFactory.decodeResource(getResources(), pic,options);        int imgWidth=options.outWidth;        int screenWidth=screen.getWidth();        if(screenWidth<imgWidth){            int ratio =Math.round((float)imgWidth/(float)screenWidth);            options.inSampleSize=ratio;        }        options.inJustDecodeBounds=false;        Bitmap ScaledImg=BitmapFactory.decodeResource(getResources(),pic,options);        img.setImageBitmap(ScaledImg);        return (null);    }    ///=/*    public void setScaleImage(EventAssetValueListenerView view){        // Get the ImageView and its bitmap        Drawable drawing = view.getDrawable();        Bitmap bitmap = ((BitmapDrawable)drawing).getBitmap();        // Get current dimensions        int width = bitmap.getWidth();        int height = bitmap.getHeight();        float xScale = ((float) 4) / width;        float yScale = ((float) 4) / height;        float scale = (xScale <= yScale) ? xScale : yScale;        Matrix matrix = new Matrix();        matrix.postScale(scale, scale);        Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);        BitmapDrawable result = new BitmapDrawable(scaledBitmap);        width = scaledBitmap.getWidth();        height = scaledBitmap.getHeight();        view.setImageDrawable(result);        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();        params.width = width;        params.height = height;        view.setLayoutParams(params);    }*/}//        StuDepartment.setText(MyId);// StudentGroup.setText(Sgroup);// StuSemester.setText("");// StuDepartment.setText("");//        Byte[] myImga=intent.get("myImga");//        String StuDepartmentI=;     /*   if(StuDepartmentI==1){            StuDepartment.setText("حاسب");        }else        if (StuDepartmentI == 4){            StuDepartment.setText("تخصص عام");            StuDepartment.setTextColor(Color.MAGENTA);        }        else if (StuDepartmentI == 1){            StuDepartment.setText("تخصص تحكم");            StuDepartment.setTextColor(Color.BLUE);        }        else if (StuDepartmentI == 2){            StuDepartment.setTextColor(Color.RED);            StuDepartment.setText("تخصص إتصالات");        }        else if (StuDepartmentI == 3){            StuDepartment.setText("تخصص حاسب");            StuDepartment.setTextColor(Color.CYAN);        }        else return;*/////        int position=intent.getExtras().getInt("position");//        holder.imga.setImageBitmap(BitmapFactory.decodeByteArray(students.get(position).getImga(),0,students.get(position).getImga().length));//this code need to mdify//        MyImga.setImageBitmap(BitmapFactory.decodeByteArray(MyImgaH,0,MyImgaH.length));//        StudenName.setText(student.getStuName());