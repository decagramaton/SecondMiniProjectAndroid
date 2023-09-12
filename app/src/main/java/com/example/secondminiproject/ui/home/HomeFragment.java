package com.example.secondminiproject.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.secondminiproject.MainActivity;
import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentHomeBinding;
import com.example.secondminiproject.datastore.AppKeyValueStore;
import com.example.secondminiproject.dto.Board;
import com.example.secondminiproject.dto.Product;
import com.example.secondminiproject.service.ProductService;
import com.example.secondminiproject.service.ServiceProvider;
import com.example.secondminiproject.ui.ProductAdapter;
import com.example.secondminiproject.ui.home.HomeAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private FragmentHomeBinding binding;
    private NavController navController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        navController = NavHostFragment.findNavController(this);

        // Header AppBar 생성
        initHeaderMenu();

        // 추천 여행 상품 목록 출력
        initRecyclerView();


        //카테고리(나라별)
        initCategoryJapan();
        initCategoryHongkong();
        initCategoryTaiwan();
        initCategoryMongolia();
        initCategoryNorway();
        initCategoryGreece();
        initCategoryKeyna();


        return binding.getRoot();
    }

    private void initCategoryJapan() {
        binding.btnHomeCategoryJapan.setOnClickListener(v -> {
            navController.navigate(R.id.dest_product_list);
        });
    }

    private void initCategoryHongkong() {
        binding.btnHomeCategoryHongkong.setOnClickListener(v -> {
            navController.navigate(R.id.dest_product_list);
        });
    }

    private void initCategoryTaiwan() {
        binding.btnHomeCategoryTaiwan.setOnClickListener(v -> {
            navController.navigate(R.id.dest_product_list);
        });
    }

    private void initCategoryMongolia() {
        binding.btnHomeCategoryMongolia.setOnClickListener(v -> {
            navController.navigate(R.id.dest_product_list);
        });
    }

    private void initCategoryNorway() {
        binding.btnHomeCategoryNorway.setOnClickListener(v -> {
            navController.navigate(R.id.dest_product_list);
        });
    }

    private void initCategoryGreece() {
        binding.btnHomeCategoryGreece.setOnClickListener(v -> {
            navController.navigate(R.id.dest_product_list);
        });
    }

    private void initCategoryKeyna() {
        binding.btnHomeCategoryKenya.setOnClickListener(v -> {
            navController.navigate(R.id.dest_product_list);
        });
    }


    private void initHeaderMenu() {
        MenuProvider menuProvider = new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                // Step1. Menu Layout 인플레이션화.
                menuInflater.inflate(R.menu.home_head_menu, menu);

                String mid = AppKeyValueStore.getValue(getContext(), "mid");
                if(mid == null) {
                    menu.findItem(R.id.item_home_login).setVisible(true);
                    menu.findItem(R.id.item_home_logout).setVisible(false);
                    menu.findItem(R.id.item_home_my_page).setVisible(false);
                } else {
                    menu.findItem(R.id.item_home_login).setVisible(false);
                    menu.findItem(R.id.item_home_logout).setVisible(true);
                    menu.findItem(R.id.item_home_my_page).setVisible(true);
                }


            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                // Step2. 아이콘 별 이벤트 처리
                if(menuItem.getItemId() == R.id.item_home_search){

                    return true;
                } else if (menuItem.getItemId() == R.id.item_home_login) {
                    navController.navigate(R.id.action_dest_home_to_dest_login);
                    return true;
                } else if (menuItem.getItemId() == R.id.item_home_logout) {
                    AppKeyValueStore.remove(getContext(), "mid");
                    AppKeyValueStore.remove(getContext(), "mpassword");
                    getActivity().invalidateMenu();
                    return true;
                } else if (menuItem.getItemId() == R.id.item_home_my_page) {
                    navController.navigate(R.id.action_dest_home_to_dest_my_page);
                    return true;
                }

                return false;
            }
        };

        // Step3. Activity에 AppBar 출력 설정
        getActivity().addMenuProvider(menuProvider, getViewLifecycleOwner(), Lifecycle.State.RESUMED);
    }

    private void initRecyclerView() {
        // Step1. 수직방향으로 1라인에 1개의 ViewHolder가 들어가도록 설정
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(linearLayoutManager);

        // Step2. 어샙터 생성
        ProductAdapter productAdapter = new ProductAdapter();

        // Step3. Data를 얻고, Adapter에 설정
        ProductService productService = ServiceProvider.getProductService(getContext());
        Call<List<Board>> call = productService.getProductList();
        call.enqueue(new Callback<List<Board>>() {
            @Override
            public void onResponse(Call<List<Board>> call, Response<List<Board>> response) {
                Log.i(TAG, "onResponse() ");
                List<Board> BoardList = response.body();

                productAdapter.setList(BoardList);
                binding.recyclerView.setAdapter(productAdapter);
            }

            @Override
            public void onFailure(Call<List<Board>> call, Throwable t) {
                Log.i(TAG, "onFailure() ");
                Log.i(TAG, t.toString());
            }
        });

    }

}