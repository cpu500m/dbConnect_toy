package toy1.upload_toy.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import toy1.upload_toy.service.ItemService;
import toy1.upload_toy.web.dto.ItemDto;
import toy1.upload_toy.web.dto.Post;
import toy1.upload_toy.web.dto.ItemForm;
import toy1.upload_toy.web.annotation.Login;
import toy1.upload_toy.web.dto.MemberDto;
import toy1.upload_toy.web.exception.PostAuthorizationException;
import toy1.upload_toy.service.FileService;
import toy1.upload_toy.web.session.SessionConst;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

    private final ItemService itemService;

    private final FileService fileService;

    /**
     * 홈 컨트롤러.
     * 로그인 여부를 판단하여 적절한 페이지로 보냄.
     */
    @GetMapping("/")
    public String goHome(@Login MemberDto memberDto, Model model) {

        log.debug("home");

        // List<ItemDto> 가져옴
        List<Post> posts = itemService.findAll();
        model.addAttribute("posts", posts);

        if (memberDto == null) {
            return "home";
        }

        model.addAttribute("memberDto", memberDto);
        return "userHome";
    }

    /**
     * 게시물 등록.
     * session을 이용하여 작성자를 자동 기입.
     */
    @GetMapping("/addPost")
    public String post(@ModelAttribute ItemForm itemForm,
                       HttpServletRequest request,
                       Model model) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new PostAuthorizationException("비정상적인 접근 (글 작성이 허가되지 않음)");
        }

        // 작성자의 이름을 자동 기입하기 위하여 memberDto를 넘김.
        MemberDto memberDto = (MemberDto)session.getAttribute(SessionConst.LOGIN_IDENTIFIER);
        model.addAttribute("memberDto", memberDto);
        return "addPost";
    }

    @PostMapping("/addPost")
    public String addPost(@Validated @ModelAttribute ItemForm itemForm,
                          BindingResult bindingResult,
                          @ModelAttribute MemberDto memberDto,
                          RedirectAttributes redirectAttributes) throws IOException {

        log.debug("addPost");

        // 작성자 저장
        itemForm.setWriter(memberDto.getNickName());

        /* error가 있다면 재전송 */
        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "addPost";
        }

        /* 저장 */
        Long itemId = itemService.saveItem(itemForm);

        redirectAttributes.addAttribute("itemId", itemId);
        return "redirect:/post/{itemId}";
    }

    /**
     * 게시물 열람
     */
    @GetMapping("/post/{itemId}")
    public String readPost(@PathVariable Long itemId, Model model) {
        log.debug("readPost");

        // item 찾아서 model에 추가해서 넘김
        ItemDto item = itemService.findItem(itemId);
        model.addAttribute("item", item);
        return "post";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileService.getFullPath(filename));
    }

    // attach file 다운로드 메서드인데 게시판 구현 목록에서 attach 파일은 굳이 필요하다 생각 안들어서 (어차피 이미지 올리면되니까)
    // 뺴버렸음
    /*@GetMapping("/attach/{itemId}")
    public ResponseEntity<Resource> download(@PathVariable Long itemId) throws MalformedURLException {
        Item item = itemRepository.findById(itemId);
        String storeName = item.getAttachFile().getStoreName();
        String originName = item.getAttachFile().getOriginName();

        UrlResource resource = new UrlResource("file:" + fileService.getFullPath(storeName));

        String encodedOriginName = UriUtils.encode(originName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedOriginName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }*/
}
