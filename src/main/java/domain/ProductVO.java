package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter

public class ProductVO {
	private int pno;
	private String class1;
	private String name;
	private int like1;
	private int sellmount;
	private int discount;
	private String image;
	private int currentmount;
	private int price;
	private String content;
	private String writer;
}
