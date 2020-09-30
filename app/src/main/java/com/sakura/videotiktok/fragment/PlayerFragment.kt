package com.sakura.videotiktok.fragment

import android.media.MediaPlayer
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.sakura.videotiktok.R
import kotlinx.android.synthetic.main.fragment_player.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class PlayerFragment(private val url: String) : Fragment() {
    /**
     * 对播放器进行配置
     */
    private val mediaPlayer = MediaPlayer() //播放器控件
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
         * 播放器
         * 视频加载时显示progressBar
         * 加载完成隐藏
         */
        mediaPlayer.apply {
            setOnPreparedListener {
                progressBarH.max = mediaPlayer.duration  //当前进度条位置
                seekTo(1) //第一帧，这里跟预加载有关联
                progressBar.visibility = View.INVISIBLE
            }
            setDataSource(url)
            prepareAsync()
            progressBar.visibility = View.VISIBLE
        }
        /**
         * 协成
         * 需要添加lifecycle-runtime的依赖
         */
        lifecycleScope.launch {
            while (true) { //循环 进度条的长度等于播放器当前播放的位置，500毫秒刷新一次
                progressBarH.progress = mediaPlayer.currentPosition
                delay(500)
            }
        }
        surfaceView.holder.addCallback(object : SurfaceHolder.Callback {   //视频显示
            override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {
                mediaPlayer.setDisplay(p0)  //显示到p0
                mediaPlayer.setScreenOnWhilePlaying(true)  //屏幕不关闭
            }

            override fun surfaceDestroyed(p0: SurfaceHolder?) {}

            override fun surfaceCreated(p0: SurfaceHolder?) {}

        })


    }


    override fun onResume() {
        //恢复
        super.onResume()
        mediaPlayer.start()  //播放 （BUG是第一个视频不会播放，所以下面这段代码是解决这个问题的）
        lifecycleScope.launch {
            while(!mediaPlayer.isPlaying){  // 如果视频没有播放，那就调用播放，500毫秒调用一次，直到播放为止
                mediaPlayer.start()
                delay(500)

            }
        }
    }

    override fun onPause() {
        //暂停
        super.onPause()
        mediaPlayer.pause()
    }

}
