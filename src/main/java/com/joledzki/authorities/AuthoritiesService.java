package com.joledzki.authorities;

import com.joledzki.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.swing.plaf.LabelUI;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
public class AuthoritiesService {


    public List<String> getAuthoritiesNameForUser(Collection<? extends GrantedAuthority> authorities){
        List<Authorities> authorities2 = (List<Authorities>) authorities;
        List<String> authoritiesName = new LinkedList<>();

        for(int i=0; i<authorities.size(); i++){
            authoritiesName.add(authorities2.get(i).getName());
        }

        return authoritiesName;
    }


}
