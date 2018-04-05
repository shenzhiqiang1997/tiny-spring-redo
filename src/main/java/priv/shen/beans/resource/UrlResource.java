package priv.shen.beans.resource;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 统一定位资源 实现了获取资源输入流的方法
 */
public class UrlResource implements Resource {
    private final URL url;

    public UrlResource(URL url) {
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws Exception {
        //从统一资源定位中获取资源链接
        URLConnection urlConnection=url.openConnection();
        //打开资源链接
        urlConnection.connect();
        //从资源链接中获取输入流
        return urlConnection.getInputStream();
    }
}
