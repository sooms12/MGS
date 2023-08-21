package com.icia.mgs.service;

import com.icia.mgs.dao.OrdertRepository;
import com.icia.mgs.dto.OrdertDTO;
import com.icia.mgs.dto.OrdertEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class OrdertService {


    private final OrdertRepository otrepo;
    private ModelAndView mav=new ModelAndView();

    public static Map<String, Object> getResultObject() {
        Map<String, Object> result = new HashMap<>();
        result.put("oDate", "2023-07-19"); // 예시로 임의의 데이터 설정
        result.put("sNum", 12345); // 예시로 임의의 데이터 설정
        result.put("oStatus", "결제완료"); // 예시로 임의의 데이터 설정
        result.put("oPrice", 10000); // 예시로 임의의 데이터 설정
        return result;
    }

    //결제
    public String successPay(String oNum) {
        String result = "NO";
        OrdertEntity entity = otrepo.findById(oNum).orElse(null);
        OrdertDTO ordert = new OrdertDTO();
        ordert.setOPay(1);
        System.out.println(entity + "," + ordert);
        otrepo.save(entity);
        result = "OK";
        return result;
    }


    // DB 입력
    public String insertDB(OrdertDTO order) {
        // 여기서 주문 정보를 Entity로 변환하고 DB에 저장하는 로직을 구현
        // 예를 들어, OrdertEntity entity = OrdertEntity.toEntity(order);와 같이 변환 후 저장
        // 결제 전
        System.out.println("order: " + order);
        OrdertEntity entity = OrdertEntity.toEntity(order);

        // 결제 전
        entity.setOPay(0);
        String result = null;

        // ONUM(예약번호 생성)
        String uuid = UUID.randomUUID().toString().substring(0, 6);
        entity.setONum("RN-" + uuid);
        entity.setONum("RN-" + uuid);
        System.out.println("entity : " + entity);
        try {
            otrepo.save(entity);
            result = entity.getONum();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 예약 내역
    public ModelAndView myOrder() {
        mav = new ModelAndView();

        String Id = "orderId";
        List<OrdertEntity> entityList;

        entityList = otrepo.findBymId(Id);
        System.out.println("entityList :" + entityList);
        mav.addObject("list", entityList);
        mav.setViewName("order/myOrder");
        return mav;
    }

}