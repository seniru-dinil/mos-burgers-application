package edu.icet.mos.dto;


import edu.icet.mos.util.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private Integer id;
    private String name;
    private String description;
    private String imgUrl;
    private Double price;
    private Category category;
}
