package com.gootrip.util;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2016/11/14.
 */
public class CharTools {
    /**
     * 转换编码 ISO-8859-1到GB2312
     * @param text 111
     * @return
     */
    public static final String ISO2GB(String text) {
        String result;
        try {
            result = new String(text.getBytes("ISO-8859-1"), "GB2312");
        }
        catch (UnsupportedEncodingException ex) {
            result = ex.toString();
        }
        return result;
    }
}
