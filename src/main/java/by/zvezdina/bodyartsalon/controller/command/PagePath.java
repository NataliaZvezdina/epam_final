package by.zvezdina.bodyartsalon.controller.command;

public final class PagePath {

    public static final String ERROR_404_PAGE = "jsp/error404.jsp";
    public static final String ERROR_500_PAGE = "jsp/error500.jsp";

    public static final String INDEX = "index.jsp";
    public static final String HOME = "jsp/home.jsp";
    public static final String SIGN_UP = "jsp/sign-up.jsp";
    public static final String NEW_CLIENT_SIGNED_UP = "jsp/new-client-signed-up.jsp";
    public static final String VERIFICATION = "jsp/verification.jsp";
    public static final String WELCOME = "jsp/welcome.jsp";

    public static final String JEWELRY = "jsp/jewelry.jsp";
    public static final String GO_TO_JEWELRY_DEFINED_PAGE = "controller?command=show_all_jewelry&page=";
    public static final String EDIT_JEWELRY = "jsp/admin/edit-jewelry.jsp";

    public static final String ORDERS = "jsp/orders.jsp";
    public static final String BASKET = "jsp/basket.jsp";
    public static final String ORDER_CREATED_NOTIFICATION = "jsp/client/order-created-notification.jsp";

    public static final String ALL_USERS = "jsp/admin/users.jsp";
    public static final String GO_TO_UPDATED_USERS_LIST = "controller?command=show_all_users";
    public static final String OPEN_USER_PROFILE = "jsp/admin/user-profile.jsp";
    public static final String RETURN_TO_CLIENT_PROFILE = "controller?command=open_profile&userRole=CLIENT&userId=";

    public static final String TOP_UP_BALANCE = "jsp/client/top-up-balance.jsp";
    public static final String CLIENT_PROFILE = "jsp/client/client-profile.jsp";

    private PagePath() {
    }
}
