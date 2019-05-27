package org.lastsurprise.goodgame.util;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    private static final String KEY="GoodGameWellPlayed";
    private static final String EXP="exp";
    private static final String PAYLOAD="payload";

    //加密
    public static <T> String signJwt(T object,long time){
        try{
            JWTSigner signer=new JWTSigner(KEY);
            Map<String,Object> claims=new HashMap<>();
            ObjectMapper objectMapper=new ObjectMapper();
            String objectString=objectMapper.writeValueAsString(object);
            claims.put(PAYLOAD,objectString);
            claims.put(EXP,System.currentTimeMillis()+time);
            return signer.sign(claims);
        } catch (JsonProcessingException e) {
            return "sign JWT failed";
        }
    }

    //解密
    public static <T> T unsignJwt(String jwt,Class<T> classT){
        JWTVerifier verifier=new JWTVerifier(KEY);
        ObjectMapper objectMapper=new ObjectMapper();
        try{
            Map<String,Object> claims=verifier.verify(jwt);
            if (claims.containsKey(EXP)&&claims.containsKey(PAYLOAD)){
                long exp=(Long)claims.get(EXP);
                long currentTimeMillis=System.currentTimeMillis();
                if (exp>currentTimeMillis){
                    String objectString=(String)claims.get(PAYLOAD);
                    return objectMapper.readValue(objectString,classT);
                }
            }
        } catch (Exception e) {}
        return null;
    }
}
