package com.example.secondminiproject.ui.myPage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.secondminiproject.ui.home.PageFragment;

public class MypageBannerAdapter extends FragmentStateAdapter {
    public MypageBannerAdapter(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }
    @NonNull
    @Override
    //각 페이지로 넘어갈때의 fragment가 뭐야? 무슨페이지야?
    public Fragment createFragment(int position) {
        MypagePageFragment mypagePageFragment = new MypagePageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pageNo",position+1);
        mypagePageFragment.setArguments(bundle);

        return mypagePageFragment;
    }

    @Override
    public int getItemCount() {
        // 몇개를 돌릴거야? 지금 예시에서는 5개!
        return Integer.MAX_VALUE;
    }
}
