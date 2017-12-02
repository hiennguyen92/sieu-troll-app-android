package startfirst.troll.sieutroll.database;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import startfirst.troll.sieutroll.dto.CauHoi;





import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper{

	 private static String DB_PATH;
	 
	    private static String DB_NAME = "databasecauhoi.db";
	 
	    private SQLiteDatabase myDataBase; 
	 
	    private final Context myContext;
	
	
	 public DataBaseHelper(Context context) {
		 
	    	super(context, DB_NAME, null, 1);
	        this.myContext = context;
	        DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
	 }

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		
	}
	
	
	
	public ArrayList<CauHoi> GetAllCauHoi() {
		ArrayList<CauHoi> uti = new ArrayList<CauHoi>();
		Cursor dataRusult = myDataBase.query("tablecauhoi", new String[]{"_id","cauhoi","cau_a","cau_b","cau_c","cau_d","dapan","giaithich","billgates","opama","giaosuxoay","ykienkhangia","nammuoi","doicauhoi"}, null, null, null, null, null);
		
		dataRusult.moveToFirst();
		while (!dataRusult.isAfterLast()) {
			CauHoi temp = new CauHoi();
			temp.set_id(dataRusult.getInt(0));
			temp.set_cauhoi(dataRusult.getString(1));
			temp.set_cau_a(dataRusult.getString(2));
			temp.set_cau_b(dataRusult.getString(3));
			temp.set_cau_c(dataRusult.getString(4));
			temp.set_cau_d(dataRusult.getString(5));
			temp.set_dapan(dataRusult.getString(6));
			temp.set_giaithich(dataRusult.getString(7));
			temp.set_BillGates(dataRusult.getString(8));
			temp.set_Opama(dataRusult.getString(9));
			temp.set_GSXoay(dataRusult.getString(10));
			temp.set_YKienKhanGia(dataRusult.getString(11));
			temp.set_NamMuoiNamMuoi(dataRusult.getString(12));
			temp.set_DoiCauHoi(dataRusult.getShort(13));
			uti.add(temp);

			dataRusult.moveToNext();
		}
		dataRusult.close();
		return uti;
	}
	
	
	


	
	
	

	

	
	
	
	
	
	public void createDataBase() throws IOException{
		 
    	boolean dbExist = checkDataBase();
 
    	if(dbExist){
    		//do nothing - database already exist
    		
    	}else{
 
    		//By calling this method and empty database will be created into the default system path
               //of your application so we are gonna be able to overwrite that database with our database.
        	this.getReadableDatabase();
 
        	try {
 
    			copyDataBase();
 
    		} catch (IOException e) {
 
        		throw new Error("Error copying database");
 
        	}
    	}
 
    }
	
	
	
	
	public void openDataBase() throws SQLException{
		 
    	//Open the database
        String myPath = DB_PATH + DB_NAME;
    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY);
 
    }
	
	@Override
	public synchronized void close() {
		if(myDataBase != null)
		    myDataBase.close();
		super.close();
	}
	
	
	
	private void copyDataBase() throws IOException{
		 
    	//Open your local db as the input stream
    	InputStream myInput = myContext.getAssets().open(DB_NAME);
 
    	// Path to the just created empty db
    	String outFileName = DB_PATH + DB_NAME;
 
    	//Open the empty db as the output stream
    	OutputStream myOutput = new FileOutputStream(outFileName);
 
    	//transfer bytes from the inputfile to the outputfile
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
 
    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
 
    }

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	
	
	public Boolean isInstall() {
		return checkDataBase();
	}
	
	
	
	private boolean checkDataBase(){
		 
    	SQLiteDatabase checkDB = null;
 
    	try{
    		String myPath = DB_PATH + DB_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY);
 
    	}catch(SQLiteException e){
 
    		//database does't exist yet.
 
    	}
 
    	if(checkDB != null){
 
    		checkDB.close();
 
    	}
 
    	return checkDB != null ? true : false;
    }
}
