package com.qf.anno;

import com.qf.condition.MyConditionalClass;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Conditional(MyConditionalClass.class)
public @interface MyConditional {

    String value();

}
