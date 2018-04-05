package priv.shen.beans.beanDefinition;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义了了一个bean属性的List
 * 封装对bean属性List的操作
 */
public class PropertyValues {
    private List<PropertyValue> propertyValueList
            = new ArrayList<>();

    public void addPropertyValue(PropertyValue propertyValue){
        //在这里可以检查是否有重复属性等额外操作
        propertyValueList.add(propertyValue);
    }

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }
}
