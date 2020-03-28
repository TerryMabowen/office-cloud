package cn.mbw.oc.sso.util;


import cn.mbw.oc.sso.spi.data.user.vo.UserVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mabowen
 * @date 2019-12-25 19:57
 */
public class JwtUtil {
    public static final String TOKEN_HEADER = "Auth";
    public static final String TOKEN_PREFIX = "mbw";

    public static final String SUBJECT = "mbw";

    public static final long EXPIRITION = 1000 * 24 * 60 * 60 * 7;

    public static final String APP_SECRET_KEY = "mbw_secret";

    private static final String ROLE_CLAIMS = "role_";

    /**
     *
     * @param user
     * @return
     */
    public static String generateJsonWebToken(UserVO user) {
        if (user.getId() == null || user.getName() == null || user.getFaceImage() == null) {
            return null;
        }

        Map<String,Object> map = new HashMap<>();
        map.put(ROLE_CLAIMS, "rol");

        return Jwts
                .builder()
                .setSubject(SUBJECT)
                .setClaims(map)
                .claim("id", user.getId())
                .claim("name", user.getName())
                .claim("img", user.getFaceImage())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRITION))
                .signWith(SignatureAlgorithm.HS256, APP_SECRET_KEY).compact();
    }

    /**
     * 生成token
     * @param username
     * @param role
     * @return
     */
    public static String createToken(String username, String role) {
        Map<String,Object> map = new HashMap<>();
        map.put(ROLE_CLAIMS, role);

        return Jwts
                .builder()
                .setSubject(username)
                .setClaims(map)
                .claim("username", username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRITION))
                .signWith(SignatureAlgorithm.HS256, APP_SECRET_KEY).compact();
    }


    /**
     *
     * @param token
     * @return
     */
    public static Claims checkJWT(String token) {
        try {
            return Jwts.parser().setSigningKey(APP_SECRET_KEY).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取用户名
     * @param token
     * @return
     */
    public static String getUsername(String token){
        Claims claims = Jwts.parser().setSigningKey(APP_SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get("username").toString();
    }

    /**
     * 获取用户角色
     * @param token
     * @return
     */
    public static String getUserRole(String token){
        Claims claims = Jwts.parser().setSigningKey(APP_SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get("rol").toString();
    }

    /**
     * 是否过期
     * @param token
     * @return
     */
    public static boolean isExpiration(String token){
        Claims claims = Jwts.parser().setSigningKey(APP_SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getExpiration().before(new Date());
    }
}
