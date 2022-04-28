package com.github.silvaisabela.auth.controllers;

import com.github.silvaisabela.auth.dto.AccountDto;
import com.github.silvaisabela.auth.model.Account;
import com.github.silvaisabela.auth.repositories.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/signin")
    public ModelAndView signin(AccountDto accountDto) {
        return new ModelAndView("signin");
    }

    @PostMapping("/accounts")
    public  ModelAndView create(@Valid AccountDto accountDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ModelAndView("signin");
        }

        Account account = accountRepository.findOneByEmail(accountDto.getEmail());
        if(account != null){
            return new ModelAndView("login");
        }
        account = modelMapper.map(accountDto, Account.class);
        accountRepository.save(account);
        return new ModelAndView("login");
    }

    @GetMapping("/login")
    public ModelAndView loginView(AccountDto accountDto){
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid AccountDto accountDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ModelAndView("login");
        }

        Account account = accountRepository.findOneByEmail(accountDto.getEmail());
        if(!accountDto.getPassword().equals(account.getPassword())){
            return new ModelAndView("login");
        }

        return new ModelAndView("home");
    }

    @GetMapping("/")
    public  ModelAndView index(AccountDto accountDto){
        return new ModelAndView("login");
    }
}
