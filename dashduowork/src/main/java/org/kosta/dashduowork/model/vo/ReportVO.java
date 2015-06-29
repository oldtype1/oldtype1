package org.kosta.dashduowork.model.vo;

public class ReportVO {
private String word;
private int cnt;
private String ranking;
public ReportVO() {
	super();
}
public ReportVO(String word, int cnt, String ranking) {
	super();
	this.word = word;
	this.cnt = cnt;
	this.ranking = ranking;
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
public String getRanking() {
	return ranking;
}
public void setRanking(String ranking) {
	this.ranking = ranking;
}
@Override
public String toString() {
	return "ReportVO [word=" + word + ", cnt=" + cnt + ", ranking=" + ranking
			+ "]";
}


}
