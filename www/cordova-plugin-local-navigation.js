
var exec = require('cordova/exec');


var localNavigation = {

    /**
     *      auto type should detect your device apps, and choose it by orders: gaode, baidu.
     *      if none of these map app installed, it will turn into browser, visiting gaode map's website navigation
     *      thus your location is needed
     *
     *  @param successCallback: function
     *  @param errorCallback: function
     *  @param params: object
     *      {
     *          type: 'auto' || 'amap' || 'baidu' || 'web',
     *          toLng,
     *          toLat,
     *          myLng, (needed when using browser)
     *          myLat, (needed when using browser)
     *      }
     * */
    start: function(successCallback, errorCallback, params){

        if (!params.type){
            params.type = 'auto';
        }
        if (!params.toLng || !params.toLat){
            errorCallback('params missing');
            return;
        }
        if (params.type === 'browser'){
            if(!params.myLng || !params.myLat){
                errorCallback('myLng & myLat params is required when using web navigation!');
                return;
            }
        }

        exec(
            successCallback,
            errorCallback,
            'LocalNavigation',
            'navigation',
            [params]
        )
    }
}

module.exports = localNavigation;

