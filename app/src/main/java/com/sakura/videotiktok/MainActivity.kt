package com.sakura.videotiktok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.sakura.videotiktok.fragment.FooFragment
import com.sakura.videotiktok.fragment.VideoFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //ViewPager2控件
        mainViewPager.apply {
            //创建适配器
            adapter = object : FragmentStateAdapter(this@MainActivity) {  //类似java里面的匿名内部类
                override fun getItemCount() = 3  //适配器数目为3

                // 根据当前position的Id来设置界面
                override fun createFragment(position: Int): Fragment = when (position) {
                    1 -> VideoFragment()
                    else -> FooFragment()
                }
            }
            //设置启动后的默认的界面为第二个，并且取消初始平滑滚动
            setCurrentItem(1, false)
        }
        //tab绑定到fragment上
        TabLayoutMediator(tabLayout, mainViewPager) { tab: TabLayout.Tab, i: Int ->
            tab.text = when (i) {  //绑定导航栏文字
                1 -> "Video"
                else -> "Foo"
            }
        }.attach() //不加不会起作用
    }
}