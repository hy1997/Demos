package com.huyi.demo.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


/**
 * 1.ServletContextListener -- 监听servletContext对象的创建以及销毁
 * <p>
 *     1.1    contextInitialized(ServletContextEvent arg0)   -- 创建时执行
 * <p>
 *     1.2    contextDestroyed(ServletContextEvent arg0)  -- 销毁时执行
 * <p>
 * 2.HttpSessionListener  -- 监听session对象的创建以及销毁
 * <p>
 *     2.2   sessionCreated(HttpSessionEvent se)   -- 创建时执行
 * <p>
 *     2.2   sessionDestroyed(HttpSessionEvent se) -- 销毁时执行
 * <p>
 * 3.ServletRequestListener -- 监听request对象的创建以及销毁
 * <p>
 *     3.1    requestInitialized(ServletRequestEvent sre) -- 创建时执行
 * <p>
 *     3.2    requestDestroyed(ServletRequestEvent sre) -- 销毁时执行
 * <p>
 * 4.ServletContextAttributeListener  -- 监听servletContext对象中属性的改变
 * <p>
 *     4.1    attributeAdded(ServletContextAttributeEvent event) -- 添加属性时执行
 * <p>
 *     4.2    attributeReplaced(ServletContextAttributeEvent event) -- 修改属性时执行
 * <p>
 *     4.3    attributeRemoved(ServletContextAttributeEvent event) -- 删除属性时执行
 * <p>
 * 5.HttpSessionAttributeListener  --监听session对象中属性的改变
 * <p>
 *     5.1    attributeAdded(HttpSessionBindingEvent event) -- 添加属性时执行
 * <p>
 *     5.2    attributeReplaced(HttpSessionBindingEvent event) -- 修改属性时执行
 * <p>
 *     5.3    attributeRemoved(HttpSessionBindingEvent event) -- 删除属性时执行
 * <p>
 * 6.ServletRequestAttributeListener  --监听request对象中属性的改变
 * <p>
 *     6.1    attributeAdded(ServletRequestAttributeEvent srae) -- 添加属性时执行
 * <p>
 *     6.2    attributeReplaced(ServletRequestAttributeEvent srae) -- 修改属性时执行
 * <p>
 *     6.3    attributeRemoved(ServletRequestAttributeEvent srae) -- 删除属性时执行
 */
@WebListener
public class RequestListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.err.println("start");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.err.println("delete");
    }
}
