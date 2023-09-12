package com.example.secondminiproject.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentProductListBinding;
import com.example.secondminiproject.dto.Board;
import com.example.secondminiproject.service.ProductService;
import com.example.secondminiproject.service.ServiceProvider;
import com.example.secondminiproject.ui.ProductAdapter;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListFragment extends Fragment {

    private static final String TAG = "ProductListFragment";
    private FragmentProductListBinding binding;
    private NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProductListBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);


        initRecyclerView();

        return binding.getRoot();
    }





    private void initRecyclerView() {
        // Step1. 수직방향으로 1라인에 1개의 ViewHolder가 들어가도록 설정
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(linearLayoutManager);

        // Step2. 어샙터 생성
        ProductAdapter productAdapter = new ProductAdapter();

        ProductService productService = ServiceProvider.getProductService(getContext());
        Call<List<Board>> call = productService.getProductList();
        call.enqueue(new Callback<List<Board>>() {
             @Override
             public void onResponse(Call<List<Board>> call, Response<List<Board>> response) {
                 List<Board> BoardList = response.body();

                 //어댑터에 데이터 세팅
                 productAdapter.setList(BoardList);

                 //RecyclerView에 어댑터 세팅
                 binding.recyclerView.setAdapter(productAdapter);
             }

             @Override
             public void onFailure(Call<List<Board>> call, Throwable t) {

             }
         });


        // Step4. RecyclerView에 Adapter 설정
        binding.recyclerView.setAdapter(productAdapter);

        //항목을 클릭했을때 콜백 객체를 등록
        productAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Board board = productAdapter.getItem(position);

                Bundle args = new Bundle();
                args.putSerializable("board", board);

                navController.navigate(R.id.action_dest_product_list_to_dest_product_detail,args);
            }
        });

    }
}