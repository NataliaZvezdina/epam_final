package by.zvezdina.bodyartsalon.controller.command;

public enum CommandType {

    CHANGE_LOCALE,
    SIGN_IN,
    LOGOUT,
    SIGN_UP,
    VERIFY,

    SHOW_ALL_JEWELRY,
    ADD_JEWELRY,
    EDIT_JEWELRY,
    DELETE_JEWELRY,
    RESTORE_JEWELRY,

    SHOW_ALL_ORDERS,
    ADD_ITEM_TO_BASKET,
    SHOW_BASKET,
    CREATE_ORDER,
    RECOUNT_ORDER_WHILE_ADDING_ITEM,
    RECOUNT_ORDER_WHILE_REMOVING_ITEM,

    SHOW_ALL_USERS,
    DELETE_USER,
    RESTORE_USER,
    OPEN_PROFILE,
    CHANGE_CLIENT_DISCOUNT,

    MAKE_APPOINTMENT,

    GO_TO_LOGIN,
    LOGIN_USER,
    GO_TO_PROFILE,

    DEFAULT
}
