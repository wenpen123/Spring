package com.wenpeng.Test;

import com.wenpeng.Mode.Account;
import com.wenpeng.Utils.mybatiesUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.List;

public class mybatiesTest
{
    /**
     * 根据id查询用户
     */
    @Test
    public void mybatiesTest() throws IOException
    {
        //读取配置文件
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //根据配置文件构建会话工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Connection connection = sqlSession.getConnection();
        Account account = sqlSession.selectOne("com.wenpeng.Mode.Account.findAccountById", 2);
        System.out.println(account.toString());
        sqlSession.close();
    }

    /**
     * 模糊查询用户，根据名称模糊查询
     *
     * @throws IOException
     */
    @Test
    public void mybatiesTest2() throws IOException
    {
        //读取配置文件
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //根据配置文件构建会话工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Account> accounts = sqlSession.selectList("com.wenpeng.Mode.Account.findAccountByName", "h");
        for (Account account : accounts) {
            System.out.println(account);
        }
        sqlSession.close();
    }

    /**
     * 添加用户
     *
     * @throws Exception
     */
    @Test
    public void mybatiesTest3() throws Exception
    {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Account account = new Account();
        account.setUsername("张飞");
        account.setBalance(5000);
        int insert = sqlSession.insert("com.wenpeng.Mode.Account.addAccount", account);
        if (insert > 0) {
            System.out.println("成功插入" + insert + "条数据");
        }
        else {
            System.out.println("新增失败");
        }
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 更新用户
     *
     * @throws IOException
     */
    @Test
    public void updateAccount() throws IOException
    {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Account account = new Account();
        account.setId(10);
        account.setUsername("张飞");
        account.setBalance(1000);
        sqlSession.update("com.wenpeng.Mode.Account.updateAccount", account);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 删除用户
     *
     * @throws IOException
     */
    @Test
    public void deleteAccount() throws IOException
    {/*
        //获取会话工厂
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
    *//*    int tem;
        while ((tem = inputStream.read()) != -1) {
            System.out.print((char) tem);
        }*//*
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);*/
        mybatiesUtils mybatiesUtils = new mybatiesUtils();
        SqlSession sqlSession = mybatiesUtils.getSqlSession();
        int count = sqlSession.delete("com.wenpeng.Mode.Account.deleteAccount", 10);
        if (count > 0) {
            System.out.println("成功删除" + count + "条数据");
        }
        else {
            System.out.println("删除失败");
        }
        sqlSession.commit();
        sqlSession.close();
    }
}








