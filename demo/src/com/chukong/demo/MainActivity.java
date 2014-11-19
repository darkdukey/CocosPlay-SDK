package com.chukong.demo;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.chukong.cocosplay.CocosPlay;
import com.chukong.cocosplay.CocosPlay.DemoGameEndedListener;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        CocosPlay.init(this, "000001");
        
        Button btnStart = (Button) findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
		    	
				Drawable loadingBackground = getResources().getDrawable(R.drawable.bg_default_landscape);
				
				//Start Game
				CocosPlay.runDemoGame(MainActivity.this, "com.chukong.cosmiccrash2", loadingBackground, new DemoGameEndedListener(){

					@Override
					public String getDialogBtnText() {
						return "";
					}

					@Override
					public String getDialogContent() {
						return "";
					}

					@Override
					public void onGameExit() {
					}

					@Override
					public void onNextStep() {
					}
					
				});
			}
		});
    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		CocosPlay.destroy();
	}

	@Override
	protected void onPause() {
		super.onPause();
		
		CocosPlay.pause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		CocosPlay.resume();
	}
    
    
}
