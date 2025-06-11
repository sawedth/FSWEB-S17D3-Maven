package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
public class KoalaController {
    Map<Integer, Koala> koalas;

    @PostConstruct
    void init(){
        koalas = new HashMap<>();
    }

    @GetMapping("/koalas")
    public List<Koala> kangarooList(){
        return koalas.values().stream().toList();
    }

    @GetMapping("/koalas/{id}")
    public Koala koalaReturn(@PathVariable Integer id){
        if(id < 0){
            throw new ZooException("Please insert an id bigger than 0", HttpStatus.BAD_REQUEST);
        }
        if(!koalas.containsKey(id)){
            throw new ZooException("Please insert a legit id", HttpStatus.NOT_FOUND);
        }
        if(id < 0){
            throw new ZooException("Please insert an id bigger than 0", HttpStatus.BAD_REQUEST);
        }
        return koalas.get(id);
    }

    @PostMapping("/koalas")
    public Koala koalaPost(@RequestBody Koala koala){
        koalas.put(koala.getId(), koala);
        return koala;
    }

    @PutMapping("/koalas/{id}")
    public Koala koalaUpdate(@PathVariable Integer id,@RequestBody Koala koala){
        koalas.put(id, koala);
        return koala;
    }

    @DeleteMapping("/koalas/{id}")
    public Koala koalaDelete(@PathVariable Integer id){
        if(id < 0){
            throw new ZooException("Please insert an id bigger than 0", HttpStatus.BAD_REQUEST);
        }
        if(!koalas.containsKey(id)){
            throw new ZooException("Please insert a legit id", HttpStatus.NOT_FOUND);
        }
        Koala deletedKoala = koalas.get(id);
        koalas.remove(id);
        return deletedKoala;
    }
}
