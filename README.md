# NavRouter

## 简介
支持给`activity`绑定url，从而利用`url`唤起`activity`,并支持浏览器跳应用页面。可结合Android Studio 2.3 自带的`App Links Assistant`对`url`和`activity`的映射关系进行统一管理，使用详情可参见我的上一篇文章[Android Studio 2.3 特性：生成Android App Links](http://blog.leomobile.top/2017/03/20/Android%20Studio%202.3%20%E7%89%B9%E6%80%A7%EF%BC%9A%E7%94%9F%E6%88%90Android%20App%20Links/)

## 使用
### 1.集成
由于暂时未将库上传至jCenter，目前需要手动引入`navrouter`模块进行调用（后期补上）
### 2.使用
####2.1 配置映射关系
在`AndroidManifest`中配置`activity`与`url`的映射关系，其中`data`标签配置的scheme、host、path即为给定url的相关信息。
![AndroidManifest](http://upload-images.jianshu.io/upload_images/1894569-9c45a76ffb1e63c8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

可利用Android Studio 2.3中的`App Links Assistant`进行统一管理，并直接输入url测试是否关联成功。
![](http://upload-images.jianshu.io/upload_images/1894569-8a85a544c4f72d42.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### 2.2 调用跳转
直接调用下来方法，传入`context`和对应的`url`即可跳转指定的页面

```
NavRouter.from(context).toUri("http://blog-debug.leozheng.com/test");
```
#### 2.3 支持获取参数
在`activity`的`oncreate`可以通过`intent`拿到原始的`uri`,而`uri`包含了所有参数的信息，使用`uri.getQueryParameter(key)`就能得到对应的值。

```
Intent appLinkIntent = getIntent();
String appLinkAction = appLinkIntent.getAction();
Uri appLinkData = appLinkIntent.getData(); //传递原始信息的Uri
```
由于取出的为String类型，为了方便使用，通过`ParamUtils`工具类能简单获取给定类型的值。例如：
```int year = ParamUtils.getIntExtra(appLinkData, "year", 0);```

同样，使用者也可以根据个人的需求，封装其他的方法（如将jsonString转换为对应的实体，这里也就不展开了）。
#### 2.4 支持CallBack
使用者可以利用两种方法实现CallBack
1.在`Application`中实现`NavRouterCallBackFactory`，即可实现全局统一控制

```
public class RouterApplication extends Application implements NavRouterCallBackFactory {

    @Override
    public NavRouterCallBack provideNavRouterCallBack() {
        return new NavRouterCallBack() {
            @Override
            public boolean beforeOpen() {
                Log.d("NavRouter", "beforeOpen from Application");

                return true; //此处可添加跳转前置判断
            }

            @Override
            public void afterOpen() {
                Log.d("NavRouter", "afterOpen from Application");
            }

            @Override
            public void onError(Intent intent) {
                Toast.makeText(getApplicationContext(), "There is no activity matched for " + intent, Toast.LENGTH_SHORT).show();
            }
        };
    }
}
```

2.在`Activity`中使用，可根据某一特定`activity`进行专门配置

```          /**
                 * Show the custom NavRouterCallBack.Compared with default callback {@link RouterApplication}
                 */
                NavRouter.from(MainActivity.this).toUri(((TextView) v).getText().toString(), -1, new NavRouterCallBack() {
                    @Override
                    public boolean beforeOpen() {
                        Log.d("NavRouter", "beforeOpen from " + MainActivity.class.getSimpleName());
                        Toast.makeText(MainActivity.this, MainActivity.class.getSimpleName() + " was reject in Activity", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    @Override
                    public void afterOpen() {
                        Log.d("NavRouter", "afterOpen from " + MainActivity.class.getSimpleName());
                    }
                    @Override
                    public void onError(Intent intent) {
                        Log.d("NavRouter", "beforeOpen from " + MainActivity.class.getSimpleName());
                    }
                });
```

####2.5 支持传Bundle
调用`NavRouter.from(context).putExtra(key,bundle).toUri(Uri)` 可传递Bundle给对应的`activity`，并用`getIntent().getBundleExtra(key)`取得对应的Bundle。

#### 2.6 支持外部浏览器及WebView唤起
WebView中唤起可参考Demo中的`NavWebViewClient`进行自定义定制。而从浏览器唤起时，可通过控制需要唤起的`activity`的`launchMode`修改页面的启动模式



## 开源协议
Apache License 2.0

## 联系作者
- git直接提[issue](https://github.com/zktufo/NavRouter)
- 邮件 zkt713@gmail.com


