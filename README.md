## Android快速开发的一些例子
---

#### CustomTabLayout
自定义的底部Tab.

布局内容 包含文字与图片

-  TabItem跟节点id：custom_tablayout_item
-  ImageVie的id: custom_tablayout_image
-  TextView的id: custom_tablayout_text

TabInfo:

| 参数        | 类型           | 说明 | 是否必填|
| :-------------: |:-------------:| :-----:|:----:|
| count      | int | Tab的个数 | Yes|
| currentTab | int      |  默认选中的tab pisition |Yes|
| tabText | String[]     |    Tab文字 |Yes|
| normalResId | int     |    默认图片 | NO|
| selectResId |int      |    选择的图片 |NO|
| normalTextColor | int    |    默认文字颜色 |NO|
| selectTextColor | int      |   选择文字颜色 |NO|
| normalTextSizeSp | int      |   默认文字大小 |NO|
| normalTextSizeSp | int      |   选择文字大小 |NO|
使用方式：

```
 customTabLayout = (CustomTabLayout) findViewById(R.id.custom_tablayout);
        //strs:文字描述  5：有5个Tab  0：默认选中的是第一个
        TabInfo.TabInfoBuilder tabInfoBuilder = new TabInfo.TabInfoBuilder(strs,count, 0);
        List<TabInfo> tabInfos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            TabInfo tabInfo = tabInfoBuilder
                    .setNormalResId(normalResId[i])
                    .setSelectResId(selectResId[i])
                    .setNormalTextSizeSp(12)
                    .setSelectTextSizeSp(12)
                    .setNormalTextColor(getResources().getColor(R.color.colorAccent))
                    .setSelectTextColor(getResources().getColor(R.color.colorPrimaryDark))
                    .build();
            tabInfos.add(tabInfo);
        }
        customTabLayout.addTab(tabInfos,R.layout.tablayout_item);

```
