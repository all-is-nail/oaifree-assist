package org.example.oaifreeassist.util.struct.mapper;

import lombok.Data;
import org.junit.jupiter.api.Test;

public class MapStructTest {

    @Test
    public void testMapStruct() {
        DemoDO demoDO = new DemoDO();
        demoDO.setName("demo");
        demoDO.setSize(100);

        DemoDTO demoDTO = DemoDOMapper.INSTANCE.demoDO2DemoDTO(demoDO);
        System.out.println("demoDTO:" + demoDTO);
    }

    @Data
    static class DemoDO {
        private String name;
        private Integer size;
    }

    @Data
    static class DemoDTO {
        private String demoName;
        private String demoSize;// 类型与 source 中不一致
    }
}
