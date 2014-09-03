package com.example.cadastro.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cadastro.R;

@SuppressLint({ "ViewHolder", "InflateParams" })
public class PickPhotoAdapter extends BaseAdapter {
    private Activity ctx;
    private List<Map<String, Object>> itens;
    
    public PickPhotoAdapter(Activity ctx) {
      this.ctx = ctx;
      itens = new ArrayList<Map<String, Object>>();
      Map<String, Object> item = new HashMap<String, Object>();
      item.put("nome", "Câmera");
      item.put("icone", android.R.drawable.ic_menu_camera);
      itens.add(item);
      
      Map<String, Object> item2 = new HashMap<String, Object>();
      item2.put("nome", "Galeria");
      item2.put("icone", android.R.drawable.ic_menu_gallery);
      itens.add(item2);
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	View view = ctx.getLayoutInflater().inflate(R.layout.row, null);
        TextView label=(TextView)view.findViewById(R.id.label);
		Map<String, Object> item = (Map<String, Object>) itens.get(position);
		Log.i("adapter", String.valueOf(item.keySet()));
		
        label.setText((String) item.get("nome"));
        ImageView icon=(ImageView)view.findViewById(R.id.icon);
        icon.setImageResource((Integer)item.get("icone"));
        return view;
    }

	@Override
	public int getCount() {
		return itens.size();
	}

	@Override
	public Object getItem(int position) {
		return itens.get(position);
	}

	@Override
	public long getItemId(int position) {
		return itens.get(position).hashCode();
	}

}
