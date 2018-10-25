package com.sucsoft.navigation;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;

import java.util.List;

class Navigation {
    private Context context;

    Navigation(Context context){
        this.context = context;
    }


    void autoNavigation(String lng, String lat, String myLng, String myLat){

        /*是否安装了高德地图*/
        if (isInstallApk(context, "com.autonavi.minimap")) {
            AMapNavigation(lng, lat);
        }

        /*是否安装了百度地图*/
        else if (isInstallApk(context, "com.baidu.BaiduMap")) {
            BDMapNavigation(lng, lat);
        }

        /*都没有的时候调用高德WEB导航*/
        else if (!isInstallApk(context, "com.autonavi.minimap")) {
            AmapWebNavigation(lng, lat, myLng, myLat);
        }
    }

    void AMapNavigation(String lng, String lat){
        Intent intents = new Intent();
        intents.setData(Uri.parse("androidamap://navi?sourceApplication=nyx_super&lat=" + lat + "&lon=" + lng + "&dev=0&style=2"));
        context.startActivity(intents);
    }

    void BDMapNavigation(String lng, String lat){
        Intent intent = new Intent();
        intent.setData(Uri.parse("baidumap://map/navi?coord_type=gcj02&src=com.sucsoft.ztec&location=" + lat + "," + lng ));
        context.startActivity(intent);
    }

    void AmapWebNavigation(String lng, String lat, String myLng, String myLat){
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse("http://uri.amap.com/navigation?from=" + myLng + "," + myLat + "&to="+ lng + "," + lat + "&mode=car&src=com.sucsoft.ztec"));
        context.startActivity(intent);
    }

    /** 判断手机中是否安装指定包名的软件 */
    private boolean isInstallApk(Context context, String name) {
        List<PackageInfo> packages = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packages.size(); i++) {
            PackageInfo packageInfo = packages.get(i);
            if (packageInfo.packageName.equals(name)) {
                return true;
            } else {
                continue;
            }
        }
        return false;
    }
}
