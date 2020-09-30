package com.sakura.videotiktok.fragment

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sakura.videotiktok.R
import kotlinx.android.synthetic.main.fragment_video.*


class VideoFragment : Fragment() {
    /**
     * 播放器
     * 垂直viewpage2
     */

    //模拟数据
    private val VideoUrl = listOf<String>(
        "http://upos-sz-mirrorhw.bilivideo.com/upgcxcode/28/22/210272228/210272228-1-208.mp4?e=ig8euxZM2rNcNbhVhzdVhwdlhzdzhwdVhoNvNC8BqJIzNbfq9rVEuxTEnE8L5F6VnEsSTx0vkX8fqJeYTj_lta53NCM=&uipk=5&nbs=1&deadline=1601442232&gen=playurl&os=hwbv&oi=837317101&trid=329369810fb34c85b26331d43ccafd54T&platform=html5&upsig=9ce620fb55f6e04dff861cfcf0187399&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=0&orderid=0,1&logo=80000000",
        "http://upos-sz-mirrorhw.bilivideo.com/upgcxcode/08/49/203164908/203164908-1-208.mp4?e=ig8euxZM2rNcNbhHhwdVhwdlhzeVhwdVhoNvNC8BqJIzNbfq9rVEuxTEnE8L5F6VnEsSTx0vkX8fqJeYTj_lta53NCM=&uipk=5&nbs=1&deadline=1601442691&gen=playurl&os=hwbv&oi=837317101&trid=5f5279e78a0049f9ae6f7c5525687e34T&platform=html5&upsig=8319ef5f4741196fc64cd5d1ee8f1970&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=0&orderid=0,1&logo=80000000",
        "http://upos-sz-mirrorhw.bilivideo.com/upgcxcode/60/19/200531960/200531960-1-208.mp4?e=ig8euxZM2rNcNbhB7wdVhwdlhzUMhwdVhoNvNC8BqJIzNbfq9rVEuxTEnE8L5F6VnEsSTx0vkX8fqJeYTj_lta53NCM=&uipk=5&nbs=1&deadline=1601444295&gen=playurl&os=hwbv&oi=837317101&trid=00701658742a48e8b7b657a6b0369a0cT&platform=html5&upsig=dbb7cc608924659609fd26e058d60952&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=0&orderid=0,1&logo=80000000"
   ,"http://upos-sz-mirrorkodo.bilivideo.com/upgcxcode/12/94/235239412/235239412-1-208.mp4?e=ig8euxZM2rNcNbNVhbdVhwdlhbdghwdVhoNvNC8BqJIzNbfq9rVEuxTEnE8L5F6VnEsSTx0vkX8fqJeYTj_lta53NCM=&uipk=5&nbs=1&deadline=1601444718&gen=playurl&os=kodobv&oi=837317101&trid=19ee3cb83b05482a9f06e5935860df25T&platform=html5&upsig=29c8b236ba9b665cd8a437284e3cfbcd&uparams=e,uipk,nbs,deadline,gen,os,oi,trid,platform&mid=0&orderid=0,1&logo=80000000"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoViewPage.apply {  // 适配器  内部类方式实现
            adapter = object : FragmentStateAdapter(this@VideoFragment) {
                override fun getItemCount() = VideoUrl.size //传入列表长度

                override fun createFragment(position: Int) =
                    PlayerFragment(VideoUrl[position])  //根据列表传入fragment
            }
            offscreenPageLimit = 2  //预加载的实现。原理是同时加载多个视频
        }


    }

}