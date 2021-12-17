package by.zvezdina.bodyartsalon.command;

public final class PagePath {

    public static final String ERROR_404_PAGE = "jsp/error404.jsp";
    public static final String ERROR_500_PAGE = "jsp/error500.jsp";

    public static final String LOGIN_PAGE = "jsp/login-page.jsp";
    public static final String PROFILE = "jsp/profile.jsp";
    public static final String GO_TO_PROFILE = "controller?command=go_to_profile";
    public static final String GO_TO_LOGIN = "controller?command=go_to_login";

    private PagePath() {
    }
}
