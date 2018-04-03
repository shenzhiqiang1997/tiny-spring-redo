package priv.shen;

import org.junit.Assert;
import org.junit.Test;
import priv.shen.resource.Resource;
import priv.shen.resource.ResourceLoader;
import priv.shen.resource.UrlResourceLoader;

public class ResourceLoaderTest {
    @Test
    public void test() throws Exception {
        ResourceLoader resourceLoader=new UrlResourceLoader();
        Resource resource=resourceLoader.getResource("tiny-ioc.xml");
        Assert.assertNotNull(resource.getInputStream());
    }
}
