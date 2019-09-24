package com.vi.tmall.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class MybatisGenerator {
	public static void main(String[] args) throws Exception{
		 System.out.println("开始逆向工程！");
	        List<String> warnings = new ArrayList<String>();
	        boolean overwrite = true;
	        InputStream is= MybatisGenerator.class.getClassLoader().getResource("generator.xml").openStream();
	        ConfigurationParser cp = new ConfigurationParser(warnings);
	        Configuration config = cp.parseConfiguration(is);
	        is.close();
	        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
	        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
	        myBatisGenerator.generate(null);
	        System.out.println("完成！");
	}
}
