package com.example.vintec;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by Manish on 9/30/2016.
 */

public class NetworkOperation {



    public static String generateUrl(String url, Map<String,String> params)
    {

        StringBuilder builder = new StringBuilder();

        for (String key : params.keySet())
        {
            Object value = params.get(key);
            //   if (value != null)
            //  {
            try
            {
                value = URLEncoder.encode(String.valueOf(value), "UTF-8");


                if (builder.length() > 0)
                    builder.append("&");
                builder.append(key).append("=").append(value);
            }
            catch (UnsupportedEncodingException e)
            {
            }
            //  }
        }

        url += "?" + builder.toString();



        return url;

    }
}

