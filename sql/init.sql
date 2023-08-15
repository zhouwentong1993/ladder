create table meta_http_request_entity
(
    id                         bigint auto_increment comment '主键'
        primary key,
    url                        varchar(256)                          not null comment '请求地址',
    content_type               varchar(64)                           not null comment 'content_type',
    protocol                   varchar(64)                           not null comment '请求协议',
    request_type               varchar(64)                           not null comment '请求类型，POST GET',
    character_encoding         varchar(64) default 'utf-8 '          not null comment '字符编码',
    ref_platform               varchar(64)                           not null comment '所属业务，比如某个配送平台',
    ref_business               varchar(64)                           not null comment '所属业务，比如查询价格',
    create_time                datetime                              null comment '创建时间',
    update_time                timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    ref_class                  varchar(128)                          not null comment '对应的类名',
    response_success_judgement varchar(128)                          not null comment '响应值如何判断成功请求表达式',
    response_message           varchar(128)                          not null comment '响应信息，通常是失败时要提示前端的',
    response_mapping           varchar(1024)                         not null comment '响应映射关系',
    response_class             varchar(128)                          not null comment '响应映射的对象'
)
    comment 'http 请求元数据表' auto_increment = 5;

create table meta_request_entity
(
    id           bigint auto_increment comment '主键'
        primary key,
    field_name   varchar(128) not null comment '字段名称',
    field_type   varchar(128) not null comment '字段类型',
    expression   varchar(128) not null comment '字段执行表达式',
    `desc`       varchar(128) null comment '字段描述字段',
    mapped_type  varchar(128) null comment '映射类型',
    class_name   varchar(128) not null comment '所属类名',
    ref_business varchar(128) not null comment '所属业务，比如获取预估价',
    ref_platform varchar(64)  null comment '所属平台，比如达达',
    create_time  datetime     null comment '创建时间',
    update_time  timestamp    null comment '更新时间
'
)
    auto_increment = 21;

INSERT INTO ladder.meta_http_request_entity (id, url, content_type, protocol, request_type, character_encoding, ref_platform, ref_business, create_time, update_time, ref_class, response_success_judgement, response_message, response_mapping, response_class) VALUES (3, 'https://testopen.365paotui.cn/api/v2/userLogin.do', 'application/x-www-form-urlencoded', 'HTTPS', 'POST', 'utf-8', 'PT356', 'GET_TOKEN', '2023-08-11 15:37:03', '2023-08-11 15:37:06', 'com.wentong.ladder.code.pt365.PT365Token', ' ', ' ', ' ', ' ');
INSERT INTO ladder.meta_http_request_entity (id, url, content_type, protocol, request_type, character_encoding, ref_platform, ref_business, create_time, update_time, ref_class, response_success_judgement, response_message, response_mapping, response_class) VALUES (4, 'https://testopen.365paotui.cn/api/expressCost.do', 'application/x-www-form-urlencoded', 'HTTPS', 'POST', 'utf-8', 'PT356', 'PRE_PRICE', '2023-08-11 15:37:03', '2023-08-15 17:07:59', 'com.wentong.ladder.code.pt365.PT365Amount', 'errorcode==0', 'seq.map("code",errorcode,"message",message)', 'let map = seq.map("deliveryFee",decimal(data.totalCost)-decimal(data.defCouCost),"distance",data.totalKm); if(decimal(data.defCouCost)!=0) {map["discountFee"]=decimal(data.defCouCost);} return map;', ' ');


INSERT INTO ladder.meta_request_entity (id, field_name, field_type, expression, `desc`, mapped_type, class_name, ref_business, ref_platform, create_time, update_time) VALUES (9, 'userOpenId', 'String', 'temporaryModel.storeChannelBean.special_key', '开放平台注册用户ID', 'EXPRESSION', 'PT365Token', 'GET_TOKEN', 'PT365', '2023-08-11 15:27:33', '2023-08-11 15:27:36');
INSERT INTO ladder.meta_request_entity (id, field_name, field_type, expression, `desc`, mapped_type, class_name, ref_business, ref_platform, create_time, update_time) VALUES (10, 'password', 'String', 'temporaryModel.storeChannelBean.secret', '密码', 'EXPRESSION', 'PT365Token', 'GET_TOKEN', 'PT365', '2023-08-11 15:28:33', '2023-08-11 15:28:37');
INSERT INTO ladder.meta_request_entity (id, field_name, field_type, expression, `desc`, mapped_type, class_name, ref_business, ref_platform, create_time, update_time) VALUES (11, 'serviceKey', 'String', 'temporaryModel.storeChannelBean.appKey', '服务密钥（去首页->现在开始->开发服务中查看）', 'EXPRESSION', 'PT365Token', 'GET_TOKEN', 'PT365', '2023-08-11 15:28:33', '2023-08-11 15:28:37');
INSERT INTO ladder.meta_request_entity (id, field_name, field_type, expression, `desc`, mapped_type, class_name, ref_business, ref_platform, create_time, update_time) VALUES (12, 'user365Id', 'String', 'temporaryModel.storeChannelBean.special_key', '扣款的365账号(该账户需要第三方平台验证通过)(需要和下单接口的user365Id参数值一致，不传使用三方平台主账号进行计算运费)', 'EXPRESSION', 'PT365Amount', 'PRE_PRICE', 'PT365', '2023-08-11 15:28:33', '2023-08-11 15:28:37');
INSERT INTO ladder.meta_request_entity (id, field_name, field_type, expression, `desc`, mapped_type, class_name, ref_business, ref_platform, create_time, update_time) VALUES (13, 'senderAddressLon', 'String', 'temporaryModel.storeChannelBean.transportLongitude', '发货地坐标经度', 'EXPRESSION', 'PT365Amount', 'PRE_PRICE', 'PT365', '2023-08-11 15:28:33', '2023-08-11 15:28:37');
INSERT INTO ladder.meta_request_entity (id, field_name, field_type, expression, `desc`, mapped_type, class_name, ref_business, ref_platform, create_time, update_time) VALUES (14, 'senderAddressLat', 'String', 'temporaryModel.storeChannelBean.transportLatitude', '发货地坐标纬度', 'EXPRESSION', 'PT365Amount', 'PRE_PRICE', 'PT365', '2023-08-11 15:28:33', '2023-08-11 15:28:37');
INSERT INTO ladder.meta_request_entity (id, field_name, field_type, expression, `desc`, mapped_type, class_name, ref_business, ref_platform, create_time, update_time) VALUES (15, 'receiverAddressLon', 'String', 'str(addOrderParam.receiverLng)', '收货地坐标经度', 'EXPRESSION', 'PT365Amount', 'PRE_PRICE', 'PT365', '2023-08-11 15:28:33', '2023-08-11 15:28:37');
INSERT INTO ladder.meta_request_entity (id, field_name, field_type, expression, `desc`, mapped_type, class_name, ref_business, ref_platform, create_time, update_time) VALUES (16, 'receiverAddressLat', 'String', 'str(addOrderParam.receiverLat)', '收货地坐标纬度', 'EXPRESSION', 'PT365Amount', 'PRE_PRICE', 'PT365', '2023-08-11 15:28:33', '2023-08-11 15:28:37');
INSERT INTO ladder.meta_request_entity (id, field_name, field_type, expression, `desc`, mapped_type, class_name, ref_business, ref_platform, create_time, update_time) VALUES (17, 'weight', 'String', 'str(addOrderParam.cargoWeight/1000)', '重量单位公斤', 'EXPRESSION', 'PT365Amount', 'PRE_PRICE', 'PT365', '2023-08-11 15:28:33', '2023-08-11 15:28:37');
INSERT INTO ladder.meta_request_entity (id, field_name, field_type, expression, `desc`, mapped_type, class_name, ref_business, ref_platform, create_time, update_time) VALUES (18, 'transportation', 'String', 'str(1600)', '配送工具 有效值：1600（其他） 1601（汽车）', 'EXPRESSION', 'PT365Amount', 'PRE_PRICE', 'PT365', '2023-08-11 15:28:33', '2023-08-11 15:28:37');
INSERT INTO ladder.meta_request_entity (id, field_name, field_type, expression, `desc`, mapped_type, class_name, ref_business, ref_platform, create_time, update_time) VALUES (20, 'token', 'String', 'getTokenLadderFunction()', '访问凭证', 'REF_JAVA_CODE', 'PT365Amount', 'PRE_PRICE', 'PT365', '2023-08-11 15:28:33', '2023-08-11 15:28:37');

