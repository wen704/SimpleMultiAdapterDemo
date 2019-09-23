# SimpleMultiAdapterDemo
## RecyclerAdapter 多样式适配器
>以 module 形式存在
>提前创建好 `library` module

#### 代码注释丰富,详见代码,思路见下方
### 思路
* 需要一个用于 RecyclerView 的 item 布局, item 是否可点击, item 内控件与数据的绑定, 在多样式中是否使用当前布局 [RViewItem 接口](https://github.com/wen704/SimpleMultiAdapterDemo/blob/master/library/src/main/java/com/gaofu/library/model/RViewItem.java) (泛型接口,用于传入对应的 JavaBean)
* 需要一个通用的 RecyclerView.ViewHolder, 可以收集所有控件, 提供当前整个 item, 用于提供实现点击事件的设置 [RViewHolder 类](https://github.com/wen704/SimpleMultiAdapterDemo/blob/master/library/src/main/java/com/gaofu/library/holder/RViewHolder.java) (继承 RecyclerView.ViewHolder)
* 需要一个 item 的点击,长按监听事件 [ItemListener 接口](https://github.com/wen704/SimpleMultiAdapterDemo/blob/master/library/src/main/java/com/gaofu/library/listener/ItemListener.java) (泛型接口,通过传入的 JavaBean,在相应点击事件上快速获取数据)
* 需要一个通用的 RecyclerViewAdapter, 用于构造器区分多样式, 可以添加数据, 修改整个数据源, 设置点击事件监听 [RViewAdapter 类](https://github.com/wen704/SimpleMultiAdapterDemo/blob/master/library/src/main/java/com/gaofu/library/base/RViewAdapter.java) (集成 RecyclerView.Adapter 且 ViewHolder 为上方创建的 `RViewHolder`)
* 需要一个样式管理, 可以添加不同样式, 可以获取样式数量, 返回对应数据所需要使用的样式, 可以绑定数据 [RViewItemManager 类](https://github.com/wen704/SimpleMultiAdapterDemo/blob/master/library/src/main/java/com/gaofu/library/manager/RViewItemManager.java) (用于管理所有的样式,其中 `getItemViewType` 方法与 `RViewItem` 中的 `isItemView` 对应,在实现类中即可关联)
* (非必要项)需要一个 SwipeRefreshLayout 帮助类,用于设置下拉刷新以及实现事件监听 [SwipeRefreshHelper 类](https://github.com/wen704/SimpleMultiAdapterDemo/blob/master/library/src/main/java/com/gaofu/library/SwipeRefreshHelper.java)
* 需要一个 View 的 Create 类, 用于辅助获取 SwipeRefreshLayout, RecyclerView, Adapter, 同时拥有设置是否可分页功能 [RViewCreate 接口](https://github.com/wen704/SimpleMultiAdapterDemo/blob/master/library/src/main/java/com/gaofu/library/core/RViewCreate.java) (在相应的 Activity 或 Fragment 中实现该类,辅助下方的 `RViewHelper` 创建)
* 最后需要一个 RecyclerView 的帮助类, 实现 Builder 方法, 快速初始化 Adapter [RViewHelper 类](https://github.com/wen704/SimpleMultiAdapterDemo/blob/master/library/src/main/java/com/gaofu/library/RViewHelper.java) (该类中的 `Builder` 通过上方的实现,即可快速创建,例: new RViewHelper.Builder(this, this).build(); )

