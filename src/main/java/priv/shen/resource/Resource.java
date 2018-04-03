package priv.shen.resource;

import java.io.InputStream;

/**
 * 资源定位的接口
 * 定义了资源输入流获取的规范
 */
public interface Resource {
    InputStream getInputStream() throws Exception;
}
