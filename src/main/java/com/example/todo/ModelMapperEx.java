//package com.example.todo;
//
//import org.springframework.stereotype.Component;
//
//@Component
//public class ModelMapperEx extends ModelMapper {
//    public <T, U> List<U> mapList(List<T> source, Class<U> targetClass) {
//        return source.stream()
//                .map(element -> map(element, targetClass))
//                .toList();
//    }
//}