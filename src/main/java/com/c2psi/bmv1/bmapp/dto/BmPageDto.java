package com.c2psi.bmv1.bmapp.dto;

import com.c2psi.bmv1.dto.Pagebm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BmPageDto {
    Integer pageNo = 0;
    Integer pageSize = 10;
    public Pageable getPageable(Pagebm dto) {
        Integer page = Objects.nonNull(dto.getPagenum()) ? dto.getPagenum() : this.pageNo;
        Integer size = Objects.nonNull(dto.getPagesize()) ? dto.getPagesize() : this.pageSize;
        PageRequest pageRequest = PageRequest.of(page, size);

        return pageRequest;
    }
}
