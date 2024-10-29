package pl.edu.pjatk.MPR_Spring_32.service;

import org.springframework.stereotype.Component;

@Component
public class StringUtilsService {
    public String wholeCapital(String string){
        return string.toUpperCase();
    }
    public String firstCapital(String string){
        return string.toLowerCase().substring(0, 1).toUpperCase() + string.toLowerCase().substring(1);
    }
}
