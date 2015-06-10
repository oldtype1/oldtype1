package org.kosta.dashduowork.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TilesViewController {
	/**
	 * @PathVariable : url 정보를 변수로 할당받기 위해 사용
	 * -> 타일즈 뷰 제공시 편리함
	 * 요청받은 매핑정보와 일치하는 매핑url이 있으면
	 * 그 메서드에 매핑해주고 없으면 PathVariable 적용 메서드가 실행된다.
	 * @param viewId
	 * @return
	 */
	@RequestMapping("{viewId}.do")
	public String showView(@PathVariable String viewId){
		System.out.println("TilesViewController가 TilesViewResolver로 응답 : "+viewId);
		return viewId;
	}
}
