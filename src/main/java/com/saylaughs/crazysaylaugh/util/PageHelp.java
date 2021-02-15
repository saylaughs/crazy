package com.saylaughs.crazysaylaugh.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageHelp {
    private int currPage;
    private int pageSize;
    private int endPage;
    List<?> list;
}
