package startfirst.troll.sieutroll;

import java.io.IOException;

import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppAd.AdMode;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GameOverActivity extends Activity {

	MediaPlayer mp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_game_over);
		mp = new MediaPlayer();
		
		if(mp.isPlaying())
        {  
            mp.stop();
            mp.reset();
        } 
        try {

            AssetFileDescriptor afd;
            afd = getAssets().openFd("over.mp3");
            mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            mp.prepare();
            mp.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
		
		Button btnAgain = (Button)findViewById(R.id.btnAgain);
		
		btnAgain.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mp.stop();
				Intent iStart = new Intent(GameOverActivity.this, QuestionActivity.class);
				startActivity(iStart);
				GameOverActivity.this.finish();
			}
		});
		
	}
	
	
	@Override
	protected void onDestroy() {
		mp.stop();
		super.onDestroy();
	}

}
