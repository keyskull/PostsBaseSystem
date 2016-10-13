package feature;



/**
 * Created by Jane on 2016/10/12.
 */

abstract class Api<T>{
    abstract T Api();
    T apply(){return Api();}
}

public interface ApiInterface{
    Api getApiClass();
    BaseInfo getBaseInfo();
}
