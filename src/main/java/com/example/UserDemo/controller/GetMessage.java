package com.example.UserDemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.util.*;


@org.springframework.web.bind.annotation.RestController
@RequestMapping ("/message")
public class GetMessage {
    File file = new File("greetings.txt");
    File newFile = new File("log.txt");


    @GetMapping("/messages")
    public String getMessages() {
        StringBuilder builder = new StringBuilder();
        String x;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            while(( x = bufferedReader.readLine()) != null ){
                builder.append(x);
            }
        } catch(IOException exception){

            System.out.println(exception.getMessage());
        }
        return builder.toString();
    }

    @GetMapping ("/counted")
    public int getMessageCount() {
         int count = 1;
        List<String> myList;
        String x;
        String myMessage = null;
        StringBuilder sb = new StringBuilder();

        try (BufferedReader buff = new BufferedReader(new FileReader(newFile))) {
            while ((x = buff.readLine()) != null) {
                sb.append(x);
                myMessage = sb.toString();
            }
            assert myMessage != null;
            myList = Arrays.asList(myMessage.split("#"));
            count =myList.size()-1;
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        return count;
    }


    @PostMapping("/messages")
public String postGreeting(@RequestBody String message) {
    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
        bufferedWriter.write(message + "#");

    }catch (IOException exception) {
        System.out.println(exception.getMessage());
    }
    return "message successfully saved";
}


    @PostMapping("/counted")
    public String postMessage() {
        try (BufferedWriter writerLog = new BufferedWriter(new FileWriter(newFile, true))) {
            writerLog.write("New message posted, " + "# \n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "Message Posted Successfully";
}
}