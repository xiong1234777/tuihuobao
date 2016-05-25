package com.new_car_pg.car_pg.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.new_car_pg.car_bg.R;
import com.new_car_pg.car_pg.fragment.MainFrag;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    //设置标题
    toolbar.setSubtitle("车曝光");
    //设置监听是事件
    toolbar.setOnMenuItemClickListener(mToolBarListener);

    //
    getSupportFragmentManager().beginTransaction().add(R.id.frag_content,new MainFrag()).commit();
  }

  private Toolbar.OnMenuItemClickListener mToolBarListener = new Toolbar.OnMenuItemClickListener() {
    @Override
    public boolean onMenuItemClick(MenuItem item) {
      Toast.makeText(MainActivity.this,"搜索索索", Toast.LENGTH_SHORT).show();
      return false;
    }
  };

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // 為了讓 Toolbar 的 Menu 有作用，這邊的程式不可以拿掉
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }
}
