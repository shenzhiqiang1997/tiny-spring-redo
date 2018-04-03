package priv.shen.resource;

/**
 * 资源加载接口
 * 定义了加载资源的规范
 */
public interface ResourceLoader {
    Resource getResource(String location) throws Exception;
}
