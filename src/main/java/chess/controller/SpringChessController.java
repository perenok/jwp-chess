package chess.controller;

import chess.dto.*;
import chess.service.SpringChessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class SpringChessController {

    private SpringChessService springChessService;

    public SpringChessController(SpringChessService springChessService) {
        this.springChessService = springChessService;
    }

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/rooms")
    @ResponseBody
    public List<String> roomNames() {
        return springChessService.rooms();
    }

    @PostMapping("/rooms")
    @ResponseBody
    public ResponseEntity<String> createRoom(@RequestBody RoomNameDto roomName) {
        springChessService.createRoom(roomName.getRoomName());
        return ResponseEntity.status(HttpStatus.CREATED).body("방 생성 성공!");
    }

    @GetMapping("/rooms/{roomName}")
    public String enterRoom(@PathVariable String roomName, Model model) {
        model.addAttribute("roomName", roomName);
        return "chess";
    }

    @PutMapping(value = "/rooms/{roomName}/move")
    @ResponseBody
    public ResponseEntity<ResponseDto> move(@RequestBody PositionDto positionDto, @PathVariable String roomName) {
        return ResponseEntity.ok().body(springChessService.move(positionDto, roomName));
    }

    @PutMapping(value = "/rooms/{roomName}/restart")
    @ResponseBody
    public void restart(@PathVariable String roomName) {
        springChessService.restartRoom(roomName);
    }

    @GetMapping("/rooms/{roomName}/board")
    @ResponseBody
    public Map<String, String> currentBoard(@PathVariable String roomName) {
        return springChessService.currentBoardByRoomName(roomName);
    }

    @GetMapping(value = "/rooms/{roomName}/turn")
    @ResponseBody
    public TurnDto currentTurn(@PathVariable String roomName) {
        return new TurnDto(springChessService.turnName(roomName));
    }

    @GetMapping(value = "/rooms/{roomName}/score")
    @ResponseBody
    public ScoreDto score(@PathVariable String roomName) {
        return springChessService.score(roomName);
    }

    @DeleteMapping("/rooms/{roomName}")
    @ResponseBody
    public void deleteRoom(@PathVariable String roomName) {
        springChessService.deleteRoom(roomName);
    }
}
