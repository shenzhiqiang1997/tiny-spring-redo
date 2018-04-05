package priv.shen.beans.resource;

import java.net.URL;

/**
 * 统一资源加载器 实现了统一资源的加载
 */
public class UrlResourceLoader implements ResourceLoader {
    @Override
    public Resource getResource(String location) throws Exception {
        //从当前类加载器获取指定位置的资源定位符
        URL url=this.getClass().getClassLoader().getResource(location);
        return new UrlResource(url);
    }
}
