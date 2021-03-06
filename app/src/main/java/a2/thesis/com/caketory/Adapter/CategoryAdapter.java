package a2.thesis.com.caketory.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;

import java.util.List;

import a2.thesis.com.caketory.Utils.Constants;
import a2.thesis.com.caketory.Entity.ItemCategory;
import a2.thesis.com.caketory.Network.VolleySingleton;
import a2.thesis.com.caketory.R;

/**
 * Created by Amin on 24/01/2018.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    private Context context;
    private CategoryAdapterListener categoryAdapterListener;
    private List<ItemCategory> categoryList;

    public CategoryAdapter(Context context, List<ItemCategory> categoryList) {
        this.context = context;
        this.categoryAdapterListener = (CategoryAdapterListener) context;
        this.categoryList = categoryList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final ItemCategory itemCategory = categoryList.get(position);

        String imagePath = Constants.imagesDirectory + categoryList.get(position).getCategoryImage();
        Glide.with(context)
                .load(imagePath)
                .centerCrop()
                .into(holder.image);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryAdapterListener.onCategoryItemClicked(itemCategory.getCategoryID(), itemCategory.getCategoryName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public interface CategoryAdapterListener {
        void onCategoryItemClicked(long id, String name);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView image;

        MyViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_cat);
            image = itemView.findViewById(R.id.card_image_cat);
        }
    }
}
