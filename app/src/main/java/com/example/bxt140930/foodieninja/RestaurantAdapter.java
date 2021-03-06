package com.example.bxt140930.foodieninja;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class RestaurantAdapter extends BaseAdapter {

    Context context;
    ArrayList<Restaurants> restaurantsList;
    private static LayoutInflater inflater = null;

    public RestaurantAdapter(Context context, ArrayList<Restaurants> RestaurantsList) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.restaurantsList = RestaurantsList;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return restaurantsList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return restaurantsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.restaurant_row, null);

        TextView text = (TextView) vi.findViewById(R.id.ResName);
        text.setText(restaurantsList.get(position).getName());

        text = (TextView) vi.findViewById(R.id.ResWhour);
        text.setText(restaurantsList.get(position).getWorkingHours());

        text = (TextView) vi.findViewById(R.id.ResWaitTime);
        text.setText(context.getString(R.string.EstimatedWaitTime) + " " + restaurantsList.get(position).getEstimatWaitPerPerson());

        byte[] decodedString = Base64.decode(restaurantsList.get(position).getImageUrl(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        ImageView IV = (ImageView) vi.findViewById(R.id.IVLogo);
        IV.setImageBitmap(decodedByte);

        ImageButton IMGBTN = (ImageButton) vi.findViewById(R.id.IMGBTNGetMenu);
        IMGBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(context, MenuActivity.class);
                I.putExtra("ID",restaurantsList.get(position).getId());
                context.startActivity(I);
            }
        });

        return vi;
    }
}