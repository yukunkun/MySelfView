##　类似window7　的气泡的`view`
####　这个只能做来玩玩儿，没有太多的使用价值，看着好玩，有密集恐惧症的就不要玩儿了
[简书链接](http://www.jianshu.com/p/51c706222a7b)
####　上图
![不重叠气泡](https://github.com/yukunkun/MySelfView/blob/master/shootscreen/image1/S71213-15364457.jpg)
![重叠气泡](https://github.com/yukunkun/MySelfView/blob/master/shootscreen/image2/S71213-15383785.jpg)

####　其中有两种状态，一种可以重叠，一种不能重叠。类似Window 7　气泡的屏保。
#### 一个使用两个自定义view,一个就是一个圆圈，一个实现动画，边界计算

[圆圈的view](https://github.com/yukunkun/MySelfView/blob/master/app/src/main/java/com/yk/myselfview/views/RainBallView.java)
[计算距离的view](https://github.com/yukunkun/MySelfView/blob/master/app/src/main/java/com/yk/myselfview/views/RainBallLayout.java)
####　使用,考入两个自定义view,直接使用
[使用链接](https://github.com/yukunkun/MySelfView/blob/master/app/src/main/java/com/yk/myselfview/activity/BallsViewActivity.java)
#### xml布局中
        <com.yk.myselfview.views.RainBallLayout android:layout_width="match_parent"
                                                    android:id="@+id/rain_ball_layout"
                                                    android:layout_height="match_parent">
            </com.yk.myselfview.views.RainBallLayout>
            
####　页面

        public class BallsViewActivity extends AppCompatActivity {
        
            private RainBallLayout mRainBallLayout;
        
            private List<Integer> mListColor=new ArrayList<>();
        
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_balls_view);
                mListColor.add(Color.BLUE);
                mListColor.add(Color.BLACK);
                mListColor.add(Color.GREEN);
                mListColor.add(Color.RED);
                mListColor.add(Color.CYAN);
        
                mRainBallLayout = (RainBallLayout) findViewById(R.id.rain_ball_layout);
                for (int i = 0; i < 1; i++) {
                    RainBallView rainBallView=getRainBallView();
                    mRainBallLayout.addBallsView(rainBallView);
                    //s是否可以相交
                    mRainBallLayout.isReward(true);
                    mRainBallLayout.startAmimation();
                }
            }
        
            @Override
            protected void onDestroy() {
                super.onDestroy();
                mRainBallLayout.stopAmimation();
            }
        
            public void addBall(View view) {
                RainBallView rainBallView = getRainBallView();
                mRainBallLayout.addBallsView(rainBallView);
            }
        
            @NonNull
            private RainBallView getRainBallView() {
                Random random=new Random();
                int color = random.nextInt(4);
                RainBallView rainBallView=new RainBallView(this);
                rainBallView.setColors(mListColor.get(color));
                rainBallView.setPainStyle(Paint.Style.STROKE);
                rainBallView.setWidth(4);
                return rainBallView;
            }
        }