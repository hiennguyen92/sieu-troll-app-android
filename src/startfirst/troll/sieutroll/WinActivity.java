package startfirst.troll.sieutroll;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class WinActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_win);
		
		
		Button btnPhaDao = (Button)findViewById(R.id.btnPhaDao);
		btnPhaDao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				System.exit(0);	
			}
		});
	}

}
