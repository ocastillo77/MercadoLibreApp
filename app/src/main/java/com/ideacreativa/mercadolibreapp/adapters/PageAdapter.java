package com.ideacreativa.mercadolibreapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.ideacreativa.mercadolibreapp.R;
import com.ideacreativa.mercadolibreapp.models.ProductoDetalle;

import java.util.List;

public class PageAdapter extends PagerAdapter {

    private Context mContext;
    List<ProductoDetalle.picture> pictures;

    public PageAdapter(Context context, List<ProductoDetalle.picture> pictures) {
        mContext = context;
        this.pictures = pictures;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.page, collection, false);
        ImageView image = layout.findViewById(R.id.image);

        Glide.with(mContext).load(pictures.get(position).url).into(image);
        collection.addView(layout);

        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return pictures.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }

}
