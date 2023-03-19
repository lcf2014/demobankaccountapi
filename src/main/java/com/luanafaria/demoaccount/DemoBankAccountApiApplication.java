package com.luanafaria.demoaccount;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;

@SpringBootApplication
public class DemoBankAccountApiApplication {

    public static HashSet<Integer> removeDuplicates(int[] arrayInt){
        HashSet<Integer> setIntegers = new HashSet<>();

        for(int number: arrayInt){
            setIntegers.add(number);
        }
        return setIntegers;


    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoBankAccountApiApplication.class, args);
        int[] arrayInt = {0,0,0,0,0,1,1,1,2,2,2,4,4,4,5,6,6,6,8,8,8};
        System.out.println(removeDuplicates(arrayInt));
    }

}
