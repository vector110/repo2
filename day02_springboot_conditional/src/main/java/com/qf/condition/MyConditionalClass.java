package com.qf.condition;

import com.qf.anno.MyConditional;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

public class MyConditionalClass implements Condition {//获取注解的value

    //AnnotatedTypeMetadata 获取注解的元数据
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
       /* try {
            Class.forName("com.mysql.jdbc.Driver");
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        Map<String, Object> attributesMap = annotatedTypeMetadata.getAnnotationAttributes(MyConditional.class.getName());
        if(attributesMap.containsKey("value")){
            String value = (String) attributesMap.get("value");
            if(value.contains("mysql")) {
                try {
                    Class.forName(value);
                    return true;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return false;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
        //return false;//返回true 则表示条件成立 如果返回false则表示条件不成立
    }
}
