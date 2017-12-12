### `VerticalViewPager` 一个竖直滚动的`ViewPager`
#### 继承于`ViewPager`,与`ViewPager`完全一样的用法,包括适配器，监听都是一样的，只是滑动方向不同。
#### 偶尔我们会发现`viewpager`想要竖直滑动，但是官方又没有提供方法，在`stackoverflow`上发现了这个，可以完美实现我们的需求，在这里做一下记录
#### 实现的东西，说不定就会遇到. 
[简书介绍](http://www.jianshu.com/p/f1163d1161a2)
[VerticalViewPager获取](https://github.com/yukunkun/MySelfView/blob/master/app/src/main/java/com/yk/myselfview/views/VerticalViewPager.java)

![](http://upload-images.jianshu.io/upload_images/3001453-068ff97e6b749129.jpg)
![](http://upload-images.jianshu.io/upload_images/3001453-a1b489e884924803.jpg)
#### 用法很简单，拷贝直接使用：
     <com.yk.myselfview.views.VerticalViewPager 
            android:layout_width="match_parent"
            android:layout_centerInParent="true"
            android:id="@+id/vertical_viewpager"
            android:layout_height="240dp">
     </com.yk.myselfview.views.VerticalViewPager>
#### 代码：
    mVerticalViewPager = (VerticalViewPager) findViewById(R.id.vertical_viewpager);
    //适配器
    VerticalViewpagerAdapter verticalViewpagerAdapter=new VerticalViewpagerAdapter(this,mList);
            mVerticalViewPager.setAdapter(verticalViewpagerAdapter);
    
            mVerticalViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    
                }
    
                @Override
                public void onPageSelected(int position) {
                    Toast.makeText(VerticalViewPagerActivity.this, "position:"+position, Toast.LENGTH_SHORT).show();
                }
    
                @Override
                public void onPageScrollStateChanged(int state) {
    
                }
            });
#### 适配器
        /**
         * Created by yukun on 17-12-12.
         */
        
        public class VerticalViewpagerAdapter extends PagerAdapter {
        
            private Context mContext;
            private List<Integer> mList;
        
            public VerticalViewpagerAdapter(Context mContext, List<Integer> list) {
                this.mContext = mContext;
                mList = list;
            }
        
            @Override
            public int getCount() {
                return mList.size();
            }
        
            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }
        
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView=new ImageView(mContext);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setImageResource(mList.get(position));
                container.addView(imageView);
                return imageView;
            }
        
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View)object);
            }
        }
            