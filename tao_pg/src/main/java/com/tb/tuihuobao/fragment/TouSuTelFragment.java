package com.tb.tuihuobao.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.tb.tuihuobao.R;
import com.tb.tuihuobao.bean.TelPhoneIfo;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import comm.BaseFrag;
import comm.BaseP;
import comm.CommonAdapter;
import comm.ViewHolder;
import comm.utils.DataTools;
import comm.utils.XutilsHelper;

/**
 * Created by zxh on 2016/5/17.
 */
public class TouSuTelFragment extends BaseFrag {

  @ViewInject(R.id.lv_content)
  private ListView mTelContent;

  List<TelPhoneIfo> datas =null;

  private CommonAdapter<TelPhoneIfo> mAdapter = null;

  @Override
  public void initData(Bundle savedInstanceState) {

    String telinfo= XutilsHelper.getResStr(R.string.telinfo);


    try {
      JSONObject jsonObject = new JSONObject(telinfo);

      datas = DataTools.getGosn().fromJson(jsonObject.getJSONArray("list")
              .toString
              (), new
              TypeToken<List<TelPhoneIfo>>() {
              }.getType());

    } catch (JSONException e) {
      e.printStackTrace();
    }


    if(datas==null){
      return;
    }

    if (null == mAdapter) {

      mAdapter = new CommonAdapter<TelPhoneIfo>(getContext(), datas, R.layout.adapter_telephone) {
        @Override
        public void convert(ViewHolder helper, TelPhoneIfo item) {
          helper.setText(R.id.name, item.name);
          helper.setText(R.id.number, item.number);
        }
      };

      mTelContent.setAdapter(mAdapter);

      mTelContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

          String s = datas.get(position).number;

          //意图：想干什么事
          Intent intent = new Intent();
          intent.setAction(Intent.ACTION_CALL);
          //url:统一资源定位符
          //uri:统一资源标示符（更广）

          intent.setData(Uri.parse("tel:" + s));
          //开启系统拨号器
          startActivity(intent);

        }
      });

    }


  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_tousu_tel;
  }

  @Override
  protected BaseP createPresenter() {
    return null;
  }
}
