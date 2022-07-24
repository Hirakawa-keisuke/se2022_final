package com.example.linebot.replier;

import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

public class BookOn implements Replier{

    private final  String text;

    public BookOn(String text){
        this.text = text;
    }

    @Override
    public Message reply(){
        return new TextMessage(text);
    }

}
