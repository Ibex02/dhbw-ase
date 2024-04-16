package de.dhbw.ase.wgEinkaufsliste.plugins.rest.test;

import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import de.dhbw.ase.wgEinkaufsliste.plugins.authentication.ContextProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${apiPrefix}/test")
public class TestController {

    private final ContextProvider context;

    @Autowired
    public TestController(ContextProvider context) {
        this.context = context;
    }

    @GetMapping
    public User getUser() throws Exception {
        var user = context.getUser();
        return user;
    }
}
