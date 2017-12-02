package startfirst.troll.sieutroll;


import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppAd.AdMode;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private StartAppAd startAppAd = new StartAppAd(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StartAppAd.init(this, "103675401", "203486583");
		setContentView(R.layout.activity_main);
		startAppAd.loadAd(AdMode.AUTOMATIC);
		Button btnStart = (Button)findViewById(R.id.btnStart);
		btnStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent iStart = new Intent(MainActivity.this, QuestionActivity.class);
				startActivity(iStart);
				
			}
		});
		
		Button btnExit = (Button)findViewById(R.id.btnExit);
		btnExit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startAppAd.showAd(); 
				startAppAd.loadAd();
				MainActivity.this.finish();
			}
		});
		
		
		Button btnDanhSach = (Button)findViewById(R.id.btnDanhSach);
		btnDanhSach.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "Danh Sách Đang Trong Quá Trình Cập Nhật", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		
		Button btnThongTin = (Button)findViewById(R.id.btnAbout);
		btnThongTin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent iAbout = new Intent(MainActivity.this, AboutActivity.class);
				startActivity(iAbout);
				
			}
		});
		
		
	}
	
	@Override
	public void onBackPressed() {
		startAppAd.onBackPressed();
		super.onBackPressed();
	}


}
