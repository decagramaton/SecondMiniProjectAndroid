package com.example.secondminiproject.ui.userInfo;

import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.secondminiproject.R;
import com.example.secondminiproject.databinding.FragmentHomeBinding;
import com.example.secondminiproject.databinding.FragmentMyPageBinding;
import com.example.secondminiproject.databinding.FragmentUserInfoBinding;
import com.example.secondminiproject.ui.home.HomeBannerAdapter;

import java.util.Timer;
import java.util.TimerTask;


public class UserInfoFragment extends Fragment {
    private static final String TAG = "UserInfoFragment";
    private FragmentUserInfoBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        navController= NavHostFragment.findNavController(this);

        binding = FragmentUserInfoBinding.inflate(inflater);

        initBtnImageChange();
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    private void initBtnImageChange() {
        ActivityResultLauncher<PickVisualMediaRequest> activityResultLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.PickVisualMedia(),
                        uri -> {
                            if (uri != null) {
                                binding.profileImage.setImageURI(uri);
                                binding.profileImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            }
                        }
                );
        binding.btnImageChange.setOnClickListener(v -> {
            PickVisualMediaRequest request = new PickVisualMediaRequest.Builder()
                    .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                    .build();
            activityResultLauncher.launch(request);
        });
    }
}