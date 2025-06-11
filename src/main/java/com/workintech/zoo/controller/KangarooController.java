package com.workintech.zoo.controller;
import org.springframework.http.HttpStatus;
import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class KangarooController {
    Map<Integer, Kangaroo> kangaroos;

    @PostConstruct
    void init(){
        kangaroos = new HashMap<>();
    }

    @GetMapping("/kangaroos")
    public List<Kangaroo> kangarooList(){
        return kangaroos.values().stream().toList();
    }

    @GetMapping("/kangaroos/{id}")
    public Kangaroo kangarooReturn(@PathVariable Integer id){
        if(id < 0){
            throw new ZooException("Please insert an id bigger than 0", HttpStatus.BAD_REQUEST);
        }
        if(!kangaroos.containsKey(id)){
            throw new ZooException("Please insert a legit id", HttpStatus.NOT_FOUND);
        }
        return kangaroos.get(id);
    }

    @PostMapping("/kangaroos")
    public Kangaroo kangarooPost(@RequestBody Kangaroo kangaroo){
        if(kangaroo.getId() < 0  ){
            throw new ZooException("Please insert an id bigger than 0", HttpStatus.BAD_REQUEST);
        }
        if(kangaroo.getIsAggressive() == null  ){
            throw new ZooException("Please insert an id bigger than 0", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroo;
    }

    @PutMapping("/kangaroos/{id}")
    public Kangaroo kangarooUpdate(@PathVariable Integer id,@RequestBody Kangaroo kangaroo){
        kangaroos.remove(id);
        kangaroos.put(id, kangaroo);
        return kangaroo;
    }

    @DeleteMapping("/kangaroos/{id}")
    public Kangaroo kangarooDelete(@PathVariable Integer id){
        if(id < 0 ){
            throw new ZooException("Please insert an id bigger than 0", HttpStatus.BAD_REQUEST);
        }
        if(!kangaroos.containsKey(id)){
            throw new ZooException("Please insert a legit id", HttpStatus.NOT_FOUND);
        }
        Kangaroo deletedKangaroo = kangaroos.get(id);
        kangaroos.remove(id);
        return deletedKangaroo;
    }

}
