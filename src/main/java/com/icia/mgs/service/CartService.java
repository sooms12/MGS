package com.icia.mgs.service;

import com.icia.mgs.dao.CartDAO;
import com.icia.mgs.dao.CartRepository;
import com.icia.mgs.dto.CartDTO;
import com.icia.mgs.dto.CartEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository carepo;
    private  final  CartDAO cadao;



    // 장바구니
//    public ModelAndView ccList(String mId) {
//        mav = new ModelAndView();
//
//        System.out.println("4: " + mId);
//
//        List<CartDTO> ccList = new ArrayList<>();
//
//        if (mId != null) { // mId가 null이 아닌 경우에만 쿼리 실행
//            List<CartEntity> cartEntityList = carepo.findAllBymId(mId);
//            System.out.println("5: " + cartEntityList);
//
//            for (CartEntity entity : cartEntityList) {
//                if (mId.equals(entity.getMId())) {
//                    ccList.add(CartDTO.toDTO(entity));
//                    System.out.println("6: " + entity);
//                }
//            }
//        } else {
//            System.out.println("mId : " + mId);
//        }
//        mav.setViewName("cart/list");
//        mav.addObject("ccList",ccList);
//
//        return mav;
//    }

    // 장바구니 담기
    public List<CartDTO> addToCart(CartDTO cart) {

        System.out.println("2: " + cart);

        // CartDTO의 필드들을 CartEntity로 복사
        CartEntity cartEntity = new CartEntity();
        cartEntity.setMId(cart.getMId());
        cartEntity.setSNum(cart.getSNum());
        cartEntity.setCCount(cart.getCCount());
        carepo.save(cartEntity);
        System.out.println("3 : " + cartEntity);


        List<CartDTO> ccList = new ArrayList<>();
        ccList = cctlist(cart.getMId());


        System.out.println("4 : " + ccList);

        return ccList;
    }


    // 장바구니 담은 목록 리스트
    public List<CartDTO> cctlist(String mId) {
        List<CartDTO> ctlist = new ArrayList<>();
        List<CartEntity> entityList = carepo.findAllBymId(mId);

        System.out.println("mId : " + mId);
        for (CartEntity entity : entityList){

                ctlist.add(CartDTO.toDTO(entity));
        }
        return cadao.ctlist(mId);
    }

    // 장바구니 삭제
    public List<CartDTO> deleteCartItem(CartDTO cart) {
        System.out.println("delect 2: " + cart);

        carepo.deleteById(cart.getCtNum());
        System.out.println("delect 3 : " + cart);


        List<CartDTO> deleteCartItem = new ArrayList<>();
        deleteCartItem = cctlist(cart.getMId());


        System.out.println("delect 4 : " + deleteCartItem);

        return deleteCartItem;
    }


}
