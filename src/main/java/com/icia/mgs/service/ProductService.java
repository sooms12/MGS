package com.icia.mgs.service;

import com.icia.mgs.dao.*;
import com.icia.mgs.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    private ModelAndView mav;
    private final ProductRepository prepo;
    private final SaleRepository srepo;
    private final CompanyRepository crepo;
    private final ProductDAO pdao;
    private Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/product");


    // 상품 등록 페이지 이동
    public ModelAndView pRegiForm(int cNum) {
        mav = new ModelAndView();
        Optional<CompanyEntity> entity = crepo.findById(cNum);

        CompanyDTO company = new CompanyDTO();
        company = CompanyDTO.toDTO(entity.get());

        mav.setViewName("product/register");
        mav.addObject("company", company);

        return mav;
    }

    // 상품 등록
    public ModelAndView pRegister(ProductDTO product) {
        mav = new ModelAndView();

        for (int i =0; i<product.getPmPic().length; i++){
            if (!product.getPmPic()[i].isEmpty()){
                System.out.println(i + " : " + product.getPmPic()[i].getOriginalFilename());

                Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/product");

                String uuid = UUID.randomUUID().toString().substring(0, 8);

                String originalFileName = product.getPmPic()[i].getOriginalFilename();

                String pcPicName = uuid + "_" + originalFileName;


                switch (i) {
                    case 0:
                        product.setPmPicName(pcPicName);
                        break;
                    case 1:
                        product.setPiPicName1(pcPicName);
                        break;
                    case 2:
                        product.setPiPicName2(pcPicName);
                        break;
                    case 3:
                        product.setPiPicName3(pcPicName);
                        break;
                }

                System.out.println("pmPicName : " + product.getPmPicName());
                System.out.println("piPicName1 : " + product.getPiPicName1());
                System.out.println("piPicName2 : " + product.getPiPicName2());
                System.out.println("piPicName3 : " + product.getPiPicName3());


                String savePath = path + "/" + pcPicName;

                try {
                    product.getPmPic()[i].transferTo(new File(savePath));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }

        ProductEntity entity = ProductEntity.toEntity(product);

        prepo.save(entity);
        mav.setViewName("redirect:/pList");

        return mav;
    }

    // 상품 목록(페이징)
    public List<ProductDTO> pPagingList() {
        List<ProductDTO> productList = new ArrayList<>();

        List<ProductEntity> entityList = prepo.findAll();

        for (ProductEntity entity : entityList) {
            productList.add(ProductDTO.toDTO(entity));
        }

        return pdao.productList();
    }

    // 상품 검색
    public List<ProductDTO> pSearchList(SearchDTO search) {
        return pdao.pSearchList(search);
    }

    // 상품 정보 상세보기
    public ModelAndView pView(int pNum) {
        mav = new ModelAndView();

        ProductDTO product = pdao.pView(pNum);

        mav.addObject("view",product);
        mav.setViewName("product/view");

        return mav;
    }

    // 상품 수정 페이지 이동
    public ModelAndView pModiForm(int pNum) {
        mav = new ModelAndView();

        ProductDTO product = (ProductDTO)pView(pNum).getModel().get("view");

        List<CompanyEntity> entityList = crepo.findAll();


        mav.addObject("company" , entityList);
        mav.setViewName("product/modify");
        mav.addObject("modify", product);

        return mav;
    }

    // 상품 수정
    public ModelAndView pModify(ProductDTO product) {
        mav = new ModelAndView();

        if (!product.getPmPic()[0].isEmpty()) {
            String delPath = path + "/" + product.getPmPicName();

            File delFile = new File(delPath);

            if (delFile.exists()){

                delFile.delete();
            }
        }

        if (!product.getPmPic()[1].isEmpty()) {
            String delPath = path + "/" + product.getPiPicName1();

            File delFile = new File(delPath);

            if (delFile.exists()){
                delFile.delete();
            }
        }

        if (!product.getPmPic()[2].isEmpty()) {
            String delPath = path + "/" + product.getPiPicName2();

            File delFile = new File(delPath);

            if (delFile.exists()){
                delFile.delete();
            }
        }

        if (!product.getPmPic()[3].isEmpty()) {
            String delPath = path + "/" + product.getPiPicName3();

            File delFile = new File(delPath);

            if (delFile.exists()){
                delFile.delete();
            }
        }

        for (int i =0; i<product.getPmPic().length; i++){
            if (!product.getPmPic()[i].isEmpty()){
                System.out.println(i + " : " + product.getPmPic()[i].getOriginalFilename());

                Path path = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/product");

                String uuid = UUID.randomUUID().toString().substring(0, 8);

                String originalFileName = product.getPmPic()[i].getOriginalFilename();

                String pcPicName = uuid + "_" + originalFileName;


                switch (i) {
                    case 0:
                        product.setPmPicName(pcPicName);
                        break;
                    case 1:
                        product.setPiPicName1(pcPicName);
                        break;
                    case 2:
                        product.setPiPicName2(pcPicName);
                        break;
                    case 3:
                        product.setPiPicName3(pcPicName);
                        break;
                }

                System.out.println("pmPicName : " + product.getPmPicName());
                System.out.println("piPicName1 : " + product.getPiPicName1());
                System.out.println("piPicName2 : " + product.getPiPicName2());
                System.out.println("piPicName3 : " + product.getPiPicName3());


                String savePath = path + "/" + pcPicName;

                try {
                    product.getPmPic()[i].transferTo(new File(savePath));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }

        // DTO → Entity로 변형
        ProductEntity entity = ProductEntity.toEntity(product);

        // 수정
        prepo.save(entity);

        mav.setViewName("redirect:/pView/" + product.getPNum());
        return mav;
    }

    // 상품 삭제
    public ModelAndView pDelete(int pNum) {
        mav = new ModelAndView();
        ProductDTO product = (ProductDTO) pView(pNum).getModel().get("view");

        if (product.getPmPicName()!=null) {
            String delPath = path + "/" + product.getPmPicName();
            File delFile = new File(delPath);

            if (delFile.exists()) {
                delFile.delete();
            }
        }

        if (product.getPiPicName1()!=null) {
            String delPath = path + "/" + product.getPiPicName1();
            File delFile = new File(delPath);

            if (delFile.exists()) {
                delFile.delete();
            }
        }

        if (product.getPiPicName2()!=null) {
            String delPath = path + "/" + product.getPiPicName2();
            File delFile = new File(delPath);

            if (delFile.exists()) {
                delFile.delete();
            }
        }

        if (product.getPiPicName3()!=null) {
            String delPath = path + "/" + product.getPiPicName3();
            File delFile = new File(delPath);

            if (delFile.exists()) {
                delFile.delete();
            }
        }

        // 먼저 사진들을 삭제한 후 데이터베이스 레코드 삭제를 처리합니다.
        prepo.deleteById(pNum);

        mav.setViewName("redirect:/pList");
        return mav;
    }




}
