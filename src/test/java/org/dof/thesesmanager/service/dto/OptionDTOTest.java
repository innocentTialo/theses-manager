package org.dof.thesesmanager.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.dof.thesesmanager.web.rest.TestUtil;

public class OptionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OptionDTO.class);
        OptionDTO optionDTO1 = new OptionDTO();
        optionDTO1.setId(1L);
        OptionDTO optionDTO2 = new OptionDTO();
        assertThat(optionDTO1).isNotEqualTo(optionDTO2);
        optionDTO2.setId(optionDTO1.getId());
        assertThat(optionDTO1).isEqualTo(optionDTO2);
        optionDTO2.setId(2L);
        assertThat(optionDTO1).isNotEqualTo(optionDTO2);
        optionDTO1.setId(null);
        assertThat(optionDTO1).isNotEqualTo(optionDTO2);
    }
}
