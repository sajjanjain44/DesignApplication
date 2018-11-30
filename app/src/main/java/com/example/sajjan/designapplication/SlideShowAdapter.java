package com.example.sajjan.designapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.sajjan.designapplication.R;

public class SlideShowAdapter extends PagerAdapter {

    private Context context;
    LayoutInflater inflater;

    public int[] images = {
            R.drawable.angrybirds,
            R.drawable.asphalt8,
            R.drawable.clashofclans,
            R.drawable.cuttherope,
            R.drawable.fruitninja,
            R.drawable.talkingtom,
            R.drawable.wheresmywhater,
            R.drawable.worms3,
            R.drawable.pou
    };

    public SlideShowAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slideshow_layout,container,false);
        AppCompatImageView imageView = view.findViewById(R.id.imageview_id);
//        imageView.setImageResource(images[position]);
        Glide.with(context).load(images[position]).into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Image" + (position+1),Snackbar.LENGTH_LONG).show();
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view == (ConstraintLayout)o);
    }
}
