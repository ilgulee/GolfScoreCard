package ilgulee.com.golfscorecard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


public class ListAdapter extends BaseAdapter {


    private final Context mContext;
    private final Hole[] mHoles;


    public ListAdapter(Context context, Hole[] holes){
        mContext=context;
        mHoles=holes;

    }


    @Override
    public int getCount() {
        return mHoles.length;
    }

    @Override
    public Object getItem(int position) {
        return mHoles[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if(convertView==null){
            convertView=LayoutInflater.from(mContext).inflate(R.layout.list_item,null);
            viewHolder=new ViewHolder();
            viewHolder.holeLabel=(TextView)convertView.findViewById(R.id.holeLabel);
            viewHolder.strokeCount=(TextView)convertView.findViewById(R.id.strokeCount);
            viewHolder.removeStrokeButton=(Button) convertView.findViewById(R.id.removeStrokeButton);
            viewHolder.addStrokeButton=(Button) convertView.findViewById(R.id.addStrokeButton);

            convertView.setTag(viewHolder);
        }else{
          viewHolder=(ViewHolder)convertView.getTag();
        }

        viewHolder.holeLabel.setText(mHoles[position].getLabel());
        viewHolder.strokeCount.setText(mHoles[position].getStrokeCount()+"");
        viewHolder.removeStrokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int updatedStrokeCount=mHoles[position].getStrokeCount()-1;
                if(updatedStrokeCount<0)updatedStrokeCount=0;
                mHoles[position].setStrokeCount(updatedStrokeCount);
                viewHolder.strokeCount.setText(updatedStrokeCount+"");
            }
        });
        viewHolder.addStrokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int updatedStrokeCount=mHoles[position].getStrokeCount()+1;
                mHoles[position].setStrokeCount(updatedStrokeCount);
                viewHolder.strokeCount.setText(updatedStrokeCount+"");
            }
        });
        return convertView;
    }
    private static class ViewHolder{
         TextView holeLabel;
         TextView strokeCount;
         Button removeStrokeButton;
         Button addStrokeButton;

    }
}
