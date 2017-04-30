package com.hutservicewx.util;

import com.alibaba.fastjson.JSON;
import com.hutservicewx.config.WeixinConfig;
import com.hutservicewx.exception.WeixinException;
import com.hutservicewx.msg.ButtonData;
import com.hutservicewx.msg.MenuData;

/**
 * 微信菜单创建
 *
 * @author lid
 * @date 2017年2月23日
 */
public class MenuCreate {
    public static void main(String[] args) {
        // 创建按钮
        ButtonData btn = new ButtonData();

        MenuData menu1 = new MenuData(MenuData.TYPE_CLICK, "校园资讯", "");
        MenuData menu2 = new MenuData(MenuData.TYPE_CLICK, "校园生活", "");
        MenuData menu3 = new MenuData(MenuData.TYPE_CLICK, "校园学习", "");

        MenuData subMenu11 = new MenuData(MenuData.TYPE_VIEW, "校园新闻", WeixinConfig.HOST + "/hnairlines/campusInformation/newsTable.html");
        MenuData subMenu12 = new MenuData(MenuData.TYPE_VIEW, "最新通知", "http://www.vzhushou.com/notices.html");
        MenuData subMenu13 = new MenuData(MenuData.TYPE_VIEW, "社团活动", WeixinConfig.HOST + "/hnairlines/campusInformation/activeTable.html");

        MenuData subMenu21 = new MenuData(MenuData.TYPE_VIEW, "快递查询", WeixinConfig.HOST + "/hnairlines/campusLife/courierTable.html");
        MenuData subMenu22 = new MenuData(MenuData.TYPE_VIEW, "失误招领", WeixinConfig.HOST + "/hnairlines/campusLife/lostThing.html");
        MenuData subMenu23 = new MenuData(MenuData.TYPE_VIEW, "一卡通查询", WeixinConfig.HOST + "/hnairlines/campusLife/oneCardTable.html");

        MenuData subMenu31 = new MenuData(MenuData.TYPE_VIEW, "课表查询", WeixinConfig.HOST + "/hnairlines/campusStudy/courseTable.html");
        MenuData subMenu32 = new MenuData(MenuData.TYPE_VIEW, "成绩查询", WeixinConfig.HOST + "/hnairlines/campusStudy/scoreTable.html");
        MenuData subMenu33 = new MenuData(MenuData.TYPE_VIEW, "图书借阅查询", WeixinConfig.HOST + "/hnairlines/campusStudy/bookTable.html");
        MenuData subMenu34 = new MenuData(MenuData.TYPE_VIEW, "实习就业查询", WeixinConfig.HOST + "/hnairlines/campusStudy/employmentTable.html");

        try {
            // 菜单之间的关系
            btn.addMenu(menu1);
            btn.addMenu(menu2);
            btn.addMenu(menu3);

            //menu1 子菜单
            menu1.addSubMenu(subMenu11);
            menu1.addSubMenu(subMenu12);
            menu1.addSubMenu(subMenu13);

            //menu2 子菜单
            menu2.addSubMenu(subMenu21);
            menu2.addSubMenu(subMenu22);
            menu2.addSubMenu(subMenu23);

            //menu3 子菜单
            menu3.addSubMenu(subMenu31);
            menu3.addSubMenu(subMenu32);
            menu3.addSubMenu(subMenu33);
            menu3.addSubMenu(subMenu34);

            String menustr = JSON.toJSONString(btn);

            MenuUtil.create(menustr);

        } catch (WeixinException e) {
            e.printStackTrace();
        }
    }
}
