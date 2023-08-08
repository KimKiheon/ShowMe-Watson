package com.watson.business.house.service;

import com.watson.business.exception.HouseErrorCode;
import com.watson.business.exception.HouseException;
import com.watson.business.house.domain.entity.House;
import com.watson.business.house.domain.entity.HouseFile;
import com.watson.business.house.domain.entity.houseContractInfoDetail.MonthlyInfo;
import com.watson.business.house.domain.entity.houseContractInfoDetail.SaleInfo;
import com.watson.business.house.domain.entity.houseContractInfoDetail.YearlyInfo;
import com.watson.business.house.domain.repository.HouseFileRepository;
import com.watson.business.house.domain.repository.HouseRepository;
import com.watson.business.house.dto.houserequest.ContractRequest;
import com.watson.business.house.dto.houserequest.HouseRegistRequest;
import com.watson.business.house.dto.houserequest.HouseUpdateRequest;
import com.watson.business.house.dto.houseresponse.HouseDetailResponse;
import com.watson.business.house.dto.houseresponse.HouseListResponse;
import com.watson.business.region.dto.EmdNameResponse;
import com.watson.business.region.service.RegionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.watson.business.house.dto.item.STATUS.TRADING;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class HouseServiceImp implements HouseService {
    private final HouseRepository houseRepository;
    private final HouseFileRepository houseFileRepository;
    private final HouseImageServiceImp houseImageService;
    private final RegionService regionService;

    public List<HouseListResponse> findAllHouses() {
        List<House> houseEntityList = houseRepository.findAll();
        List<HouseListResponse> allHouseList = new ArrayList<>();
        for (House h : houseEntityList) {
            EmdNameResponse emdNameResponse = regionService.getEmdNameByEmdCode(h.getDongCode());
            HouseListResponse houseListResponse = listEntityToDto(h, emdNameResponse);
//           dongCode

            switch (h.getContractCode()) {
                case 1:    // 월세
                    houseListResponse.setDeposit(h.getMonthlyInfo().getDeposit());
                    houseListResponse.setMaintenance(h.getMonthlyInfo().getMaintenance());
                    houseListResponse.setMonthlyRent(h.getMonthlyInfo().getMonthlyRent());
                    break;

                case 2:    // 전세
                    houseListResponse.setDeposit(h.getYearlyInfo().getDeposit());
                    houseListResponse.setMaintenance(h.getYearlyInfo().getMaintenance());
                    break;

                case 3:
                    houseListResponse.setSalePrice(h.getSaleInfo().getSalePrice());
                    break;// 매매
                default:
                    throw new HouseException(HouseErrorCode.NOT_FOUND_HOUSE_INFO);
            }

            // TODO: access-key 존재시 isWish 관련 기능 구현  : 매물(h.getId()) + wish테이블

            allHouseList.add(houseListResponse);
        }

        return allHouseList;
    }

    public HouseDetailResponse findHouseByHouseId(Long houseId) {
        House house = houseRepository.findHouseById(houseId);
        EmdNameResponse emdNameResponse = regionService.getEmdNameByEmdCode(house.getDongCode());

        if (house == null) {
            throw new HouseException(HouseErrorCode.NOT_FOUND_HOUSE_INFO);
        }

        HouseDetailResponse houseDetailResponse = HouseDetailResponse.builder()
//                .realtor(house.getRealtorId())
                .maintenanceList(house.getMaintenanceList())
                .contractCode(house.getContractCode())
                .totalFloor(house.getTotalFloor())
                .buildingUse(house.getBuildingUse())
                .approvalBuildingDate(house.getApprovalBuildingDate())
                .bathroom(house.getBathroom())
                .room(house.getRoom())
                .content(house.getContent())
                .regDate(house.getRegDate())
                .houseOption(house.getHouseOption())
                .build();

        HouseListResponse houseListResponse = listEntityToDto(house, emdNameResponse);
        houseDetailResponse.setHouseListResponse(houseListResponse);

        switch (house.getContractCode()) {
            case 1:      // 월세
                houseListResponse.setDeposit(house.getMonthlyInfo().getDeposit());
                houseListResponse.setMaintenance(house.getMonthlyInfo().getMaintenance());
                houseDetailResponse.setMaintenanceList(house.getMonthlyInfo().getMaintenanceList());
                houseListResponse.setMonthlyRent(house.getMonthlyInfo().getMonthlyRent());
                break;

            case 2:   // 전세
                houseListResponse.setDeposit(house.getYearlyInfo().getDeposit());
                houseDetailResponse.setMaintenanceList(house.getMonthlyInfo().getMaintenanceList());
                houseListResponse.setMaintenance(house.getYearlyInfo().getMaintenance());
                break;

            case 3:
                houseListResponse.setSalePrice(house.getSaleInfo().getSalePrice());
                break;// 매매
            default:
                throw new HouseException(HouseErrorCode.NOT_FOUND_HOUSE_INFO);
        }

//        // TODO: isWish 로직 필요

        return houseDetailResponse;
    }

    public Long addHouse(List<MultipartFile> file, HouseRegistRequest request, String realtorId) {

        ContractRequest contractRequest = request.getContractInfo();

//        realtorId 받아오기

        House house = House.builder()
                .contractCode(request.getContractCode())
                .dongCode(request.getDongCode())
                .houseCode(request.getHouseCode())
                .squareMeter(request.getSquareMeter())
                .floor(request.getFloor())
                .totalFloor(request.getTotalFloor())
                .address(request.getAddress())
                .title(request.getTitle())
                .content(request.getContent())
                .supplyAreaMeter(request.getSupplyAreaMeter())
                .buildingUse(request.getBuildingUse())
                .approvalBuildingDate(request.getApprovalBuildingDate())
                .bathroom(request.getBathroom())
                .room(request.getRoom())
                .weeklyViewCount(0)
                .status(TRADING)
                .realtorId(realtorId)
                .houseFiles(new ArrayList<>())
                .build();

        // 이미지 저장
        List<String> houseFileList = houseImageService.addFile(file, "house");
        houseFileRepository.save(HouseFile.builder()
                .fileName(houseFileList.get(0))
                .house(house)
                .isDeleted(false)
                .build());
        for (String houseFilePath : houseFileList) {
            house.addHouseFile(new HouseFile(houseFilePath));
        }
        log.debug("{}", houseFileList);
        /**
         * 1: 월세
         * 2: 전세
         * 3: 매매
         */
        switch (house.getContractCode()) {
            case 1:
                MonthlyInfo monthInfo = MonthlyInfo.builder()
                        .deposit(contractRequest.getDeposit())
                        .monthlyRent(contractRequest.getMonthlyRent())
                        .maintenance(contractRequest.getMaintenance())
                        .maintenanceList(contractRequest.getMaintenanceList())
                        .build();
                house.setMonthlyInfo(monthInfo);
                break;
            case 2:
                YearlyInfo yearlyInfo = YearlyInfo.builder()
                        .deposit(contractRequest.getDeposit())
                        .maintenance(contractRequest.getMaintenance())
                        .maintenanceList(contractRequest.getMaintenanceList())
                        .build();
                house.setYearlyInfo(yearlyInfo);
                break;
            case 3:
                SaleInfo saleInfo = SaleInfo.builder()
                        .salePrice(contractRequest.getSalePrice())
                        .build();
                house.setSaleInfo(saleInfo);
                break;
            default:
                throw new HouseException(HouseErrorCode.NOT_FOUND_HOUSE_INFO);
        }

        return houseRepository.save(house).getId();
    }

    @Override
    public Long modifyHouse(Long houseId, List<MultipartFile> file, HouseUpdateRequest houseUpdateRequest, String realtorId) {
        House house = houseRepository.findHouseById(houseId);
        house.editHouse(houseUpdateRequest.getTitle(), houseUpdateRequest.getContent());

//        기존 파일 삭제 : houseId를 기준으로 찾아오기
        List<HouseFile> deleteHouseFileList = houseFileRepository.findHouseFileByHouseId(houseId);
        for(HouseFile d : deleteHouseFileList) {
            houseFileRepository.delete(d);
        }
//        새 파일 추가
        List<String> houseFileList = houseImageService.addFile(file, "house");
        houseFileRepository.save(HouseFile.builder()
                .fileName(houseFileList.get(0))
                .house(house)
                .isDeleted(false)
                .build());
        for (String houseFilePath : houseFileList) {
            house.addHouseFile(new HouseFile(houseFilePath));
        }

        return houseId;
    }

    private HouseListResponse listEntityToDto(House house, EmdNameResponse emdNameResponse) {
        return HouseListResponse.builder()
                .houseId(house.getId())
                .houseCode(house.getHouseCode())
                .squareMeter(house.getSquareMeter())
                .suppleAreaMeter(house.getSupplyAreaMeter())
                .floor(house.getFloor())
                .address(house.getAddress())
                .title(house.getTitle())
                .status(house.getStatus())
//                .fileName(house.getHouseFiles().get(0).getFileName())
//                .maintenance(0)
                .sidoName(emdNameResponse.getSidoName())
                .gunguName(emdNameResponse.getGunguName())
                .dongleeName(emdNameResponse.getDongLeeName())
                .build();
    }
}
