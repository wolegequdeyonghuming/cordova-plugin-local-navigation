## a simple navigation plugin.

using origin source here: [跳转到高德地图或百度地图或高德网页导航](https://blog.csdn.net/w752325717/article/details/77584632)

what i do is modify it into cordova plugin.

* How to use:
   ```
   localNavigation.start(function(){}, function(e){alert(e)}, {
               type:'auto',
               toLng:"113.010517",
               toLat:"27.968234",
               myLng:"112.891259",
               myLat:"28.193234"
           })
   ```

* type:
    > default by 'auto', can be as: 'amap', 'baidu', 'web'.

    > when 'auto', the plugin will detect what map you have install,
    by order as below

* myLng & myLat:
    > is needed when using 'web' navigation. thus, when using 'auto', by considering of the 'web' situation, it's better not be null.
