package com.example.linebot.replier;

import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;

// フォローされた時用の返信クラス
public class Follow implements Replier {

    private FollowEvent event;

    public Follow(FollowEvent event) {
        this.event = event;
    }

    @Override
    public Message reply() {
        String text = "画像を送信すると画像内にある本の名前が出力されます";
        return new TextMessage(text);
    }
}
