package domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
	private int mno;
	private String name;
	private String id;
	private String pwd;
	private String addr;
	private int buymount;
	private String email;
	private int tell;
	private int point;
	private String basket;
	private Date joinDate;
	private MemberGrade grade;
	
	public enum MemberGrade{
		ADMIN
	}
}

