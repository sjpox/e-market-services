package app.e_market_services.categories.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CategoryRequest {
    private String categoryName;
    private String description;
}
