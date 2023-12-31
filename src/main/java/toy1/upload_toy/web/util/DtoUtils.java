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
    public static MemberDto memberToMemberDto(Member member) {
        if (member == null) {
            return null;
        }
        return MemberDto.createMemberDto(member.getLoginId(), member.getPassword(),member.getNickName());
    }
    public static Post itemToPost(Item item){
        if (item == null) {
            return null;
        }
        return Post.createPost(item.getItemId(),item.getTitle(),item.getWriter());
    }

    public static Item itemFormToItem(ItemForm itemForm) {

        if (itemForm == null) {
            return null;
        }
        return Item.createItem(itemForm.getTitle(), itemForm.getWriter(), itemForm.getText());
    }


}
