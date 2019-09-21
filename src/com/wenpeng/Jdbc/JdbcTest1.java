package com.wenpeng.Jdbc;


import com.wenpeng.Dao.AccountDao;
import com.wenpeng.Mode.Account;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcTest1
{

    /**
     * 使用execute()方法建表
     *
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext1.xml");
        JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
        //使用execute执行sql语句，创建用户账户管理表
        jdbcTemplate.execute("create account");
        System.out.println("账户表创建成功");

    }

    //修改账户
    @Test
    public void test() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
        //使用execute执行sql语句，创建用户账户管理表
        jdbcTemplate.execute("update account set username='zhaoyun' where id='1'");
        System.out.println("账户表创建成功");


    }

    //添加账户
    @Test
    public void test1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountDao accountDao = (AccountDao) applicationContext.getBean("accountDao");
        Account account = new Account();
        account.setUsername("文朋3");
        account.setBalance(2.1702);
        int num = accountDao.addAccount(account);
        if (num > 0) {
            System.out.println("成功插入了" + num + "条数据");
        } else {
            System.out.println("添加失败");
        }
    }

    //根据id查询用户
    @Test
    public void test2() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountDao accountDao = (AccountDao) applicationContext.getBean("accountDao");
        Account account = accountDao.findAccount(1);
        System.out.println(account.getUsername() + "==" + account.getBalance());
    }

    //查询所有用户
    @Test
    public void test3() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountDao accountDao = (AccountDao) applicationContext.getBean("accountDao");
        List<Account> allAccount = accountDao.findAllAccount();
        for (Account accounts : allAccount
        ) {
            System.out.println(accounts);
        }
    }

    //测试转账方法
    @Test
    public  void test4(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext1.xml");
        AccountDao accountDao = (AccountDao) applicationContext.getBean("accountDao");
        accountDao.transfer(1,6,10);
    }
}
