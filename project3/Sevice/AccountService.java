package com.example.project3.Sevice;


import com.example.project3.Model.Account;
import com.example.project3.Model.Customer;
import com.example.project3.Model.User;
import com.example.project3.Repository.AccountRepository;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
public class AccountService {


    private final AccountRepository accountRepository;
    private final AuthRepository authRepository;
    private final CustomerRepository customerRepository;


    public void CreateAccount(Integer userId ,Account account) {
        User user = authRepository.findUserById(userId);
        Customer customer = customerRepository.findCustomerById(user.getId());

        account.setCustomer(customer);
        accountRepository.save(account);


    }





    public Account accountDetail(Integer userId , Integer accountId) {
        User user = authRepository.findUserById(userId);
        Account account = accountRepository.findAccountById(accountId);


        if (account.getCustomer().getId() == user.getId()) {
            return account;
        }

        return null;
    }



    public void deposit(Integer userId , Integer accountId, Integer amount) {
        User user = authRepository.findUserById(userId);
        Account account = accountRepository.findAccountById(accountId);


        if (account.getCustomer().getId() == user.getId()) {
            account.setBalance(account.getBalance() + amount);

            accountRepository.save(account);
        }

    }




    public void withdraw(Integer userId , Integer accountId, Integer amount) {
        User user = authRepository.findUserById(userId);
        Account account = accountRepository.findAccountById(accountId);

        if (account.getCustomer().getId() == user.getId()) {
           if (account.getBalance() >= amount) {
               account.setBalance(account.getBalance() - amount);
               accountRepository.save(account);
           }
        }
    }




    public void transferFunds(Integer userAccountId ,  Integer anotherUserId ,  Integer amount) {

        Account account = accountRepository.findAccountById(userAccountId);
        Account anotherAccount = accountRepository.findAccountById(anotherUserId);

        if (account.getBalance() >= amount){
            account.setBalance(account.getBalance() - amount);
            anotherAccount.setBalance(anotherAccount.getBalance() + amount);
            accountRepository.save(account);
            accountRepository.save(anotherAccount);
        }

    }





}
