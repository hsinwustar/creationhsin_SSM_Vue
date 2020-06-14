package com.whx.creationhsin.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LikeUtils {
    public int getLike(int x,int y){
        return x+y;
    }
    public int getLikesub(int x,int y){
        return x-y;
    }


}
