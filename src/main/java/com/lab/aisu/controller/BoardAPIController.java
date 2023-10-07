package com.lab.aisu.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lab.aisu.dto.BoardVO;
import com.lab.aisu.dto.Criteria;
import com.lab.aisu.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/board/api/*")
@AllArgsConstructor
public class BoardAPIController {

	// @AllArgsConstructor로 인해 객체를 생성할 때 자동주입.
	// 만일, 생성자를 만들지 않을 경우에는 @Setter(onMethod_ = {@Autowired})를 이용해서 처리해야 함.
	private BoardService service;

	@RequestMapping(value="/posts", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BoardVO>> list(Criteria cri) {
		List<BoardVO> list = service.getList(cri);
		
		ResponseEntity<List<BoardVO>> result = null;
		
		if(list.isEmpty()) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(list);
		} else {
			result = ResponseEntity.status(HttpStatus.OK).body(list);
		}
		
		return result;
	}
/*	
 * 	과연, 데이터를 반환하지 않는 API의 경우... APIController에 굳이 만들어줘야 하는가? -> 시영 질문
 * 
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public void register() {
		
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(BoardVO board, RedirectAttributes rttr) {

		service.register(board);
		rttr.addFlashAttribute("result", board.getBno());
		
		// 'redirect:' 접두어를 사용하면 SpringMVC가 내부적으로 response.sendRedirect()를 처리해준다.
		return "redirect:/board/list";
	}
*/
	@RequestMapping(value="/post/{asdf}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	@RequestMapping(value="/post/{asdf}", method=RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public BoardVO get(@PathVariable("asdf") long bno, Criteria cri) {
//	public ResponseEntity<BoardVO> get(@PathVariable("asdf") long bno, Criteria cri) {
//	public String get(@PathVariable("asdf") long bno, Criteria cri) {
		
//		ResponseEntity<BoardVO> result = null;
//		result = ResponseEntity.status(HttpStatus.OK).body(service.get(bno));
//		return result;
		
		return service.get(bno);
//		return "{'name' : 'kim', 'age' : 10}";
	}

	@RequestMapping(value="/post", method=RequestMethod.PUT)
	public String modify(BoardVO board, Criteria cri, RedirectAttributes rttr) {
//	public String modify(@RequestParam("bno") long bno, 
//			@RequestParam("title") String title, 
//			@RequestParam("content") String content, 
//			@RequestParam("writer") String writer, 
//			RedirectAttributes rttr) {
//		ㄴ> 근데 이렇게 보내면 service.modify(bno, title, content, writer) 이렇게 보내나?
//		    이러면 결국 사용되는 파라미터는 명확하지만 모든 절차마다 메소드의 매개변수를 4개씩 넣어줘야 하는데..
//		    만약 이게 너무 오바고 배보다 배꼽이 크다면? 
//		    service 단에서 VO 객체를 생성하고 BoardVO board = new BoardVO();
//		    setter로 값을 쫙 할당해 준 뒤 (= board.setBno(bno); board.setTitle(title); 등등)
//		    그 다음 메소드부터는 매개변수로 board 객체 1개를 넣어 진행하는 걸까?
		
		if (service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
//			rttr.addFlashAttribute("pageNum", cri.getPageNum());
//			rttr.addFlashAttribute("amount", cri.getAmount());
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		return "redirect:/post";
		// redirect는 지정된 주소로 새로운 HTTP GET 요청을 보내는 것. (= Request객체나 Model객체에 데이터를 담아도 전달되지 않는다는 의미.)
	}

	@RequestMapping(value="/post", method=RequestMethod.DELETE)
	public String remove(@RequestParam("bno") long bno, Criteria cri, RedirectAttributes rttr) {

		if (service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		return "redirect:/post";
	}
}