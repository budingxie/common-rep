package com.py.web.traff;

import com.py.rpc.bo.UserBO;
import com.py.web.bo.UserReqBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


/**
 * description：所有对象copy
 *
 * @author pengyou@xiaomi.com
 * @version 1.0.0
 * @date 2020/12/1
 */
@Mapper
public interface AllMapper {

    AllMapper INSTANCE = Mappers.getMapper(AllMapper.class);

    @Mapping(target = "id", source = "id")
    UserBO userReqBO2UserBO(UserReqBO userReqBO);
}