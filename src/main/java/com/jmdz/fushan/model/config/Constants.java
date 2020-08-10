package com.jmdz.fushan.model.config;

/**
 * Constants
 *
 * @author LiCongLu
 * @date 2020-05-29 11:54
 */
public class Constants {

    /**
     * utf-8
     */
    public static final String UTF8 = "utf-8";

    /**
     * timezone = GMT+8
     */
    public static final String GMT8 = "GMT+8";


    /**
     * /error
     */
    public static final String ERROR_PATH = "/error";

    /**
     * 失败
     */
    public static final int FAILURE = 0;
    /**
     * 异常
     */
    public static final int ERROR = -2;

    /**
     * 登录失效
     */
    public static final int LOGIN_INVALID = -1;

    /**
     * 成功
     */
    public static final int SUCCESS = 1;

    /**
     * 会话标记
     */
    public static final String SESSION_USER = "session_user";

    /**
     * 会话
     */
    public static final String SESSION = "session";

    /**
     * token
     */
    public static final String AUTHORIZATION = "token";

    /**
     * 批量预定
     */
    public static final String PI_LIANG_YUDING = "批量预定";

    /**
     * 缩略图大小
     */
    public static final int ThumbSize = 160;
    /**
     * 二维码大小
     */
    public static final int QRCodeSize = 500;


    /**
     * 车辆接运表状态常量，FMIS_VehicleManage.CarryState
     * 值是 FMIS_Items 里定义 Code =  YWZL_CDRWZT 的类型
     * 在 Fmis_ItemDetails 表里的详情数据集合
     * 此数据常量不具备通用型
     */
    public final class CarryState {

        /**
         * 尚未出车
         */
        public static final int ShangWeiChuChe = 0;

        /**
         * 任务完成
         */
        public static final int RenWuWanCheng = 1;

        /**
         * 已出车
         */
        public static final int YiChuChe = 2;
    }


    /**
     * 火化状态
     */
    public final class CrematingState {
        /**
         * 预约
         */
        public static final int Order = 0;
        /**
         * 调度
         */
        public static final int Dispatch = 1;

        /**
         * 火化中，即等待火化
         */
        public static final int Begin = 2;

        /**
         * 冷却中，即火化完成
         */
        public static final int Cooling = 3;

        /**
         * 完成，即骨灰冷却完成
         */
        public static final int End = 4;
    }

    /**
     * 守灵告别状态
     */
    public final class MournState {
        /**
         * 默认,预订未入厅
         */
        public static final int Normal = 0;

        /**
         * 入厅
         */
        public static final int Begin = 1;

        /**
         * 出厅
         */
        public static final int End = 2;
    }

    /**
     * 冷藏状态
     */
    public final class ColdStorageState {
        /**
         * 默认
         * 当存在未入藏状态的冷藏信息时，代表着预定
         * 济南目前不存在这种情况
         */
        public static final int Normal = 0;
        /**
         * 入藏
         */
        public static final int In = 1;
        /**
         * 出藏
         */
        public static final int Out = 2;
    }

    /**
     * 服务业务编码
     *
     * @author LiCongLu
     * @date 2020-06-10 14:04
     */
    public final class ServiceConst {
        /**
         * 整容
         */
        public static final int ZhengRong = 10;

        /**
         * 告别
         */
        public static final int GaoBie = 20;

        /**
         * 守灵
         */
        public static final int ShouLing = 25;

        /**
         * 丧葬用品
         */
        public static final int SangZangYongPin = 30;

        /**
         * 车辆租用
         */
        public static final int CheLiangZuYong = 40;

        /**
         * 冷藏棺租用
         */
        public static final int LengCangGuanZuYong = 50;

        /**
         * 其它服务项目
         */
        public static final int QiTaFuWuXiangMu = 60;

        /**
         * 火化
         */
        public static final int HuoHua = 70;

        /**
         * 国际运尸
         */
        public static final int GuoJiYunShi = 80;

        /**
         * 骨灰寄存
         */
        public static final int GuHuiJiCun = 90;
    }


    /**
     * 文件路径
     */
    public final class DirPath {
        /**
         * 身份证图片保存目录
         */
        public static final String imageDir = "/image";

        /**
         * 身份证缩略图片保存目录
         */
        public static final String thumbDir = "/thumb";

        /**
         * PDF文件
         */
        public static final String pdfDir = "/pdf";
    }

    /**
     * 资料编码
     *
     * @author LiCongLu
     * @date 2020-07-09 15:35
     */
    public final class RecordCode {
        /**
         * 默认目录值
         */
        public static final int RecordListCode = 0;

        /**
         * 默认图片编码
         */
        public static final int RecordImageCode = 1000;

        /**
         * 逝者身份证图片
         */
        public static final int DeadBasicCode = 1001;

        /**
         * 告别厅确认单签字
         */
        public static final int FarewellSignCode = 4013;

        /**
         * 告别厅PDF文件
         */
        public static final int FarewellPDFCode = 4014;

        /**
         * PDF文件
         */
        public static final int RecordFilePDFCode = 1;

        /**
         * 业务编号二维码文件
         */
        public static final int OperationNoQRCodeCode = 1011;
    }
}
