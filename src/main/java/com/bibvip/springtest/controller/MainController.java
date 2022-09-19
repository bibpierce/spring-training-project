package com.bibvip.springtest.controller;

import com.bibvip.springtest.model.User;
import com.bibvip.springtest.services.UserService;
import com.bibvip.springtest.services.impl.WriteData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping(path = "/spring-test")
@Slf4j
public class MainController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/add")
    public void addNewUser(@RequestParam("name") String name,
                           @RequestParam("picture") MultipartFile picture,
                           @RequestParam("vacCard") MultipartFile vacCard,
                           @RequestParam("primaryId") MultipartFile primaryId,
                           @RequestParam("secondaryId") MultipartFile secondaryId) throws IOException {
        try {
            byte[] pic = picture.getBytes();
            byte[] vac = vacCard.getBytes();
            byte[] prm = primaryId.getBytes();
            byte[] sec = secondaryId.getBytes();

            User user = new User(1L,name,pic,vac,prm,sec,
                    picture.getOriginalFilename(),
                    vacCard.getOriginalFilename(),
                    primaryId.getOriginalFilename(),
                    secondaryId.getOriginalFilename());

            userService.insert(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/all")
    public Iterable<User> getAllUsers() {
        return userService.findAll();
    }


    @DeleteMapping("/del/{id}")
    public User deleteUser(@Valid @PathVariable Long id) throws Exception {

        return userService.delById(id);
    }

    @PutMapping("/upd/{id}")
    public void updateUser(@PathVariable Long id,
                           @RequestParam("name") String name,
                           @RequestParam("picture") MultipartFile picture,
                           @RequestParam("vacCard") MultipartFile vacCard,
                           @RequestParam("primaryId") MultipartFile primaryId,
                           @RequestParam("secondaryId") MultipartFile secondaryId) throws IOException {

        byte[] pic = picture.getBytes();
        byte[] vac = vacCard.getBytes();
        byte[] prm = primaryId.getBytes();
        byte[] sec = secondaryId.getBytes();
        User user = new User(id,name,pic,vac,prm,sec,
                picture.getOriginalFilename(),
                vacCard.getOriginalFilename(),
                primaryId.getOriginalFilename(),
                secondaryId.getOriginalFilename());

        userService.update(user);
    }

    @GetMapping("/sel/{id}")
    public User selectUser(@PathVariable long id) throws Exception {
        return userService.findById(id);
    }

    @GetMapping("/exp")
    public void exportToExcel() throws IOException {
        List<User> listOfUser = userService.findAll();
        WriteData export = new WriteData();
        export.exportAll(listOfUser);
    }

    @GetMapping("/single_exp/{id}")
    public void singleExport(@Valid @PathVariable long id) throws IOException {
        try {
            User singleListOfUser = userService.singleExport(id);
            WriteData sinExport = new WriteData();
            sinExport.singleExport(singleListOfUser);
        } catch (NullPointerException e){
            log.info("Invalid ID");
        }
    }
}

