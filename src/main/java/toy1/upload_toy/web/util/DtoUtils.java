package toy1.upload_toy.web.util;

import toy1.upload_toy.domain.Item;
import toy1.upload_toy.domain.Member;
import toy1.upload_toy.web.dto.ItemForm;
import toy1.upload_toy.web.dto.Post;
import toy1.upload_toy.web.dto.MemberDto;

/**
 * 도매인객체를 Dto로 변환해주는 편의 메서드 집함
 */
public class DtoUtils {
    public static Member memberDtoToMember(MemberDto memberDto) {
        if (memberDto == null) {
            return null;
        }
        return Member.builder()
                .loginId(memberDto.getLoginId())
                .password(memberDto.getPassword())
                .nickName(memberDto.getNickName())
                .build();
    }

    public static Post itemToPost(Item item) {
        if (item == null) {
            return null;
        }
        return Post.builder()
                .postId(item.getItemId())
                .title(item.getTitle())
                .writer(item.getWriter())
                .build();
    }

    public static Item itemFormToItem(ItemForm itemForm) {

        if (itemForm == null) {
            return null;
        }
        return Item.builder()
                .title(itemForm.getTitle())
                .writer(itemForm.getWriter())
                .text(itemForm.getText())
                .build();
    }


}
