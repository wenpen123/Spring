package com.wenpeng.Impl;

import com.wenpeng.Dao.AccountDao;
import com.wenpeng.Mode.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class AccountDaoImpl implements AccountDao
{
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate()
    {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addAccount(Account account)
    {
        String sql = "insert into account (username,balance) value (?,?)";
        Object[] objects = new Object[]{
                account.getUsername(),
                account.getBalance()
        };
        int update = jdbcTemplate.update(sql, objects);
        return update;
    }

    @Override
    public int updateAccount(Account account)
    {
        String sql = "update account set name=? , balance= ? where id=?";
        Object[] objects = new Object[]{
                account.getUsername(),
                account.getBalance(),
                account.getId()
        };
        int update = jdbcTemplate.update(sql, objects);
        return update;
    }

    @Override
    public int deleteAccount(int id)
    {
        String sql = "delete from account where id= ?";
        int update = jdbcTemplate.update(sql, id);
        return update;
    }

    @Override
    public Account findAccount(int id)
    {
        String sql = "select * from account where id=?";
        Account query = (Account) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Account>(Account.class), id);
        return query;
    }

    @Override
    public List<Account> findAllAccount()
    {
        String sql = "select * from account";
        List<Account> accounts = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Account>(Account.class));
        return accounts;
    }

    /**
     * @param outUser 转出账户
     * @param inUser  转入账户
     * @param balance 金额
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false)
    public void transfer(int outUser, int inUser, double balance)
    {
        //转出金额
        String outSql = "update account set balance = balance - ? where id= ?";
        //转入金额
        String inSql = "update account set balance = balance + ? where id= ?";
        jdbcTemplate.update(outSql, balance, outUser);
//        int i = 10/0;
        jdbcTemplate.update(inSql, balance, inUser);

    }
}
