package com.xw.samlpe.bubbleseekbar;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xw.repo.BubbleSeekBar;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    BubbleSeekBar mBubbleSeekBar0;
    BubbleSeekBar mBubbleSeekBar1;
    BubbleSeekBar mBubbleSeekBar2;
    BubbleSeekBar mBubbleSeekBar3;
    BubbleSeekBar mBubbleSeekBar4;
    BubbleSeekBar mBubbleSeekBar5;
    BubbleSeekBar mBubbleSeekBar6;
    BubbleSeekBar mBubbleSeekBar8;
    BubbleSeekBar mBubbleSeekBar9;
    TextView mProgressText;
    ObservableScrollView mObsScrollView;

    StringBuilder mStringBuilder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        mBubbleSeekBar0 = (BubbleSeekBar) findViewById(R.id.bubble_seek_bar_0);
        mBubbleSeekBar1 = (BubbleSeekBar) findViewById(R.id.bubble_seek_bar_1);
        mBubbleSeekBar2 = (BubbleSeekBar) findViewById(R.id.bubble_seek_bar_2);
        mBubbleSeekBar3 = (BubbleSeekBar) findViewById(R.id.bubble_seek_bar_3);
        mBubbleSeekBar4 = (BubbleSeekBar) findViewById(R.id.bubble_seek_bar_4);
        mBubbleSeekBar5 = (BubbleSeekBar) findViewById(R.id.bubble_seek_bar_5);
        mBubbleSeekBar6 = (BubbleSeekBar) findViewById(R.id.bubble_seek_bar_6);
        mBubbleSeekBar8 = (BubbleSeekBar) findViewById(R.id.bubble_seek_bar_8);
        mBubbleSeekBar9 = (BubbleSeekBar) findViewById(R.id.bubble_seek_bar_9);
        mProgressText = (TextView) findViewById(R.id.progress_text);
        mObsScrollView = (ObservableScrollView) findViewById(R.id.content_main);

        setSupportActionBar(toolbar);

        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mObsScrollView.smoothScrollTo(0, 0);

                int progress = new Random().nextInt(100);
                mBubbleSeekBar0.setProgress(progress);
                Snackbar.make(view, "set random progress = " + progress, Snackbar.LENGTH_SHORT).show();
            }
        });

        mBubbleSeekBar0.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListenerAdapter() {
            @Override
            public void getProgressOnActionUp(int progress) {
                Toast.makeText(MainActivity.this, "progressOnActionUp:" + progress, Toast.LENGTH_SHORT).show();
            }
        });

        mBubbleSeekBar5.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListenerAdapter() {
            @Override
            public void onProgressChanged(int progress) {
                mStringBuilder.delete(0, mStringBuilder.length());

                mStringBuilder.append("(listener) int:").append(progress);
            }

            @Override
            public void onProgressChanged(float progress) {
                mStringBuilder.append(", float:").append(progress);

                mProgressText.setText(mStringBuilder.toString());
            }
        });

        mObsScrollView.setOnScrollChangedListener(new ObservableScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                // 调用修正偏移方法
                mBubbleSeekBar0.correctOffsetWhenContainerOnScrolling();
                mBubbleSeekBar1.correctOffsetWhenContainerOnScrolling();
                mBubbleSeekBar2.correctOffsetWhenContainerOnScrolling();
                mBubbleSeekBar3.correctOffsetWhenContainerOnScrolling();
                mBubbleSeekBar4.correctOffsetWhenContainerOnScrolling();
                mBubbleSeekBar5.correctOffsetWhenContainerOnScrolling();
                mBubbleSeekBar6.correctOffsetWhenContainerOnScrolling();
                mBubbleSeekBar8.correctOffsetWhenContainerOnScrolling();
                mBubbleSeekBar9.correctOffsetWhenContainerOnScrolling();
            }
        });

        mBubbleSeekBar6.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListenerAdapter() {
            @Override
            public void getProgressOnFinally(int progress) {
                Toast.makeText(MainActivity.this, "progressOnFinally(int):" + progress, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
