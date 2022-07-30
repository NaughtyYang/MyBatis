package com.amarsoft.mybatis.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Statement;
import java.util.Properties;

/**
 * @author yzhao
 * @date 2022-07-19
 *
 * 用来实现mybatis的插件接口，来完成一个插件用来包装四大对象
 */
@Intercepts(value = {
        @Signature(type = StatementHandler.class,method = "parameterize",args = Statement.class)
})
public class MyPlugin implements Interceptor {

    /*
    *
    * 用来拦截目标方法，并返回执行结果
    * */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        //拿到拦截对象

        Object target = invocation.getTarget();

        MetaObject metaObject = SystemMetaObject.forObject(target);
        //metaObject.getValue("parameterHandler.")

        Object proceed = invocation.proceed();
        return proceed;
    }

    //用来为当前对象创建代理对象
    @Override
    public Object plugin(Object target) {
        Object wrap = Plugin.wrap(target, this);
        return wrap;
    }

    //将插件注册时的properties设置进来
    @Override
    public void setProperties(Properties properties) {
        System.out.println("参数是:"+properties.getProperty("username").toString());
    }
}
