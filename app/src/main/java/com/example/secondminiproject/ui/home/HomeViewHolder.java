package com.example.secondminiproject.ui.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.dto.Product;

import java.text.DecimalFormat;
import java.util.Currency;

public class HomeViewHolder extends RecyclerView.ViewHolder {
    private int pno;
    private ImageView image;
    private TextView title;
    private TextView subTitle;
    private TextView content;
    private TextView price;
    private RatingBar rating;
    private TextView ratingScore;
    private TextView ratingCountByProduct;


    public HomeViewHolder(@NonNull View itemView, HomeAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);
        this.image = itemView.findViewById(R.id.image);
        this.title = itemView.findViewById(R.id.title);
        this.subTitle = itemView.findViewById(R.id.subtitle);
        this.content = itemView.findViewById(R.id.content);
        this.price = itemView.findViewById(R.id.price);
        this.rating = itemView.findViewById(R.id.rating);
        this.ratingScore = itemView.findViewById(R.id.rating_score);
        this.ratingCountByProduct = itemView.findViewById(R.id.rating_count_by_product);

        //클릭 이벤트 처리
        itemView.setOnClickListener(v -> {
            onItemClickListener.onItemClick(v, getAdapterPosition());//어뎁터 항목번호
        });
    }

    public void setData(Product product){
        this.pno = pno;
        this.image.setImageResource(product.getImage());
        this.title.setText(product.getTitle());
        this.subTitle.setText(product.getSubTitle());
        this.content.setText(product.getContent());

        String currencySymbol = Currency.getInstance("KRW").getSymbol();
        DecimalFormat df = new DecimalFormat("#,###");
        this.price.setText(currencySymbol + " " + df.format(product.getPrice()));

        this.rating.setRating(product.getRating());
        this.ratingScore.setText(String.valueOf(product.getRating()));
        this.ratingCountByProduct.setText("(" + product.getRatingCountByProduct() + ")");
    }
}
