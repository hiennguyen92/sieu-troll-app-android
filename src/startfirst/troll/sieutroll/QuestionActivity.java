package startfirst.troll.sieutroll;

import java.io.IOException;
import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockFragmentActivity;

import startfirst.troll.sieutroll.database.DataBaseHelper;
import startfirst.troll.sieutroll.dto.CauHoi;
import startfirst.troll.sieutroll.fragment.FragmentQuestion;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

public class QuestionActivity extends SherlockFragmentActivity {
	

	DataBaseHelper DBHelper;
	ArrayList<CauHoi> _data;
	int Heart = 3;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        getSupportActionBar().hide();
        
        DBHelper = new DataBaseHelper(this);
        
        try {
			DBHelper.createDataBase();
			DBHelper.openDataBase();
			_data = DBHelper.GetAllCauHoi();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        FragmentTransaction mFragTransaction = getSupportFragmentManager().beginTransaction();
		mFragTransaction.replace(R.id.content_frame, FragmentQuestion.newFragment(_data,0,Heart,true,true,true,true)).commit();
        
        
        
    }

    
}
