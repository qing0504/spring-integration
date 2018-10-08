package wcy.springframework.service.impl;

import wcy.springframework.service.OutputService;

public class OutputServiceImpl implements OutputService {

    @Override
    public void output(String text) {
        System.out.println(text);
    }

}
