package com.wenpeng.Dao;

import com.wenpeng.Mode.Account;

import java.util.List;

public interface AccountDao
{
    public int addAccount(Account account);

    public int updateAccount(Account account);

    public int deleteAccount(int id);

    //根据id查询账户
    public Account findAccount(int id);

    //查询所有账户
    public List<Account> findAllAccount();

    //转账方法
    public void transfer(int outUser, int inUser, double balance);


}
