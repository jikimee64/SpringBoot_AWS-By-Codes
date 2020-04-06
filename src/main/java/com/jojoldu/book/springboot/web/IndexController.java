package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.LoginUser;
import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import com.jojoldu.book.springboot.domain.user.User;
import com.jojoldu.book.springboot.service.PostsService;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController { //페이지에 관련된 컨트롤러 담당

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        //1.@LoginUser SessionUser user
        //기존에(User) httpSessioon.getAttribute("user)로 가져오던 세션 정보 값이 개선되었다.
        //이제는 어느 컨트롤러든지 @LoginUser맘ㄴ 사용하면 세션 정보를 가져올 수 있다.

        //Model : 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장
        //여기서는 postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달
        model.addAttribute("posts", postsService.findAllDesc());
        //1.앞서 작성된 CustomOAuth2UserService에서 로그인 성공 시 세션에 SessionUser를 저장하도록 구성
        //즉, 로그인 성공 시 httpSession.getAttribute("user")에서 값을 가져올 수 있다.
        if(user != null) { //세션에 저장된 값이 있을 때만 model에 userName으로 등록
                            //세션에 저장된 값이 없으면 model엔 아무런 값이 없는 상태이니 로그인
                            //버튼이 보이게 된다.
            model.addAttribute("userNames", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";

    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

}
