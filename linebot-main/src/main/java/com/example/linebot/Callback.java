package com.example.linebot;

import com.example.linebot.modelApi.BookAPI;
import com.example.linebot.replier.Follow;
import com.example.linebot.replier.ImgOn;
import com.example.linebot.service.ImgService;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import org.springframework.beans.factory.annotation.Autowired;

@LineMessageHandler

public class Callback {

    private final ImgService imgService;
    
    @Autowired
    public Callback(ImgService imgService){
        this.imgService = imgService;
    }


    // フォローイベントに対応する
    @EventMapping
    public Message handleFollow(FollowEvent event) {
        // 実際はこのタイミングでフォロワーのユーザIDをデータベースにに格納しておくなど
        Follow follow = new Follow(event);
        return follow.reply();
    }

    // 画像のメッセージイベントに対応する
    @EventMapping
    public Message handleImg(MessageEvent<ImageMessageContent> event) {
        ImgOn imgOn = imgService.doReplyOfNewItem(event);
        return imgOn.reply();
    }

}
