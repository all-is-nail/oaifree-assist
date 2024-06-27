package org.example.oaifreeassist.util.struct.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DemoDOMapper {

    DemoDOMapper INSTANCE = Mappers.getMapper(DemoDOMapper.class);

    @Mappings({
            @Mapping(target = "demoName", source = "name"),
            @Mapping(source = "size", target = "demoSize")
    })
    MapStructTest.DemoDTO demoDO2DemoDTO(MapStructTest.DemoDO demoDO);
}
