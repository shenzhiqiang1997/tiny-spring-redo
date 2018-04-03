package priv.shen.beanDefinitionReader;

import org.w3c.dom.*;
import priv.shen.beanDefinition.BeanDefinition;
import priv.shen.beanDefinition.PropertyValue;
import priv.shen.beanDefinition.PropertyValues;
import priv.shen.resource.ResourceLoader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * 从XML文件中加载bean定义的加载器
 * 实现了从xml文件中加载bean定义的方法
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(ResourceLoader resourceLoader){
        super(resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        //通过资源加载器获得bean定义的xml文件的输入流
        InputStream inputStream=getResourceLoader().getResource(location).getInputStream();
        doLoadBeanDefinitions(inputStream);
    }

    //从资源流中加载bean定义
    private void doLoadBeanDefinitions(InputStream inputStream) throws Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder= factory.newDocumentBuilder();
        //从输入流中解析得到xml文档
        Document document=builder.parse(inputStream);
        registerBeanDefinitions(document);
        //加载完成后关闭资源流
        inputStream.close();
    }

    //从解析出的xml文档中加载并注册bean定义
    private void registerBeanDefinitions(Document document) {
        //得到xml文档的根元素
        Element root = document.getDocumentElement();
        //以根元素为起点解析出bean定义
        parseBeanDefinitions(root);
    }

    //从xml文档中解析出bean定义
    private void parseBeanDefinitions(Element root) {
        //得到根元素的全部bean子元素
        NodeList beans = root.getElementsByTagName("bean");
        for (int i = 0; i < beans.getLength(); i++) {
            Node node=beans.item(i);
            if (node instanceof Element){
                Element beanElement=(Element)node;
                processBeanDefinition(beanElement);
            }
        }
    }

    //处理bean的name与beanClassName信息
    private void processBeanDefinition(Element beanElement) {
        //从bean元素中解析出bean的名称与bean的Class名称
        String name=beanElement.getAttribute("name");
        String beanClassName=beanElement.getAttribute("class");

        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName(beanClassName);
        //进一步处理该bean的属性信息
        processProperty(beanElement,beanDefinition);

        //将该bean定义放入map中存放
        getRegister().put(name,beanDefinition);
    }

    //处理bean的属性信息
    private void processProperty(Element beanElement, BeanDefinition beanDefinition) {
        NodeList properties=beanElement.getElementsByTagName("property");

        for (int i = 0; i < properties.getLength(); i++) {
            Node node=properties.item(i);
            if (node instanceof Element){
                Element propertyElement=(Element)node;
                String name=propertyElement.getAttribute("name");
                Object value=propertyElement.getAttribute("value");
                beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name,value));
            }
        }
    }


}
