package priv.shen.beans;

import org.junit.Assert;
import org.junit.Test;
import priv.shen.beans.resource.Resource;
import priv.shen.beans.resource.ResourceLoader;
import priv.shen.beans.resource.UrlResourceLoader;

public class ResourceLoaderTest {
    @Test
    public void test() throws Exception {
        ResourceLoader resourceLoader=new UrlResourceLoader();
        Resource resource=resourceLoader.getResource("tiny-ioc.xml");
        Assert.assertNotNull(resource.getInputStream());
    }
}
