package org.kosta.dashduowork.model.vo;

public class ReportVO {
private String word;
private int cnt;
public ReportVO() {
	super();
}
public ReportVO(String word, int cnt) {
	super();
	this.word = word;
	this.cnt = cnt;
}
public String getWord() {
	return word;
}
public void setWord(String word) {
	this.word = word;
}
public int getCnt() {
	return cnt;
}
public void setCnt(int cnt) {
	this.cnt = cnt;
}
@Override
public String toString() {
	return "ReportVO [word=" + word + ", cnt=" + cnt + "]";
}


}
