package com.sjw.mybottombar.tablayoutandfragment;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sjw.mybottombar.R;
import com.sjw.mybottombar.fragment.DataGenerator;
import com.sjw.mybottombar.fragment.MyFragmentManager;

import java.util.List;


public class TablayotFragmentActivity extends FragmentActivity {

    private TabLayout mTabLayout;
    private Fragment[] mFragmensts;
    private MyFragmentManager myFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fablayot_fragment);
        mFragmensts = DataGenerator.getFragments("TabLayout Tab");
        FragmentManager fragmentManager = getSupportFragmentManager();
        myFragmentManager = new MyFragmentManager(fragmentManager, R.id.home_container);
        initView();

    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.bottom_tab_layout);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                onTabItemSelected(tab.getPosition());
                // Tab 选中之后，改变各个Tab的状态
                for (int i = 0; i < mTabLayout.getTabCount(); i++) {
                    View view = mTabLayout.getTabAt(i).getCustomView();
                    ImageView icon = (ImageView) view.findViewById(R.id.tab_content_image);
                    TextView text = (TextView) view.findViewById(R.id.tab_content_text);
                    if (i == tab.getPosition()) { // 选中状态
                        icon.setImageResource(DataGenerator.mTabResPressed[i]);
                        text.setTextColor(getResources().getColor(R.color.selectColor));
                    } else {// 未选中状态
                        icon.setImageResource(DataGenerator.mTabRes[i]);
                        text.setTextColor(getResources().getColor(R.color.unSelectColor));
                    }
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        // 提供自定义的布局添加Tab
        for (int i = 0; i < 4; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setCustomView(DataGenerator.getTabView(this, i)));
        }

    }

    private void onTabItemSelected(int position) {

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = mFragmensts[0];
                break;
            case 1:
                fragment = mFragmensts[1];
                break;

            case 2:
                fragment = mFragmensts[2];
                break;
            case 3:
                fragment = mFragmensts[3];
                break;
        }

        //切换
        myFragmentManager.switchFragment(fragment);


//        List<Fragment> fragmentList = fragmentManager.getFragments();
//
//        //1.隐藏所有的
//        for (Fragment mFragment : fragmentList) {
//
//            fragmentTransaction.hide(mFragment);
//
//        }
//        //2.如果容器里面没有就添加，否则显示
//        if (!fragmentList.contains(fragment)) {
//
//
//            fragmentTransaction.add(R.id.home_container, fragment);
//
//
//        } else {
//            fragmentTransaction.show(fragment);
//
//        }
//        fragmentTransaction.commit();
//        if (fragment != null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.home_container, fragment).commit();
//        }
    }

}
