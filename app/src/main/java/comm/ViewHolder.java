package comm;


import android.content.Context;
import android.text.SpannableString;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * @author zxh
 * 公用的viewholder
 */
public class ViewHolder {
	private SparseArray<View> mViews =null;
	private View mConvertView = null;
	/**
	 * 构造viewholder
	 * @param context
	 * @param parent
	 * @param layoutId
	 * @param position
	 */
	private ViewHolder(Context context, ViewGroup parent, int layoutId,  
            int position) {
       this.mViews = new SparseArray<View>();
       mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
               false);
       //setTag
       mConvertView.setTag(this);
     }

  /**
     * 拿到一个ViewHolder对象 
     * @param context 
     * @param convertView 
     * @param parent 
     * @param layoutId 
     * @param position 
     * @return 
     */  
    public static ViewHolder get(Context context, View convertView,  
            ViewGroup parent, int layoutId, int position)  
    {  
      
        if (convertView == null)  
        {  
            return new ViewHolder(context, parent, layoutId, position);  
        }  
        return (ViewHolder) convertView.getTag();  
    }

    /**
     * 设置TextView的值
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, String text)
    {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    /**设置spanna**/
    public ViewHolder setText(int viewId, SpannableString text)
    {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    /** 
     * 通过控件的Id获取对于的控件，如果没有则加入views 
     * @param viewId 
     * @return 
     */  
    public <T extends View> T getView(int viewId)  
    {  
          
        View view = mViews.get(viewId);  
        if (view == null)  
        {  
            view = mConvertView.findViewById(viewId);  
            mViews.put(viewId, view);  
        }  
        return (T) view;  
    }  
  
    
	public View getConvertView() {
		// TODO Auto-generated method stub
		return mConvertView;
	}

    public ViewHolder setImageResource(int viewId, int resId)
    {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

  public TextView getTextView(int view_id) {
    return (TextView) getView(view_id);
  }

  public void setViewGone(int iv_tip) {
    getView(iv_tip).setVisibility(View.GONE);
  }
}
