package com.py.rpc.traff;

import com.github.pagehelper.PageInfo;
import com.py.rpc.dto.PageableDTO;
import com.py.rpc.dto.UserDTO;
import com.py.rpc.po.UserPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

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

    /**
     * UserPO --> UserDTO
     *
     * @param userPO
     * @return
     */
    @Mapping(target = "id", source = "id")
    UserDTO userPO2DTO(UserPO userPO);

    /**
     * UserDTO --> UserPO
     *
     * @param userDTO
     * @return
     */
    @Mapping(target = "id", source = "id")
    UserPO userDTO2PO(UserDTO userDTO);

    /**
     * PageInfo --> PageableDTO
     * @param pageInfo
     * @return
     */
    @Mapping(target = "data",
            expression = "java( pageInfo.getList().stream().map(ele -> com.py.rpc.traff.AllMapper.INSTANCE.userPO2DTO(ele)).collect( java.util.stream.Collectors.toList() ) )" )
    @Mapping(target = "pageNum", source = "pageNum")
    @Mapping(target = "pageSize", source = "pageSize")
    @Mapping(target = "total", source = "total")
    PageableDTO<List<UserDTO>> pageInfo2Pageable(PageInfo<UserPO> pageInfo);
}