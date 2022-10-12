package flustix.fluxifyed.database.api.v1.authentification;

import flustix.fluxifyed.database.Database;

public class TokenGen {
    public static String generateToken(String userid) {
        StringBuilder token = new StringBuilder();

        for (int i = 0; i < 32; i++)
            token.append(genRandomChar());

        try {
            Database.executeQuery("INSERT INTO tokens (token, userid) VALUES ('" + token + "', '" + userid + "') ON DUPLICATE KEY UPDATE token = '" + token + "'");
            return token.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    private static String genRandomChar() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";
        return chars.charAt((int) (Math.random() * chars.length())) + "";
    }
}
