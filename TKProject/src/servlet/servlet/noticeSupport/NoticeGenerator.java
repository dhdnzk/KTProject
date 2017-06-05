package servlet.servlet.noticeSupport;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by bumskim on 2017. 6. 5..
 * class for support notice page
 */
public class NoticeGenerator {
    public NoticeGenerator(HttpServletRequest request,
                           String message,
                           String link,
                           String link_view) {

        request.setAttribute("message", message);
        request.setAttribute("link", link);
        request.setAttribute("link_view", link_view);
    }
}
