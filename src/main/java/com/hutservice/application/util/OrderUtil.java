//package com.hutservice.application.util;
//
//import com.railwayservice.application.config.AppConfig;
//import com.railwayservice.order.entity.MainOrder;
//import com.railwayservice.order.entity.SubOrder;
//import com.railwayservice.order.service.OrderStatic;
//import com.railwayservice.user.entity.User;
//
//import java.math.BigDecimal;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
///**
// * 订单工具类， 包括生成订单号等工具
// *
// * @author lid
// * @date 2017.2.13
// */
//public class OrderUtil {
//    /**
//     * 根据当前系统时间来生成订单号
//     *
//     * @author lid
//     * @date 2017.2.13
//     */
//    public static String GenerateOrderNo() {
//        SimpleDateFormat sdf = new SimpleDateFormat(AppConfig.DATE_TIME_FORMAT_DETAIL);
//        return sdf.format(new Date());
//    }
//
//    /**
//     * 根据子单列表计算主单总价格
//     */
//    public static BigDecimal calculateTotalPrice(List<SubOrder> listSubOrder) {
//        BigDecimal totalPrice = new BigDecimal(0);
//        if (0 == listSubOrder.size()) {
//            return totalPrice;
//        }
//
//        for (int index = 0; index < listSubOrder.size(); index++) {
//            if (null != listSubOrder.get(index).getTotalPrice()) {
//                totalPrice = totalPrice.add(listSubOrder.get(index).getTotalPrice());
//            }
//        }
//        return totalPrice;
//    }
//
//    /**
//     * 添加订单时设置主订单的初始状态。
//     * 其余未设置的信息目前计划由前端构造对象之后传入
//     *
//     * @param User user, MainOrder mainOrder, List<SubOrder> listSubOrder
//     * @return MainOrder
//     * @author lid
//     * @date 2017.2.13
//     */
//    public static MainOrder SetMainOrderInitialData(User user, MainOrder mainOrder, List<SubOrder> listSubOrder) {
//        //设置订单号
//        mainOrder.setOrderNo(OrderUtil.GenerateOrderNo());
//
//        //设置购买人
//        mainOrder.setUserId(user.getUserId());
//
//        //设置订单状态   初始状态为未完成
//        mainOrder.setOrderStatus(OrderStatic.MAINORDER_STATUS_WAIT_ACCEPT);
//
//        //设置支付状态  初始状态为未支付
//        mainOrder.setPayStatus(OrderStatic.PAY_STATUS_UNPAYED);
//
//        //设置订单总价格
//        mainOrder.setOrderTotalPrice(OrderUtil.calculateTotalPrice(listSubOrder));
//
//        return mainOrder;
//    }
//
//    /**
//     * TODO
//     * 添加订单时设置子订单的初始状态  (待完善)
//     *
//     * @param user
//     * @param mainOrder
//     * @param subOrder
//     * @return SubOrder
//     * @author lid
//     * @data 2017.2.14
//     */
//    public static SubOrder SetSubOrderInitialData(User user, MainOrder mainOrder, SubOrder subOrder) {
//
//        //设置主订单id
//        subOrder.setMainOrderId(mainOrder.getOrderId());
//
//        return subOrder;
//    }
//
//}
