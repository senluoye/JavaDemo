package com.qks.freemarkerdemo.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qks.freemarkerdemo.entity.Person;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

/**
 * @ClassName TestFreemarker
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-11-09 11:17
 */
public class TestFreemarker {
    String dir = "F:\\CodeAbout\\java\\Demo\\FreeMarkerDemo\\src\\main\\resources\\templates\\fm";


    /**
     * 模板引擎转换
     *
     * @throws Exception
     */
    @Test
    public void t1() throws Exception {
        Configuration conf = new Configuration();
        //加载模板文件(模板的路径)
        conf.setDirectoryForTemplateLoading(new File(dir));
        // 加载模板
        Template template = conf.getTemplate("/freemarker-demo.ftl");
        // 定义数据

        Map<String, String> root = new HashMap<>();
        root.put("world", "Hello World");
        // 定义输出
        Writer out = new FileWriter(dir + "/freemarker.html");
        template.process(root, out);
        System.out.println("转换成功");
        out.flush();
        out.close();
    }

    /**
     * 转换实体Bean
     *
     * @throws IOException
     * @throws TemplateException
     */
    @Test
    public void t2() throws IOException, TemplateException {
        Configuration conf = new Configuration();
        //加载模板文件(模板的路径)
        conf.setDirectoryForTemplateLoading(new File(dir));
        // 加载模板
        Template template = conf.getTemplate("/freemarker-demo2.ftl");
        // 定义数据

        Person person = new Person();
        person.setId(1);
        person.setName("小明");
        Map<String, Object> root = new HashMap<>();
        root.put("person", person);
        // 定义输出
        Writer out = new FileWriter(dir + "/freemarker-demo2.html");
        template.process(root, out);
        System.out.println("转换成功");
        out.flush();
        out.close();
    }

    /**
     * List
     *
     * @throws TemplateException
     * @throws IOException
     */
    @Test
    public void t3() throws TemplateException, IOException {
        Configuration conf = new Configuration();
        //加载模板文件(模板的路径)
        conf.setDirectoryForTemplateLoading(new File(dir));
        // 加载模板
        Template template = conf.getTemplate("/freemarker-demo3.ftl");
        // 定义数据

        Person p1 = new Person();
        p1.setId(1);
        p1.setName("小明");
        Person p2 = new Person();
        p2.setId(2);
        p2.setName("小华");
        List<Person> person = new ArrayList<>();
        person.add(p1);
        person.add(p2);
        Map<String, Object> root = new HashMap();
        root.put("person", person);
        // 定义输出
        Writer out = new FileWriter(dir + "/freemarker-demo3.html");
        template.process(root, out);
        System.out.println("转换成功");
        out.flush();
        out.close();
    }

    /**
     * Map
     *
     * @throws IOException
     * @throws TemplateException
     */
    @Test
    public void t4() throws IOException, TemplateException {
        Configuration conf = new Configuration();
        //加载模板文件(模板的路径)
        conf.setDirectoryForTemplateLoading(new File(dir));
        // 加载模板
        Template template = conf.getTemplate("/freemarker-demo4.ftl");
        // 定义数据

        Map<String, Object> root = new HashMap<>();
        Map<String, String> mxs = new HashMap<>();
        mxs.put("fbb", "范冰冰");
        mxs.put("lbb", "李冰冰");

        root.put("mxs", mxs);

        // 定义输出
        Writer out = new FileWriter(dir + "/freemarker-demo4.html");
        template.process(root, out);
        System.out.println("转换成功");
        out.flush();
        out.close();
    }

    /**
     * List<Map>
     *
     * @throws IOException
     * @throws TemplateException
     */
    @Test
    public void t5() throws IOException, TemplateException {
        Configuration conf = new Configuration();
        //加载模板文件(模板的路径)
        conf.setDirectoryForTemplateLoading(new File(dir));
        // 加载模板
        Template template = conf.getTemplate("/freemarker-demo5.ftl");
        // 定义数据

        Map root = new HashMap();
        List<Map> maps = new ArrayList<Map>();
        Map pms1 = new HashMap();
        pms1.put("id1", "范冰冰");
        pms1.put("id2", "李冰冰");
        Map pms2 = new HashMap();
        pms2.put("id1", "曾志伟");
        pms2.put("id2", "何炅");
        maps.add(pms1);
        maps.add(pms2);
        root.put("maps", maps);

        // 定义输出
        Writer out = new FileWriter(dir + "/freemarker-demo5.html");
        template.process(root, out);
        System.out.println("转换成功");
        out.flush();
        out.close();
    }
}
