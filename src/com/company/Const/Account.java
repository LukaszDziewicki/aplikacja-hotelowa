package com.company.Const;

public class Account {
    private static final int ADMIN_ID = 1;
    private static final String ADMIN_PASSWORD = "admin";

    private static final int RECEPTION_ID = 2;
    private static final String RECEPTION_PASSWORD = "reception";

    private static final int KEEPER_ID = 3;
    private static final String KEEPER_PASSWORD = "keeper";


    public static int whoLogged(int userId, String password) {
        if (userId == ADMIN_ID && password.equals(ADMIN_PASSWORD)) {
            return 1;
        } else if (RECEPTION_ID == 2 && password.equals(RECEPTION_PASSWORD)) {
            return 2;
        } else if (KEEPER_ID == 3 && password.equals(KEEPER_PASSWORD)) {
            return 3;
        }
        return 0;
    }
}
