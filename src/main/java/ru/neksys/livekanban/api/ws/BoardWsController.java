package ru.neksys.livekanban.api.ws;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import ru.neksys.livekanban.security.UserPrincipal;

import java.security.Principal;

@Controller
public class BoardWsController {

    @MessageMapping("/whoami")
    public void whoami(Principal principal) {
        UserPrincipal me = (UserPrincipal) principal;
        // me.userId(), me.displayName() ...
    }
}
