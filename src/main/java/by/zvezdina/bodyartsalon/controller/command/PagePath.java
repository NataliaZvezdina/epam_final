package by.zvezdina.bodyartsalon.controller.command;

public final class PagePath {

    public static final String ERROR_404_PAGE = "jsp/error/error404.jsp";
    public static final String ERROR_500_PAGE = "jsp/error/error500.jsp";

    public static final String INDEX = "index.jsp";
    public static final String HOME = "jsp/home.jsp";
    public static final String SIGN_UP = "jsp/sign-up.jsp";
    public static final String NEW_CLIENT_SIGNED_UP = "jsp/new-client-signed-up.jsp";
    public static final String VERIFICATION = "jsp/verification.jsp";
    public static final String WELCOME = "jsp/welcome.jsp";

    public static final String JEWELRY = "jsp/content/jewelry.jsp";
    public static final String GO_TO_JEWELRY_DEFINED_PAGE = "controller?command=show_all_jewelry&page=";
    public static final String EDIT_JEWELRY = "jsp/admin/edit-jewelry.jsp";
    public static final String ADD_JEWELRY = "jsp/admin/add-jewelry.jsp";

    public static final String FACILITIES = "jsp/content/facilities.jsp";
    public static final String GO_TO_FACILITY_DEFINED_PAGE = "controller?command=show_all_facilities&page=";
    public static final String EDIT_FACILITY = "jsp/admin/edit-facility.jsp";
    public static final String ADD_FACILITY = "jsp/admin/add-facility.jsp";

    public static final String ORDERS = "jsp/admin/orders.jsp";
    public static final String BASKET = "jsp/basket.jsp";
    public static final String ORDER_CREATED_NOTIFICATION = "jsp/client/order-created-notification.jsp";
    public static final String CLIENT_ORDERS_LIST = "jsp/client/orders-list.jsp";
    public static final String ORDER_CANCELLED = "jsp/common/order-cancelled.jsp";
    public static final String ORDER_RECEIVED = "jsp/common/order-received.jsp";

    public static final String ALL_USERS = "jsp/admin/users.jsp";
    public static final String GO_TO_UPDATED_USERS_LIST = "controller?command=show_all_users";
    public static final String OPEN_USER_PROFILE = "jsp/admin/user-profile.jsp";
    public static final String RETURN_TO_CLIENT_PROFILE = "controller?command=open_profile&userRole=CLIENT&userId=";

    public static final String TOP_UP_BALANCE = "jsp/client/top-up-balance.jsp";
    public static final String BALANCE_RECHARGED = "jsp/client/balance-recharged.jsp";
    public static final String CLIENT_PROFILE = "jsp/client/client-profile.jsp";

    public static final String UPDATE_PASSWORD = "jsp/update-password.jsp";
    public static final String ADMIN_PROFILE = "jsp/admin/admin-profile.jsp";
    public static final String PASSWORD_CHANGED = "jsp/common/password-changed.jsp";
    public static final String UPDATE_PROFILE = "jsp/common/update-profile.jsp";

    public static final String SINGLE_ORDER_DETAILS = "jsp/admin/single-order-details.jsp";
    public static final String PIERCERS = "jsp/content/piercers.jsp";
    public static final String PIERCER_PROFILE = "jsp/piercer/piercer-profile.jsp";
    public static final String ADD_ADMIN = "jsp/admin/add-admin.jsp";
    public static final String ADD_PIERCER = "jsp/admin/add-piercer.jsp";
    public static final String EDIT_PIERCER_WORKING_INFO = "jsp/admin/edit-piercer-info.jsp";


    public static final String MAKE_APPOINTMENT = "jsp/client/make-appointment.jsp";
    public static final String RELEVANT_APPOINTMENTS_BY_PIERCER = "jsp/piercer/relevant-appointments.jsp";
    public static final String SINGLE_APPOINTMENT = "jsp/common/single-appointment.jsp";
    public static final String RELEVANT_APPOINTMENTS_BY_CLIENT = "jsp/client/relevant-appointments.jsp";
    public static final String APPOINTMENT_DELETED = "jsp/common/appointment-deleted.jsp";
    public static final String APPOINTMENT_CREATED = "jsp/client/appointment-created.jsp";

    private PagePath() {
    }
}
