//package com.hutservice.user.service;
//
//import com.hutservice.user.entity.TravelRoute;
//import com.hutservice.user.entity.User;
//import com.hutservice.user.vo.TravelRouteVo;
//
///**
// * 用户行程服务类
// *
// * @author lid
// * @date 2017.2.16
// */
//public interface TravelRouteService {
//    /**
//     * 根据routeId删除行程
//     *
//     * @param routeId
//     * @author lid
//     * @date 2017.2.22
//     */
//    void deleteTravelRoute(String routeId);
//
//    /**
//     * 根据routeId获取行程信息
//     *
//     * @param routeId
//     * @return TravelRoute
//     * @author lid
//     * @date 2017.2.18
//     */
//    TravelRoute getTravelRouteByRouteId(String routeId);
//
//    /**
//     * 修改travelRoute
//     *
//     * @param travelRoute
//     * @return TravelRoute
//     * @author lid
//     * @date 2017.2.18
//     */
//    TravelRoute updateTravelRoute(TravelRoute travelRoute);
//
//    /**
//     * @return TravelRoute
//     * @author lid
//     * @date 2017.2.20
//     */
//    TravelRoute addTravelRoute(User user, TravelRouteVo vo);
//
//    /**
//     * 根据userid获取行程列表
//     *
//     * @param userId
//     * @return List<TravelRoute>
//     * @author lid
//     * @date 2017.2.18
//     */
//    TravelRoute getTravelRouteByUserId(String userId);
//}
