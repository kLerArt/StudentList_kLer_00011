package programable.android.klerart.showuseronrecyclerview.Database;import android.content.ContentValues;import android.content.Context;import android.database.Cursor;import android.database.sqlite.SQLiteDatabase;import android.database.sqlite.SQLiteQueryBuilder;import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;import java.util.ArrayList;import java.util.List;import programable.android.klerart.showuseronrecyclerview.Modell.Student;public class Database extends SQLiteAssetHelper {    private static final String DB_NAME="Students.sqlite";    private static final int DB_Version=1;    private static final String StuId="ID";    private static final String OrginalId="OrginalId";    private static final String StuNAme="FullName";    private static final String StuIMG="IMGStudent";    private static final String StuSemester="Semester";    private static final String StuDepartment="Department";    private static final String StuGroup="StudentGroup";    private static final String StuCount="Count";    private static final String StuComment="Comment";    private static final String StuYearOfId="YearOfId";    private static final String StuSeason="Season";    private static final String TableName="Student";    Database database;    public long insertNote(String note,String Id,byte[] Img) {        // get writable database as we want to write data        SQLiteDatabase db = this.getWritableDatabase();        ContentValues values = new ContentValues();        // `id` and `timestamp` will be inserted automatically.        // no need to add them        values.put(StuId, Id);        values.put(StuNAme, note);        values.put(StuGroup, 1);        values.put(StuSemester, 1);        values.put(StuDepartment, 1);        values.put(StuSeason, 1);        values.put(StuYearOfId, 1);        values.put(StuIMG,Img);        String k="1";        long id = db.insert(TableName, null, values);        // insert row//        long id = db.update(TableName,  values,OrginalId +"=?",new String[]{String.valueOf(k)});        // close db connection        db.close();        // return newly inserted row id        return id;    }    public long UpdatNewData(String note,String Id,byte[] Img) {        // get writable database as we want to write data        SQLiteDatabase db = this.getWritableDatabase();        ContentValues values = new ContentValues();        // `id` and `timestamp` will be inserted automatically.        // no need to add them        values.put(StuId, Id);        values.put(StuNAme, note);        values.put(StuGroup, 1);        values.put(StuSemester, 1);        values.put(StuDepartment, 1);        values.put(StuSeason, 1);        values.put(StuYearOfId, 1);        values.put(StuIMG,Img);        String k="1";//        long id = db.insert(TableName, null, values);        // insert row        long id = db.update(TableName,  values,OrginalId +"=?",new String[]{k});        // close db connection        db.close();        // return newly inserted row id        return id;    }    public  long DeletRow(int Orginal_Id){        SQLiteDatabase db = this.getWritableDatabase();        long id=db.delete(TableName,OrginalId+"=?",new String[]{String.valueOf(Orginal_Id)});        return id;    }    public void addAlarm(String note)    {        SQLiteDatabase db = this.getWritableDatabase();        ContentValues values = new ContentValues();        values.put(OrginalId, 0);        values.put(StuId, 1993102);        values.put(StuNAme, note);        values.put(StuGroup, 1);        values.put(StuSemester, 1);        values.put(StuDepartment, 1);        values.put(StuSeason, 1);        values.put(StuYearOfId, 1);/*        values.put(KEY_DESC, alarm.getDesc());        values.put(KEY_REPEAT_DAY, alarm.getRepeatDay());        values.put(KEY_REPEAT_TYPE, alarm.getRepeatType());        values.put(KEY_CALENDAR, Long.toString(alarm.getCalendarInMillis()));        values.put(KEY_APP, alarm.getApp());        values.put(KEY_ACTIVE, alarm.getActive());*/        db.insert(TableName, null, values);        db.close();    }//    String[] SqlSelect={StuId,StuNAme,StuIMG,StuSemester,StuDepartment,StuGroup,StuCount,StuComment,StuYearOfId,StuSeason};    public void Database(){    }    public Database(Context context) {        super(context, DB_NAME, null, DB_Version);    }    public List<Student> getAllStudent(){        SQLiteDatabase db=getReadableDatabase();        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();//        String[] SqlSelect={"ID","FullName","IMGStudent","Semester","Department","StudentGroup"};        String[] SqlSelect={StuId,StuNAme,OrginalId,StuIMG,StuSemester,StuDepartment,StuGroup,StuCount,StuComment,StuYearOfId,StuSeason};        String tableName="Student";        qb.setTables(tableName);        Cursor cursor=qb.query(db,SqlSelect,null,null,null,null,null);        System.out.println("count : " +cursor.getCount());        List<Student> result=new ArrayList<>();        if(cursor.moveToFirst()){            do {                Student student=new Student();                student.setStudentId(cursor.getInt(cursor.getColumnIndex("ID")));                student.setStuName(cursor.getString(cursor.getColumnIndex("FullName")));                student.setStuSemester(cursor.getInt(cursor.getColumnIndex("Semester")));                student.setStuGroup(cursor.getInt(cursor.getColumnIndex("StudentGroup")));                student.setStuDepartment(cursor.getInt(cursor.getColumnIndex("Department")));                student.setImga(cursor.getBlob(cursor.getColumnIndex("IMGStudent")));                student.setOrginalId(cursor.getInt(cursor.getColumnIndex(OrginalId)));                result.add(student);            }while (cursor.moveToNext());        }        return result;    }    public List<Student> getAStudentBayg(){        SQLiteDatabase db=getReadableDatabase();        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();        String[] SqlSelect={StuId,StuNAme,OrginalId,StuIMG,StuSemester,StuDepartment,StuGroup,StuCount,StuComment,StuYearOfId,StuSeason};        String tableName="Student";        qb.setTables(tableName);        Cursor cursor=qb.query(db,SqlSelect," Semester LIKE 4",null,null,null,null);        System.out.println("count : " +cursor.getCount());        List<Student> result=new ArrayList<>();        if(cursor.moveToFirst()){            do {                Student student=new Student();                student.setStudentId(cursor.getInt(cursor.getColumnIndex("ID")));                student.setStuName(cursor.getString(cursor.getColumnIndex("FullName")));                student.setStuSemester(cursor.getInt(cursor.getColumnIndex("Semester")));                student.setStuGroup(cursor.getInt(cursor.getColumnIndex("StudentGroup")));                student.setStuDepartment(cursor.getInt(cursor.getColumnIndex("Department")));                student.setImga(cursor.getBlob(cursor.getColumnIndex("IMGStudent")));                result.add(student);            }while (cursor.moveToNext());        }        return result;    }    public List<Student> getDependTo(String SpacifyName){        SQLiteDatabase db=getReadableDatabase();        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();//        String[] SqlSelect={"ID","FullName","IMGStudent","Semester","Department","StudentGroup"};        String[] SqlSelect={StuId,StuNAme,OrginalId,StuIMG,StuSemester,StuDepartment,StuGroup,StuCount,StuComment,StuYearOfId,StuSeason};        // String[] SqlSelect={"ID","FullName","Semester","Group"};        String tableName="Student";        qb.setTables(tableName);        Cursor cursor=qb.query(db,SqlSelect,"FullName LIKE ? or ID LIKE ?",new String[]{"%"+SpacifyName+"%"},null,null,null);        System.out.println("count : " +cursor.getCount());        List<Student> result=new ArrayList<>();        if(cursor.moveToFirst()){            do {                Student student=new Student();                student.setStudentId(cursor.getInt(cursor.getColumnIndex(StuId)));                student.setStuName(cursor.getString(cursor.getColumnIndex(StuNAme)));                student.setStuSemester(cursor.getInt(cursor.getColumnIndex(StuSemester)));                student.setStuGroup(cursor.getInt(cursor.getColumnIndex(StuGroup)));                student.setStuDepartment(cursor.getInt(cursor.getColumnIndex(StuDepartment)));                student.setImga(cursor.getBlob(cursor.getColumnIndex(StuIMG)));                result.add(student);            }while (cursor.moveToNext());        }        return result;    }    //================================    //]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]    public List<Student> getDependToID(String SpacifyName){        SQLiteDatabase db=getReadableDatabase();        SQLiteQueryBuilder qb=new SQLiteQueryBuilder();        String[] SqlSelect={StuId,StuNAme,OrginalId,StuIMG,StuSemester,StuDepartment,StuGroup,StuCount,StuComment,StuYearOfId,StuSeason};        // String[] SqlSelect={"ID","FullName","Semester","Group"};        String tableName="Student";        qb.setTables(tableName);        Cursor cursor=qb.query(db,SqlSelect," ID = "+SpacifyName,null,null,null,null);        System.out.println("count : " +cursor.getCount());        List<Student> result=new ArrayList<>();        if(cursor.moveToFirst()){            do {                Student student=new Student();                student.setStudentId(cursor.getInt(cursor.getColumnIndex(StuId)));                student.setStuName(cursor.getString(cursor.getColumnIndex(StuNAme)));                student.setStuSemester(cursor.getInt(cursor.getColumnIndex(StuSemester)));                student.setStuGroup(cursor.getInt(cursor.getColumnIndex(StuGroup)));                student.setStuDepartment(cursor.getInt(cursor.getColumnIndex(StuDepartment)));                student.setImga(cursor.getBlob(cursor.getColumnIndex(StuIMG)));                result.add(student);            }while (cursor.moveToNext());        }        return result;    }    /*    public List<Student> UpdatRow(String name){        SQLiteDatabase db=this.getWritableDatabase();        ContentValues contentValues=new ContentValues();        SQLiteQueryBuilder gb=new SQLiteQueryBuilder();        String[] SqlSelect={StuId,StuNAme,OrginalId,StuIMG,StuSemester,StuDepartment,StuGroup,StuCount,StuComment,StuYearOfId,StuSeason};        String tableName="Student";        gb.setTables(tableName);        List<Student> result=new ArrayList<>();        Cursor cursor=gb.query(db,SqlSelect,"set Semester=1 WHERE"+StuDepartment+"=1",null,null,null,null);        String sql=" "+tableName+"SET NAME = kLER  WHERE ID = ?";        SQLiteStatement sqLiteStatement=db.compileStatement(sql);        return result;    }*/    //============not use not Modifed    //=========more from SQLite Database to ListView - Part 2_ Insert Data - Android Studio Tutorial.mp4    public boolean inserDataMethod (String data1){        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();        ContentValues contentValues=new ContentValues();        contentValues.put(StuNAme,data1);        contentValues.put(StuId,102247);        contentValues.put(StuDepartment,2);        contentValues.put(StuSemester,7);        contentValues.put(StuGroup,2);        contentValues.put(StuYearOfId,2019);        contentValues.put(StuSeason,2);        Long result=sqLiteDatabase.insert(TableName,null,contentValues);        return result!=-1; //if result equal to -1ndata Dosent insert    }}