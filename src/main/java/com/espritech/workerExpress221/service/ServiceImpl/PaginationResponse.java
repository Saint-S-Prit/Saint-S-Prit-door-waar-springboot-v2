package com.espritech.workerExpress221.service.ServiceImpl;

import lombok.Data;
import java.util.List;



@Data
public class PaginationResponse<T> {
    private List<T> content;
    private int numberPage;
    private int numberSize;
    private long totalElement;
    private long totalPages;
    private boolean last;
}
