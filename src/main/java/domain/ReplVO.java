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
public class ReplVO {
	private int rno ;
	private int pno ;
	private String id ;
	private String replcontent ;
	private String replStar ;
	private Date replyDate;
}
