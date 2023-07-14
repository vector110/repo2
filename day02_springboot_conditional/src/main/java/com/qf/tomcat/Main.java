package com.qf.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.Writer;

public class Main {

    File tmpDir = new File("D:\\develop\\apache-tomcat-8.0.49");
    Tomcat tomcat = new Tomcat();

    public static void main(String[] args) throws Throwable {
        new Main().init();
    }

    private void init() throws Throwable {
        /*Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    try {
                        tomcat.destroy();
                    } catch (LifecycleException e) {
                        e.printStackTrace();
                    }
                })
        );*/
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    tomcat.destroy();
                } catch (LifecycleException e) {
                    e.printStackTrace();
                }
            }
        }));
        test();
    }

    private void test() throws Throwable {
        tomcat.setBaseDir(tmpDir.getAbsolutePath()); // 设置工作目录
        tomcat.setHostname("localhost"); // 主机名, 将生成目录: {工作目录}/work/Tomcat/{主机名}/ROOT
        System.out.println("工作目录: " + tomcat.getServer().getCatalinaBase().getAbsolutePath());

        tomcat.setPort(80);
        Connector conn = tomcat.getConnector(); // Tomcat 9.0 必须调用 Tomcat#getConnector() 方法之后才会监听端口
        System.out.println("连接器设置完成: " + conn);

        // contextPath要使用的上下文映射，""表示根上下文
        // docBase上下文的基础目录，用于静态文件。相对于服务器主目录必须存在 ({主目录}/webapps/{docBase})
        Context ctx = tomcat.addContext("", /*{webapps}/~*/ "/ROOT");

        Tomcat.addServlet(ctx, "globalServlet", new HttpServlet() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void service(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/plain");
                response.setHeader("Server", "Embedded Tomcat");
                try{
                    Writer writer = response.getWriter();
                    writer.write("Hello, Embedded Tomcat!");
                    writer.flush();
                }catch (Exception e){

                }
            }
        });
        ctx.addServletMappingDecoded("/", "globalServlet");

        tomcat.start();
        System.out.println("tomcat 已启动");
        tomcat.getServer().await();
    }

}


   /* tomcat 嵌入正常, 让我们继续, 如何令 tomcat 加载 Spring Framework ?

        嵌入式 tomcat 集成 Spring 框架*/

/*public class Main {
    Tomcat tomcat;

    {
        tomcat = new Tomcat();
//      tomcat.setAddDefaultWebXmlToWebapp(false);
//      tomcat.noDefaultWebXmlPath();
    }

    public void run() throws Throwable {
        tomcat.setBaseDir("F:\\Game\\tomcat");
        tomcat.setHostname("localhost");
        tomcat.setPort(80);
//      tomcat.enableNaming();

//      tomcat.getHost().setAutoDeploy(false);
//      tomcat.getEngine().setBackgroundProcessorDelay(-1);

        Context ctx = tomcat.addContext("", "ROOT");

        ctx.addLifecycleListener(new LifecycleListener() {
            public void lifecycleEvent(LifecycleEvent event) {
//              System.out.println(event.getLifecycle().getState().name());
                if (event.getLifecycle().getState() == LifecycleState.STARTING_PREP) {
                    try {
                        new SpringServletContainerInitializer().onStartup(new HashSet<Class<?>>() {
                            private static final long serialVersionUID = 1L;
                            {
                                add(WebAppInitializer.class);
                            }
                        }, ctx.getServletContext());
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            }
        });

//      tomcat.init();
        tomcat.getConnector();
        tomcat.start();
        tomcat.getServer().await();
    }*/

    /*public static void main(String[] args) throws Throwable {
        new Main().run();
    }*/
