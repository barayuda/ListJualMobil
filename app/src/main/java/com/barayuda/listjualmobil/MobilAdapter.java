package com.barayuda.listjualmobil;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by barayuda on 10/27/16.
 */

public class MobilAdapter extends BaseAdapter {
    // params
    ArrayList listItem;
    Activity activity;

    public MobilAdapter(Activity activity, ArrayList listItem) {
        this.activity = activity;
        this.listItem = listItem;
    }

    // method ini akan menentukan seberapa banyak data yang akan ditampilkan didalam ListView
    // ListItem.size() == jumlah data dalam object list yang ada
    @Override
    public int getCount() {
        return listItem.size();
    }

    // method ini untuk mengakses per-item objek dalam list
    @Override
    public Object getItem(int position) {
        return listItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // method ini yang akan menampilkan baris per baris dari item yang ada di ListView
    // dengan menggunakan pattern ViewHolder untuk optimasi performa dari ListView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = null;

        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_list, null);
            holder.txtHarga = (TextView)view.findViewById(R.id.txt_item_harga);
            holder.txtLokasi = (TextView)view.findViewById(R.id.txt_item_lokasi);
            holder.txtTitle = (TextView)view.findViewById(R.id.txt_item_title);
            holder.imgItem = (ImageView)view.findViewById(R.id.img_item);
            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }

        MobilModel mobil = (MobilModel)getItem(position);
        holder.txtTitle.setText(mobil.getTitle());
        holder.txtHarga.setText(mobil.getHarga());
        holder.txtLokasi.setText(mobil.getLokasi());

        /**
         * ket:
         * .load berfungsi untuk mengambil url gambar
         * .placeholder berfungsi untuk menggantikan gambar sementara ketika sendang menguduh gambar
         * .error berfungsi untuk mengantikan gambar ketika gagal mengunduh
         * .into berfungsi untuk target letak untuk menampilkan gambar
         */
        Picasso.with(activity).load(mobil.getImage())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(holder.imgItem);

        return view;
    }

    static class ViewHolder {
        ImageView imgItem;
        TextView txtTitle, txtHarga, txtLokasi;
    }
}
