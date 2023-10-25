package toy1.upload_toy.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;
import toy1.upload_toy.domain.Item;
import toy1.upload_toy.web.dto.ItemForm;
import toy1.upload_toy.domain.Member;
import toy1.upload_toy.domain.Post;
import toy1.upload_toy.web.annotation.Login;
import toy1.upload_toy.web.dto.MemberDto;
import toy1.upload_toy.web.exception.PostAuthorizationException;
import toy1.upload_toy.web.file.UploadFile;
import toy1.upload_toy.repository.ItemRepository;
import toy1.upload_toy.repository.PostRepository;
import toy1.upload_toy.service.FileService;
import toy1.upload_toy.web.session.SessionConst;
import toy1.upload_toy.web.util.DtoUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

    private final ItemRepository itemRepository;
    private final PostRepository postRepository;

    private final FileService fileService;

    /**
     * 홈 컨트롤러.
     * 로그인 여부를 판단하여 적절한 페이지로 보냄.
     */
    @GetMapping("/")
    public String goHome(@Login Member member, Model model) {
        log.debug("home");
        List<Post> posts = postRepository.findAll();

        MemberDto memberDto = DtoUtils.memberToMemberDto(member);
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

        /* 세션저장소로 부터 Member 객체 꺼내 dto 객체로 변환 */
        Member member = (Member)session.getAttribute(SessionConst.LOGIN_IDENTIFIER);
        MemberDto memberDto = DtoUtils.memberToMemberDto(member);
        model.addAttribute("memberDto", memberDto);
        return "addPost";
    }

    @PostMapping("/addPost")
    public String addPost(@Validated @ModelAttribute ItemForm itemForm,
                          BindingResult bindingResult,
                          @ModelAttribute MemberDto memberDto,
                          RedirectAttributes redirectAttributes) throws IOException {
        List<UploadFile> imageFiles = fileService.storeFiles(itemForm.getImageFiles());
        UploadFile attachFile = fileService.storeFile(itemForm.getAttachFile());

        // 작성자 저장
        itemForm.setWriter(memberDto.getNickName());

        log.debug("addPost");

        /* error가 있다면 재전송 */
        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult);
            return "addPost";
        }

        /* 저장 */

        // item
        Item item = Item.createItem(itemForm.getTitle(), itemForm.getWriter(), itemForm.getText()
                , imageFiles, attachFile);
        itemRepository.save(item);

        // post
        Post post = Post.createPost(itemForm.getTitle(), itemForm.getWriter());
        postRepository.save(post);

        redirectAttributes.addAttribute("itemId", item.getItemId());
        return "redirect:/post/{itemId}";
    }

    /**
     * 게시물 열람
     */
    @GetMapping("/post/{itemId}")
    public String readPost(@PathVariable Long itemId, Model model) {
        log.debug("readPost");
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "post";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileService.getFullPath(filename));
    }

    @GetMapping("/attach/{itemId}")
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
    }
}
