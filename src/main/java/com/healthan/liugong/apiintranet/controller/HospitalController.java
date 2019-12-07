package com.healthan.liugong.apiintranet.controller;

import com.healthan.liugong.apiintranet.common.BizEnum;
import com.healthan.liugong.apiintranet.common.Results;
import com.healthan.liugong.apiintranet.exception.BusinessException;
import com.healthan.liugong.apiintranet.service.HospitalService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @autor yb
 * @date 2019/10/15 11:11
 **/
@RequestMapping("/api/hospital")
@RestController
public class HospitalController {

    @Resource
    private HospitalService hospitalService;

    /**
     * 获取普通患者信息
     *
     * @param requestMap
     * @return
     */
    @RequestMapping(value = "/patientCheck", method = RequestMethod.POST)
    public Results<Map<String, Object>> patientCheck(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("patientCheck", requestMap));
    }

    /**
     * 获取儿童就诊卡列表
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/getChildCards")
    public Results<Map<String, Object>> getChildCards(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("getChildCards", requestMap));
    }

    /**
     * 建档
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/createPatientInfo")
    public Results<Map<String, Object>> creatPatientInfo(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("createPatientInfo", requestMap));
    }

    /**
     * 密码设置/信息修改
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/changePatientInfo")
    public Results<Map<String, Object>> changePatientInfo(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("changePatientInfo", requestMap));
    }

    /**
     * 门诊充值
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/deposit")
    public Results<Map<String, Object>> deposit(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("deposit", requestMap));
    }

    /**
     * 住院充值
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/inpatientDeposit")
    public Results<Map<String, Object>> inpatientDeposit(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("inpatientDeposit", requestMap));
    }

    /**
     * 就诊卡充值消费记录查询
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/getPrepaymentRecord")
    public Results<Map<String, Object>> getPrepaymentRecord(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("getPrepaymentRecord", requestMap));
    }

    /**
     * 门诊住院账户余额查询
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/depositQuery")
    public Results<Map<String, Object>> depositQuery(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("balanceQuery", requestMap));
    }

    /**
     * 门诊预交金退款
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/outpatientRefund")
    public Results<Map<String, Object>> outpatientRefund(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("outpatientRefund", requestMap));
    }

    /**
     * 住院预交金退款
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/inpatientRefund")
    public Results<Map<String, Object>> inpatientRefund(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("inpatientRefund", requestMap));
    }

    /**
     * 办卡
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/createPatientCard")
    public Results<Map<String, Object>> createPatientCard(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("createPatientCard", requestMap));
    }

    /**
     * 换卡
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/changePatientCard")
    public Results<Map<String, Object>> changePatientCard(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("ChangePatientCard", requestMap));
    }

    /**
     * 就诊卡查询
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/inquirePatientCard")
    public Results<Map<String, Object>> inquirePatientCard(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("inquirePatientCard", requestMap));
    }

    /**
     * 发放病历本
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/saleMedicalHistoryBook")
    public Results<Map<String, Object>> saleMedicalHistoryBook(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("saleMedicalHistoryBook", requestMap));
    }


    /**
     * 办理电子就诊卡
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/createEPC")
    public Results<Map<String, Object>> createEPC(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("createEPC", requestMap));
    }

    /**
     * 绑定/解绑电子就诊卡
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/bindingEPC")
    public Results<Map<String, Object>> bindingEPC(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("bindingEPC", requestMap));
    }

    /**
     * 获取医生排班
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/getDocSchedule")
    public Results<Map<String, Object>> getDocSchedule(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("getDctorSchedule", requestMap));
    }

    /**
     * 获取号源信息
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/getReservationInfo")
    public Results<Map<String, Object>> getReservationInfo(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("getReservationInfo", requestMap));
    }

    /**
     * 确认预约
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/confirmReservation")
    public Results<Map<String, Object>> confirmReservation(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("confirmReservation", requestMap));
    }

    /**
     * 取消预约
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/cancelReservation")
    public Results<Map<String, Object>> cancelReservation(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("cancelReservation", requestMap));
    }

    /**
     * 预约情况查询
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/inquireReservationInfo")
    public Results<Map<String, Object>> inquireReservationInfo(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("inquireReservationInfo", requestMap));
    }

    /**
     * 正常取号
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/takeReservation")
    public Results<Map<String, Object>> takeReservation(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("takeReservation", requestMap));
    }

    /**
     * 获取急诊医生排班
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/getDocScheduleER")
    public Results<Map<String, Object>> getDocScheduleER(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("getDocScheduleER", requestMap));
    }

    /**
     * 急诊取号
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/takeReservationER")
    public Results<Map<String, Object>> takeReservationER(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("takeReservationER", requestMap));
    }

    /**
     * 获取待缴费或已缴费列表
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/getToPayList")
    public Results<Map<String, Object>> getToPayList(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("getToPayList", requestMap));
    }

    /**
     * 获取待缴费或已缴费列表明细信息
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/getToPayDetail")
    public Results<Map<String, Object>> getToPayDetail(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("getToPayDetail", requestMap));
    }

    /**
     * 获取单次门诊费用明细
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/getBillDetailBySN")
    public Results<Map<String, Object>> getBillDetailBySN(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("getBillDetailBySN", requestMap));
    }

    /**
     * 缴费
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/payment")
    public Results<Map<String, Object>> payment(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("payment", requestMap));
    }

    /**
     * 门诊自助结算（打印发票）
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/outpatientSettle")
    public Results<Map<String, Object>> outpatientSettle(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("outpatientSettle", requestMap));
    }

    /**
     * 门诊发票打印回执
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/invoicePrint")
    public Results<Map<String, Object>> invoicePrint(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("invoicePrint", requestMap));
    }

    /**
     * 同步处方审核状态
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/changePrescStatus")
    public Results<Map<String, Object>> changePrescStatus(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("changePrescStatus", requestMap));
    }

    /**
     * 同步线上支付结果
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/synchronousPayment")
    public Results<Map<String, Object>> synchronousPayment(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("synchronousPayment", requestMap));
    }

    /**
     * 查询药品信息
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/inquireDrugsInfo")
    public Results<Map<String, Object>> inquireDrugsInfo(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("inquireDrugsInfo", requestMap));
    }

    /**
     * 查询非药品信息
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/inquireChargeInfo")
    public Results<Map<String, Object>> inquireChargeInfo(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("inquireChargeInfo", requestMap));
    }

    /**
     * 就诊情况查询
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/performanceStatus")
    public Results<Map<String, Object>> performanceStatus(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("performanceStatus", requestMap));
    }

    /**
     * 获取处方主记录
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/getAdviceList")
    public Results<Map<String, Object>> getAdviceList(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("getAdviceList", requestMap));
    }

    /**
     * 获取处方明细
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/getAdviceDetail")
    public Results<Map<String, Object>> getAdviceDetail(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("getAdviceDetail", requestMap));
    }

    /**
     * 同步处方取药地点
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/changePharmacy")
    public Results<Map<String, Object>> changePharmacy(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("changePharmacy", requestMap));
    }

    /**
     * 获取患者入院证信息
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/getInHospitalInfo")
    public Results<Map<String, Object>> getInHospitalInfo(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("getInHospitalInfo", requestMap));
    }

    /**
     * 办理入院
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/hospitalize")
    public Results<Map<String, Object>> hospitalize(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("hospitalize", requestMap));
    }

    /**
     * 取消办理入院
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/leaveHospital")
    public Results<Map<String, Object>> leaveHospital(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("leaveHospital", requestMap));
    }

    /**
     * 查询住院记录
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/getInpatientRecord")
    public Results<Map<String, Object>> inpatientRecord(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("getInpatientRecord", requestMap));
    }

    /**
     * 查询住院日清单
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/inpatientDailyBill")
    public Results<Map<String, Object>> inpatientDailyBill(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("inpatientDailyBill", requestMap));
    }

    /**
     * 获取住院汇总清单
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/inpatientCollectionBill")
    public Results<Map<String, Object>> inpatientCollectionBill(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("inpatientCollectionBill", requestMap));
    }

    /**
     * 获取出院小结
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/getLeaveSummary")
    public Results<Map<String, Object>> getLeaveSummary(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("getLeaveSummary", requestMap));
    }

    /**
     * 获取医院信息
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/getHospInfo")
    public Results<Map<String, Object>> getHospInfo(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("getHospInfo", requestMap));
    }

    /**
     * 获取门诊大科室信息
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/getTopOutDepartment")
    public Results<Map<String, Object>> getTopOutDepartment(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("getTopOutDepartment", requestMap));
    }

    /**
     * 获取门诊科室信息
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/getOutDepartment")
    public Results<Map<String, Object>> getOutDepartment(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("getOutDepartment", requestMap));
    }

    /**
     * 获取医生列表
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/getDocInfo")
    public Results<Map<String, Object>> getDocInfo(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("getDoctorInfo", requestMap));
    }

    /**
     * 获取可流转药品信息
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/inquireOutDrugsInfo")
    public Results<Map<String, Object>> inquireOutDrugsInfo(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("inquireOutDrugsInfo", requestMap));
    }

    /**
     * 获取基础数据字典
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/getBaseDictionary")
    public Results<Map<String, Object>> getBaseDictionary(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("getBaseDictionary", requestMap));
    }

    /**
     * 查询住院患者绑卡数
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/bindingStatistics")
    public Results<Map<String, Object>> bindingStatistics(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("bindingStatistics", requestMap));
    }

    /**
     * 查询患者绑卡记录
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/getWechatBindInfo")
    public Results<Map<String, Object>> getWechatBindInfo(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("getWechatBindInfo", requestMap));
    }

    /**
     * 获取药店信息
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/getDrugStore")
    public Results<Map<String, Object>> getDrugStore(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("getDrugStore", requestMap));
    }

    /**
     * 获取诊疗业务日报表
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/getDailyReport")
    public Results<Map<String, Object>> getDailyReport(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("getDailyReport", requestMap));
    }

    /**
     * 获取业务统计数据
     *
     * @param requestMap
     * @return
     */
    @RequestMapping("/getStatistics")
    public Results<Map<String, Object>> getStatistics(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("getStatistics", requestMap));
    }

    /**
     * 查询检验报告列表
     *
     * @param requestMap
     * @return
     */
    @RequestMapping(value = "/examineSheetList", method = RequestMethod.POST)
    public Results<Map<String, Object>> examineSheetList(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("examineSheetList", requestMap));
    }

    /**
     * 获取检验报告详情
     *
     * @param requestMap
     * @return
     */
    @RequestMapping(value = "/lisReportDetail", method = RequestMethod.POST)
    public Results<Map<String, Object>> lisReportDetail(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getPackageHis("lisReportDetail", requestMap));
    }

    /**
     * 获取门诊病人排队信息，预约挂号详情
     *
     * @param requestMap
     * @return
     */
    @RequestMapping(value = "/getDispPrecontractInfo", method = RequestMethod.POST)
    public Results<Map<String, Object>> getDispPrecontractInfo(@RequestBody Map<String, Object> requestMap) {
        return new Results(hospitalService.getByHdcHis("getDispPrecontractInfo", requestMap));
    }

    /**
     * 获取门诊/住院病人信息，评价医院
     *
     * @param requestMap
     * @return
     */
    @RequestMapping(value = "/getPatientInfoForInternet", method = RequestMethod.POST)
    public Results<Map<String, Object>> getPatientInfoForInternet(@RequestBody Map<String, Object> requestMap) {
        if("1".equals(requestMap.get("operationType"))){
            return new Results(hospitalService.getByOHis("getOutPatientInfoForInternet", requestMap));
        }else if("2".equals(requestMap.get("operationType"))){
            return new Results(hospitalService.getByOHis("getInPatientInfoForInternet", requestMap));
        }else{
            throw new BusinessException(BizEnum.OPERATION_TYPE_ERR);
        }

    }
}
